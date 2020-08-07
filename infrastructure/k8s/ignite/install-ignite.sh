#!/bin/bash

kubectl apply -f ignite-namespace.yaml
kubectl apply -f ignite-service-account.yaml
kubectl apply -f ignite-account-role.yaml
kubectl apply -f ignite-role-binding.yaml
kubectl apply -f ignite-service.yaml
kubectl apply -f ignite-config.yaml
kubectl apply -f ignite-deployment.yaml