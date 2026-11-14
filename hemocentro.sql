-- =============================================
-- Banco de dados: Hemocentro (SQL Server)
-- Criacao das tabelas a partir do DER
-- Atencao: se o banco Hemocentro ja existir, ele e apagado (com os dados)
-- e criado de novo; as outras conexoes com ele sao encerradas
-- =============================================

USE master;
GO

IF DB_ID('Hemocentro') IS NOT NULL
BEGIN
    ALTER DATABASE Hemocentro SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE Hemocentro;
END
GO

CREATE DATABASE Hemocentro;
GO

USE Hemocentro;
GO

-- ---------- Tabelas de apoio ----------

CREATE TABLE Endereco (
    cod_endereco INT IDENTITY(1,1) PRIMARY KEY,
    logradouro   VARCHAR(150) NOT NULL,
    numero       VARCHAR(10)  NOT NULL,
    cep          CHAR(8)      NOT NULL,
    cidade       VARCHAR(100) NOT NULL,
    uf           CHAR(2)      NOT NULL
);

CREATE TABLE TipoSang (
    tipo VARCHAR(3) PRIMARY KEY
);

CREATE TABLE Hemocomponente (
    sigla VARCHAR(5)  PRIMARY KEY,
    nome  VARCHAR(50) NOT NULL UNIQUE
);

-- ---------- Pessoas ----------

-- Reside: cada pessoa tem um endereco
CREATE TABLE Pessoa (
    cod_pessoa   INT IDENTITY(1,1) PRIMARY KEY,
    nome         VARCHAR(100) NOT NULL,
    cpf          CHAR(11)     NOT NULL UNIQUE,
    telefone     VARCHAR(11)  NOT NULL,
    cod_endereco INT          NOT NULL REFERENCES Endereco (cod_endereco)
);

-- Especializacoes de Pessoa: a chave e a mesma da pessoa
-- Possui: cada doador tem um tipo sanguineo
CREATE TABLE Doador (
    cod_pessoa     INT        PRIMARY KEY REFERENCES Pessoa (cod_pessoa) ON DELETE CASCADE,
    tipo_sanguineo VARCHAR(3) NOT NULL REFERENCES TipoSang (tipo)
);

CREATE TABLE Funcionario (
    cod_pessoa INT           PRIMARY KEY REFERENCES Pessoa (cod_pessoa) ON DELETE CASCADE,
    salario    DECIMAL(10,2) NOT NULL
);

-- COREN: registro do tecnico no Conselho Regional de Enfermagem (unico)
CREATE TABLE Tecnico (
    cod_pessoa INT         PRIMARY KEY REFERENCES Pessoa (cod_pessoa) ON DELETE CASCADE,
    coren      VARCHAR(20) NOT NULL UNIQUE
);

-- ---------- Hospitais e pedidos ----------

-- Pertence: cada hospital tem um endereco
CREATE TABLE Hospital (
    cod_hospital INT IDENTITY(1,1) PRIMARY KEY,
    razao_social VARCHAR(150) NOT NULL,
    cnpj         CHAR(14)     NOT NULL UNIQUE,
    cod_endereco INT          NOT NULL REFERENCES Endereco (cod_endereco)
);

-- Realiza: cada pedido e de um hospital
CREATE TABLE Pedido (
    num_pedido       INT IDENTITY(1,1) PRIMARY KEY,
    data_solicitacao DATE        NOT NULL,
    data_envio       DATE        NULL,
    status           VARCHAR(30) NOT NULL,
    cod_hospital     INT         NOT NULL REFERENCES Hospital (cod_hospital)
);

-- Tem (N:N): os tipos sanguineos de cada pedido
CREATE TABLE Tem (
    num_pedido     INT        NOT NULL REFERENCES Pedido (num_pedido),
    tipo_sanguineo VARCHAR(3) NOT NULL REFERENCES TipoSang (tipo),
    PRIMARY KEY (num_pedido, tipo_sanguineo)
);

-- ---------- Coletas, analises e hemocomponentes ----------

-- Realiza e Coleta: cada coleta (bolsa) tem um doador e um funcionario
-- Gera: num_pedido fica vazio ate a bolsa ser enviada
CREATE TABLE Coleta (
    cod_coleta      INT IDENTITY(1,1) PRIMARY KEY,
    data_coleta     DATE        NOT NULL,
    data_validade   DATE        NOT NULL,
    status          VARCHAR(10) NOT NULL DEFAULT 'DISPONIVEL'
                    CHECK (status IN ('DISPONIVEL', 'DESCARTADA', 'ENVIADA')),
    cod_doador      INT         NOT NULL REFERENCES Doador (cod_pessoa),
    cod_funcionario INT         NOT NULL REFERENCES Funcionario (cod_pessoa),
    num_pedido      INT         NULL REFERENCES Pedido (num_pedido)
);

-- Possui (1:1): cod_coleta e UNIQUE, entao cada coleta tem no maximo uma analise
-- Faz: cada analise e feita por um tecnico
CREATE TABLE Analise (
    cod_analise INT IDENTITY(1,1) PRIMARY KEY,
    status      VARCHAR(30) NOT NULL,
    cod_coleta  INT         NOT NULL UNIQUE REFERENCES Coleta (cod_coleta),
    cod_tecnico INT         NOT NULL REFERENCES Tecnico (cod_pessoa)
);

-- Produz (N:N): vira tabela; a chave junta a analise e o hemocomponente,
-- e volume_ml e o atributo do relacionamento
CREATE TABLE Produz (
    cod_analise          INT        NOT NULL REFERENCES Analise (cod_analise),
    sigla_hemocomponente VARCHAR(5) NOT NULL REFERENCES Hemocomponente (sigla),
    volume_ml            INT        NOT NULL,
    PRIMARY KEY (cod_analise, sigla_hemocomponente)
);

-- ---------- Dados iniciais ----------

INSERT INTO TipoSang (tipo) VALUES
    ('A+'), ('A-'), ('B+'), ('B-'), ('AB+'), ('AB-'), ('O+'), ('O-');

INSERT INTO Hemocomponente (sigla, nome) VALUES
    ('CH',   'Concentrado de Hemácias'),
    ('CP',   'Concentrado de Plaquetas'),
    ('CRIO', 'Crioprecipitado'),
    ('PFC',  'Plasma Fresco Congelado');
