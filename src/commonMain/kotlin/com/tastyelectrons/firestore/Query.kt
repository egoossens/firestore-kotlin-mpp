package com.tastyelectrons.firestore

import kotlinx.coroutines.channels.ReceiveChannel

expect open class Query {
    fun observe(): ReceiveChannel<QuerySnapshot>
    fun orderBy(field: String, orderDirection: OrderDirection = OrderDirection.ASCENDING): Query
    fun startAt(value: Any): Query
    fun endAt(value: Any): Query
    suspend fun get(): QuerySnapshot
    fun limit(limit: Int): Query
    fun whereLessThan(field: String, value: Any): Query
    fun whereGreaterThan(field: String, value: Any): Query
}