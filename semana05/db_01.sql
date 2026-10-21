CREATE DATABASE IF NOT EXISTS BDAula01;

SHOW DATABASES;

USE BDAula01;

CREATE TABLE pessoa (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sexo VARCHAR(1) NOT NULL,
    idioma VARCHAR(10) NOT NULL
);

CREATE TABLE veiculo (
	id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(30),
    placa VARCHAR(7),
    id_pessoa INT NOT NULL,
    FOREIGN KEY(id_pessoa) REFERENCES pessoa(id)
);

SHOW TABLES;
DESC pessoa;

INSERT INTO pessoa (nome, sexo, idioma) 
VALUES
("Ricardo", "M", "portugues"),
("João", "M", "portugues"),
("Rafael", "M", "frances"),
("Joe", "M", "Espanhol"),
("Mary", "F", "ingles");

DESC veiculo;

INSERT INTO veiculo (modelo, placa, id_pessoa) 
VALUES
('Civic', 'ABC1234', 1),
('Corolla', 'XYZ5678', 2),
('Ram', 'FRN9911', 3),
('Mustang', 'ESP4321', 4),
('Focus', 'ENG8877', 5);

SELECT * FROM pessoa;
SELECT * FROM veiculo
