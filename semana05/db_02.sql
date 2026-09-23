CREATE DATABASE IF NOT EXISTS escola;
USE escola;


CREATE TABLE alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    idade INT NOT NULL,
    curso VARCHAR(50) NOT NULL
);

INSERT INTO alunos (nome, idade, curso) 
VALUES
("João", 20, "matemática"),
("Maria", 22, "história"),
("Pedro", 21, "ciência da computação"),
("Ana", 19, "biologia"),
("Carlos", 23, "economia");


CREATE TABLE professores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    idade INT NOT NULL,
    disciplina VARCHAR(50) NOT NULL -- Coluna correta
);

INSERT INTO professores (nome, idade, disciplina) 
VALUES
("John", 40, "matemática"),
("Mary", 42, "história"),
("Rafael", 41, "ciência da computação");


CREATE TABLE matriculas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_aluno INT,
    id_professor INT,
    data_matricula DATE,
    FOREIGN KEY (id_aluno) REFERENCES alunos(id), 
    FOREIGN KEY (id_professor) REFERENCES professores(id)
);

INSERT INTO matriculas (id_aluno, id_professor, data_matricula)
VALUES
(1, 1, '2023-01-15'),
(2, 2, '2023-02-20'),
(3, 3, '2023-03-10'),
(4, 1, '2023-04-05'),
(5, 2, '2023-05-12');

select *  from matriculas

