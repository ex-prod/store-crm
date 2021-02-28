FROM openjdk:11-jdk
RUN adduser spring
USER spring:spring
RUN mkdir /home/spring/images
RUN chmod -R 755 /home/spring/images
RUN chown -R spring:spring /home/spring/images
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
