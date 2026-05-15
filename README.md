# Kumbara-Kala

Kumbara-Kala is a Jetpack Compose Android app for clay artisans to upload products, tell product stories, generate shareable story cards, and educate buyers about clay health and eco benefits.

## Tech Stack

- Kotlin
- Jetpack Compose and Material 3
- MVVM with ViewModel, StateFlow, Coroutines
- Navigation Compose
- Firebase Auth, Firestore, Storage
- Coil image loading
- Canvas bitmap generation for story cards

## Firebase Setup

1. Create a Firebase project.
2. Add an Android app with package name `com.kumbarakala.app`.
3. Download `google-services.json`.
4. Place it at `app/google-services.json`.
5. Enable Firebase Authentication with Email/Password.
6. Create Firestore collections: `products`, `users`, `favorites`, `artisans`.
7. Create Storage folders: `product_images/`, `artisan_images/`, `story_cards/`.

The Gradle build applies the Google Services plugin only when `app/google-services.json` exists, so the demo UI can compile before Firebase is connected.

## Run Instructions

1. Open this folder in Android Studio.
2. Let Gradle sync.
3. Add `app/google-services.json` for real Firebase features.
4. Run the `app` configuration on an emulator or Android device.
5. Tap `Continue Demo` to test the app with sample products.

## Features Implemented

- Splash, login/register, home dashboard, product catalog, product details, story card generator, artisan profile, upload product screen.
- Search, category filtering, favorite toggles, dummy sample data fallback.
- Story card generation as a 1080 x 1920 bitmap using Android Canvas.
- WhatsApp share intent with fallback to Android share sheet.
- Firebase Firestore/Storage repository methods and offline persistence setting.
- Earthy Material 3 theme with dark mode support.

## Notes

- Product images upload to Firebase Storage only after Firebase is configured.
- The `Download Story Card` button writes the generated card to app cache for sharing. For a public gallery download, extend it to use MediaStore.
