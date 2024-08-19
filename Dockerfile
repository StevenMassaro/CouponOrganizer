FROM ibm-semeru-runtimes:open-21-jre
EXPOSE 8080
ADD /target/CouponOrganizer.jar CouponOrganizer.jar
ENTRYPOINT ["java","-jar","CouponOrganizer.jar"]
