# Mobile Computing - Assignment Report
**Student Name:** Elina Rai

## Chapter 1: Mobile Computing Fundamentals

### 1. Android Studio Configuration
*   **Installation**: Downloaded Android Studio Hedgehog from the official website.
*   **SDK**: Installed Android SDK 34 (Upside Down Cake).
*   **Device**: Configured an Android Emulator (Pixel 6 Pro, API 34).

### 2. "Hello World" Code Explanation
In the `MainActivity.kt`:
*   `package com.example.assignment`: Defines the namespace for the application.
*   `import ...`: Brings in necessary Android classes (like `AppCompatActivity`, `Bundle`, `Log`).
*   `class MainActivity : AppCompatActivity()`: This is the entry point of the screen. `AppCompatActivity` provides backward compatibility for older Android versions.
*   `onCreate(savedInstanceState: Bundle?)`: The first method called when the activity starts. It's used for initialization.
*   `setContentView(R.layout.activity_main)`: Links the Kotlin code to its XML layout file.

### 5. Android vs iOS Comparison Report
| Feature | Android | iOS |
|---|---|---|
| **Developer** | Google | Apple |
| **Source Code** | Open Source (AOSP) | Closed Source |
| **Hardware** | Multiple Manufacturers (Samsung, Pixel, etc.) | Apple Only (iPhone) |
| **App Store** | Google Play Store | Apple App Store |
| **Customization**| High (Widgets, Launchers, ROMs) | Limited / Controlled |
| **Security** | Flexible, relies on user/Play Protect | Highly Restricted "Walled Garden" |

---

## Chapter 2: Android Basics and Setup

### 1. Project Structure Explanation
*   **`manifests/`**: Contains `AndroidManifest.xml` which defines app components (Activities, Permissions).
*   **`java/`**: Contains the Kotlin source code files.
*   **`res/layout/`**: Contains XML files defining the User Interface.
*   **`res/drawable/`**: Contains images and icons.
*   **`gradle scripts/`**: Contains build configuration files like `build.gradle`.

### 5. APK Generation & Build Process
The build process converts source code into an APK (Android Package) file:
1.  **Compilation**: Kotlin/Java code is compiled into DEX (Dalvik Executable) files.
2.  **Resource Packaging**: XML layouts and images are compiled and bundled.
3.  **Linking**: Compiled code and resources are linked together.
4.  **Signing**: The package is signed with a certificate for security.
5.  **Zipping**: The final files are zipped into an `.apk` file ready for installation.

---

## Chapter 8: Deployment and Security

### 4. Security Techniques
*   **Encryption**: Demonstrated in `StorageActivity.kt` using Base64 encoding for basic data obfuscation.
*   **Runtime Permissions**: Implemented in `MultimediaActivity.kt` to ensure user privacy for Camera and Location.

### 5. Potential Security Issues
*   **Unencrypted Storage**: Storing sensitive data in plain text in SharedPreferences or Files.
*   **Insecure API calls**: Not using HTTPS for network requests (mitigated by using modern libraries).
*   **Excessive Permissions**: Asking for permissions the app doesn't actually need.
