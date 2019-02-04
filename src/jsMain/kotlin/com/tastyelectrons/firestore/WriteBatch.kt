package com.tastyelectrons.firestore

import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

actual class WriteBatch(private val writeBatch: dynamic) {
    actual fun set(documentReference: DocumentReference, data: Any) = WriteBatch(writeBatch.set(documentReference.documentReference, js("JSON.parse(JSON.stringify(data))")))

    actual suspend fun commit() = suspendCoroutine<Unit> { continuation ->
        writeBatch.commit()
                .then { continuation.resume(Unit) }
                .catch { err -> continuation.resumeWithException(err) }
    }
}