CREATE TABLE tb_instructor
(
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    cnh VARCHAR(11) UNIQUE,
    speciality VARCHAR(100),
    street VARCHAR(100),
    number VARCHAR(20),
    complement VARCHAR(100),
    neighborhood VARCHAR(100),
    city VARCHAR(100),
    state VARCHAR(50),
    cep VARCHAR(9)

)