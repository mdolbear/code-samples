# code-samples
This repository is used for code samples that I develop for educational purposes. There are some things, like the stuff under
the hacker x project, which were done quick and dirty in the form that hacker x expects.
Other parts of the project would give one a clearer view of my coding style, etc. These are problems that I have enjoyed solving in the 
areas of data structures, general java, and a rest service that runs in Spring Boot.

To successfully run the tests, you need to have docker installed. The tic-tac-toe web service tess use
test containers. You don't have to install mysql on your box. An image will be pulled down at test time.

Build :

mvn clean install -p build-docker-image

The above creates a docker image as well.



#Kubernetes setup:

Create nginx artifacts:

```kubectl create -f code/code-samples/k8s/igctl-default-backend-svc.yml```

```kubectl create -f code/code-samples/k8s/ingress-controller.yml```

Create k8s artifacts for mysql:

cd ~/code-samples/k8s

```kubectl create -f code/code-samples/k8s/mysql-persistent-volume.yml```

```kubectl create -f code/code-samples/k8s/mysql-deployment.yml```

Now build the database (I don't have an automated way to do this yet):

To get to mysql client:
kubectl get pods -- for the it command below
kubectl exec -it mysql-587f56f69-244sx  -- /bin/bash

Once in the container, run:
mysql mysql -u root -p

Pw: guswhana

In mysql,

create database demoprojectsdb;

use demoprojectsdb;

show tables; -- to make sure there are no tables

Nothing should be present

Now the k8s for the tic-tac-toe service:

```kubectl create -f code/code-samples/k8s/tictactoe-deployment.yml```

'''kubectl create -f code/code-samples/k8s/tictactoe-service.yml```


Now to create the k8s for the ingress controller:

```kubectl create -f code/code-samples/k8s/ingress.yml```


At this point on the mac, you will need to add an entry in your /etc/hosts file like so:

127.0.0.1       mytictactoe.localhost


Now try to play a game:

1) First get the ingress for the tic tact toe service:

Macintosh:k8s customer$ kubectl get ingress
NAME                HOSTS                   ADDRESS     PORTS     AGE
tictactoe-ingress   mytictactoe.localhost   localhost   80        17m
Macintosh:k8s customer$ 


```curl -X POST http://mytictactoe.localhost/tictactoe/creategame```

```curl http://mytictactoe.localhost/tictactoe/allgames```

```curl -d "game_id=1" -X POST http://mytictactoe.localhost/tictactoe/startgame```

```curl -d "game_id=1&marker=X&pindex=0" -X PUT http://mytictactoe.localhost/tictactoe/chooseplayer```

```curl -d "game_id=1&marker=O&pindex=1" -X PUT http://mytictactoe.localhost/tictactoe/chooseplayer```

```curl http://mytictactoe.localhost/tictactoe/state?game_id=1```

```curl -d "game_id=1&pindex=0&row=0&column=0" -X PUT http://mytictactoe.localhost/tictactoe/move```

```curl -d "game_id=1&pindex=1&row=1&column=0" -X PUT http://mytictactoe.localhost/tictactoe/move```

```curl http://mytictactoe.localhost/tictactoe/state?game_id=1```

```curl -d "game_id=1&pindex=0&row=1&column=1" -X PUT http://mytictactoe.localhost/tictactoe/move```

```curl -d "game_id=1&pindex=1&row=2&column=0" -X PUT http://mytictactoe.localhost/tictactoe/move```

```curl http://mytictactoe.localhost/tictactoe/state?game_id=1```

```curl -d "game_id=1&pindex=0&row=2&column=2" -X PUT http://mytictactoe.localhost/tictactoe/move```

```curl -d "game_id=1&pindex=1&row=2&column=1" -X PUT http://mytictactoe.localhost/tictactoe/move```

```curl http://mytictactoe.localhost/tictactoe/state?game_id=1```


#To set up Prometheus monitoring:

1) Set up both the pom dependencies for micrometer and prometheus (pom.xml), as well as the properties in the
applcation.yml. 

2) Once this has been completed, deploy the application to the K8s cluster and double-check that metrics are 
appearing with the following via the ingress:

curl http://localhost:portNumber/actuator/prometheus

3) Create and deploy prometheus inside of the k8s cluster:

kubectl create namespace monitoring
kubectl create -f code/code-samples/k8s/prometheus/prom-config-map.yaml -n monitoring
kubectl create -f code/code-samples/k8s/prometheus/prom-deployment.yaml -n monitoring

4) Start and access the ui via a port forward:
kubectl get pods -n namespace

NAME                                     READY     STATUS    RESTARTS   AGE
prometheus-deployment-7c8fdf9b89-wsbx5   1/1       Running   0          6m

$ kubectl port-forward prometheus-deployment-7c8fdf9b89-wsbx5 9090:9090 -n monitoring

Now log into http://localhost:9090
If you go to Status->Service Discovery
and click on kubernetes-service-endpoints
you should see target labels for the blackjack pod

Now go to Status-> Targets you should see an endpoint in the state UP.

If you now go to the graph page, you should be able to pick some jvm* metrics and graph them.