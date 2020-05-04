#!/bin/bash
docker build -t cvxnx/mazgo-api:latest .
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push cvxnx/mazgo-api:latest
