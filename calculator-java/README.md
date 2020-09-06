# Calculator Lib and Web App in Sprint Boot

* arithmetic expression lexer, parser and interpreter in Java


## Setup and Run

```bash
./mvnw install
./mvnw spring-boot:run -pl application

# (1 + 2) * 3
open http://localhost:8080/calc?text=%281%20%2B%202%29%20%2A%203
# should be 9.0
```


## Coverage Report

```bash
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test
mvn org.jacoco:jacoco-maven-plugin:report

open library/target/site/jacoco/index.html
open application/target/site/jacoco/index.html
```


## Reference

* Creating a Multi Module Project
  - https://spring.io/guides/gs/multi-module/
