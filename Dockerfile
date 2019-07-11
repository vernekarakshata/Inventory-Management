FROM tomcat:8.0.20-jre8
COPY /ArtifactoryWarDeploy/com/mindtree/inventory/InventoryManagement/0.0.1-SNAPSHOT/InventoryManagement-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/
