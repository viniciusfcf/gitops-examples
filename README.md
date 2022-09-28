# gitops-examples

This repository is to share what I had learned in the last weeks about #ArgoCD and #GitOps.

Sugestions are welcome.

Complete examples using CR, Charts (TODO) and Kustomize (TODO).

All examples using Quarkus/MicroProfile application. Simple application that exposes `/hello` with a customizable property `quarkus.app.custom.message`
- Source code: `quarkus-app` folder in this repository
- Image: viniciusfcf/gitops-quarkus-app-jvm

# Prerequisites

The examples contained in this section require.

* OpenShift >= 4.10 up and running
* the [oc](https://access.redhat.com/downloads/content/290) OpenShift client command-line tool

# Content

| Folder  | Comments |
| ------------- | ------------- |
| `env`  | Files to configure OpenShift  |
| `helm`  | GitOps examples using [Helm Charts](https://helm.sh/docs/topics/charts/)  |
| `kustomize`  | GitOps examples using [Kustomize](https://kustomize.io/)  |
| `plain-yaml`  | GitOps examples using managed Custom Resources  |
| `quarkus-app`  | Quarkus application  |

Colocar aqui uma tabela com a estrutura geral do repositório

Separação dos ambientes por pasta (dev/qa/prod)

# Installing ArgoCD


Login into Openshift
```
oc login --token=<TOKEN> --server=<SERVER>
```

Install GitOps (ArgoCD) Operator
```
oc apply -f env/gitops-operator.yaml
```

Grant permissions for an Argo CD instance to manage cluster configuration.
```
oc apply -f env/cluster-role.yaml
```
```
oc apply -f env/cluster-role-binding.yaml
```

Install Serverless (KNative) Operator
```
oc apply -f env/serverless-operator.yaml
```

Configure KnativeServing
```
oc apply -f env/serverless-serving.yaml
```

Print ArgoCD route
```
oc get route openshift-gitops-server -n openshift-gitops -o jsonpath='{.spec.host}'
```

Extract ArgoCD admin password
```
oc -n openshift-gitops extract secret/openshift-gitops-cluster --to=-
```

create application in ArgoCD
```
oc create -f env/argocd-app-plain-yaml.yaml
```

Print Route
```
APP_HOST=$(oc get route my-app-name-route -n my-app-ns -o jsonpath='{.spec.host}')
```

Call endpoint
```
curl $APP_HOST/hello
```


Print Serverless Route
```
oc get ksvc my-app-serverless -n my-app-ns
```



# Pros e Cons

## plain-yaml 
* Does not update deployment if configmap is updated
* So many files, worst, `yamls` files

## kustomize
* Simplifies configuration, mainly with more than one environment to deploy
* Update deployment if configmap is updated using `configMapGenerator`
* Think twice before update `base` yamls, they affect all environments.

## Building my helm chart

* Create the folder `charts` In my repository `viniciusfcf.github.io`
* Package (Working dir: `<BASE>/gitops-examples/helm/dev`)
  ```
  helm package -d <DIR>/viniciusfcf.github.io/charts .
  ```
* Generate `index.yaml` repo file
  ```
  helm repo index <DIR>/viniciusfcf.github.io/charts
  ```
* Push `index.yaml` and `*.tgz` files to git
