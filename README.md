# SecurityDemo
Docker compose file to create postgresql with pgadmin for easy access.. add redis later

version: '3.5'

services: 
  database:
    container_name: pg_database
    image: postgres:13-alpine
    volumes:
      - postgres-data:/var/lib/postgresql/data
    ports: 
      - 5432:5432
    networks:
      - pgNetwork
    restart: unless-stopped
    env_file:
      - ./db_env.env

  pgadmin:
    container_name: pgadmin
    image: dpage/pgadmin4
    ports:
      - 5050:80
    volumes:
      - pgadmin-data:/var/lib/pgadmin
    networks:
      - pgNetwork
    depends_on:
      - database
    restart: unless-stopped
    env_file:
      - ./pgadmin_env.env

networks:
  pgNetwork:
    driver: bridge

volumes:
  postgres-data:
  pgadmin-data:
