<div align="center">
  <image height= "100" width ="100" src="https://github.com/avidraghav/SpaceDawn/assets/49483235/d6ee6d64-4eda-40b2-b49f-3ff2b8e9772e" /></image><br>
  <strong>Space Dawn</strong>
</div>
<br>

[![Android-CI Actions Status](https://github.com/avidraghav/SpaceDawn/workflows/Android-CI/badge.svg)](https://github.com/avidraghav/SpaceDawn/actions)
[![GitHub license](https://img.shields.io/badge/License-Apache-blue.svg)](LICENSE)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
<br>

**Space Dawn** is a demo app built to demonstrate the use of **Mutli-Module Clean MVVM Architecture** with **Jetpack Compose** by following **Material-3** guidelines along with other MAD libraries which
lets a user **Save reminders/alarms for the upcoming Rocket Launches From around the world**.
<br>

## 🛠 Built With
|  Architecture   | Multi-Module Clean MVVM Architecture |
|----------------	|------------------------------	|
| <img height="20" src="https://3.bp.blogspot.com/-VVp3WvJvl84/X0Vu6EjYqDI/AAAAAAAAPjU/ZOMKiUlgfg8ok8DY8Hc-ocOvGdB0z86AgCLcBGAsYHQ/s1600/jetpack%2Bcompose%2Bicon_RGB.png">    UI Framework  | [Jetpack Compose](https://www.jetbrains.com/lp/compose-multiplatform/)         |                        |
| 💉 DI                | [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android)                        |             |
| 🌐 Networking        | [Retrofit](https://github.com/square/retrofit) + [Moshi](https://github.com/square/moshi)                   |
| :floppy_disk: Local Database      | [Room Database](https://developer.android.com/topic/libraries/architecture/room)                   |
| :compass: Navigation       |  [Compose Destinations Navigation](https://developer.android.com/jetpack/compose/navigation) |
| :building_construction: Persistent Background Work  | [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) + [AlarmManager](https://developer.android.com/reference/android/app/AlarmManager) |
| :thread: Asynchronous Work     |  [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)|
| 🖼️ Image Loading     |  [Coil](https://coil-kt.github.io/coil/)|
<br>

## :movie_camera: Working Demo
<table style="width:100%">
  <tr>
    <th> <video src="https://github.com/avidraghav/SpaceDawn/assets/49483235/4e320b7d-d66d-4e42-8c94-705057fb8939" /></th>
       <th><p>The reminder in the demo fires right away and this is purposeful as to demonstrante the functionality.</p></th>
  </tr>
</table>
<br>

## :iphone: Screenshots Dark Theme
<table style="width:100%">
  <tr>
    <th>Upcoming Launches Screen</th>
    <th>Reminders Screen</th> 
    <th>Notification Sample</th> 
  </tr>
  <tr>
    <td><img src = "art/upcoming_launches_a10.png" width=240/></td> 
    <td><img src = "art/reminders_a10.png" width=240/></td>
    <td><img src = "https://github.com/avidraghav/SpaceDawn/assets/49483235/a710b182-8a5b-4424-8ae4-b49fdeb2ba87" width=240/></td>
  </tr>
</table>
<br>

## 🏗️ Architecture Diagram
<div align="center">
<img src="https://github.com/avidraghav/SpaceDawn/assets/49483235/3012faf2-ad12-46c6-a74a-246376581847" width="600" height="800">
</div>
<br>

## ✔️ Tests 
Tests are up and coming 🚧

### 🚀  The app uses [Launch Library 2](https://thespacedevs.com/llapi) API for getting upcoming Rocket Launches.

## License

```
Copyright 2023 Raghav Aggarwal

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

Useful links
1. https://plugins.gradle.org/plugin/org.jlleitschuh.gradle.ktlint
2. https://sdkman.io/usage
3. https://www.kodeco.com/10562143-continuous-integration-for-android
