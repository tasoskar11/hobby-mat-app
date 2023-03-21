# hobby-mat

## Description

Hobby Mat is a project about a platform that connects households that typically cook food everyday but would like to have access a list of their favorite meals and especially how and how often they were cooked. It is a food log, where a household is logging food activity and ingredients. It can be used to rapidly access a view and help planning for what’s to be cooked in the future.

A first MVP could be a platform where the household is able to register their food activity and view it in an efficient way. A list of features can be added like to plan for the week and produce a grocery list, calculate intake of calories, link with recipes and ingredients and share with other users.


## Development

mvp for logging the food activity. should follow guide here:
https://github.com/mongodb-developer/mongodb-springboot/blob/main/mdb-spring-boot/src/main/java/com/example/mdbspringboot/MdbSpringBootApplication.java


### How to build locally
```
mvn clean install -DskipTests
```

### Prerequisites

Tested in openjdk version 17, Apache Maven 3.3.9
```
MacBook-Pro:hobby-mat anka07$ java -version
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T17:41:47+01:00)
Maven home: /usr/local/Cellar/maven/3.3.9/libexec
Java version: 17.0.6, vendor: Eclipse Adoptium
Java home: /Users/anka07/.sdkman/candidates/java/17.0.6-tem
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.7", arch: "x86_64", family: "mac"
```


### How to start artemis-cluster

This application relies on the usage of the artemis-docker-cluster. A cluster of artemis docker containers
that should have the latest Artemis TEST cluster configuration (security settings, address settings etc)


### How to run locally

In terminal:
```
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

In IntelliJ/VSCode:

Right click on HobbyMatApplication and run.
It will fail because by default it does not have a spring.profiles.active value.
Stop the app and click edit run configuration and set active profiles to local.
Run again.

