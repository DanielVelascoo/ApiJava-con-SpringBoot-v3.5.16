ALTER TABLE producto
    ADD COLUMN categoria_id BIGINT;

ALTER TABLE producto
    ADD CONSTRAINT fk_producto_categoria
        FOREIGN KEY (categoria_id)
            REFERENCES categoria(id);