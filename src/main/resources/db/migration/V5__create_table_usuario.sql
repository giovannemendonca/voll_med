CREATE TABLE USUARIOS(
    ID SERIAL NOT NULL,
    LOGIN VARCHAR(100) UNIQUE NOT NULL,
    SENHA VARCHAR(255) NOT NULL,

    PRIMARY KEY (ID)
);