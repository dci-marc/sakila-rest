# ---- Runtime Stage ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Non-root User
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

ENTRYPOINT ["java","-jar","app.jar"]
