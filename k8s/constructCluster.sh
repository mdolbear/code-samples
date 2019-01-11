#MySql
kubectl create -f mysql-persistent-volume.yml
kubectl create -f mysql-deployment.yml

#Blackjack service
kubectl create -f tictactoe-deployment.yml
kubectl create -f tictactoe-service.yml

#Ingress
kubectl create -f igctl-default-backend-svc.yml
kubectl create -f ingress-controller.yml
kubectl create -f ingress.yml

#Prometheus
kubectl create namespace monitoring
kubectl create -f prometheus/prom-config-map.yaml
kubectl create -f prometheus/prom-deployment.yaml

