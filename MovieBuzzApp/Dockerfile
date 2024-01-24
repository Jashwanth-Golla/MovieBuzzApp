FROM openjdk
EXPOSE 8090
ADD target/movie-buzz-app.jar movie-buzz-app.jar
ENTRYPOINT [ "java","-jar","/movie-buzz-app.jar","foreground"]

