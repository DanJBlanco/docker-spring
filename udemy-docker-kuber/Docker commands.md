Create image and build project ( -t assign a name or tag to image  -f folder where docker files location)
docker build -t NAME_IMAGE . -f ./DIRECTORY_DOCKERFILE/Dockerfile 

list images
docker images

delete or remove image ( -f force remove )
docker rmi -f NAME_OR_ID_IMAGE

list container
docker ps(?)

Run images
docker run NAME_OR_ID_IMAGE


