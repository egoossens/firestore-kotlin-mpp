package com.tastyelectrons.firestore

expect class QuerySnapshot {
    fun getDocuments(): List<QueryDocumentSnapshot>
    fun getDocumentChanges(): List<DocumentChange>
    fun size(): Int
    fun isEmpty(): Boolean
    fun getQuery(): Query
}