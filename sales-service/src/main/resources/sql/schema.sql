CREATE DATABASE IF NOT EXISTS grupo_cordillera;
USE grupo_cordillera;

CREATE TABLE sucursal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    region VARCHAR(100),
    telefono VARCHAR(20),
    estado VARCHAR(20) DEFAULT 'ACTIVO'
);

CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(15) UNIQUE NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    email VARCHAR(100),
    telefono VARCHAR(20),
    direccion VARCHAR(255),
    fecha_registro DATE DEFAULT (CURRENT_DATE)
);

CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    categoria VARCHAR(100),
    estado VARCHAR(20) DEFAULT 'ACTIVO'
);

CREATE TABLE venta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    cliente_id BIGINT NOT NULL,
    sucursal_id BIGINT NOT NULL,
    estado VARCHAR(20) DEFAULT 'COMPLETADA',
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (sucursal_id) REFERENCES sucursal(id)
);

CREATE TABLE detalle_venta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    venta_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (venta_id) REFERENCES venta(id),
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);
