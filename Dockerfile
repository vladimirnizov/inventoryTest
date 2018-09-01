FROM openjdk:8
ADD target/InventoryTest-0.0.1-SNAPSHOT.jar InventoryTest-0.0.1-SNAPSHOT.jar 
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "InventoryTest-0.0.1-SNAPSHOT.jar"]