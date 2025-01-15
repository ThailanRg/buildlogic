//plugins {
//    `kotlin-dsl`
//}
//
//group = "br.com.onde-e-mais-barato.buildlogic"
//
//dependencies {
//    compileOnly(libs.kotlin.gradle.plugin)
//    compileOnly(libs.android.tools.common)
//    compileOnly(libs.gradle)
//    compileOnly(libs.ksp.gradlePlugin)
//}
//
//
//tasks {
//    validatePlugins {
//        enableStricterValidation = true
//        failOnWarning = true
//    }
//}
//
//gradlePlugin {
//    plugins {
//        register("composeFeatureConvention") {
//            id = libs.plugins.buildlogic.android.compose.feature.get().pluginId
//            implementationClass = "AndroidFeatureComposeConventionPlugin"
//        }
//    }
//}