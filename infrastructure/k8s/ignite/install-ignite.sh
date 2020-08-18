#!/bin/bash

kubectl apply -f ignite-namespace.yaml
kubectl apply -f ignite-service-account.yaml
kubectl apply -f ignite-account-role.yaml
kubectl apply -f ignite-role-binding.yaml
kubectl apply -f ignite-service.yaml
kubectl apply -f ignite-config.yaml
kubectl apply -f ignite-deployment.yaml

#docker run -d -v /Users/dmytro_yerusalimtsev/Downloads/ignite_work_dir:/persistence -e IGNITE_WORK_DIR=/persistence -p 11211:11211 -p 47100:47100 -p 47500:47500 -p 49112:49112 -p 10800:10800 -p 10900:10900 apacheignite/ignite:2.8.1
