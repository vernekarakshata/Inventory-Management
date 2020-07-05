FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_BUILDER
WORKDIR /app-dir/
COPY ["src", "pom.xml", "/app-dir/"]
RUN mvn clean install -DskipTests


FROM tomcat:8.0.20-jre8
COPY --from=MAVEN_TOOL_BUILDER /app-dir/target/InventoryManagement.war /usr/local/tomcat/webapps/
