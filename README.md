# ChallengeKaizenGaming APP

## Overview

This Android app fetches a list of events from a given API endpoint and displays them in a user-friendly manner. The events are grouped by sport, and each event shows information about the competitors, a countdown timer indicating when the event is scheduled to start, and a favorite button. Users can filter the events based on their favorite status using a toggle button at the header of each sport. The countdown timer is updated in real-time to reflect the time remaining until the event starts.

## Features

- **Fetch Events**: The app fetches a list of events from an API endpoint.
- **Event List**: Displays the list of events in a scrollable list grouped by sport, with competitors details and countdown timers.
- **Favorite Filter**: Users can filter events by favorite status (showing either all events or only favorites).
- **Real-Time Countdown**: The countdown timer updates in real-time to show the accurate time remaining until the event starts.
- **Error Handling**: Displays an appropriate message if no events are available or if there is an error fetching the data from the API.
- **Expandable Events**: Users can collapse or expand the details of each event per sport.
- **Compatibility**: The app is compatible with Android SDK 21 and above and works on both emulators and physical devices.

## Libraries and Architecture

The app uses **MVVM architecture** for better separation of concerns and maintainability. Below are the main libraries used in the project:

- **Retrofit** for API requests:
  ```gradle
  implementation "com.squareup.retrofit2:retrofit:2.9.0"
  implementation "com.squareup.retrofit2:converter-gson:2.9.0"

- **Kotlin & Coroutines** for asynchronous operations:
  ```gradle
  implementation "androidx.core:core-ktx:1.15.0"
  implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"

## Clone Repository

(https://github.com/PepeTheMeister/Sports-List-Repo.git)

















