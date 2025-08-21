build:
	@docker compose build --pull --no-cache

start:
	@docker compose up -d

start-mariadb:
	@docker compose up -d mariadb

start-mysql:
	@docker compose up -d mysql

start-postgres:
	@docker compose up -d postgres

start-redis:
	@docker compose up -d redis

start-minio:
	@docker compose up -d minio

start-dev:
	@make start-mariadb && make start-redis

stop:
	@docker compose down

restart:
	@make stop && make start

pause:
	@docker unpause & docker compose pause

unpause:
	@docker compose unpause

native:
	@./mvnw clean package -DskipTests && \
		./mvnw spring-boot:build-image -DskipTests -Dpack.verbose=true -Dspring.aot.debug=true && \
		./build-copy.sh

all:
	@make build && make start
