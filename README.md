<div align="center">

<br/>

```
██╗  ██╗██╗   ██╗███╗   ███╗██████╗  █████╗ ██████╗  █████╗       ██╗  ██╗ █████╗ ██╗      █████╗ 
██║ ██╔╝██║   ██║████╗ ████║██╔══██╗██╔══██╗██╔══██╗██╔══██╗      ██║ ██╔╝██╔══██╗██║     ██╔══██╗
█████╔╝ ██║   ██║██╔████╔██║██████╔╝███████║██████╔╝███████║█████╗█████╔╝ ███████║██║     ███████║
██╔═██╗ ██║   ██║██║╚██╔╝██║██╔══██╗██╔══██║██╔══██╗██╔══██║╚════╝██╔═██╗ ██╔══██║██║     ██╔══██║
██║  ██╗╚██████╔╝██║ ╚═╝ ██║██████╔╝██║  ██║██║  ██║██║  ██║      ██║  ██╗██║  ██║███████╗██║  ██║
╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝      ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝
```

### 🏺 *Where Clay Tells Its Story*

<br/>

![Kotlin](https://img.shields.io/badge/Kotlin-2.3.21-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-Material_3-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-Auth_·_Firestore_·_Storage-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![Android](https://img.shields.io/badge/Android-Studio-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-8B4513?style=for-the-badge)

<br/>

> **Kumbara-Kala** is a modern Android marketplace built for clay artisans — a place to upload products, narrate their craft stories, generate shareable visual story cards, and educate buyers about the beauty, health, and eco-conscious benefits of clay.

<br/>

</div>

---

## 📖 About the Project

Clay artisanship is an ancient art form — yet artisans often lack a platform that honours the *story* behind each piece. **Kumbara-Kala** bridges that gap.

The app empowers clay artisans to:
- **Showcase** handcrafted clay products with rich imagery
- **Narrate** the story of each piece — the inspiration, the clay, the craft
- **Generate** a beautifully designed 1080×1920 story card using Android Canvas, ready to share instantly
- **Educate** buyers about the health and environmental advantages of clay products
- **Reach** customers directly via WhatsApp sharing or Android's native share sheet

Whether you're a potter, a buyer, or a clay enthusiast — Kumbara-Kala brings the kiln to your fingertips.

---

## ✨ Features

| Feature | Description |
|---|---|
| 🎨 **Product Catalog** | Browse clay products with category filters and search |
| 📖 **Story Cards** | Auto-generate stunning 1080×1920 story card bitmaps per product |
| 📤 **WhatsApp Share** | Share story cards directly to WhatsApp or via Android share sheet |
| 🔍 **Search & Filter** | Search by name, filter by clay category |
| ❤️ **Favourites** | Bookmark products for later |
| 🏺 **Artisan Profiles** | Dedicated profile pages for clay makers |
| 🌿 **Eco Education** | In-app content on the health and eco benefits of clay |
| 🔐 **Firebase Auth** | Email/password login and registration |
| 🌑 **Dark Mode** | Full Material 3 dark mode support |
| 📴 **Demo Mode** | Works with sample data before Firebase is connected |

---

## 🗂️ Project Structure

```
Kumbara-Kala/
│
├── app/                              # Main Android application module
│   ├── src/
│   │   └── main/
│   │       ├── java/com/kumbarakala/app/
│   │       │   ├── ui/               # Jetpack Compose screens & components
│   │       │   │   ├── splash/       # Splash screen
│   │       │   │   ├── auth/         # Login & Registration screens
│   │       │   │   ├── home/         # Home dashboard
│   │       │   │   ├── catalog/      # Product catalog & category filter
│   │       │   │   ├── product/      # Product detail & story card generator
│   │       │   │   ├── upload/       # Upload product screen
│   │       │   │   ├── artisan/      # Artisan profile screen
│   │       │   │   └── theme/        # Material 3 earthy theme & typography
│   │       │   ├── viewmodel/        # MVVM ViewModels with StateFlow
│   │       │   ├── repository/       # Firebase Firestore & Storage repositories
│   │       │   ├── model/            # Data models (Product, User, Artisan, etc.)
│   │       │   ├── navigation/       # Navigation Compose graph & routes
│   │       │   └── utils/            # Canvas bitmap gen, share intent helpers
│   │       ├── res/                  # Resources (drawables, strings, colors)
│   │       └── AndroidManifest.xml
│   ├── google-services.json          # ⚠️ Add your own (see Firebase Setup)
│   └── build.gradle.kts              # App-level Gradle config
│
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties
│
├── build.gradle.kts                  # Project-level Gradle config
├── settings.gradle.kts               # Module settings
├── gradle.properties                 # JVM & Gradle flags
├── gradlew / gradlew.bat             # Gradle wrapper scripts
├── local.properties                  # SDK path (auto-generated, not committed)
└── README.md
```

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| **Language** | Kotlin 2.3.21 |
| **UI Framework** | Jetpack Compose + Material 3 |
| **Architecture** | MVVM — ViewModel, StateFlow, Coroutines |
| **Navigation** | Navigation Compose |
| **Backend** | Firebase Authentication, Firestore, Storage |
| **Image Loading** | Coil |
| **Story Card Generation** | Android Canvas → Bitmap (1080×1920) |
| **Build System** | Gradle with Kotlin DSL (`.kts`) |
| **Minimum SDK** | Android 8.0 (API 26) |

---

## 🔥 Firebase Setup

Kumbara-Kala uses Firebase for authentication, database, and media storage. Follow these steps before enabling live features:

**1. Create a Firebase Project**
Go to [console.firebase.google.com](https://console.firebase.google.com) and create a new project.

**2. Register Your Android App**
Add an Android app with the package name:
```
com.kumbarakala.app
```

**3. Download `google-services.json`**
Download the config file from your Firebase project dashboard.

**4. Place it in the correct location**
```
app/google-services.json
```

**5. Enable Authentication**
In the Firebase Console → Authentication → Sign-in Method, enable **Email/Password**.

**6. Create Firestore Collections**
```
products/
users/
favorites/
artisans/
```

**7. Create Storage Folders**
```
product_images/
artisan_images/
story_cards/
```

> 💡 **Note:** The Gradle build applies the Google Services plugin only when `google-services.json` is present, so the demo UI compiles and runs without Firebase configured.

---

## 🚀 Getting Started

### Prerequisites

- Android Studio **Hedgehog** or later
- JDK 17+
- Android SDK with API 26+
- A Firebase account (optional for demo mode)

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/sha924/Kumbara-Kala.git

# 2. Open in Android Studio
# File → Open → select the Kumbara-Kala folder

# 3. Let Gradle sync automatically
# (Android Studio will prompt you)

# 4. (Optional) Add Firebase config
cp /path/to/google-services.json app/google-services.json

# 5. Run the app
# Click ▶ Run, or use the terminal:
./gradlew installDebug
```

### Demo Mode

Don't have Firebase set up yet? No problem.

On the home screen, tap **"Continue Demo"** to explore the full app experience with sample clay products and pre-loaded artisan data.

---

## 📦 Key Dependencies

```kotlin
// Jetpack Compose BOM
implementation(platform("androidx.compose:compose-bom:2024.xx.xx"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")

// Navigation
implementation("androidx.navigation:navigation-compose:2.7.x")

// ViewModel & Lifecycle
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.x")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.x")

// Firebase
implementation(platform("com.google.firebase:firebase-bom:33.x.x"))
implementation("com.google.firebase:firebase-auth-ktx")
implementation("com.google.firebase:firebase-firestore-ktx")
implementation("com.google.firebase:firebase-storage-ktx")

// Image Loading
implementation("io.coil-kt:coil-compose:2.x.x")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.x")
```

---

## 🏺 App Screens

```
Splash Screen
    └── Login / Register
            └── Home Dashboard
                    ├── Product Catalog ──── Product Detail
                    │       └── Category Filter     └── Story Card Generator
                    │                                       └── Share (WhatsApp / Android)
                    ├── Upload Product
                    ├── Artisan Profile
                    └── Favourites
```

---

## 🌿 Why Clay?

Kumbara-Kala isn't just a marketplace — it's a movement. Clay cookware and vessels are:

- 🌱 **Eco-friendly** — zero synthetic materials, 100% biodegradable
- 💊 **Health-conscious** — no harmful chemicals, natural mineral leaching
- ♻️ **Sustainable** — durable, repairable, and locally sourced
- 🎨 **Artisanal** — every piece carries a human story

The app includes an in-built education section explaining these benefits to buyers.

---

## 📝 Notes & Known Limitations

- Product images upload to Firebase Storage **only** after `google-services.json` is configured.
- The **Download Story Card** button writes the generated 1080×1920 bitmap to the app cache directory for sharing. To save to the public gallery, extend the feature using `MediaStore`.
- Firebase Firestore **offline persistence** is enabled by default for a smooth experience with intermittent connectivity.

---

## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

<div align="center">

Made with 🏺 and ❤️ for clay artisans everywhere

**[⭐ Star this repo](https://github.com/sha924/Kumbara-Kala)** · **[🐛 Report a Bug](https://github.com/sha924/Kumbara-Kala/issues)** · **[💡 Request a Feature](https://github.com/sha924/Kumbara-Kala/issues)**

</div>
