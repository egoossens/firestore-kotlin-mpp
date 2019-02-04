package com.tastyelectrons.firestore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.withContext
import com.google.cloud.firestore.Query as FirestoreQuery

actual open class Query(private val query: FirestoreQuery) {
    /* actual fun observe(): ReceiveChannel<QuerySnapshot> = object : ConflatedChannel<QuerySnapshot>() {
        private val listenerRegistration: ListenerRegistration
        val listener = EventListener<com.google.cloud.firestore.QuerySnapshot> { querySnapshot, firestoreException ->
            if (firestoreException != null) {
                Logger.getLogger(Query::class.simpleName).severe("Query Error - $query: ${firestoreException.localizedMessage}")
                close(firestoreException)
            } else {
                querySnapshot?.let {
                    Logger.getLogger(Query::class.simpleName).info("Query Received - ${querySnapshot.size()}")
                    offer(QuerySnapshot(it))
                }
            }
        }

        init {
            listenerRegistration = query.addSnapshotListener(listener)
        }

        override fun afterClose(cause: Throwable?) {
            super.afterClose(cause)
            listenerRegistration.remove()
        }
    } */

    actual fun observe(): ReceiveChannel<QuerySnapshot> {
        val channel = Channel<QuerySnapshot>(Channel.CONFLATED)
        val listenerRegistration = query.addSnapshotListener { querySnapshot, firestoreException ->
            if (firestoreException != null) {
                channel.close(firestoreException)
            } else {
                querySnapshot?.let {
                    channel.offer(QuerySnapshot(it))
                }
            }
        }

        channel.invokeOnClose { listenerRegistration.remove() }
        return channel
    }

    actual suspend fun get(): QuerySnapshot = withContext(Dispatchers.Default) {
        QuerySnapshot(query.get().get())
    }

    actual fun orderBy(field: String, orderDirection: OrderDirection): Query {
        val direction = if (orderDirection === OrderDirection.ASCENDING) FirestoreQuery.Direction.ASCENDING else FirestoreQuery.Direction.DESCENDING
        return Query(query.orderBy(field, direction))
    }

    actual fun startAt(value: Any) = Query(query.startAt(value))

    actual fun endAt(value: Any) = Query(query.endAt(value))

    actual fun limit(limit: Int) = Query(query.limit(limit))

    actual fun whereLessThan(field: String, value: Any) = Query(query.whereLessThan(field, value))

    actual fun whereGreaterThan(field: String, value: Any) = Query(query.whereGreaterThan(field, value))
}