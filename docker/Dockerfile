FROM lpicanco/java11-alpine
MAINTAINER Alexander Kirillov <alexander.kirillov.work@gmail.com>
WORKDIR /target
ADD product-service-0.0.1-SNAPSHOT.jar product-service.jar
EXPOSE 4080
VOLUME /tmp:/tmp
CMD ["java","-jar","product-service.jar"]
