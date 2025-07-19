# 📇 Contacts List App (Kotlin + MVVM)

A simple, modern Android application for managing a local list of contacts. This project was developed as a technical hiring task to demonstrate proficiency in Kotlin, clean architecture (MVVM), 
and core Android Jetpack libraries.

## ✨ Features

- **View Contacts:** Displays a list of all contacts, sorted alphabetically.
- **Add Contact:** A dedicated screen with a form to add new contacts.
- **Contact Details:** View the full information for a selected contact on a separate screen.
- **Search:** Filter the contact list by name in real-time.
- **Data Persistence:** All contact data is stored locally and persists between app launches using a Room database.
- **Empty State:** A helpful message is shown when the contact list is empty.

 ## 🏆 Bonus Features Implemented

- **Swipe to Delete:** Easily delete a contact by swiping the item left or right in the list.
- **Dependency Injection (Manual):** Used a `ViewModelFactory` to manually inject the `Repository` dependency into the `ViewModel`, promoting testability and separation of concerns.
- **Dark Mode Compatibility:** The app fully supports both light and dark themes by leveraging Material Design's `DayNight` theme and providing alternative color resources.
- **Test Cases:**
  - **Unit Tests:** Written for the `ContactViewModel` to verify its logic in isolation using JUnit and Mockito.
 
 ### Architecture
This project follows the **Model-View-ViewModel (MVVM)** architecture pattern, which is highly recommended by Google for building robust and maintainable apps.

- **Model:** Represents the data layer, consisting of the Room Database (`Contact`, `ContactDao`, `AppDatabase`) and the `Repository`.
- **View:** Represents the UI Layer (`MainActivity`, Fragments, and XML layouts). The View observes changes in the ViewModel and updates the UI accordingly.
- **ViewModel:** Acts as a bridge between the Model and the View. It holds and processes all the data required by the UI and is lifecycle-aware.

### Tech Stack
- **Language:** [Kotlin](https://kotlinlang.org/) (100% Kotlin code)
- **Asynchronous Programming:** [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) for handling background tasks like database operations.
- **Architecture:** MVVM (Model-View-ViewModel)
- **Jetpack Components:**
  - **Room:** For robust, local data persistence.
  - **ViewModel:** To store and manage UI-related data in a lifecycle-conscious way.
  - **LiveData:** To build data objects that notify views of any database changes.
  - **Navigation Component:** To handle all in-app navigation and pass data between screens safely.
  - **ViewBinding:** To interact with XML layouts in a type-safe manner.
- **UI:**
  - **XML Layouts:** With `ConstraintLayout` for building responsive and flexible UIs.
  - **RecyclerView:** For efficiently displaying the list of contacts.
  - **Material Design 3:** For modern UI components and full theme support.
- **Testing:**
  - **JUnit:** For standard unit testing.
  - **Mockito:** For creating mock objects to test the `ViewModel` in isolation.
  - **Espresso:** For UI and instrumentation testing.
  - **AndroidX Test:** Core libraries for both unit and instrumentation tests.
