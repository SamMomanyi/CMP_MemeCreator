![screenshow](https://github.com/user-attachments/assets/bcac9118-4406-415e-8ba1-88b5b8931e34)# 🎨 CMP Meme Creator

A cross-platform meme creation app built with **Compose Multiplatform (CMP)**, targeting both **Android** and **iOS** from a single Kotlin codebase.

---

## ✨ Features

- 🖼️ Pick any image from your device as a meme base
- ✍️ Add and position custom text overlays
- 🎨 Shared UI across Android and iOS using Jetpack Compose
- 💾 Export and save the finished meme to your device
- ⚡ Reactive state management with Kotlin Coroutines & Flow

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| UI | Compose Multiplatform |
| Language | Kotlin (KMP) |
| Architecture | MVI (Model-View-Intent) |
| Dependency Injection | Koin |
| Async | Kotlin Coroutines & Flow |
| Platforms | Android · iOS |

---

## 🏗 Architecture

This project follows **MVI (Model-View-Intent)** with clean separation of concerns:

```
User Interaction (Intent)
        ↓
   ViewModel
        ↓
  State Update (Model)
        ↓
   Composable UI (View)
```

- **State** — Immutable data classes that represent the full UI state at any point in time
- **Intent** — Sealed classes representing every possible user action
- **ViewModel** — Processes intents and emits new state via `StateFlow`
- **Composables** — Observe state and render the UI; they only emit intents, never mutate state directly

---

## 📂 Project Structure

```
CMP_MemeCreator/
├── composeApp/               # Shared KMP module (90%+ of the codebase)
│   └── src/
│       ├── commonMain/       # Shared UI, ViewModels, business logic
│       ├── androidMain/      # Android platform-specific implementations
│       └── iosMain/          # iOS platform-specific implementations
├── iosApp/                   # Thin iOS native wrapper (Swift/Xcode)
├── build.gradle.kts          # Root build config
└── settings.gradle.kts       # Module declarations
```

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or later (with KMP plugin)
- Xcode 15+ (for iOS builds)
- JDK 17+

### Clone & Run

```bash
git clone https://github.com/SamMomanyi/CMP_MemeCreator.git
cd CMP_MemeCreator
```

**Android:**

Open the project in Android Studio and run the `composeApp` configuration on an emulator or device.

**iOS:**

```bash
cd iosApp
open iosApp.xcodeproj
```

Then build and run from Xcode on a simulator or physical device.
![homescreen](https://github.com/user-attachments/assets/6ffd9097-7e8b-4487-84e3-23fc064f674f)
![screenshow](https://github.com/user-attachments/assets/c065767f-32e0-4239-932a-3febe1e0818a)
![position text](https://github.com/user-attachments/assets/7bea3b7c-edfd-48b5-89ea-82b9d67a7bcd)
![expand and contract ](https://github.com/user-attachments/assets/3ae99172-e112-4aaf-a964-d68e654c2b9c)


## 📸 Screenshots

>
