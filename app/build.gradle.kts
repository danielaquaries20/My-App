plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.kapt")
    alias(libs.plugins.ksp)
    alias(libs.plugins.daggerHilt)
//    alias(libs.plugins.google.gms)
    alias(libs.plugins.firebaseGms)
    alias(libs.plugins.firebaseCrashlytic)
    alias(libs.plugins.firebasePerf)
}

android {
    namespace = "com.daniel.myapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.daniel.myapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.mathXparser)

    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    implementation(libs.activity.ktx)
    implementation(libs.daggerHilt)
    ksp(libs.daggerHiltCompiler)

    implementation(libs.coroutine)
//    implementation(platform(libs.firebase.bom))
//    implementation(libs.firebase.analytic)

    implementation(libs.coreCrocodic)
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}