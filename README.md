Airlines App
Architecture Overview
The application follows the MVVM (Model-View-ViewModel) architectural pattern to ensure a clean, modular, and testable codebase.
	•	Model: Represents the data layer, including data sources (remote API, local persistence) and data models (Airline data class).
	•	View: The UI layer, built with Jetpack Compose, responsible for displaying data and handling user interactions. It observes changes in the ViewModel.
	•	ViewModel: Acts as an intermediary between the View and the Model. It exposes data streams to the View, handles UI logic, and interacts with the Model to fetch and process data.
Tech Stack & Libraries
	•	Language: Kotlin
	•	Architecture: MVVM
	•	UI Toolkit: Jetpack Compose
	•	Navigation: Jetpack Navigation Component
	•	Networking: Retrofit for API calls
	•	Asynchronous Operations: Coroutines + Flow
	•	Dependency Injection: Hilt
	•	Persistence: Document-based database (Realm)
	•	Testing (Optional): Not Done
Build & Run Instructions
	1	Clone the Repository: git clone <your-repository-url>
	2	cd AirlinesExplorer
	1	Open in Android Studio: Open the project in Android Studio (Jellyfish | 2023.3.1 or newer recommended).
	3	Sync Gradle Project: Allow Android Studio to sync the Gradle project dependencies.
	4	Run on Device/Emulator: Select an Android emulator or a physical device and run the application.
Challenges & Improvements
Challenges Faced
	•	State Management with Compose: Ensuring efficient and correct state management in Jetpack Compose, especially with asynchronous data loading and error handling.
	•	Network Error Handling: Implementing robust error handling for network requests (e.g., no internet connection, server errors) and providing meaningful feedback to the user.
	•	Lazy Loading Performance: Optimizing the lazy-loading scrollable list for smooth performance with a large number of items.
	•	Mocking API: Setting up and managing a local mocked API for development and testing purposes.
Potential Improvements
	•	Offline Caching: Implement a robust offline caching mechanism using a local database (Realm) to allow users to browse airlines even without an internet connection.
	•	Dark Mode Support: Implement a dark theme for improved user experience in low-light conditions.
	•	Improved Error States: Provide more detailed and user-friendly error messages and recovery options for network failures or other issues.
	•	Image Loading Optimization: Implement a dedicated image loading library (e.g., Coil or Glide) for more efficient caching and loading of airline logos.
