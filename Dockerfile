FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Cambiar permisos para el archivo gradlew
RUN chmod +x gradlew

COPY src ./src

RUN ./gradlew build --no-daemon --exclude-task bootJar --exclude-task test

EXPOSE 8081

ENTRYPOINT ["./gradlew", "bootRun", "--no-daemon"]
