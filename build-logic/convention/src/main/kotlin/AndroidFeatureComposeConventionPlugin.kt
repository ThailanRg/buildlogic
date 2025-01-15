import extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {

            apply(plugin = "org.jetbrains.kotlin.plugin.compose")


            dependencies {
                val bom = libs.findLibrary("androidx-compose-bom").get()
                add("implementation", platform(bom))
                add("implementation", libs.findLibrary("androidx.ui").get())
                add("implementation", libs.findLibrary("androidx.activity.compose").get())
                add("implementation", libs.findLibrary("androidx.ui.tooling").get())
                add("implementation", libs.findLibrary("androidx.ui.tooling.preview").get())
                add("implementation", libs.findLibrary("androidx.ui.graphics").get())
                add("implementation", libs.findLibrary("androidx.material3").get())
                add("implementation", libs.findLibrary("androidx.navigation.compose").get())
                add("implementation", libs.findLibrary("insert.koin.androidx.compose.navigation").get())
            }
        }
    }
}