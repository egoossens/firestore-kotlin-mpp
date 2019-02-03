package com.tastyelectrons.firestore

import com.google.cloud.firestore.DocumentSnapshot

actual open class DocumentSnapshot(val documentSnapshot: DocumentSnapshot) {
    actual fun getReference(): DocumentReference = DocumentReference(documentSnapshot.reference)
    actual fun getId(): String = documentSnapshot.id
    actual fun exists(): Boolean = documentSnapshot.exists()
    actual fun getString(field: String): String? = documentSnapshot.getString(field)
    actual fun getBoolean(field: String): Boolean? = documentSnapshot.getBoolean(field)
    actual fun getDouble(field: String): Double? = documentSnapshot.getDouble(field)
    actual fun getNumber(field: String): Number? = documentSnapshot.getLong(field)
    actual fun getData(): Map<String, Any>? = documentSnapshot.data
    actual fun contains(field: String): Boolean = documentSnapshot.contains(field)
}