CREATE DATABASE IF NOT EXISTS grupo_cordillera_users;
USE grupo_cordillera_users;

CREATE TABLE rol (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(15) UNIQUE NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    rol_id BIGINT NOT NULL,
    sucursal_id BIGINT,
    estado VARCHAR(20) DEFAULT 'ACTIVO',
    FOREIGN KEY (rol_id) REFERENCES rol(id)
);
