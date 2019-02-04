package com.tastyelectrons.firestore

import com.tastyelectrons.firestore.external.DocumentSnapshot

actual open class DocumentSnapshot(val documentSnapshot: DocumentSnapshot) {
    @JsName("getReference")
    actual fun getReference(): DocumentReference = DocumentReference(documentSnapshot.ref)
    @JsName("getId")
    actual fun getId(): String = documentSnapshot.id
    @JsName("exists")
    actual fun exists(): Boolean = documentSnapshot.exists
    @JsName("getString")
    actual fun getString(field: String): String? = documentSnapshot.get(field) as? String
    @JsName("getBoolean")
    actual fun getBoolean(field: String): Boolean? = documentSnapshot.get(field) as? Boolean
    @JsName("getDouble")
    actual fun getDouble(field: String): Double? = documentSnapshot.get(field) as? Double
    @JsName("getLong")
    actual fun getNumber(field: String): Number? = documentSnapshot.get(field) as? Number
    @JsName("getData")
    actual fun getData(): dynamic = documentSnapshot.data()
    @JsName("contains")
    actual fun contains(field: String): Boolean = documentSnapshot.get(field) !== undefined
}