# code-samples
This repository is used for code samples that I develop for educational purposes. There are some things, like the stuff under
the hacker x project, which were done quick and dirty in the form that hacker x expects.
Other parts of the project would give one a clearer view of my coding style, etc. These are problems that I have enjoyed solving in the 
areas of data structures, general java, and a rest service that runs in Spring Boot.

Build :
mvn clean install -DskipTests=true -p build-docker-image

Skipping right now because I was trying to get tc-containers to work 
(https://github.com/testcontainers/testcontainers-java/blob/master/docs/usage/database_containers.md)
but its blowing up.

The above creates a docker image as well.



#Kubernetes setup:

Create k8s artifacts for mysql:

cd ~/code-samples/k8s

```kubectl create -f mysql-persistent-volume.yml```

```kubectl create -f mysql-deployment.yml```

Now build the database (I don't have an automated way to do this yet):

To get to mysql client:
kubectl exec -it mysql-587f56f69-244sx  -- /bin/bash

Once in the container, run:
mysql mysql -u root -p

Pw: guswhana

In mysql,

create database demoprojectsdb;

use demoprojectsdb;

show tables; -- to make sure there are no tables

Create tables: use script in src/main/resources/db/migration/V1__init.sql


After running the above, the url for mongo will be mongodb://mongo:27017/db

Now the k8s for the tic-tac-toe service:

```kubectl create -f code/blackjack/src/main/resources/k8s/tictactoe-deployment.yml```

'''kubectl create -f tictactoe-service.yml```


Now try to play a game:

1) First get node port for the tictactoe service:

us17mac00258:code-samples micdol$ kubectl get services
NAME         TYPE        CLUSTER-IP    EXTERNAL-IP   PORT(S)                         AGE
kubernetes   ClusterIP   10.96.0.1     <none>        443/TCP                         21m
mysql        ClusterIP   None          <none>        3306/TCP                        11m
tictactoe    NodePort    10.105.73.6   <none>        8080:31949/TCP,4000:32217/TCP   8m
us17mac00258:code-samples micdol$ 


```curl -X POST http://localhost:31949/tictactoe/creategame```

```curl http://localhost:31949/tictactoe/allgames```

```curl -d "game_id=1" -X POST http://localhost:31949/tictactoe/startgame```

```curl -d "game_id=1&marker=X&pindex=0" -X PUT http://localhost:31949/tictactoe/chooseplayer```

```curl -d "game_id=1&marker=O&pindex=1" -X PUT http://localhost:31949/tictactoe/chooseplayer```

```curl http://localhost:31949/tictactoe/state?game_id=1```

```curl -d "game_id=1&pindex=0&row=0&column=0" -X PUT http://localhost:31949/tictactoe/move```

```curl -d "game_id=1&pindex=1&row=1&column=0" -X PUT http://localhost:31949/tictactoe/move```

```curl http://localhost:31949/tictactoe/state?game_id=1```

```curl -d "game_id=1&pindex=0&row=1&column=1" -X PUT http://localhost:31949/tictactoe/move```

```curl -d "game_id=1&pindex=1&row=2&column=0" -X PUT http://localhost:31949/tictactoe/move```

```curl http://localhost:31949/tictactoe/state?game_id=1```

```curl -d "game_id=1&pindex=0&row=2&column=2" -X PUT http://localhost:31949/tictactoe/move```

```curl -d "game_id=1&pindex=1&row=2&column=1" -X PUT http://localhost:31949/tictactoe/move```

```curl http://localhost:31949/tictactoe/state?game_id=1```
