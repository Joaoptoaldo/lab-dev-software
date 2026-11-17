CREATE DATABASE IF NOT EXISTS ClienteDB;

USE ClienteDB;

CREATE TABLE IF NOT EXISTS pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    idioma VARCHAR(20) NOT NULL
);

INSERT INTO pessoa (nome, sexo, idioma)
VALUES
("João Silva", "M", "portugues"),
("Maria Santos", "F", "ingles"),
("Pedro Oliveira", "M", "espanhol"),
("Ana Costa", "F", "frances"),
("Carlos Ferreira", "M", "alemao");


DESC pessoa;
SELECT * FROM pessoa;