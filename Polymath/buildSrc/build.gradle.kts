plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
    testImplementation("junit:junit:4.13.1")
}

gradlePlugin {
    plugins {
        create("simplePlugin") {
            id = "dev.patbeagan.debugPlugins"
            implementationClass = "dev.patbeagan.buildconfig.DebugPrintPlugins"
        }
        create("applyCommon") {
            id = "dev.patbeagan.applyCommonKMP"
            implementationClass = "dev.patbeagan.buildconfig.ApplyCommonKMPSettings"
        }
    }
}

