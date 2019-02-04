package com.tastyelectrons.firestore

expect class Firestore {
    fun batch(): WriteBatch
    fun document(path: String): DocumentReference
    fun collection(path: String): CollectionReference
}