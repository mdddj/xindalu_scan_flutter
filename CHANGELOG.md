# 4.0.0

* **破坏性变更**：最低 Flutter 版本提升至 3.47.0，Dart 版本要求为 >=3.12.0 <4.0.0。
* Android 插件和 example 迁移到 AGP 9.0.0 内置 Kotlin，移除旧 Kotlin Android 插件和 `kotlinOptions`，改用 `kotlin.compilerOptions`。
* example 的 Gradle Wrapper 升级至 9.2.1；构建需要 JDK 17 或更高版本，Java 和 Kotlin 编译目标统一为 JVM 17。
* 移除旧 Kotlin 源码目录配置和已废弃的 `android.enableR8` 属性，更新 Android lint 配置。
* 升级时，宿主应用需移除 `kotlin-android` / `org.jetbrains.kotlin.android`，在 `android/gradle.properties` 中启用 `android.builtInKotlin=true`，并确保其他插件兼容内置 Kotlin。example 暂时保留 `android.newDsl=false` 作为 Flutter DSL 过渡配置。

# 3.0.0

* 适配 flutter 新版本

# 2.0.1

* gradle 脚本升级

# 2.0.0

* 升级gradle到7.xx,这个版本开始需要flutter版本3.3.7+

# 1.0.1
 compileSdkVersion to 30

# 1.0.0
* 升级到空安全

## 0.0.1

* 初始版本更新,完成基本功能

## 0.0.3

* bug修复,优化代码

## 0.0.5

 * 更新了小细节
