# Project: demo-dao-jdbc

## 🌐 Language / Idioma
- [English](#english-version)
- [Portuguese](#versão-em-português)

---

## English Version

### Overview
This is a Java console project that demonstrates the use of the **DAO (Data Access Object) pattern** with plain JDBC and PostgreSQL. It performs CRUD operations for two main entities: `Seller` and `Department`.

### Package Structure
```
application        --> Contains test classes with main methods (Program, Program2)
db                 --> Infrastructure for DB connection and custom exceptions
model.entities     --> Business entities: Seller, Department
model.dao          --> DAO interfaces
model.dao.impl     --> Concrete implementations using JDBC
```

### Database
**Database**: `coursejdbc`

**Configuration file (`db.properties`)**:
```properties
user=postgres
password=12345
dburl=jdbc:postgresql://localhost:5432/coursejdbc
useSSL=false
```

**Expected tables:**
```sql
CREATE TABLE department (
  id SERIAL PRIMARY KEY,
  name VARCHAR(60)
);

CREATE TABLE seller (
  id SERIAL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  email VARCHAR(100) NOT NULL,
  birth_date TIMESTAMP NOT NULL,
  base_salary DOUBLE PRECISION NOT NULL,
  department_id INTEGER NOT NULL,
  FOREIGN KEY (department_id) REFERENCES department(id)
);
```

### Key Components
- `DB.java`: Manages database connections and resource cleanup.
- `DbException` / `DbIntegrityException`: Custom error handling.
- `Seller` / `Department`: Business entities with relationships.
- DAO interfaces and JDBC implementations.
- `Program` and `Program2`: Console apps for testing DAO operations.

### How to Run
1. Create the PostgreSQL database and tables.
2. Configure `db.properties`.
3. Compile and run from your IDE.

---

## Versão em Português

### Visão Geral
Projeto Java console que demonstra o uso do **padrão DAO (Data Access Object)** com JDBC puro e PostgreSQL. Realiza operações CRUD para as entidades `Seller` e `Department`.

### Estrutura de Pacotes
```
application        --> Contém classes com o main para testes (Program, Program2)
db                 --> Infraestrutura de conexão e exceções personalizadas
model.entities     --> Entidades de negócio: Seller, Department
model.dao          --> Interfaces DAO
model.dao.impl     --> Implementações com JDBC
```

### Banco de Dados
**Banco**: `coursejdbc`

**Arquivo de configuração (`db.properties`)**:
```properties
user=postgres
password=12345
dburl=jdbc:postgresql://localhost:5432/coursejdbc
useSSL=false
```

**Tabelas esperadas:**
```sql
CREATE TABLE department (
  id SERIAL PRIMARY KEY,
  name VARCHAR(60)
);

CREATE TABLE seller (
  id SERIAL PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  email VARCHAR(100) NOT NULL,
  birth_date TIMESTAMP NOT NULL,
  base_salary DOUBLE PRECISION NOT NULL,
  department_id INTEGER NOT NULL,
  FOREIGN KEY (department_id) REFERENCES department(id)
);
```

### Componentes Principais
- `DB.java`: Gerencia conexões e fechamento de recursos.
- `DbException` / `DbIntegrityException`: Tratamento de erros personalizados.
- `Seller` / `Department`: Entidades de negócio com relacionamento.
- Interfaces DAO e implementações via JDBC.
- `Program` e `Program2`: Aplicações para testar operações CRUD.

### Como Executar
1. Crie o banco e as tabelas no PostgreSQL.
2. Configure o `db.properties`.
3. Compile e execute pela sua IDE.

---
Educational and demonstrative project for Java and JDBC best practices.
