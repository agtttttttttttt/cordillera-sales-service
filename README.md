# Cordillera Sales Service - Backend

Solución empresarial distribuida basada en microservicios para la gestión de pacientes, citas, medicamentos, cuidados y facturación de una clínica veterinaria.

## Arquitectura

```
[Cliente] → [API REST :8180] → [JWT Auth] → [Controller] → [Service] → [Repository] → [MySQL]
```

## Microservicios

| Ruta | Métodos | Descripción |
|------|---------|-------------|
| `/login` | POST | Autenticación JWT |
| `/patient` | GET, POST, PUT, DELETE | Pacientes (mascotas) |
| `/appointment` | GET, POST, PUT, DELETE | Citas veterinarias |
| `/medication` | GET, POST, PUT, DELETE | Medicamentos |
| `/care` | GET, POST, PUT, DELETE | Servicios de cuidado |
| `/invoice` | GET, POST, PUT, DELETE | Facturación con cálculo automático |

## Tecnologías

- Java 17, Spring Boot 3.4.4
- Spring Security + JWT (jjwt 0.12.5)
- Spring Data JPA / Hibernate, MySQL 8
- Maven, Docker, Docker Swarm
- GitHub Actions (CI/CD)
- AWS Lambda + SQS (serverless)

## Docker

```bash
# Construir y ejecutar local
docker compose up --build

# Swarm (3 réplicas del backend)
docker stack deploy -c docker-compose-swarm.yml backend-stack
```

## CI/CD

Pipeline automatizado en GitHub Actions:
1. Build y test con Maven
2. Publicar imagen en Docker Hub
3. Deploy automático a servidor cloud via SSH

## Desarrollo local

```bash
mvn clean package -DskipTests
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

## Estructura del proyecto

```
src/main/java/com/duoc/backend/
├── Appointment/   → controller, service, repository, model
├── Care/          → controller, service, repository, model
├── Invoice/       → controller, service, repository, model
├── Medication/    → controller, service, repository, model
├── Patient/       → controller, service, repository, model
├── LoginController.java       → autenticación
├── WebSecurityConfig.java     → seguridad Spring
├── JWT*.java                  → filtros JWT
├── User.java / UserRepository → usuarios
└── BackendApplication.java     → entry point
```
