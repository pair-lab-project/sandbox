FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY gradlew build.gradle settings.gradle* ./
COPY gradle gradle
RUN ./gradlew dependencies --no-daemon
COPY src src
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
RUN useradd --system --uid 1001 appuser
COPY --from=build --chown=appuser:appuser /app/build/libs/*.jar app.jar
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
