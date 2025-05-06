# Bosta Task

This project is part of the **Bosta Task**, designed to demonstrate the implementation of key Android development features using modern libraries and practices. The main functionality includes a custom spinner to display cities and districts, the ability to reveal uncovered districts, and search functionality for filtering the data dynamically. 

The app follows a **clean architecture** using **MVVM** (Model-View-ViewModel), with state management handled by **StateFlow** and **ViewModel**. Networking is done through **Retrofit**, and background tasks are managed with **Kotlin Coroutines**. Unit testing is integrated to ensure stability, utilizing **Turbine**, **MockK**, and **JUnit** for testing asynchronous operations and business logic.

## Screenshots
<table style="width: 100%; border-collapse: collapse;"><tbody><tr><th style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;">search</th><th style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;">spinner</th><th style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;">uncovered</th></tr><tr><td style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="search" src="https://github.com/user-attachments/assets/e05d7782-1f9f-4da1-aac8-5b9fc010df32"></td><td style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="spinner" src="https://github.com/user-attachments/assets/9c5dd6b3-4c01-4e72-ab55-be4c62ad3372"></td><td style="width: 33.333333333333336%; text-align: center; border: 1px solid #ccc; padding: 8px;"><img style="max-width: 100%; height: auto;" alt="uncovered" src="https://github.com/user-attachments/assets/4d77df96-1220-489d-b62e-47b3436eaa90"></td></tr></tbody></table>


### Technologies Used
- **Kotlin**: The primary programming language used in this project.
- **Dagger 2**: Dependency injection for cleaner, maintainable code.
- **Retrofit**: For making network requests to fetch cities and districts data.
- **Coroutines**: To handle background tasks asynchronously.
- **MVVM Architecture**: To separate concerns and make the app more modular and testable.
- **RecyclerView**: For displaying lists of cities and districts efficiently.
- **StateFlow & ViewModel**: For managing UI state in a lifecycle-aware manner.
- **XML**: For designing the UI layouts.
- **Unit Testing**: Testing libraries like Turbine, MockK, and JUnit are used for testing.

---
## Installation

To set up the project on your local machine:

1. Clone the repository:
    ```bash
    git clone https://github.com/mohamedebrahem13/Bosta-Task.git
    ```

2. Open the project in Android Studio.

3. Make sure to sync the project with Gradle files by clicking "Sync Now" in the bar that appears at the top of Android Studio.

4. Run the app on an emulator or physical device.

## Testing

- The project uses **unit tests** to verify the logic, especially with asynchronous tasks like network calls and flow emissions.
- **Turbine** is used to test Kotlin Flows.
- **MockK** is used to mock dependencies for testing.
- **JUnit** is used for assertions.
