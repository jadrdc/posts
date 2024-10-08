# Multi-Module Android Project
<img width="441" alt="Screenshot 2024-10-07 at 6 57 37 PM" src="https://github.com/user-attachments/assets/d17d4384-a5a3-41e3-932b-32ea99c68f16">
<img width="382" alt="Screenshot 2024-10-07 at 8 55 45 PM" src="https://github.com/user-attachments/assets/ae671acf-1440-4b82-93f1-6b439dd8c034">

This project is built using a multi-module architecture with the MVVM pattern (Model-View-ViewModel). The project is structured to improve scalability, modularity, and maintainability. Each module has a specific responsibility, and the application layers are cleanly separated. The project also uses modern libraries and technologies such as Jetpack Compose, Koin, Flow, Room, and Retrofit.

## Project Structure

The application consists of the following modules:

## 1. App
This module is the main entry point of the application and handles all startup configurations. It contains the Application class and any necessary initializations such as dependency injection, logging, or startup configuration.

Key Responsibilities:

* Main entry point (MainActivity)
* Application class for initializing the app
* App-level configurations like dependency injection setup
 
## 2. Core
This module contains reusable components and utilities that are shared across different modules. These utilities help manage common tasks such as error handling, network status monitoring, and flow observation.

Key Responsibilities:

* OperationResult: A generic class for handling success, error, and loading states.
* NetworkStatus: For monitoring network changes to handle offline modes.
* Observers for Flow events.


## 3. Domain
This module contains the core business logic. It defines the interfaces and models that the application will use, independent of any specific data source or UI. The domain layer follows the principle of dependency inversion, where it only depends on abstractions.

Key Responsibilities:

* Define interfaces for repositories, use cases, and business logic.
* Hold domain models (pure Kotlin classes representing your core entities).

## 4. Data
This module implements the data sources and repositories, which provide the data to the domain layer. It includes both remote (using Retrofit for network requests) and local (using Room for local storage) data sources.

Key Responsibilities:

* Implement the repository interfaces defined in the domain layer.
* Manage data fetching from remote (Retrofit) and local (Room) sources.
* Handle data synchronization and offline mode using caching strategies.

## 5. Presentation
This module contains the UI layer and the ViewModels. The UI is built using Jetpack Compose and follows the MVVM pattern. ViewModels manage the state and handle interaction with the domain layer. Jetpack Compose Navigation is used for managing the app's navigation flow.

Key Responsibilities:

* Compose UI components and screens.
* ViewModels that interact with the domain layer via use cases.
* Navigation between screens using Jetpack Compose Navigation.

# Libraries & Tools Used

* Jetpack Compose: For building the UI declaratively.
* Jetpack Compose Navigation: For navigation within the app.
* Koin: Dependency injection library.
* Flow: For emitting asynchronous streams of data.
* Room: Local database solution for managing offline data.
* Retrofit: For making network requests.
* Android Paging for endless scrolling and pagination.

# App Startup: 
  The App module initializes the application by setting up Koin for dependency injection and any other configurations (like logging).

# UI Layer (Presentation):
The Presentation module contains Compose UI components and screens. It communicates with the ViewModels to get the required data.
ViewModels observe data from the Domain layer and update the UI accordingly.
Jetpack Compose Navigation is used to navigate between different screens.

# Data Flow:
Remote data is fetched via Retrofit in the Data module. If the device is offline, Room (local database) provides cached data.
Data flows from the Data layer to the Domain layer, where the business logic resides.
The Presentation module then observes the data using Flow and reacts to state changes (like loading, success, or error) using the OperationResult class.

# Offline Mode:
NetworkStatus in the Core module monitors network connectivity. When offline, the app retrieves data from the Room database to ensure smooth operation.
Dependency Injection with Koin

The project uses Koin for dependency injection. Dependencies are declared in modules and injected into classes such as ViewModels, Repositories, and UseCases.


## Future Enhancements

* Implement more complex caching strategies for offline handling.
* Expand the domain logic with additional use cases.
* Add more unit tests to improve test coverage.
* Implement pluggin for graddle

This project demonstrates a scalable and maintainable approach to building Android applications with MVVM and Jetpack Compose in a multi-module setup. It ensures clear separation of concerns, allowing easy testing and reusability of modules across projects.
