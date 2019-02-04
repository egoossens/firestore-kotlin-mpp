buildscript {
    apply(from = "config.gradle.kts")
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlin_version"]}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${extra["kotlin_version"]}")
    }

    repositories {
        mavenCentral()
    }
}

plugins {
    id("org.jetbrains.kotlin.multiplatform").version("1.3.20")
}

group = "com.tastyelectrons.firestore-kotlin-mpp"
version = "0.1.0"

fun ext(key: String): String = extra[key] as String

repositories {
    mavenCentral()
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://jitpack.io")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        compilations.all {
            kotlinOptions {
                sourceMap = true
                sourceMapEmbedSources = "always"
                moduleKind = "commonjs"
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(kotlin("stdlib-common", ext("kotlin_version")))
                implementation(ext("kotlin_coroutines_common"))
                implementation(ext("kotlin_serialization_common"))
            }
        }

        jvm {
            compilations["main"].defaultSourceSet.dependencies {
                api(kotlin("stdlib-jdk8", ext("kotlin_version")))
                implementation(ext("kotlin_coroutines_jvm"))
                implementation(ext("kotlin_serialization_jvm"))

                implementation(ext("firebase_admin"))
            }
        }

        js {
            compilations["main"].defaultSourceSet.dependencies {
                api(kotlin("stdlib-js", ext("kotlin_version")))

                implementation(ext("kotlin_coroutines_js"))
                implementation(ext("kotlin_serialization_js"))
            }
        }
    }
}

/* publishing {
    repositories {
        maven(uri("$buildDir/repo"))
    }
}

// Publishing

//// Add a Javadoc JAR to each publication as required by Maven Central

val javadocJar by tasks.creating(Jar::class) {
    archiveClassifier.value("javadoc")
    // TODO: instead of a single empty Javadoc JAR, generate real documentation for each module
}

publishing {
    publications.withType<MavenPublication>().all {
        artifact(javadocJar)
    }
}

//// The root publication also needs a sources JAR as it does not have one by default

val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.value("sources")
}

publishing.publications.withType<MavenPublication>().getByName("kotlinMultiplatform").artifact(sourcesJar)

//// Customize the POMs adding the content required by Maven Central

fun customizeForMavenCentral(pom: org.gradle.api.publish.maven.MavenPom) = pom.withXml {
    fun Node.add(key: String, value: String) {
        appendNode(key).setValue(value)
    }

    fun Node.node(key: String, content: Node.() -> Unit) {
        appendNode(key).also(content)
    }

    asNode().run {
        add("description", "Kotlin Multiplatform wrapper for the Cloud Firestore SDKs")
        add("name", "Firestore Kotlin MPP")
        add("url", "https://github.com/egoossens/firestore-kotlin-mpp")
        node("organization") {
            add("name", "com.github.egoossens")
            add("url", "https://github.com/egoossens")
        }
        node("issueManagement") {
            add("system", "github")
            add("url", "https://github.com/egoossens/firestore-kotlin-mpp/issues")
        }
        node("licenses") {
            node("license") {
                add("name", "Apache License 2.0")
                add("url", "https://github.com/egoossens/firestore-kotlin-mpp/blob/master/LICENSE")
                add("distribution", "repo")
            }
        }
        node("scm") {
            add("url", "https://github.com/egoossens/firestore-kotlin-mpp")
            add("connection", "scm:git:git://github.com/egoossens/firestore-kotlin-mpp.git")
            add("developerConnection", "scm:git:ssh://github.com/egoossens/firestore-kotlin-mpp.git")
        }
        node("developers") {
            node("developer") {
                add("name", "egoossens")
            }
        }
    }
}

publishing {
    publications.withType<MavenPublication>().all {
        customizeForMavenCentral(pom)
        signing.sign(this)
    }
} */