FROM oracle/graalvm-ce:19.2.0.1 as graalvm
COPY . /home/app/person-service-micronaut
WORKDIR /home/app/person-service-micronaut
RUN gu install native-image
RUN native-image --no-server -cp target/person-service-micronaut-*.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/person-service-micronaut .
ENTRYPOINT ["./person-service-micronaut"]
