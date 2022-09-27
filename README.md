# gitops-examples

Complete examples using CRD, Charts (TODO) and Kustomize (TODO).

This repository assumes you're using Argo CD.

All examples using Quarkus/MicroProfile applications

Colocar aqui uma tabela com a estrutura geral do repositório

Separação dos ambientes por pasta (dev/qa/prod)

Como fazer configurar ambiente no OpenShift

Como configurar ambiente no OpenShift

health check tambem

# Prerequisites

The examples contained in this section require,

* the [oc](https://access.redhat.com/downloads/content/290) OpenShift client command-line tool
* a [kubeconfig](https://kubernetes.io/docs/tasks/access-application-cluster/configure-access-multiple-clusters/) file for an existing OpenShift cluster (default location is `~/.kube/config`)
* the [argocd](https://github.com/argoproj/argo-cd/releases/latest) command-line tool

# Installing ArgoCD

1. ```oc login --token=<TOKEN> --server=<SERVER>```
1. ```oc apply -f env/gitops-operator.yaml```
```oc apply -f env/cluster-role.yaml```
```oc apply -f env/cluster-role-binding.yaml```
ArgoCD route
1. ```oc get route openshift-gitops-server -n openshift-gitops -o jsonpath='{.spec.host}'```
extract admin password
1. ```oc -n openshift-gitops extract secret/openshift-gitops-cluster --to=-```
create application in ArgoCD
```oc create -f env/argocd-app-plain-yaml.yaml```
