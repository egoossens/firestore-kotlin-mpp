package com.tastyelectrons.firestore

import com.tastyelectrons.firestore.external.DocumentChange

actual class DocumentChange(private val documentChange: DocumentChange) {
    @JsName("getDocument")
    actual fun getDocument(): QueryDocumentSnapshot = QueryDocumentSnapshot(documentChange.doc)
    @JsName("getType")
    actual fun getType(): DocumentChangeType = DocumentChangeType.valueOf(documentChange.type.toString().toUpperCase())
}