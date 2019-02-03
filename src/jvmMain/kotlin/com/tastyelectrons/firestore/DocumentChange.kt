package com.tastyelectrons.firestore

import com.google.cloud.firestore.DocumentChange

actual class DocumentChange(private val documentChange: DocumentChange) {
    actual fun getDocument(): QueryDocumentSnapshot = QueryDocumentSnapshot(documentChange.document)
    actual fun getType(): DocumentChangeType = documentChange.type
}