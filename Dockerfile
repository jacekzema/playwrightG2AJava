FROM mcr.microsoft.com/playwright/java:v1.45.0-jammy

RUN mkdir /tests
WORKDIR /tests
COPY . /tests/

RUN mvn install -DskipTests
RUN chmod +x ./run-tests.sh

