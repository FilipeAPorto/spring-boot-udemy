CREATE table cliente (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR(100)
);

create table produto (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO VARCHAR(100),
    preco_unitario NUMERIC(20,2)
);

create table pedido (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    cliente_id INTEGER REFERENCES CLIENTE(ID),
    DATA_PEDIDO TIMESTAMP,
    TOTAL NUMERIC(20,2)
);

create table item_pedido (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    pedido_id INTEGER REFERENCES pedido (ID),
    produto_id INTEGER REFERENCES produto (ID),
    quantidade INTEGER
);