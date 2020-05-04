FROM azul/zulu-openjdk-alpine:14

LABEL maintainer="git@notfab.net"

COPY build/libs/*.jar /opt/

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "/opt/mazgo-api.jar", "--spring.config.location=/opt/"]
