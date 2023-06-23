FROM openjdk:19-alpine
ADD target/studentmanagment.java-1.0-SNAPSHOT.jar student-app.jar
CMD ["java","-jar","/student-app.jar"]