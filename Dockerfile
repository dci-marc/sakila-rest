# ---- Build Stage ----
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Install Git LFS
RUN apk add --no-cache git-lfs \
    && git lfs install

# Copy project
COPY . .

# Pull LFS objects since Railway isn't pulling them
RUN --mount=type=git,repo=https://github.com/dci-marc/sakila-rest.git \
    git lfs pull

# Maven Build
RUN ./mvnw clean package -DskipTests

# ---- Runtime Stage ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Non-root User
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

ENTRYPOINT ["java","-jar","app.jar"]
