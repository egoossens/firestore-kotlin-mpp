package com.tastyelectrons.firestore

expect class DocumentChange {
    fun getDocument(): QueryDocumentSnapshot
    fun getType(): DocumentChangeType
}