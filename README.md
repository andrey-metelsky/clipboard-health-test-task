# Test Assignment for the Senior Automation QA position at the Clipboard Health project 

This is test project for UI automated testing of **https://www.amazon.in/**.

This automation framework consists of:

* JAVA 1.17
* Selenium WebDriver
* TestNG
* Gradle

## How to install
Prerequisites : 
* JDK 1.8 or higher https://www.java.com/en/download/
* IDE (Eclipse, IntelliJ, etc) https://www.jetbrains.com/idea/download/
* Gradle

1. Clone project from https://github.com/andrey-metelsky/clipboard-health-test-task.git
2. Import dependencies

## How to run tests?
There are several options:
* Via Gradle command: gradle clean test
* By running testng.xml
* Browser/Platform/Environment configuration can be set in *src/test/resources/general.properties*;

## How to write new tests?
1. Create new page object java classes in the *src/main/java/com/clipboardhealth/automation/pageobject*;
2. Create new test in the *src/test/java/com/clipboardhealth/automation*;

## Report
1. TestNG report can be found in *test-output/index.html*;