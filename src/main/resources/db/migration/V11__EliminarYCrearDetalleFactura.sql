-- Eliminar las tablas si existen
DROP TABLE IF EXISTS detalle_factura;
DROP TABLE IF EXISTS detallefactura;

-- Crear la tabla correctamente
CREATE TABLE detalle_factura (
    id BIGSERIAL PRIMARY KEY,
    factura_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,

    cantidad INTEGER NOT NULL,
    descripcion TEXT,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_detalle_factura
        FOREIGN KEY (factura_id)
            REFERENCES factura(id),

    CONSTRAINT fk_detalle_producto
        FOREIGN KEY (producto_id)
            REFERENCES producto(id)
);