package com.tastyelectrons.firestore

import kotlinx.coroutines.channels.ReceiveChannel

expect class DocumentReference {
    fun collection(collectionPath: String): CollectionReference
    fun observe(): ReceiveChannel<DocumentSnapshot>
    suspend fun get(): DocumentSnapshot
    fun getId(): String
    suspend fun delete()
    suspend fun set(obj: Any)
    suspend fun update(vararg fields: Pair<String, Any>)
}