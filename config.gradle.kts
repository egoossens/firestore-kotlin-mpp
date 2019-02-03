val versions = mapOf(
        "kotlin_version"                    to "1.3.20",
        "kotlin_serialization_version"      to "0.10.0",
        "kotlin_coroutines_version"         to "1.1.1",
        "firebase_admin_version"            to "6.7.0"
)
versions.entries.forEach { project.extra.set(it.key, it.value) }

val deps = mapOf(
        // Coroutines
        "kotlin_coroutines_common"          to "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${versions["kotlin_coroutines_version"]}",
        "kotlin_coroutines_jvm"             to "org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions["kotlin_coroutines_version"]}",
        "kotlin_coroutines_js"              to "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${versions["kotlin_coroutines_version"]}",
        // Serialization
        "kotlin_serialization_common"       to "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${versions["kotlin_serialization_version"]}",
        "kotlin_serialization_jvm"          to "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${versions["kotlin_serialization_version"]}",
        "kotlin_serialization_js"           to "org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${versions["kotlin_serialization_version"]}",
        // Firestore
        "firebase_admin"                    to "com.google.firebase:firebase-admin:${versions["firebase_admin_version"]}"
)
deps.entries.forEach { project.extra.set(it.key, it.value) }