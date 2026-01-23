# CMP Meme Creator 🎨

A cross-platform mobile application built using **Compose Multiplatform (CMP)** that allows users to create, customize, and share memes. This project focuses on high-quality code architecture and shared business logic between Android and iOS.

## 🚀 Key Features
- **Shared UI:** A single codebase for both Android and iOS using Jetpack Compose.
- **Meme Editor:** Custom text overlays on a variety of meme templates.
- **MVI Architecture:** Unidirectional data flow for predictable and debuggable state management.
- **Image Export:** Functionality to render and save the final meme to the local device.
- **Resource Management:** Efficient handling of fonts and images across multiple platforms.

## 🛠 Tech Stack
- **Kotlin Multiplatform (KMP):** Core logic sharing.
- **Compose Multiplatform:** Declarative UI for mobile.
- **Koin:** Dependency injection for a decoupled and testable codebase.
- **MVI (Model-View-Intent):** Clean separation of UI, state, and user actions.
- **Kotlin Coroutines & Flow:** Asynchronous programming and reactive state updates.

## 🏗 Architecture
This project follows **Clean Code** principles and the **MVI** pattern:
1. **Model (State):** Immutable data classes representing the UI state.
2. **View:** Composable functions that observe state and emit user intents.
3. **Intent (Action):** Explicit user actions handled by the ViewModels to update the state.

## 📂 Project Structure
- : Contains 90%+ of the shared application logic, UI, and resources.
- : Android-specific platform code.
- : The thin native wrapper for the iOS deployment.
