import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    `maven-publish`
}

repositories {
    mavenCentral()
}

kotlin {
    jvm {
       compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
 
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR).nodejs()
    linuxX64()

    sourceSets {
        commonMain.dependencies {
            implementation(kotlin("stdlib"))
            implementation(kotlin("test"))
            api(projects.mathBase)
        }
    }
}

//project.afterEvaluate {
//    publishing {
//        repositories {
//            maven {
//                val releasesRepoUrl = layout.buildDirectory.dir("repos/releases")
//                val snapshotsRepoUrl = layout.buildDirectory.dir("repos/snapshots")
//                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
//            }
//        }
//        publications {
//            create<MavenPublication>("maven") {
//                groupId = "org.gradle.sample"
//                artifactId = "library"
//                version = "1.1"
//                from(components["java"])
//
//                pom {
//                    name.set("My Library")
//                    description.set("A concise description of my library")
//                    url.set("http://www.example.com/library")
//                    properties.set(
//                        mapOf(
//                            "myProp" to "value",
//                            "prop.with.dots" to "anotherValue"
//                        )
//                    )
//                    licenses {
//                        license {
//                            name.set("The Apache License, Version 2.0")
//                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
//                        }
//                    }
//                    developers {
//                        developer {
//                            id.set("johnd")
//                            name.set("John Doe")
//                            email.set("john.doe@example.com")
//                        }
//                    }
//                    scm {
//                        connection.set("scm:git:git://example.com/my-library.git")
//                        developerConnection.set("scm:git:ssh://example.com/my-library.git")
//                        url.set("http://example.com/my-library/")
//                    }
//                }
//            }
//        }
//    }
//}