package com.tastyelectrons.firestore

expect class WriteBatch {
    fun set(documentReference: DocumentReference, data: Any): WriteBatch
    suspend fun commit()
}