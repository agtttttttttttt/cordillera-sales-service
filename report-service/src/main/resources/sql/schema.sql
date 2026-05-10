CREATE DATABASE IF NOT EXISTS grupo_cordillera_reports;
USE grupo_cordillera_reports;

CREATE TABLE kpi (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    formula VARCHAR(255),
    unidad VARCHAR(50),
    meta DECIMAL(10,2),
    estado VARCHAR(20) DEFAULT 'ACTIVO'
);

CREATE TABLE registro_kpi (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    kpi_id BIGINT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    periodo VARCHAR(20),
    FOREIGN KEY (kpi_id) REFERENCES kpi(id)
);

CREATE TABLE reporte (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descripcion TEXT,
    tipo VARCHAR(50),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creado_por VARCHAR(100),
    estado VARCHAR(20) DEFAULT 'ACTIVO'
);
