# playwrightG2AJava

### Precondition (installed):

- Java
- Maven
- Allure report
- Docker

To run tests (default search text = diablo 4 pc, assasins | default browser = chrome):

- mvn test

To run tests with specific search items:

- mvn test -DSEARCH_TEXT="diablo 2,diablo 3"

To run tests with specific search items and browser (firefox / chrome / safari - chrome is default):

- mvn test -DSEARCH_TEXT="diablo 4" -DBROWSER=firefox

To run tests in headless mode (true / false - false is default):

- mvn test -DHEADLESS=true

To create allure report run:

 - allure report
 - allure open

### How to run on docker?

Headless mode in docker compose CLI run:

 - SEARCH_TEXT='assasins,diablo 4 pc' docker-compose up --build

Report will be available in:
    
- ./allure-reports/latest 

