package com.tastyelectrons.firestore

import com.tastyelectrons.firestore.external.DocumentReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.promise
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.js.Promise

actual class DocumentReference(val documentReference: DocumentReference) {
    @JsName("collection")
    actual fun collection(collectionPath: String) = CollectionReference(documentReference.collection(collectionPath))

    @JsName("observe")
    actual fun observe(): ReceiveChannel<DocumentSnapshot> {
        val channel = Channel<DocumentSnapshot>(Channel.CONFLATED)
        val removeListener = documentReference.onSnapshot({
            channel.offer(DocumentSnapshot(it))
        }, {
            channel.close(it)
        })

        channel.invokeOnClose { removeListener() }
        return channel
    }

    @JsName("get_mangle123")
    actual suspend fun get(): DocumentSnapshot = DocumentSnapshot(documentReference.get().await())

    @JsName("get")
    fun getPromise(): Promise<DocumentSnapshot> = CoroutineScope(Dispatchers.Default).promise { get() }

    @JsName("getId")
    actual fun getId(): String = documentReference.id

    @JsName("delete_mangle123")
    actual suspend fun delete() = suspendCoroutine<Unit> { continuation ->
        documentReference.delete()
                .then { continuation.resume(Unit) }
                .catch { continuation.resumeWithException(it) }
    }

    @JsName("delete")
    fun deletePromise(): Promise<Unit> = CoroutineScope(Dispatchers.Default).promise { delete() }

    @JsName("set_mangle123")
    actual suspend fun set(obj: Any) = suspendCoroutine<Unit> { continuation ->
        val o = obj
        val jsObject = js("JSON.parse(JSON.stringify(o))")
        documentReference.set(jsObject)
                .then { continuation.resume(Unit) }
                .catch { continuation.resumeWithException(it) }
    }

    @JsName("set")
    fun setPromise(obj: Any): Promise<Unit> = CoroutineScope(Dispatchers.Default).promise { set(obj) }

    @JsName("update_mangle123")
    actual suspend fun update(vararg fields: Pair<String, Any>) = suspendCoroutine<Unit> { continuation ->
        val keyValues = fields.flatMap { listOf(it.first, it.second) }.toTypedArray()
        console.log("update_mangle", fields, keyValues)
        documentReference.update(fields[0].first, fields[0].second, *keyValues)
                .then { continuation.resume(Unit) }
                .catch { continuation.resumeWithException(it) }
    }

    @JsName("update")
    fun updatePromise(vararg fields: Pair<String, Any>): Promise<Unit> = CoroutineScope(Dispatchers.Default).promise { update(*fields) }
}