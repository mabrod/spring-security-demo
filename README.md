## Travis CI Build
[![Build Status](https://https://github.com/mabrod/spring-security-demo.svg?branch=master)](https://github.com/mabrod/spring-security-demo)

## Learning Spring Security

This demo shows how to implement restricted access to some parts of web application based on Spring Security authentication
and authorization functionality. It is backed up by database (accounts) defining users, credentials and their roles with privileges.
It demonstrates how to clean up session and cookies when a user logs out.
It also shows how to cache user's login attempts and react accordingly by for example blocking user's access for a specific time period.
I have added resource messages to handle different login exception errors and also UI front end.
Simple UI part is based on Thymeleaf template engine backed by Spring MVC.

## Installation

The code is build with Gradle. Wrapper properties file to start a build with specific Gradle version is provided.
I have used Postgres database to store users credentials and their roles with privileges.
Database migration is done with Flyway framework. You need to fill out your database access params defined in gradle.properties
for Flyway to execute database migration.
By default Flyway is disbled from running during application intialization. You can migrate database scripts manually first
using Flyway gradle plugin.

## Usage

It is SpringBoot 1.5 web application. SecurityDemoRunner class is the main entry point.
1. java -jar build/libs/spring_security_demo-0.1.jar
2. gradle bootRun
3. you can run SecurityDemoRunner directly from any IDE, debug it etc
