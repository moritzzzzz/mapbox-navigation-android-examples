plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(23)
        missingDimensionStrategy("settings_visibility", "internal")
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    api("com.mapbox.navigation:android:2.0.0-beta.21")
    api("com.mapbox.search:mapbox-search-android:1.0.0-beta.17")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.car.app:app:1.0.0")
}

dependencies {
    testImplementation("org.robolectric:robolectric:4.3.1")
    testImplementation("androidx.test.ext:junit:1.1.3")
    testImplementation("io.mockk:mockk:1.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
}
