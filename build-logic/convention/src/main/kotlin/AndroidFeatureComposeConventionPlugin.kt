import com.android.build.api.dsl.LibraryExtension
import extension.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidFeatureComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {

            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "com.android.library")

            extensions.configure<LibraryExtension> {
                compileSdk = 35
                defaultConfig {
                    minSdk = 24
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
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
                configureKotlinJvm()
            }

            dependencies {
                val composeBom = libs.findLibrary("androidx-compose-bom").get()
                add("implementation", platform(composeBom))
                add("androidTestImplementation", platform(composeBom))
                add("implementation", libs.findLibrary("androidx.core.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
                add("implementation", libs.findLibrary("androidx.activity.compose").get())
                add("implementation", libs.findLibrary("androidx.ui").get())
                add("implementation", libs.findLibrary("androidx.ui.graphics").get())
                add("implementation", libs.findLibrary("androidx.ui.tooling.preview").get())
                add("implementation", libs.findLibrary("androidx.material3").get())
                add("testImplementation", libs.findLibrary("junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx.junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx.espresso.core").get())
                add("androidTestImplementation", libs.findLibrary("androidx.ui.test.junit4").get())
                add("debugImplementation", libs.findLibrary("androidx.ui.tooling").get())
                add("debugImplementation", libs.findLibrary("androidx.ui.test.manifest").get())
            }
        }
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }
}