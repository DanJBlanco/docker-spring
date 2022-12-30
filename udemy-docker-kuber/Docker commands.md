Create image and build project ( -t assign a name or tag to image  -f folder where docker files location)
docker build -t NAME_IMAGE . -f ./DIRECTORY_DOCKERFILE/Dockerfile 

DEBUG Create image and build project ( -t assign a name or tag to image  -f folder where docker files location)
DOCKER_BUILDKIT=0  docker build -t NAME_IMAGE . -f ./DIRECTORY_DOCKERFILE/Dockerfile 

list images
docker images

delete or remove image ( -f force remove )
docker rmi -f NAME_OR_ID_IMAGE

list container ( running )
docker ps

Run images (-p custom post numbers)
docker run -p EXTERNAL_PORT:INTERNAL_PORT_NUMBER NAME_OR_ID_IMAGE

Stop container
docker stop NAME_OR_ID_container

TIPS ARM62 M1 Apple
ADD: --platform=linux/x86_64 to FROM, to indicate SO 
    e.g: FROM --platform=linux/x86_64 openjdk:17-jdk-alpine3.14 as builder


