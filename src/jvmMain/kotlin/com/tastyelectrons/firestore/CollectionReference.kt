package com.tastyelectrons.firestore

import com.google.api.core.ApiFutureCallback
import com.google.api.core.ApiFutures
import com.google.cloud.firestore.CollectionReference
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class CollectionReference(private val collectionReference: CollectionReference): Query(collectionReference) {
    actual suspend fun add(obj: Any) = suspendCoroutine<DocumentReference?> { continuation ->
        ApiFutures.addCallback(collectionReference.add(obj), object : ApiFutureCallback<com.google.cloud.firestore.DocumentReference?> {
            override fun onSuccess(result: com.google.cloud.firestore.DocumentReference?) = continuation.resume(result?.let { DocumentReference(it) })
            override fun onFailure(t: Throwable?) = t?.let { continuation.resumeWithException(it) } ?: Unit
        })
    }
    actual fun document(): DocumentReference = DocumentReference(collectionReference.document())
    actual fun document(childPath: String): DocumentReference = DocumentReference(collectionReference.document(childPath))
    actual fun getId(): String = collectionReference.id
    actual fun getParent(): DocumentReference? = collectionReference.parent?.let { DocumentReference(it) }
    actual fun getPath(): String = collectionReference.path
}