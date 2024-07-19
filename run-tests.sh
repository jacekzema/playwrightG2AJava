#!/bin/bash
# run-tests.sh
mvn test -DBROWSER=chrome -DHEADLESS=true -DSEARCH_TEXT="$1"
# Exit the script and keep the container running for allure to start
exit 0