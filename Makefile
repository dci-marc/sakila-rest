build:
	@docker compose build --pull --no-cache

start:
	@docker compose up

stop:
	@docker compose down

restart:
	@make stop && make start

all:
	@make build && make start
