# Superhero-Search-App
This application is built on Jetpack Compose - Kotlin and uses Superhero API to search and explore new superheroes.

# Features
1. Search superheroes. The API calls are debounced to optimize the app.
2. Save the viewed result in room db to be shown on home page.
3. Network calls are cached so if identical API calls are made within the same time frame, the cached response is shown to user. Also, allows to give a good offline experience to the user.
4. App is built completely using Jetpack compose.
5. Dependency injection implemented using Koin to prevent memory leaks and excess memory usage.
