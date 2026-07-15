ALTER TABLE factura
ADD COLUMN cliente_id BIGINT;

ALTER TABLE  factura
    ADD CONSTRAINT fk_cliente_factura
        FOREIGN KEY (cliente_id)
            REFERENCES cliente(id)