FROM oracle/graalvm-ce:19.2.0.1 as graalvm
COPY . /home/app/conference-service-micronaut
WORKDIR /home/app/conference-service-micronaut
RUN gu install native-image
RUN native-image --no-server -cp target/conference-service-micronaut-*.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/conference-service-micronaut .
ENTRYPOINT ["./conference-service-micronaut"]
