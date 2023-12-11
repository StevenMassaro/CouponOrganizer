FROM alpine:3.19
RUN apk add --no-cache --update openjdk17-jre-headless
EXPOSE 8080
ADD /target/CouponOrganizer.jar CouponOrganizer.jar
ENTRYPOINT ["java","-jar","CouponOrganizer.jar"]
