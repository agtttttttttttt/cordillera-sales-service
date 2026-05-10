# Grupo Cordillera - Microservicios

Sistema de microservicios para el monitoreo del desempeño organizacional de Grupo Cordillera.

## Microservicios

| Microservicio | Puerto | Base de datos | Descripción |
|---|---|---|---|
| `sales-service` | 8080 | `grupo_cordillera` | Gestión de ventas, clientes, productos y sucursales |
| `inventory-service` | 8081 | `grupo_cordillera_inventory` | Control de inventario, bodegas y movimientos |
| `user-service` | 8082 | `grupo_cordillera_users` | Administración de usuarios y roles |
| `report-service` | 8083 | `grupo_cordillera_reports` | KPIs, reportes y dashboard |

## Requisitos

- Java 17+
- MySQL 8+
- Maven (incluido via mvnw)

## Configuración MySQL

1. Crear las bases de datos ejecutando:

```sql
CREATE DATABASE grupo_cordillera;
CREATE DATABASE grupo_cordillera_inventory;
CREATE DATABASE grupo_cordillera_users;
CREATE DATABASE grupo_cordillera_reports;
```

2. Ejecutar los scripts en `src/main/resources/sql/schema.sql` de cada microservicio.

3. Las credenciales por defecto en `application.properties` son:
   - User: `root`
   - Password: `root`

## Ejecución

Cada microservicio se ejecuta de forma independiente:

```bash
cd sales-service
./mvnw spring-boot:run
```

Para los demás microservicios, repetir en terminales separadas cambiando `sales-service` por `inventory-service`, `user-service` o `report-service`.

## Documentación Swagger

Una vez ejecutado cada microservicio, acceder a:

| Microservicio | Swagger UI |
|---|---|
| sales-service | http://localhost:8080/swagger-ui/index.html |
| inventory-service | http://localhost:8081/swagger-ui/index.html |
| user-service | http://localhost:8082/swagger-ui/index.html |
| report-service | http://localhost:8083/swagger-ui/index.html |

## Endpoints principales

### sales-service
- `GET /api/sucursales` - Listar sucursales
- `POST /api/sucursales` - Crear sucursal
- `PUT /api/sucursales/{id}` - Actualizar sucursal
- `DELETE /api/sucursales/{id}` - Eliminar sucursal
- `GET /api/clientes` - Listar clientes
- `POST /api/clientes` - Crear cliente
- `GET /api/productos` - Listar productos
- `POST /api/productos` - Crear producto
- `GET /api/ventas` - Listar ventas
- `POST /api/ventas` - Crear venta

### inventory-service
- `GET /api/bodegas` - Listar bodegas
- `POST /api/bodegas` - Crear bodega
- `GET /api/inventarios` - Listar inventario
- `POST /api/movimientos` - Registrar movimiento

### user-service
- `GET /api/roles` - Listar roles
- `GET /api/usuarios` - Listar usuarios
- `POST /api/usuarios` - Crear usuario

### report-service
- `GET /api/kpis` - Listar KPIs
- `POST /api/kpis` - Crear KPI
- `POST /api/registros` - Registrar valor KPI
- `GET /api/reportes` - Listar reportes

## Compilar y empaquetar

```bash
cd sales-service
./mvnw clean package
```

Esto genera un archivo `.jar` en `target/sales-service-0.0.1-SNAPSHOT.jar`.

## Git Flow

- `main` - Versión estable
- `develop` - Desarrollo activo
- `feature/*` - Ramas para nuevas funcionalidades
