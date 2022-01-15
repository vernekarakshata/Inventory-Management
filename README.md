# Inventory Management
This is a sample spring boot based project with webapp based on Inventory Management. Here we can add items in cart and delete. 

## Execution


### Docker
Inventory Management is very easy to install and deploy in a Docker container.

The Dockerfile will do mvn install and then copy the war file to tomcat. 

If seperate build image is required then this project can be build by using Docker command 
```sh
docker build -t <youruser>/<yourrepository>:<image-name>.
```

By default, when we run docker-compose command, it will build the image and start the server on 8080 port and will also start mysql server as required by codebase. 

To run docker compose command: 

```sh
docker-compose up
```
Verify the deployment by navigating to your server address in
your preferred browser.

```sh
127.0.0.1:8080
```


### Jenkins CI-CD
Inventory Management can also be executed via Jenkins using CI-CD pipeline.
In this, the testing is done using repo [Inventory-Management-Selenium-Framework](https://github.com/vernekarakshata/Inventory-Management-Selenium-Framework) and shared library for Jenkinsfile is used from repo [Shared Library](https://github.com/vernekarakshata/shared-library)
