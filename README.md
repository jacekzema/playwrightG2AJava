# playwrightG2AJava

Precondition (installed):

- Java
- Maven
- Allure report

To run tests (default search text = diablo 4 pc, assasins | default browser = chrome):

- mvn test

To run tests with specific search items:

- mvn test -DSEARCH_TEXT="diablo 2,diablo 3"

To run tests with specific search items and browser (firefox / chrome / safari - chrome is default):

- mvn test -DSEARCH_TEXT="diablo 4" -DBROWSER=firefox

To run tests in headless mode (true / false - false is default):

- mvn test -DHEADLESS=true

DOCKER TIME!

