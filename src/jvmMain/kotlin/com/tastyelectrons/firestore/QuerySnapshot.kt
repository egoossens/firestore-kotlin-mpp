package com.tastyelectrons.firestore

import com.google.cloud.firestore.QuerySnapshot

actual class QuerySnapshot(private val querySnapshot: QuerySnapshot) {
    actual fun getDocuments(): List<QueryDocumentSnapshot> = querySnapshot.documents.map { QueryDocumentSnapshot(it) }
    actual fun getDocumentChanges(): List<DocumentChange> = querySnapshot.documentChanges.map { DocumentChange(it) }
    actual fun size(): Int = querySnapshot.size()
    actual fun isEmpty(): Boolean = querySnapshot.isEmpty
    actual fun getQuery(): Query = Query(querySnapshot.query)
}