package com.tastyelectrons.firestore

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import com.tastyelectrons.firestore.external.Query as FirestoreQuery

actual open class Query(private val query: FirestoreQuery) {
    @JsName("observe")
    actual fun observe(): ReceiveChannel<QuerySnapshot> {
        val channel = Channel<QuerySnapshot>(Channel.CONFLATED)
        val removeListener = query.onSnapshot({
            channel.offer(QuerySnapshot(it))
        }, {
            channel.close(it)
        })

        channel.invokeOnClose { removeListener() }
        return channel
    }

    @JsName("get")
    actual suspend fun get(): QuerySnapshot = suspendCoroutine { continuation ->
        query.get()
                .then { continuation.resume(QuerySnapshot(it)) }
                .catch { continuation.resumeWithException(it) }
    }

    @JsName("orderBy")
    actual fun orderBy(field: String, orderDirection: OrderDirection): Query {
        val direction = if (orderDirection === OrderDirection.ASCENDING) "asc" else "desc"
        return Query(query.orderBy("$field $direction"))
    }

    @JsName("startAt")
    actual fun startAt(value: Any) = Query(query.startAt(value))

    @JsName("endAt")
    actual fun endAt(value: Any) = Query(query.endAt(value))

    @JsName("limit")
    actual fun limit(limit: Int) = Query(query.limit(limit))

    @JsName("whereLessThan")
    actual fun whereLessThan(field: String, value: Any) = Query(query.where(field, "<", value))

    @JsName("whereGreaterThan")
    actual fun whereGreaterThan(field: String, value: Any) = Query(query.where(field, ">", value))
}