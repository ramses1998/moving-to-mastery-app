#!/bin/sh

echo 'Stopping Moving to mastery App...'

docker-compose down --rmi all --volumes --remove-orphans

