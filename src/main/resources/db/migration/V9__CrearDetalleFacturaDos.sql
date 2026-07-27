CREATE TABLE detallefactura (
    id BIGSERIAL PRIMARY KEY,
    factura_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INT,
    descripcion TEXT,
    precioUnitario DECIMAL(10,2) NOT NULL,
    subTotal DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_detalle_factura
        FOREIGN KEY (factura_id)
            REFERENCES factura(id),

    CONSTRAINT fk_detalle_producto
        FOREIGN KEY (producto_id)
            REFERENCES producto(id)
);
