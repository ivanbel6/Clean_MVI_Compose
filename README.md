# Android Application (Work in Progress)

[Русская версия README](README.ru.md)

A modern Android application focused on scalability, code readability, and long-term maintainability.  
The project is currently under active development and serves as a practical implementation of modern Android development approaches.

---

## 🧱 Architecture

The project follows **Clean Architecture** principles and is implemented as a **multi-module Android application**, which allows:
- clear separation of responsibilities
- easier testing
- improved scalability and maintainability

The chosen architectural pattern is **MVI**, providing:
- explicit screen state management
- predictable UI behavior
- simplified debugging and testing

---

## 🎨 UI

- **Jetpack Compose** (declarative UI)
- **Material Design 3**
- **Navigation Component**
- Animations and transitions implemented directly in Compose

The UI layer is built around a unidirectional data flow and reacts solely to state changes.

---

## ⚙️ Asynchrony & State

- **Kotlin Coroutines**
- **Flow / StateFlow / SharedFlow**

Special attention is paid to thread safety:
- `Mutex` is used where required
- atomic operations are applied when necessary

---

## 🔌 Dependency Injection

- **Dagger 2 / Hilt**

Used to reduce coupling between components and simplify testing and future scaling.

---

## 🌐 Networking

- Client-server architecture
- **REST API**
- **Retrofit**

---

## 💾 Data Storage

- **Room (SQLite)**
- **Offline-first approach** with caching
- **DataStore** for lightweight user preferences  
  (as a modern alternative to SharedPreferences)

The application remains functional under unstable or missing network conditions.

---

## ♻️ Lifecycle

Proper integration with:
- Android Lifecycle
- Architecture Components

This reduces the risk of memory leaks and lifecycle-related bugs.

---

## 🧪 Testing

- Unit tests for:
  - ViewModels
  - business logic (UseCases)
- **JUnit**

---

## 🛠️ Tooling & Build

- **Kotlin**
- **Gradle**
- **Git / GitHub**

The project structure is prepared for CI/CD pipeline integration.

---

## 🚧 Project Status

The project is under active development.  
Architecture and functionality continue to evolve as new requirements emerge.

---

## 📌 Project Goals

- Apply modern Android development practices
- Practice architectural decision-making
- Build a clean, maintainable, and extensible codebase
