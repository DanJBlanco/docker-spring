## **Images**

#### Tag

> docker tag, docker build *-t* NAME_IMAGE *.* *-f* ./DIRECTORY_DOCKERFILE/Dockerfile
>
> `-t tag: assign name`
>
> `-f file: directory where is Dockerfile`
>
> `. directory where image will be create`

#### List

> docker images

#### Delete

> docker rmi *-f*, docker image prune
>
> `-f force`

#### Analyze

> docker image inspect ID_NAME_IMAGE

## **Component**

#### Name

> docker -run --name

#### List

> docker ps

#### Delete

> docker rm, container prune

#### Analyze

> docker container inspect ID_NAME_CONTAINER

#### Run, stop, restart

> docker run / stop / start, docker run *-p* EXTERNAL_PORT:INTERNAL_PORT_NUMBER --rm -d --name NAME_OR_ID_CONTAINER --network NAME_NETWORK ID_NAME_IMAGE
>
> `-p port: assign ports`
>
> `--rm remove: delete container when stop`
>
> `-d diattach`
>
> `--name assign a name to container`
>
> `--network assign network to container`

---

## Docker Compose

> docker-compose up -d, --build, build
>
> `--build force to build and up images`
>
> `build force to build images`

> docker-compose down

## Docker HUB

###### *What is?*

Cloud repository, where we can share, store, and implements Docker's images

###### *Docker Benefits*

* We havent uninstall and reinstall local dependencies every time
* We have same eviroment ( local and production ), same version, tools and runtimes
* We are allowed to share projects with friends a teams with the same environment and configurations

###### *Share Imagens*

* Dockerfile attached with our source code
  * We need to build imagen with *docker build*
* Docker Hub's Image
  * Download images, and run the container based on it

> docker push:tag
>
> * To Share/upload to the repository, logging in is required

> docker pull image:tag
>
> * Download images

## Amazon AWS ( cloud )

> Remote deployments devices
>
> * Self-managed or manual managed
>   * VPS (Virtual Private Server), Amazon EC2 (Elastic Compute Cloud)
> * Management by the service
>   * Amazon ECS ( Elastic Container Service )

> 1. Install Docker into remote device using SSH
>
>    * ` sudo amazon-linux-extras install docker`
> 2. Docker pull from Docker hub
>
>    * `sudo curl -SL https://github.com/docker/compose/releases/download/v2.14.2/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose`
>    * `sudo chmod +x /usr/local/bin/docker-compose`
>      * Set permissions to docker compose
>    * `sudo service docker start`
>      * init dockers service
>    * `sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose`
>      * `Set docker compose global`
>    * `scp -i "spring-cloud.pem" docker-compose.yml ec2-user@ec2-3-83-206-212.compute-1.amazonaws.com:/home/ec2-user`
>      * Copy Docker-compose from my pc to aws
>    * `free`
>      * `Show available resources`
>    * `mv docker-compose-users.yml docker-compose.yml`
>      * Rename file
> 3. Run container into remote host
>
>    * `sudo docker-compose up -d`
>      * ERROR:
>        * ```Failed to start docker.service: The name org.freedesktop.PolicyKit1 was not provided by any .service files```
>      * Fixed:
>        * `sudo systemctl start docker`



## **TIPS**

> DOCKER_BUILDKIT=0 : print log to view errors
>
> `DOCKER_BUILDKIT=0  docker build -t NAME_IMAGE . -f ./DIRECTORY_DOCKERFILE/Dockerfile`

> docker run *-d* *-p* 8001:8001 NAME_OR_ID_IMAGE
>
> `-d ditach: run docker on background`

> docker *attach* *-f* NAME_OR_ID_IMAGE
>
> `attach: run docker on primary panel`
>
> `-f : view logs`

> docker logs NAME_OR_ID_IMAGE
>
> `logs : view logs`

> docker --help
>
> `Documentation`

> docker cp ./Login.java c174548a2619:/app/Login.java
>
> `Copy file into container`

> docker cp CONTAINER_ID:/app/logs ./logs
>
> `Copy logs`

> docker volume ls
>
> `ls list: list all volumens`

> docker tag udemy-docker-kuber-msvc-users djblancoh/users
>
> tag clone image and assign new name

> docker login
>
> username: djblancoh
>
> password: ********

> docker logout

## E.G:

#### Create images

> docker build -t users . -f ./msvc-users/Dockerfile

#### 

Start Mysql

> docker run -d -p 3307:3306 --name mysql8 --network spring -e MYSQL_ROOT_PASSWORD=sa123123 -e MYSQL_DATABASE=msvc_users -v data-mysql:/var/lib/mysql --restart=always mysql:8
>
> `-e enviroment: enviorement variable`
>
> `-v volumen: file to persist data`

#### Start Postgres

> docker run -d -p 5433:5432 --name postgres15 --network spring -e POSTGRES_PASSWORD=sa123123 -e POSTGRES_DB=msvc_courses -v data-postgres:/var/lib/postgresql/data --restart=always postgres:15-alpine

#### Start MSVC project

> docker run -d -p 8001:8001 --rm --name msvc-users --network spring users
>
> docker run -d -p 8002:8002 --rm --name msvc-courses --network spring courses

#### Container utilitary

> docker run -it --rm --network spring mysql:8 bash

#### Enviorment Variable

> docker run -p 8001:8090 --env PORT=8090 -d --rm --name msvc-users --network spring users
>
> docker run -p 8001:8091 --env-file ./msvc-users/.env -d --rm --name msvc-users --network spring users
>
> `--env-file file where be all variable`

#### Argument Variable

> docker build -t users . -f ./msvc-users/Dockerfile --build-arg PORT_APP=8080
>
> `--build-arg edit argumentes value`
>
> docker run -p 8001:8091 -d --rm --name msvc-users --network spring users

#### Cloud providers

> * Amazon AWS
> * Microsoft Azure
> * Google cloud plataform
> * Digital Ocean
