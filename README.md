#  Shopping Cart App - MVI Architecture with Clean Architecture

This project is a simple Shopping Cart Android application developed in Kotlin using Jetpack Compose. The application is designed to follow the MVI (Model-View-Intent) pattern and Clean Architecture principles. Users can add items to the cart by entering the product name, quantity, and price. The added items are displayed in a separate cart screen.

##  Project Overview

The aim of this project is to demonstrate unidirectional data flow, proper state management, and clean separation of concerns using the MVI architecture. It includes the following key components:

- **Intent**: Represents the user actions, such as loading the cart or adding an item.
- **State**: Represents the UI states, including Loading, Success, and Error.
- **ViewModel**: Receives intents and updates the state accordingly using StateFlow.

This project also follows Clean Architecture by dividing the codebase into three layers:

- **Presentation Layer**: Contains UI and ViewModels.
- **Domain Layer**: Includes use cases and domain models.
- **Data Layer**: Implements the repository logic and stores data in-memory.

##  MVI Implementation

### Intent

```kotlin
sealed class CartIntent {
    object LoadCart : CartIntent()
    data class AddItem(val item: CartItem) : CartIntent()
}
