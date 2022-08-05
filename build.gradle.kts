plugins {
    id(ScriptPlugins.infrastructure)
}

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath (BuildPlugins.androidGradlePlugin)
        classpath (BuildPlugins.kotlinGradlePlugin)
        classpath (BuildPlugins.hiltGradlePlugin)
        classpath (BuildPlugins.gmsService)
        classpath(BuildPlugins.gradlePlugin)
        classpath(BuildPlugins.navigation)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven( "https://jitpack.io")
    }
}
