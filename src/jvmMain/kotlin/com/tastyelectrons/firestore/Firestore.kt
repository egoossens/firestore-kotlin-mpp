package com.tastyelectrons.firestore

import com.google.cloud.firestore.Firestore

actual class Firestore(private val firestore: Firestore) {
    actual fun batch() = WriteBatch(firestore.batch())
    actual fun document(path: String) = DocumentReference(firestore.document(path))
    actual fun collection(path: String) = CollectionReference(firestore.collection(path))
}