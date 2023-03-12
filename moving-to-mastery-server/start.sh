#!/bin/sh

sudo kill $(sudo lsof -t -i:8080)

docker-compose down --rmi all --volumes --remove-orphans

docker-compose up