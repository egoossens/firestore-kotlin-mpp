package com.tastyelectrons.firestore

import com.google.cloud.firestore.WriteBatch as JvmWriteBatch

actual class WriteBatch(private val writeBatch: JvmWriteBatch) {
    actual fun set(documentReference: DocumentReference, data: Any) = WriteBatch(writeBatch.set(documentReference.documentReference, data))

    actual suspend fun commit() {
        writeBatch.commit().get()
        Unit
    }
}