#!/bin/bash

kubectl apply -f cass-operator.yaml
kubectl apply -f storage.yaml
kubectl -n cass-operator apply -f cassandra-dc.yaml

#kubectl delete cassdcs --all-namespaces --all
