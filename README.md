Take-Home App
==================

# Development Environment

**Take-Home** uses the Gradle build system and can be imported directly into Android Studio (make sure you are using the latest stable version available [here](https://developer.android.com/studio)). 

Change the run configuration to `app`.

# Architecture

The **Take-Home** app follows the
[official architecture guidance](https://developer.android.com/topic/architecture) 

# Modularization

The **Take-Home** app has been fully modularized and you can find the detailed guidance and
description of the modularization strategy used in
[modularization learning journey](docs/ModularizationLearningJourney.md).

# Build

The app contains the usual `debug` and `release` build variants. 
**Take-Home**.

The app also uses
[product flavors](https://developer.android.com/studio/build/build-variants#product-flavors) to
control where content for the app should be loaded from.

The `dev` flavor uses static local data to allow immediate building and exploring of the UI.

The `prod` flavor makes real network calls to a backend server, providing up-to-date content. At 
this time, there is not a public backend available.

For normal development use the `demoDebug` variant.

# UI
The app was designed using [Material 3 guidelines](https://m3.material.io/). Learn more about the design process and 
obtain the design files in the [Take-Home Material 3 Case Study](https://goo.gle/nia-figma) (design assets [also available as a PDF](docs/Now-In-Android-Design-File.pdf)).

The Screens and UI elements are built entirely using [Jetpack Compose](https://developer.android.com/jetpack/compose). 

The app supports dark mode.

> [!NOTE]
## Handling Unauthorized Github API Calls

If you encounter an unauthorized error when calling the GitHub API, follow these steps to generate a new token and update your project:

1. Access the GitHub Personal Access Tokens page: [Generate New Token](https://github.com/settings/personal-access-tokens)
2. Select Fine-grained tokens.
3. Generate a new token with the necessary permissions.
4. Replace the value of `GITHUB_TOKEN` in the `secrets.defaults.properties` file with the newly generated token.
5. Rebuild the project to apply the changes.