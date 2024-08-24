<h1 align="center">FilmApp</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=26"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">  
FilmApp is a demo application based on modern Android application tech-stacks and MVVM architecture.<br>
Fetching data from the network and integrating persisted data in the database via repository pattern.
</p>
</br>

<p align="center">
<img src="https://sun9-2.userapi.com/impg/k7lFP2wimxusG-QeHqnw-my85fBgjBbCk9leSQ/cy6t-K6EP_A.jpg?size=1418x1177&quality=95&sign=1febb1eb8fb39d921f364599479f4679&type=album"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 26
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- Jetpack Compose
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
  - [Koin](https://github.com/InsertKoinIO/koin) - Loading Images
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components
- Dagger Hilt - dependency injection
- Ktor - netwotk communication
  
## Architecture
FilmApp is based on MVVM architecture and a repository pattern.

![architecture](https://user-images.githubusercontent.com/24237865/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)
