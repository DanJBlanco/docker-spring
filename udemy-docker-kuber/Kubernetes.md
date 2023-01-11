## Minikube

> minikube status

> minikube stop

> minikube start --driver=docker

## Kubectl

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

> kubectl create deployment mysql8 --image=mysql:8 --port=3306 --dry-run=client -o yaml > deploymeny-mysql.yaml
>
> `create deployment file`

> kubectl apply -f deploymeny-mysql.yaml
>
> `create a deployment by a file`


## E.G.:

> kubectl create deployment mysql8 --image=mysql:8 --port=3306

> kubectl describe pods mysql8-5f67dff86b-mzt4n

> kubectl logs mysql8-5f67dff86b-mzt4n

> kubectl delete deployment mysql8
