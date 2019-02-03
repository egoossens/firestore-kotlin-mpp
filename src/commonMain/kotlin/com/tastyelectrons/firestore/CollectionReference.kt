package com.tastyelectrons.firestore

expect class CollectionReference: Query {
    suspend fun add(obj: Any): DocumentReference?
    fun document(): DocumentReference
    fun document(childPath: String): DocumentReference
    fun getId(): String
    fun getParent(): DocumentReference?
    fun getPath(): String
}