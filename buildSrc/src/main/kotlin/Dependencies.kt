object Kotlin {
    const val standardLibrary = "1.4.10"
    const val coroutines = "1.3.9"
}

object AndroidSdk {
    const val min = 26
    const val compile = 31
    const val target = compile
}

object AndroidClient {
    const val appId = "com.noban.appschedular.core"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildPlugins {
    object Versions {
        const val buildToolsVersion = "4.2.0"
        const val gradleVersion = "7.4.2"
        const val hilt = "2.38.1"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.standardLibrary}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val androidHilt = "dagger.hilt.android.plugin"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5"
    const val gmsService = "com.google.gms:google-services:4.3.10"
    const val gradlePlugin =  "com.android.tools.build:gradle"
}

object ScriptPlugins {
    const val infrastructure = "scripts.infrastructure"
    const val variants = "scripts.variants"
    const val quality = "scripts.quality"
    const val compilation = "scripts.compilation"
    const val jetbrainsAndroid = "org.jetbrains.kotlin.android"
    const val safeArgs = "androidx.navigation.safeargs.kotlin"
}

object Libraries {
    private object Versions {
        const val hilt = BuildPlugins.Versions.hilt
        const val appCompat = "1.2.0"
        const val constraintLayout = "2.0.2"
        const val recyclerView = "1.2.1"
        const val cardView = "1.0.0"
        const val material = "1.1.0"
        const val lifecycle = "2.2.0"
        const val navigation = "2.2.0"
        const val lifecycleExtensions = "2.1.0"
        const val annotations = "1.1.0"
        const val ktx = "1.3.2"
        const val room = "2.4.2"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Kotlin.standardLibrary}"
    const val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"
    const val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Kotlin.coroutines}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val androidAnnotations = "androidx.annotation:annotation:${Versions.annotations}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val navigationComponent =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationComponentUI =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val room = "androidx.room:room-common:${Versions.room}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val kaptRoomCompiler = "androidx.room:room-compiler:2.4.2"
    const val core = "androidx.core:core:1.0.1"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.0-alpha05"
    const val workManager = "androidx.recyclerview:recyclerview-selection:1.1.0"
    const val workManagerRuntime = "androidx.work:work-runtime:2.7.1"
    const val constrainLayout = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val ktxRoom = "androidx.room:room-ktx:2.3.0"



}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13.1"
        const val mockk = "1.10.0"
        const val robolectric = "4.4"
        const val kluent = "1.14"
        const val testRunner = "1.1.0"
        const val espressoCore = "3.2.0"
        const val espressoIntents = "3.1.0"
        const val testExtensions = "1.1.1"
        const val testRules = "1.1.0"
        const val hiltTesting = BuildPlugins.Versions.hilt
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val kluent = "org.amshove.kluent:kluent:${Versions.kluent}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val testRules = "androidx.test:rules:${Versions.testRules}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val espressoIntents =
        "androidx.test.espresso:espresso-intents:${Versions.espressoIntents}"
    const val testExtJunit = "androidx.test.ext:junit:${Versions.testExtensions}"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltTesting}"
}

object DevLibraries {
    private object Versions {
        const val leakCanary = "2.5"
    }

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
}
