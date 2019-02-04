package com.tastyelectrons.firestore

import com.google.cloud.firestore.DocumentReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.withContext

actual class DocumentReference(val documentReference: DocumentReference) {
    actual fun collection(collectionPath: String) = CollectionReference(documentReference.collection(collectionPath))

    actual fun observe(): ReceiveChannel<DocumentSnapshot> = CoroutineScope(Dispatchers.Default).produce {
        documentReference.addSnapshotListener { documentSnapshot, firestoreException ->
            if (firestoreException != null) {
                close(firestoreException)
            } else {
                documentSnapshot?.let {
                    async { send(DocumentSnapshot(it)) }
                }
            }

        }
    }

    actual suspend fun get(): DocumentSnapshot = withContext(Dispatchers.Default) {
        DocumentSnapshot(documentReference.get().get())
    }

    actual fun getId(): String = documentReference.id

    actual suspend fun delete() = withContext(Dispatchers.Default) {
        documentReference.delete().get()
        Unit
    }

    actual suspend fun set(obj: Any) = withContext(Dispatchers.Default) {
        documentReference.set(obj).get()
        Unit
    }

    actual suspend fun update(vararg fields: Pair<String, Any>) = withContext(Dispatchers.Default) {
        val keyValues = fields.drop(1).flatMap { listOf(it.first, it.second) }.toTypedArray()
        documentReference.update(fields[0].first, fields[0].second, *keyValues)
        Unit
    }
}