# VERSION 1.0.0

FROM 42.123.99.17:5002/envimages/ubuntu14.04_jdk1.8:v1.0.2
ENV file=xaplan-cadre-1.0.0.jar
ENV runEnv=dev
ENV host=localhost
ENV port=8888
ENV bind=0.0.0.0
ENV eureka=localhost:1000
ENV admin=localhost:1002
MAINTAINER magaoling
WORKDIR /opt
ADD ${file} .
CMD java -jar ./${file} --spring.profiles.active=${runEnv} --eureka.instance.hostname=${host} --server.port=${port} --server.address=${bind} --eureka.client.serviceUrl.defaultZone=http://${eureka}/eureka/ --spring.boot.admin.url=http://${admin}/
