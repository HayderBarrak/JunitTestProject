# JunitTestProject
Projet de test

Environement

version: '3.5'

services:
postgres:
container_name: postgres_container
image: postgres
environment:
POSTGRES_USER: ${POSTGRES_USER:-postgres}
POSTGRES_PASSWORD: ${POSTGRES_PASSWORD:-hayder}
PGDATA: /data/postgres
volumes:
- postgres:/data/postgres
ports:
- "5432:5432"
networks:
- postgres
restart: unless-stopped

pgadmin:
container_name: pgadmin_container
image: dpage/pgadmin4
environment:
PGADMIN_DEFAULT_EMAIL: ${PGADMIN_DEFAULT_EMAIL:-pgadmin4@pgadmin.org}
PGADMIN_DEFAULT_PASSWORD: ${PGADMIN_DEFAULT_PASSWORD:-hayder}
PGADMIN_CONFIG_SERVER_MODE: 'False'
volumes:
- pgadmin:/var/lib/pgadmin

    ports:
      - "${PGADMIN_PORT:-5050}:80"
    networks:
      - postgres
    restart: unless-stopped

networks:
postgres:
driver: bridge

volumes:
postgres:
pgadmin:
