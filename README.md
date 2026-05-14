<div align="center">

# 🛵 Delivery App

### Plataforma de delivery completa desenvolvida em Java + PostgreSQL

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org)
[![JDBC](https://img.shields.io/badge/JDBC-Persistence-blue?style=for-the-badge)](https://docs.oracle.com/javase/tutorial/jdbc/)
[![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/idea/)
[![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow?style=for-the-badge)]()

<br/>

> Sistema inspirado no iFood e Uber Eats, com fluxo completo de pedidos:  
> do cadastro do restaurante até a entrega na porta do cliente.

<br/>

[📋 Sobre](#-sobre-o-projeto) •
[🏗 Arquitetura](#-arquitetura) •
[🗄 Banco de Dados](#-banco-de-dados) •
[🔄 Fluxo do Pedido](#-fluxo-do-pedido) •
[✅ Funcionalidades](#-funcionalidades) •
[▶️ Como Rodar](#-como-rodar) •
[🚀 Próximas Melhorias](#-próximas-melhorias)

</div>

---

## 📋 Sobre o Projeto

O **Delivery App** é um sistema de pedidos online desenvolvido em **Java puro com JDBC**, sem frameworks externos, aplicando na prática os fundamentos de:

- Programação Orientada a Objetos
- Arquitetura em camadas (DAO + Service + View)
- Modelagem relacional com PostgreSQL
- CRUD completo via SQL nativo

O sistema contempla **três perfis de usuário** distintos, cada um com responsabilidades específicas dentro do ciclo de vida de um pedido.

---

## 🏗 Arquitetura

O projeto segue o padrão de **arquitetura em três camadas**, garantindo baixo acoplamento e separação clara de responsabilidades:

```
┌─────────────────────────────┐
│        Telas (View)         │  ← Interação com o usuário via terminal
├─────────────────────────────┤
│    Services (Negócio)       │  ← Regras e validações
├─────────────────────────────┤
│     DAOs (Persistência)     │  ← Queries SQL
├─────────────────────────────┤
│        PostgreSQL           │  ← Banco de dados relacional
└─────────────────────────────┘
```

### 📁 Estrutura de Pastas

```
src/
└── org.example/
    ├── 📂 connection/        # Configuração da conexão JDBC
    ├── 📂 dao/               # Acesso ao banco (INSERT, SELECT, UPDATE, DELETE)
    ├── 📂 models/            # Entidades que espelham as tabelas do banco
    ├── 📂 services/          # Regras de negócio e orquestração dos DAOs
    ├── 📂 telas/             # Interface via terminal (menus e interações)
    ├── 📂 utils/             # Classes auxiliares (Scanner global, Enums)
    └── 📄 Main.java          # Ponto de entrada da aplicação
```

### Detalhamento por Camada

<details>
<summary><strong>📂 connection/</strong></summary>

| Classe | Descrição |
|--------|-----------|
| `Conexao.java` | Gerencia a abertura da conexão com o PostgreSQL via JDBC |

```java
Connection conn = Conexao.conectar();
```

</details>

<details>
<summary><strong>📂 dao/</strong></summary>

Toda a lógica de acesso ao banco. Cada tabela tem seu DAO correspondente.

| Classe | Responsabilidades |
|--------|-------------------|
| `ClienteDAO` | Login, cadastro e busca de clientes |
| `RestauranteDAO` | Login, cadastro e listagem de restaurantes |
| `EntregadorDAO` | Login e cadastro de entregadores |
| `ProdutoDAO` | Cadastro, listagem e exclusão de produtos |
| `PedidoDAO` | Criação, listagem, atualização de status e vínculo com entregador |
| `ItemPedidoDAO` | Persistência dos itens de cada pedido |

</details>

<details>
<summary><strong>📂 models/</strong></summary>

Representam as entidades do banco — uma classe por tabela.

```
Usuario (abstrato)
├── Cliente
└── Entregador

Restaurante · Produto · Pedido · ItemPedido · Endereco
```

</details>

<details>
<summary><strong>📂 services/</strong></summary>

Camada intermediária com as regras de negócio. As telas nunca acessam o DAO diretamente.

| Classe | Responsabilidades |
|--------|-------------------|
| `ClienteService` | Login, cadastro e busca de endereço |
| `RestauranteService` | Login e cadastro |
| `EntregadorService` | Login, cadastro, pedidos disponíveis, aceite e finalização |
| `ProdutoService` | Cadastro, listagem e exclusão |
| `PedidoService` | Salvar pedido, atualizar status, listar por perfil |
| `ItemPedidoService` | Salvar itens do pedido |

</details>

<details>
<summary><strong>📂 telas/</strong></summary>

Interface do sistema via terminal.

| Classe | Descrição |
|--------|-----------|
| `InterfaceInicial` | Menu principal: Cliente / Restaurante / Entregador |
| `InterfaceCliente` | Login, cardápio, carrinho e acompanhamento de pedidos |
| `InterfaceRestaurante` | Gestão de pedidos e cardápio |
| `InterfaceProdutoRestaurante` | CRUD de produtos do cardápio |
| `InterfaceEntregador` | Visualização de nota de entrega, aceite e conclusão |

</details>

<details>
<summary><strong>📂 utils/</strong></summary>

| Classe | Descrição |
|--------|-----------|
| `Input` | Scanner global compartilhado por toda a aplicação |
| `StatusPedido` | Enum centralizado com todos os status possíveis |

```java
RECEBIDO → EM_PREPARO → PRONTO → EM_ENTREGA → ENTREGUE
                                             ↘ CANCELADO
```

</details>

---

## 🗄 Banco de Dados

### Diagrama de Relacionamento

```
Restaurante ──────< Produto
     │
     └──────────< Pedido >──────── Cliente
                    │
                    ├──────────── Entregador
                    │
                    └──────────< ItemPedido >──── Produto
```

### Tabelas

| Tabela | Descrição |
|--------|-----------|
| `Restaurante` | Dados do restaurante (nome, endereço, categoria, login) |
| `Cliente` | Dados do cliente incluindo endereço de entrega |
| `Entregador` | Dados do entregador e veículo |
| `Produto` | Cardápio vinculado ao restaurante |
| `Pedido` | Pedido com status e vínculos entre as partes |
| `ItemPedido` | Produtos individuais dentro de cada pedido |

### Setup do Banco

```sql
-- 1. Crie o banco
CREATE DATABASE delivery_db;

-- 2. Execute o script de criação
\i database.sql

-- 3. (Opcional) Popule com dados de teste
\i seed.sql
```

---

## 🔄 Fluxo do Pedido

```
👤 CLIENTE
  ├── Escolhe o restaurante
  ├── Seleciona produtos e monta o carrinho
  └── Confirma o pedido ──────────────────────── Status: RECEBIDO
                                                        │
🍕 RESTAURANTE                                          ▼
  ├── Visualiza o pedido recebido                Status: EM_PREPARO
  ├── Finaliza o preparo ───────────────────────── Status: PRONTO
  └── (ou cancela) ────────────────────────────── Status: CANCELADO
                                                        │
🛵 ENTREGADOR                                           ▼
  ├── Vê a nota com endereço de retirada        Status: EM_ENTREGA
  │   e endereço de entrega
  └── Conclui a entrega ───────────────────────── Status: ENTREGUE
```

---

## ✅ Funcionalidades

### 👤 Cliente
- [x] Cadastro com endereço de entrega
- [x] Login
- [x] Listar restaurantes disponíveis
- [x] Visualizar cardápio por restaurante
- [x] Montar carrinho com múltiplos produtos
- [x] Ver resumo com subtotal e taxa de entrega
- [x] Finalizar pedido
- [x] Acompanhar pedidos realizados

### 🍕 Restaurante
- [x] Cadastro e login
- [x] Criar, listar e excluir produtos do cardápio
- [x] Visualizar pedidos recebidos
- [x] Aceitar, finalizar ou cancelar pedidos

### 🛵 Entregador
- [x] Cadastro e login
- [x] Visualizar pedidos prontos com nota de entrega
- [x] Ver endereço de retirada (restaurante) e entrega (cliente)
- [x] Aceitar pedido e atualizar status para EM_ENTREGA
- [x] Finalizar entrega

---

## ▶️ Como Rodar

### Pré-requisitos

- Java 17+
- PostgreSQL instalado e rodando
- IntelliJ IDEA (recomendado)

### Passo a Passo

**1. Clone o repositório**
```bash
git clone https://github.com/seu-usuario/delivery-app.git
cd delivery-app
```

**2. Crie o banco de dados**
```sql
CREATE DATABASE delivery_db;
```

**3. Execute o script SQL**
```bash
psql -U postgres -d delivery_db -f database.sql
```

**4. Configure a conexão**

Edite `src/org/example/connection/Conexao.java`:
```java
private static final String URL  = "jdbc:postgresql://localhost:5432/delivery_db";
private static final String USER = "seu_usuario";
private static final String PASS = "sua_senha";
```

**5. Execute**

Rode o `Main.java` pela IDE ou via terminal:
```bash
javac -cp .:postgresql.jar src/org/example/Main.java
java  -cp .:postgresql.jar org.example.Main
```

### 💡 Dica — Testando múltiplos perfis

Para simular o fluxo completo, você pode rodar **3 instâncias simultâneas** no IntelliJ:

1. Vá em **Edit Configurations** → marque **Allow multiple instances**
2. Rode 3x e logue como Cliente, Restaurante e Entregador em cada janela

> As instâncias se comunicam pelo banco de dados — atualize os menus para ver as mudanças em tempo real.

---

## 🚀 Próximas Melhorias

### ⚡ Curto Prazo
- [ ] Criptografia de senhas com BCrypt
- [ ] Validação de entradas e exceções customizadas
- [ ] Logs de operações

### 🛠 Médio Prazo
- [ ] API REST com Spring Boot
- [ ] Autenticação via JWT
- [ ] Testes unitários com JUnit + Mockito
- [ ] Containerização com Docker

### 🌐 Longo Prazo
- [ ] Interface gráfica com JavaFX ou frontend web
- [ ] Integração com gateway de pagamentos
- [ ] Sistema de avaliações de restaurantes e entregadores
- [ ] Upload de imagens de produtos
- [ ] Deploy em nuvem (Railway / AWS / GCP)

---

## 🧠 Conceitos Aplicados

| Conceito | Aplicação |
|----------|-----------|
| POO | Herança (`Usuario` → `Cliente`, `Entregador`), encapsulamento |
| Padrão DAO | Uma classe de acesso ao banco por entidade |
| Service Layer | Separação entre regras de negócio e persistência |
| JDBC | Conexão e queries sem ORM |
| SQL Relacional | Foreign keys, JOINs, RETURNING, LEFT JOIN |
| CRUD | Create, Read, Update, Delete em todas as entidades |

---

## 🤝 Contribuindo

Contribuições são bem-vindas!

1. Fork o repositório
2. Crie sua branch: `git checkout -b feature/minha-feature`
3. Commit: `git commit -m 'feat: adiciona minha feature'`
4. Push: `git push origin feature/minha-feature`
5. Abra um Pull Request

---

## 📄 Licença

Projeto desenvolvido para fins educacionais — livre para uso, estudo e modificação.

---

<div align="center">

Desenvolvido com ☕ Java e muito aprendizado

⭐ Se esse projeto te ajudou, deixa uma estrela!

</div>