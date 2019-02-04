package com.tastyelectrons.firestore

expect open class DocumentSnapshot {
    fun exists(): Boolean
    fun getId(): String
    fun getString(field: String): String?
    fun getBoolean(field: String): Boolean?
    fun getDouble(field: String): Double?
    fun getNumber(field: String): Number?
    fun getData(): Map<String, Any>?
    fun contains(field: String): Boolean
    fun getReference(): DocumentReference
}