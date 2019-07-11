FROM tomcat:8.0.20-jre8
COPY /target/InventoryManagement.war /usr/local/tomcat/webapps/
