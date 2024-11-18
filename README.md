Event List App
Overview
This Android app fetches a list of events from a given API endpoint and displays them in a user-friendly manner. The events are grouped by sport, and each event shows information about the competitors, a countdown timer indicating when the event is scheduled to start, and a favorite button. Users can filter the events based on their favorite status using a toggle button at the header of each sport. The countdown timer is updated in real-time to reflect the time remaining until the event starts.

Features
Fetch Events: The app fetches a list of events from an API endpoint.
Event List: Displays the list of events in a scrollable list grouped by sport, with competitor details and countdown timers.
Favorite Filter: Users can filter events by favorite status (showing either all events or only favorites).
Real-Time Countdown: The countdown timer updates in real-time to show the accurate time remaining until the event starts.
Error Handling: Displays an appropriate message if no events are available or if there is an error fetching the data from the API.
Expandable Events: Users can collapse or expand the details of each event per sport.
Compatibility: The app is compatible with Android SDK 21 and above and works on both emulators and physical devices.
Libraries and Architecture
The app uses MVVM architecture for better separation of concerns and maintainability. Below are the main libraries used in the project:

Retrofit for API requests:

gradle
Copiar código
implementation "com.squareup.retrofit2:retrofit:2.9.0"
implementation "com.squareup.retrofit2:converter-gson:2.9.0"
Kotlin and Coroutines for asynchronous operations:

gradle
Copiar código
implementation 'androidx.core:core-ktx:1.15.0'
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
API
The app fetches data from the following API endpoint (details may vary):

API Base URL: [Your API URL Here]
Expected Response: The response should contain event data structured in a list of sports, each with details about the event, competitors, and start times.
Setup and Installation
Clone the repository:

bash
Copiar código
git clone https://github.com/yourusername/yourrepository.git
Open the project in Android Studio.

Sync Gradle: Make sure that all dependencies are correctly downloaded by syncing Gradle.

Run on Emulator or Physical Device: You can run the project on either an Android emulator or a physical device. Make sure the device or emulator is running SDK version 21 or above.

Features in Detail
Event Listing
The events are grouped by sport type.
Each event shows the competitors and a countdown timer.
A favorite button is available for each event.
Favorite Filter
A toggle button is provided at the header of each sport, which allows users to filter the events based on whether they are marked as favorites or not.
Countdown Timer
The countdown timer for each event updates in real-time, decrementing as time passes and showing the accurate time remaining until the event begins.
Expandable Events
Users can click on the header of each sport to collapse or expand the list of events under that sport.
Error Handling
If there are no events to display, a message is shown to inform the user.
In case of an error while fetching events from the API, an appropriate error message is displayed.
Development Notes
MVVM Architecture: The app follows the Model-View-ViewModel (MVVM) architecture, ensuring clear separation of UI and business logic.
Coroutines: Kotlin coroutines are used to handle API requests asynchronously, improving performance and responsiveness.
Contributing
Feel free to fork this project, create pull requests, and submit issues if you encounter any bugs or have suggestions for improvements.

This README file provides a structured overview of the app, its features, and setup instructions. It should help anyone understand the app's functionality and how to run it on their device.






