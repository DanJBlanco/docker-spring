## Minikube

> minikube status

> minikube stop

> minikube start --driver=docker

> minikube service msvc-users --url
>
> `generate url to service `



## Kubectl

### Deployments

> kubectl help ( -h )

> kubectl
>
> * create
> * expose
> * delete
> * rollout
> * scale
> * describe
> * logs
> * apply

> kubectl get
>
> `we can get list of deployments`

> `kubectl create deployment mysql8 --image=mysql:8 --port=3306 --dry-run=client -o yaml > deploymeny-mysql.yaml`
>
> create deployment file
>
> * dry-run: no execute deployment, just crate file

> kubectl apply -f deploymeny-mysql.yaml
>
> `create a deployment by a file`

> kubectl set image deployment msvc-users users=djblancoh/users:latest
>
> `Update deployments image`

> kubectl scale deployment msvc-users --replicas=3
>
> `Scale the numbers of pods instance`

> `kubectl delete -f ./deployment-users.yaml`
>
> delete deployment in declarative
>
> * -f file with associate name to deployment

> `kubectl apply -f ./deployment-users.yaml`
>
> Run a deployment, if it not exist  then create deployment
>
> * -f file

### Services

> kubectl expose deployment mysql8 --port=3306 --type=ClusterIP
>
> `--type:`
>
> * `ClusterIP: Comunication with localhost between pods and service`
> * `NodePort`
> * `LoadBalance: expose service for external AND internal use`

> `kubectl get service mysql8 -o yaml > svc-mysql.yaml`
>
> create service file

### Cluster

> `kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default`
>
> create cluster to connect spring cloud kubernetes with kubernetes

## E.G.:

> kubectl create deployment mysql8 --image=mysql:8 --port=3306

> kubectl describe pods mysql8-5f67dff86b-mzt4n

> kubectl logs mysql8-5f67dff86b-mzt4n

> kubectl delete deployment mysql8

> kubectl expose deployment mysql8 --port=3306 --type=ClusterIP
>
> `Create service`

> kubectl get services
>
> kubectl get svc

> kubectl describe service mysql8

> kubectl get all

> kubectl exec -it msvc-users-598dd6469d-dc2t2 bash

> `kubectl delete -f ./deployment-mysql.yaml`
>
> delete pods
