import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinMultiplatform)

    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets.commonMain{
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets {
        commonMain.dependencies {
			implementation(libs.androidxCoreKtx)
			implementation(libs.androidxAppcompat)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
	        implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

	        implementation(libs.androidxLifecycleViewModelSavedState)
	        implementation(libs.androidxLifecycleViewmodelCompose)
	        implementation(libs.androidxLifecycleRuntimeCompose)
	        implementation(libs.androidxNavigationCompose)

	        implementation(libs.ktorSerializationKotlinxJson)
            implementation(libs.kotlinxSerializationJson)
            implementation(libs.kotlinxCoroutinesCore)

            implementation(libs.ktorClientCore)
            api(libs.koinCore)
            implementation(libs.koinCompose)
            implementation(libs.koinComposeViewModel)

            implementation(libs.coil)
            implementation(libs.coilCompose)
            implementation(libs.coilNetworkKtor)

	        implementation(libs.roomRuntime)
            implementation(libs.sqliteBundled)

	        implementation(libs.ktorClientContentNegotiation)
	        implementation(libs.ktorClientLogging)

	        implementation(libs.oAuthJavaJwt)

	        implementation(libs.squareupOkio)
	        implementation(libs.androidxDatastore)
	        implementation(libs.androidxDatastorePreferences)


        }
        androidMain.dependencies {
            implementation(compose.preview)

            implementation(libs.androidxActivityCompose)

            implementation(libs.kotlinxCoroutinesAndroid)

            implementation(libs.ktorClientOkHttp)
            implementation(libs.koinAndroid)
            implementation(libs.koinAndroidXCompose)

            implementation(libs.sqliteWrapper)
            implementation(libs.roomRuntimeAndroid)


            implementation(libs.coilAndroid)
            implementation(libs.coilComposeAndroid)
            implementation(libs.coilNetworkKtorAndroid)
        }
        iosMain.dependencies {
            implementation(libs.ktorClientDarwin)
        }
        iosX64Main.dependencies {
            implementation(libs.koinCoreIosX64)
            implementation(libs.roomRuntimeIosX64)
            implementation(libs.coilComposeIosX64)
            implementation(libs.coilNetworkKtorIosX64)
            implementation(libs.kotlinxCoroutinesCoreIosX64)
        }
        iosArm64Main.dependencies {
            implementation(libs.koinCoreIosArm64)
            implementation(libs.roomRuntimeIosArm64)
            implementation(libs.coilComposeIosArm64)
            implementation(libs.coilNetworkKtorIosArm64)
            implementation(libs.kotlinxCoroutinesCoreIosArm64)
        }
        iosSimulatorArm64Main.dependencies {
            implementation(libs.koinCoreIosSimulatorArm64)
            implementation(libs.roomRuntimeIosSimulatorArm64)
            implementation(libs.coilComposeIosSimulatorArm64)
            implementation(libs.coilNetworkKtorIosSimulatorArm64)
            implementation(libs.kotlinxCoroutinesCoreIosSimulatorArm64)
        }
    }
}

android {
    namespace = "com.internconnect.internconnectfrontendclient"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.internconnect.internconnectfrontendclient"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspCommonMainMetadata", libs.roomCompiler)
    add("kspAndroid", libs.roomCompiler)
    add("kspIosArm64", libs.roomCompiler)
    add("kspIosX64", libs.roomCompiler)
    add("kspIosSimulatorArm64", libs.roomCompiler)
    debugImplementation(compose.uiTooling)
}
