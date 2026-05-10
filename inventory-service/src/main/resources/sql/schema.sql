CREATE DATABASE IF NOT EXISTS grupo_cordillera_inventory;
USE grupo_cordillera_inventory;

CREATE TABLE bodega (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255),
    region VARCHAR(100),
    estado VARCHAR(20) DEFAULT 'ACTIVO'
);

CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    categoria VARCHAR(100),
    estado VARCHAR(20) DEFAULT 'ACTIVO'
);

CREATE TABLE inventario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bodega_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    stock_actual INT NOT NULL DEFAULT 0,
    FOREIGN KEY (bodega_id) REFERENCES bodega(id),
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);

CREATE TABLE movimiento_inventario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    inventario_id BIGINT NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    cantidad INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    motivo VARCHAR(255),
    usuario VARCHAR(100),
    FOREIGN KEY (inventario_id) REFERENCES inventario(id)
);
