FROM alpine:3.18
RUN apk add --no-cache --update openjdk17-jre
EXPOSE 8080
ADD /target/CouponOrganizer.jar CouponOrganizer.jar
ENTRYPOINT ["java","-jar","CouponOrganizer.jar"]
