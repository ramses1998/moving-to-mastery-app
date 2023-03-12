#!/bin/sh

echo 'Killing any running process on port 8080...' 
sudo kill $(sudo lsof -t -i:8080)

echo 'Killing any running process on port 3000...'
sudo kill $(sudo lsof -t -i:3000)

echo 'Starting Moving to mastery App...'

docker-compose down --rmi all --volumes --remove-orphans
docker-compose up
