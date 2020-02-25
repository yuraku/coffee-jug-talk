#!/bin/bash

# Set browser for the test execution
echo "browser=${1:-chrome}" >>.env

# Detect OS in order to handle docker localhost issue
DOCKER_HOST="host.docker.internal"
UNAME=$(uname)

if [ "$UNAME" == "Darwin" ]; then
  echo "ip=$DOCKER_HOST" >>.env
  echo "MEMBER_HOST=$DOCKER_HOST" >>.env
fi

# Run docker compose to build and start test infrastructure with UI tests
docker-compose -f docker-compose.yml up --build --abort-on-container-exit

# Delete the env file
rm .env
