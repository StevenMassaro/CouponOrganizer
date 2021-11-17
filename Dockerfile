FROM openjdk:13-alpine
EXPOSE 8080
ADD /target/CouponOrganizer.jar CouponOrganizer.jar
ENTRYPOINT ["java","-jar","CouponOrganizer.jar"]
