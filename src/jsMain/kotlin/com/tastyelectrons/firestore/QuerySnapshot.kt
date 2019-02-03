package com.tastyelectrons.firestore

import com.tastyelectrons.firestore.external.QuerySnapshot

actual class QuerySnapshot(private val querySnapshot: QuerySnapshot) {
    @JsName("getDocuments")
    actual fun getDocuments(): List<QueryDocumentSnapshot> = querySnapshot.docs.map { QueryDocumentSnapshot(it) }
    @JsName("getDocumentChanges")
    actual fun getDocumentChanges(): List<DocumentChange> = querySnapshot.docChanges().map { DocumentChange(it) }
    @JsName("size")
    actual fun size(): Int = querySnapshot.size
    @JsName("isEmpty")
    actual fun isEmpty(): Boolean = querySnapshot.empty
    @JsName("getQuery")
    actual fun getQuery(): Query = Query(querySnapshot.query)
}