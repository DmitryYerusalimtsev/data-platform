# get CASS_USER and CASS_PASS variables into the current shell
CASS_USER=$(kubectl -n cass-operator get secret main-cluster-superuser -o json | jq -r '.data.username' | base64 --decode)
CASS_PASS=$(kubectl -n cass-operator get secret main-cluster-superuser -o json | jq -r '.data.password' | base64 --decode)
kubectl -n cass-operator exec -ti main-cluster-dc1-default-sts-0 -c cassandra -- sh -c "cqlsh -u '$CASS_USER' -p '$CASS_PASS'"

# final command
kubectl -n cass-operator exec -ti main-cluster-dc1-default-sts-0 -c cassandra -- sh -c "cqlsh -u 'main-cluster-superuser' -p 'LLgin_RH10TcpH9u-ac8Y3Huv0x5XXxypcxpvhQYyY1PqqTSUxK9Vg'"