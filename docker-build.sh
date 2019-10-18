#!/bin/sh
docker build . -t person-service-micronaut
echo
echo
echo "To run the docker container execute:"
echo "    $ docker run -p 8080:8080 person-service-micronaut"
