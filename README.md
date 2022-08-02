<h1 align="center">
  <a href="https://github.com/hexley21/ArithMathics">
    <!-- Please provide path to your logo here -->
    <img src="docs/images/logo.svg" alt="Logo" width="200" height="200">
  </a>
</h1>

<div align="center">
  Arithmathics
  <br />
  <strong>Improve your arithmetic skills by practicing</strong>

[**Explore the screenshots »**](#about)
  <br />
  <br />
 [Report a Bug](https://github.com/hexley21/ArithMathics/issues/new?assignees=&labels=bug&template=01_BUG_REPORT.md&title=bug%3A+)
  ·
[Request a Feature](https://github.com/hexley21/ArithMathics/issues/new?assignees=&labels=enhancement&template=02_FEATURE_REQUEST.md&title=feat%3A+)
  .
  [Ask a Question](https://github.com/hexley21/ArithMathics/issues/new?assignees=&labels=question&template=04_SUPPORT_QUESTION.md&title=support%3A+)
</div>

<div align="center">
<br />

[![Project license](https://img.shields.io/github/license/hexley21/ArithMathics.svg?style=flat-square)](LICENSE)
[![Pull Requests welcome](https://img.shields.io/badge/PRs-welcome-f7bd49.svg?style=flat-square)](https://github.com/hexley21/ArithMath/issues?q=is%3Aissue+is%3Aopen+label%3A%22help+wanted%22)

</div>

<details open="open">
<summary>Table of Contents</summary>

- [About](#about)
- [Roadmap](#roadmap)
- [Support](#support)
- [Project assistance](#project-assistance)
- [Contributing](#contributing)
- [Authors & contributors](#authors--contributors)
- [License](#license)
- [Acknowledgements](#acknowledgements)

</details>

---

## About

Arithmathics is a quiz game, where you solve
randomly generated airthmetic tasks of different difficulty
in order to improve your arithmetic skills

<!-- <details> -->
<summary>Screenshots</summary>
<br />

|                               Welcome Page                               |                               Menu Page                                   |
| :-------------------------------------------------------------------: | :--------------------------------------------------------------------: |
| <img src="docs/images/screenshot_menu.jpg" title="Menu" width="100%"> |<img src="docs/images/screenshot_welcome.jpg" title="Welcome" width="100%"> |


|                               Custom Difficulty Page                               |                               Game History Page                                   |
| :-------------------------------------------------------------------: | :--------------------------------------------------------------------: |
| <img src="docs/images/screenshot_custom.jpg" title="Custom Difficulty" width="100%"> |<img src="docs/images/screenshot_history.jpg" title="Game History" width="100%"> |

|                               Gameplay                               |                               Dark Theme                                   |
| :-------------------------------------------------------------------: | :--------------------------------------------------------------------: |
| <img src="docs/images/gameplay.gif" title="Gameplay" width="100%"> |<img src="docs/images/screenshot_dark.jpg" title="Dark Theme" width="100%"> |

</details>

<h3>Built With</h3>
<strong>Clean Architecture</strong>
<br>
This app uses MVVM pattern for UI follows clean architecture concept brought up by <strong>Robert C. Martin</strong>, but how exactly is it realised in Arithmathics?

Arithmathics is divided into three layers:
- **app** - *A layer that interacts with the UI, mainly Android Stuff. It would include both domain and data layers.*
  - **Activities**
  - **Fragments**
  - **ViewModels**
  - **Dependency Injection**
  
- **domain** - *Contains the business logic of the application. It is the individual and innermost module. It’s a complete java module.*
  
  - **Repository interface** - A place where functionality blueprint is kept, which will be implemented in data module*
  - **UseCases** - Each individual functionality, like inserting or reading data from Database, they act as a mediator between our Repository and app module
  - **Models** - Entities or a Value Object

- **data** *- It includes the domain layer. It would implement the interface exposed by domain layer and dispenses data to app*
  - **Repository implementation** - Data module keeps repository implementation and waits to be injected into use-cases
  - **Database** - Repository implementations contain database functionality

**Data Flow**
  - If a user event is triggered in UI then we communicate it with ViewModel.
  - ViewModel connects with the use case to get the result for the action.
  - The use case then interacts with the repository class to get the solution from database or preference

**MVVM**
- [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) is a software architectural pattern that facilitates the separation of the development of the graphical user interface (the view) from the development of the business logic or back-end logic (the model) so that the view is not dependent on any specific model platform.

**Components**
- Database - [Room](https://developer.android.com/jetpack/androidx/releases/room)
- Dependency Injection - [Dagger Hilt](https://github.com/google/dagger)
- Asynchronous approach - [RxJava](https://github.com/ReactiveX/RxJava)

**Credits**
 - Clean Architecture - [Medium article](https://medium.com/android-dev-hacksdetailed-guide-on-android-clean-architecture-9eab262a9011)
 - MVVM - [Wikipedia article](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)
<details>
<summary>Draw.io diagram</summary>
<img src="docs/images/Arithmath_diagram.png" title="Diagram" width="100%">
</details>

## Roadmap

See the [open issues](https://github.com/hexley21/ArithMathics/issues) for a list of proposed features (and known issues).

- [Top Feature Requests](https://github.com/hexley21/ArithMathics/issues?q=label%3Aenhancement+is%3Aopen+sort%3Areactions-%2B1-desc) (Add your votes using the 👍 reaction)
- [Top Bugs](https://github.com/hexley21/ArithMathics/issues?q=is%3Aissue+is%3Aopen+label%3Abug+sort%3Areactions-%2B1-desc) (Add your votes using the 👍 reaction)
- [Newest Bugs](https://github.com/hexley21/ArithMathics/issues?q=is%3Aopen+is%3Aissue+label%3Abug)

## Support


Reach out to the maintainer at one of the following places:

- [GitHub issues](https://github.com/hexley21/ArithMathics/issues/new?assignees=&labels=question&template=04_SUPPORT_QUESTION.md&title=support%3A+)
- Contact options listed on [this GitHub profile](https://github.com/hexley21)

## Project assistance

If you want to say **thank you** or/and support active development of Arithmathics:

- Add a [GitHub Star](https://github.com/hexley21/ArithMathics) to the project.
- Write interesting articles about the project on [Dev.to](https://dev.to/), [Medium](https://medium.com/) or your personal blog.

Together, we can make Arithmathics **better**!

## Contributing

First off, thanks for taking the time to contribute! Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make will benefit everybody else and are **greatly appreciated**.


Please read [our contribution guidelines](docs/CONTRIBUTING.md), and thank you for being involved!

## Authors & contributors

The original setup of this repository is by [Avtandil Kupreishvili](https://github.com/hexley21).

For a full list of all authors and contributors, see [the contributors page](https://github.com/hexley21/ArithMathics/contributors).

## License

This project is licensed under the **Apache Software License 2.0**.

See [LICENSE](LICENSE) for more information.

## Acknowledgements

This game would not be able to exist without this dependencies:
- [ReactiveX - RxJava](https://github.com/ReactiveX/RxJava) - A library for composing asynchronous and event-based programs
- [ReactiveX - RxAndroid](https://github.com/ReactiveX/RxAndroid) - RxJava bindings for Android
- [Google - Dagger Hilt](https://github.com/google/dagger) - Dependency injector for Android and Java
- [Android - Room](https://developer.android.com/jetpack/androidx/releases/room) - A robust SQLite database
- [Objecthunter - exp4j](https://www.objecthunter.net/exp4j/) - Math parser libray
- [Airbnb - Paris](https://github.com/airbnb/paris) - Apply styles to Android views programmatically

Arithmathics used these sources for design:
- [TBC Contractica](https://www.contractica.ge/) - Font
- [Google Material icons](https://fonts.google.com/icons) - Icons
