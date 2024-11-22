# Zando - Test Automation Framework 

# Overview

The Zando project is a Selenium-based automation framework developed in Kotlin, using Gradle for project/dependency management and TestNG for test execution is designed to validate the end-to-end testing scenarios of an e-commerce platform.<div style="text-align: justify"> This framework is created with Selenium WebDriver and Kotlin to simulate user actions, interact with the UI, and validate various functionalities through E2E testing.</div>
&nbsp;
# Why This Framework? 
<div style="text-align: justify">
I opted for Selenium WebDriver due to its robustness and industry-wide usage. Given its ability to handle complex web interactions across multiple browsers, I felt it was the best choice for creating an E2E framework.
</div>
&nbsp;
<div style="text-align: justify">
I choose Kotlin as my prefered language because of its concise syntax and expressive features, making the code both easier to maintain and less error-prone compared to other OOP languages. Kotlin’s null safety and modern features align well with the needs of a scalable testing framework.
</div>
&nbsp;
<div style="text-align: justify">
I decided to use Gradle because it provides powerful build automation capabilities and offers great flexibility in configuring project dependencies and managing different environments.This allowed me to easily manage third-party libraries (like Selenium and TestNG) and also automate the build and test processes.
</div>
&nbsp;
<div style="text-align: justify">
The use of Page Object Model (POM) was an obvious choice to ensure that the test scripts are modular, maintainable, and easier to update. It reduces code duplication and provides a clear separation of concerns, allowing me to focus on adding new features and test scenarios without affecting existing code.
</div>
&nbsp;

# Framework features

* **Page Object Model (POM):** Encapsulates elements and actions within different pages of the e-commerce platform (e.g., Search Page, Shopping Cart Page).
* **Error Handling & Robustness:** Includes error handling for common exceptions such as `ElementNotInteractableException`, `StaleElementReferenceException` and `WebDriverException`.
* **Multi-Platform:** Created to be prepared to be used for multiple browser.
* **Synchronization:** Implement timing and synchronization to dynamic handle portal elements.
* **Parallel-testing ready:** The framework was built to be executed in paralell and in any CI/CD platform.

# Project Setup

## Page Objects
The pages directory contains all the page object classes, which define actions and elements for specific pages (e.g., SearchPage, ShoppingCartPage).

## Tests
The tests directory holds the TestNG test classes, which contain the actual test scenarios.

## Base
The base directory contains helper classes for WebDriver initialization, element interactions, and common utilities.

## Features
The feature directory hold the the files cotaining the feature description, scenarios and steps for testing the portal.

## Prerequisites

* JDK 11 or above

* Gradle installed(or use the Gradle wrapper provided on the repository)

* Any IDE of your choice but I will recommend one that supports Kotlin and Gradle, such as IntelliJ IDEA

## Getting started

- Clone the Repository 

  `git clone https://github.com/abra1193/Zando.git`

- Build Gradle project

  `./gradlew clean build`
  
- Run tests

   `./gradlew clean test`

- Generate reports(optional)

   `./gradlew allureReport allureServe`
  
## Test Design
<div style="text-align: justify">
The tests in this framework are organized into page objects and test classes. The Page Object Model (POM) pattern is used to abstract the web page interactions into separate objects, making the code easier to maintain and scale as the application evolves.</div>

# CI/CD Integration
[![Zando workflow](https://github.com/abra1193/Zando/actions/workflows/gradle.yml/badge.svg)](https://github.com/abra1193/Zando/actions/workflows/gradle.yml)

This framework is integrated with GitHub Actions to run tests automatically on every pull request change or commit to master. The configuration for running the tests is defined in `.github/workflows/gradle.yml`. This enables seamless continuous integration, ensuring that tests are run on every change.

## Steps to execute Zando workflow

- Click on **Actions** button on the Github top menu

- Click on **Zando workflow** button in the **Actions** left pane
- Click on the right pane on **Run workflow** button
- Select your **branch or master** and select the browser you want to execute the test
- Click on **Run workflow**
- Now you will see the **Zando workflow** in the queue executing or waiting to be executed, if you tap on it you can see the console ouput logs.
- After this reports will be available(chrome for the moment) on my personal page https://abra1193.github.io/Zando 





