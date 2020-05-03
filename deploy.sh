#!/bin/bash
docker build -t fabricio20/mazgo-api:latest .
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push fabricio20/mazgo-api:latest
