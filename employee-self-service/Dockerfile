#Start with base image containing jre
FROM openjdk:17-alpine

#Who maintain this image
MAINTAINER Ajay

#Copy the file to the image
COPY target/application_name.jar application_name.jar

#Execute the application
ENTRYPOINT["java", "-jar", "application_name.jar"]
