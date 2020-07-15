https://spring.io/blog/2020/01/27/creating-docker-images-with-spring-boot-2-3-0-m1
 ./mvnw spring-boot:build-image
 docker run -it -p8080:8080 demo:0.0.1-SNAPSHOT
 Unfortunately M1 does not support Windows but it should work fine on a Mac or in a Linux VM. If you’re using Windows, please use 2.3.0.BUILD-SNAPSHOT for the time being. 
 
 Graceful shutdown:
 https://docs.spring.io/spring-boot/docs/2.3.x-SNAPSHOT/reference/html/spring-boot-features.html#boot-features-graceful-shutdown
 server.shutdown.grace-period=30s