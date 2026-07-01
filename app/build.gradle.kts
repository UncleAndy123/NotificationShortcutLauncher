plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.purespeech.notificationshortcutlauncher"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.purespeech.notificationshortcutlauncher"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "0.1"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}