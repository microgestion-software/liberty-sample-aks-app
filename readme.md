# Transición de IBM WebSphere Traditional a Liberty en AKS
Aplicación MicroProfile utilizada durante la demostración del proceso de despliegue de una aplicación basada en Open Liberty en un cluster de Azure Kubernetes Service (AKS) utilizando Open Liberty Operator y Azure DevOps Pipelines.

#### Ejecución de la prueba en el modo desarrollador

    mvn liberty:dev

Para probar la aplicación, abrir la siguiente URL en el navegador: [http://localhost:9080/liberty-sample-aks-app](http://localhost:9080/liberty-sample-aks-app/).

    

## Despliegue en Minikube

### Instalación del operador de Open Liberty

#### Instalación de certmanager

    kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.15.0/cert-manager.yaml

#### Instalación de los Custom Resources Definitions (CRDs)

    kubectl apply --server-side -f https://raw.githubusercontent.com/OpenLiberty/open-liberty-operator/main/deploy/releases/1.3.2/kubectl/openliberty-app-crd.yaml

#### Instalación Role-Based Access (RBAC)

    OPERATOR_NAMESPACE=default
    WATCH_NAMESPACE='""'

    curl -L https://raw.githubusercontent.com/OpenLiberty/open-liberty-operator/main/deploy/releases/1.2.1/kubectl/openliberty-app-rbac-watch-all.yaml \
    | sed -e "s/OPEN_LIBERTY_OPERATOR_NAMESPACE/${OPERATOR_NAMESPACE}/" \
    | kubectl apply -f -

#### Instalación del operador de Open Liberty

    curl -L https://raw.githubusercontent.com/OpenLiberty/open-liberty-operator/main/deploy/releases/1.3.2/kubectl/openliberty-app-rbac-watch-all.yaml \
    | sed -e "s/OPEN_LIBERTY_OPERATOR_NAMESPACE/${OPERATOR_NAMESPACE}/" \
    | kubectl apply -f -

### Empaquetamiento de la aplicación (.war)

    mvn clean package

### Construcción de la imagen

#### Utilizar el demonio Docker de Minikube.

    eval $(minikube -p minikube docker-env)

#### Construcción de la imagen.

    docker build -t liberty-sample-aks-app:1.0-SNAPSHOT . --file=./deployment/minikube-Dockerfile-java17

### Despliegue en el cluster

    kubectl apply -f ./deployment/minikube-deployment.yaml

### Prueba de la aplicación

#### Exponer la aplicación
    kubectl port-forward svc/liberty-sample-aks-app 9443

#### Acceso

Abrir la siguiente URL en el navegador: [http://localhost:90443/liberty-sample-aks-app](http://localhost:9443/liberty-sample-aks-app/).