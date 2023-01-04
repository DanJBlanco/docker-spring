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

## E.G:

#### Start Mysql

> docker run -d -p 3307:3306 --name mysql8 --network spring -e MYSQL_ROOT_PASSWORD=sa123123 -e MYSQL_DATABASE=msvc_users -v data-mysql:/var/lib/mysql --restart=always mysql:8

#### Start Postgres

> docker run -d -p 5433:5432 --name postgres15 --network spring -e POSTGRES_PASSWORD=sa123123 -e POSTGRES_DB=msvc_courses -v data-postgres:/var/lib/postgresql/data --restart=always postgres:15-alpine

#### Start MSVC project

> docker run -d -p 8001:8001 --rm --name msvc-users --network spring users
>
> docker run -d -p 8002:8002 --rm --name msvc-courses --network spring courses
