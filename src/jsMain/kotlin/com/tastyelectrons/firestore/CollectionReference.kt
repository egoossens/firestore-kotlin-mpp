package com.tastyelectrons.firestore

import com.tastyelectrons.firestore.external.CollectionReference

actual class CollectionReference(private val collectionReference: CollectionReference): Query(collectionReference) {
    @JsName("add")
    actual suspend fun add(obj: Any): DocumentReference? {
        val docRef = DocumentReference(collectionReference.doc())
        docRef.set(obj)
        return docRef
    }
    @JsName("documentAutoId")
    actual fun document(): DocumentReference = DocumentReference(collectionReference.doc())
    @JsName("document")
    actual fun document(childPath: String): DocumentReference = DocumentReference(collectionReference.doc(childPath))
    @JsName("getId")
    actual fun getId(): String = collectionReference.id
    @JsName("getParent")
    actual fun getParent(): DocumentReference? = collectionReference.parent?.let { DocumentReference(it) }
    @JsName("getPath")
    actual fun getPath(): String = collectionReference.path
}