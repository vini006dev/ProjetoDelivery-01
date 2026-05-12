# 🚀 Delivery App — Java + PostgreSQL

> Plataforma de delivery completa inspirada no iFood/Uber Eats, desenvolvida em Java com JDBC e PostgreSQL, seguindo arquitetura em camadas (DAO + Service + View).

---

## 📋 Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias](#tecnologias)
- [Arquitetura](#arquitetura)
- [Estrutura de Pastas](#estrutura-de-pastas)
- [Banco de Dados](#banco-de-dados)
- [Fluxo do Pedido](#fluxo-do-pedido)
- [Funcionalidades](#funcionalidades)
- [Como Rodar](#como-rodar)
- [Próximas Melhorias](#próximas-melhorias)

---

## Sobre o Projeto

O **Delivery App** é um sistema de pedidos online desenvolvido em Java puro com JDBC, simulando o fluxo real de uma plataforma de delivery: do cadastro do restaurante até a entrega ao cliente.

O sistema contempla três perfis de usuário distintos, cada um com suas responsabilidades no ciclo de vida de um pedido.

---

## Tecnologias

| Camada       | Tecnologia       |
|--------------|-----------------|
| Linguagem    | Java 17+        |
| Banco        | PostgreSQL      |
| Persistência | JDBC            |
| IDE          | IntelliJ IDEA   |

---

## Arquitetura

O projeto segue o padrão de arquitetura em três camadas:

```
Telas (View)
    ↓
Services (Regras de Negócio)
    ↓
DAOs (Acesso ao Banco de Dados)
    ↓
PostgreSQL
```

Essa separação garante baixo acoplamento, facilidade de manutenção e organização clara de responsabilidades.

---

## Estrutura de Pastas

```
src/
└── org.example/
    ├── connection/        # Configuração e abertura de conexão JDBC
    ├── dao/               # Queries SQL (INSERT, SELECT, UPDATE, DELETE)
    ├── models/            # Entidades que representam as tabelas do banco
    ├── services/          # Regras de negócio e orquestração dos DAOs
    ├── telas/             # Interface via terminal (menus e interações)
    ├── utils/             # Classes auxiliares (Scanner global, StatusPedido)
    └── Main.java          # Ponto de entrada da aplicação
```

### Detalhamento por Camada

#### `connection/`
- **`Conexao.java`** — Gerencia a abertura da conexão com o PostgreSQL via JDBC.
  ```java
  Connection conn = Conexao.conectar();
  ```

#### `dao/`
Contém toda a lógica de acesso ao banco de dados.

| Classe            | Responsabilidades                                        |
|-------------------|----------------------------------------------------------|
| `ClienteDAO`      | Login e cadastro de clientes                             |
| `RestauranteDAO`  | Login, cadastro e listagem de restaurantes               |
| `ProdutoDAO`      | Cadastro, listagem e exclusão de produtos                |
| `PedidoDAO`       | Criação, listagem e atualização de status de pedidos     |
| `ItemPedidoDAO`   | Persistência dos itens vinculados a cada pedido          |

#### `models/`
Representam as entidades do banco de dados (uma classe por tabela).

`Cliente` · `Restaurante` · `Produto` · `Pedido` · `ItemPedido` · `Entregador`

#### `services/`
Camada intermediária com as regras de negócio.

| Classe               | Responsabilidades                          |
|----------------------|--------------------------------------------|
| `ProdutoService`     | Cadastro e listagem de produtos            |
| `PedidoService`      | Salvar pedido, atualizar status, listar    |
| `RestauranteService` | Login e cadastro de restaurante            |

#### `telas/`
Interface do sistema via terminal.

| Classe                        | Descrição                                               |
|-------------------------------|---------------------------------------------------------|
| `InterfaceInicial`            | Menu principal: Cliente / Restaurante / Entregador      |
| `InterfaceCliente`            | Login, listagem, carrinho e finalização de pedidos      |
| `InterfaceRestaurante`        | Gestão de pedidos e cardápio                            |
| `InterfaceProdutoRestaurante` | CRUD de produtos do cardápio                            |
| `InterfaceEntregador`         | Visualização, aceite e conclusão de entregas            |

#### `utils/`

| Classe          | Descrição                                            |
|-----------------|------------------------------------------------------|
| `Input`         | Scanner global compartilhado por toda a aplicação    |
| `StatusPedido`  | Enum centralizado com todos os status possíveis      |

```java
// StatusPedido.java
RECEBIDO → EM_PREPARO → PRONTO → EM_ENTREGA → ENTREGUE
                                             ↘ CANCELADO
```

---

## Banco de Dados

### Tabelas

```
Restaurante
Cliente
Entregador
Produto
Pedido
ItemPedido
```

### Script de criação

```sql
-- Criar o banco
CREATE DATABASE delivery_db;

-- Executar o script completo
\i database.sql
```

---

## Fluxo do Pedido

```
[Cliente]
  Escolhe restaurante
  → Seleciona produtos
  → Adiciona ao carrinho
  → Confirma pedido           ── Status: RECEBIDO

[Restaurante]
  Aceita o pedido             ── Status: EM_PREPARO
  Finaliza o preparo          ── Status: PRONTO
  (ou cancela)                ── Status: CANCELADO

[Entregador]
  Visualiza pedidos prontos
  → Aceita a entrega          ── Status: EM_ENTREGA
  → Conclui a entrega         ── Status: ENTREGUE
```

---

## Funcionalidades

### 👤 Cliente
- [x] Cadastro e login
- [x] Listar restaurantes disponíveis
- [x] Visualizar cardápio por restaurante
- [x] Gerenciar carrinho
- [x] Finalizar pedido
- [x] Acompanhar pedidos realizados

### 🍕 Restaurante
- [x] Cadastro e login
- [x] Criar, listar e excluir produtos do cardápio
- [x] Gerenciar pedidos recebidos
- [x] Aceitar, finalizar ou cancelar pedidos

### 🛵 Entregador
- [x] Cadastro e login
- [x] Visualizar pedidos prontos para entrega
- [x] Aceitar e finalizar entregas

---

## Como Rodar

### Pré-requisitos

- Java 17+
- PostgreSQL instalado e rodando
- IntelliJ IDEA (ou outra IDE de sua preferência)

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

Edite o arquivo `src/org/example/connection/Conexao.java` com suas credenciais:
```java
private static final String URL  = "jdbc:postgresql://localhost:5432/delivery_db";
private static final String USER = "seu_usuario";
private static final String PASS = "sua_senha";
```

**5. Execute a aplicação**

Rode o arquivo `Main.java` pela IDE ou via terminal:
```bash
javac -cp .:postgresql.jar src/org/example/Main.java
java  -cp .:postgresql.jar org.example.Main
```

### Preview do Terminal

```
+---------------------------------+
|            DELIVERY             |
+---------------------------------+
|       1 - Cliente               |
|       2 - Restaurante           |
|       3 - Entregador            |
|       4 - Sair                  |
+---------------------------------+
Escolha uma opção: _
```

---

## Próximas Melhorias

### Curto Prazo
- [ ] Criptografia de senhas (BCrypt)
- [ ] Validação e tratamento de exceções customizadas
- [ ] Logs de operações

### Médio Prazo
- [ ] API REST com Spring Boot
- [ ] Autenticação via JWT
- [ ] Testes unitários (JUnit + Mockito)
- [ ] Containerização com Docker

### Longo Prazo
- [ ] Interface gráfica (JavaFX ou frontend web)
- [ ] Integração com gateway de pagamentos
- [ ] Geolocalização em tempo real
- [ ] Sistema de avaliações
- [ ] Upload de imagens de produtos
- [ ] Deploy em nuvem (AWS / GCP / Railway)

---

## Conceitos Aplicados

- Programação Orientada a Objetos (POO)
- Padrão DAO (Data Access Object)
- Service Layer
- JDBC e SQL relacional
- Foreign Keys e relacionamentos entre tabelas
- CRUD completo
- Separação de responsabilidades por camadas

---

## Contribuindo

Contribuições são bem-vindas! Siga os passos abaixo:

1. Fork o repositório
2. Crie uma branch: `git checkout -b feature/minha-feature`
3. Commit suas mudanças: `git commit -m 'feat: adiciona minha feature'`
4. Push para a branch: `git push origin feature/minha-feature`
5. Abra um Pull Request

---

## Licença

Projeto desenvolvido para fins educacionais — livre para uso, estudo e modificação.

---

*Desenvolvido com ☕ Java e muito aprendizado.*