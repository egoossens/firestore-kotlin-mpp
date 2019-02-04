package com.tastyelectrons.firestore

import com.tastyelectrons.firestore.external.Firestore

actual class Firestore(private val firestore: Firestore) {
    @JsName("batch")
    actual fun batch() = WriteBatch(firestore.batch())
    @JsName("document")
    actual fun document(path: String) = DocumentReference(firestore.doc(path))
    @JsName("collection")
    actual fun collection(path: String) = CollectionReference(firestore.collection(path))
}