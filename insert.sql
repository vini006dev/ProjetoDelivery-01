-- =========================================
-- POVOAMENTO DELIVERY APP
-- =========================================

-- =========================
-- RESTAURANTES
-- =========================
INSERT INTO Restaurante (Nome, Endereco, CNPJ, Telefone, Categoria, Email, Senha) VALUES
                                                                                      ('Burger House',      'Rua das Flores, 100 - Centro',        '12345678000101', '11999990001', 'Lanche',    'burger@email.com',   '123456'),
                                                                                      ('Pizza Palace',      'Av. Brasil, 250 - Jardins',           '12345678000102', '11999990002', 'Pizza',     'pizza@email.com',    '123456'),
                                                                                      ('Sushi Zen',         'Rua do Japão, 300 - Vila Nova',       '12345678000103', '11999990003', 'Japonesa',  'sushi@email.com',    '123456'),
                                                                                      ('Açaí do Norte',     'Rua Amazonas, 40 - Boa Vista',        '12345678000104', '11999990004', 'Açaí',      'acai@email.com',     '123456'),
                                                                                      ('Churrascaria Brasa','Av. Paulista, 900 - Bela Vista',      '12345678000105', '11999990005', 'Churrasco', 'brasa@email.com',    '123456');

-- =========================
-- CLIENTES
-- =========================
INSERT INTO Cliente (Nome, CPF, Telefone, Email, Senha, Rua, Numero, Bairro, Cidade) VALUES
                                                                                         ('Carlos Silva',    '11122233301', '11988880001', 'carlos@email.com',   '123456', 'Rua das Acácias',    '10',  'Centro',      'São Paulo'),
                                                                                         ('Ana Souza',       '11122233302', '11988880002', 'ana@email.com',      '123456', 'Av. das Rosas',      '200', 'Jardins',     'São Paulo'),
                                                                                         ('Pedro Lima',      '11122233303', '11988880003', 'pedro@email.com',    '123456', 'Rua do Pinheiro',    '55',  'Vila Madalena','São Paulo'),
                                                                                         ('Julia Mendes',    '11122233304', '11988880004', 'julia@email.com',    '123456', 'Rua das Palmeiras',  '88',  'Moema',       'São Paulo'),
                                                                                         ('Lucas Ferreira',  '11122233305', '11988880005', 'lucas@email.com',    '123456', 'Av. Brigadeiro',     '310', 'Itaim Bibi',  'São Paulo'),
                                                                                         ('Mariana Costa',   '11122233306', '11988880006', 'mariana@email.com',  '123456', 'Rua Oscar Freire',   '77',  'Cerqueira César','São Paulo');

-- =========================
-- ENTREGADORES
-- =========================
INSERT INTO Entregador (Nome, CPF, Telefone, Veiculo, Status, Email, Senha) VALUES
                                                                                ('Roberto Moto',   '22233344401', '11977770001', 'Moto',      'disponivel', 'roberto@email.com',  '123456'),
                                                                                ('Fernanda Bike',  '22233344402', '11977770002', 'Bicicleta', 'disponivel', 'fernanda@email.com', '123456'),
                                                                                ('Diego Carro',    '22233344403', '11977770003', 'Carro',     'disponivel', 'diego@email.com',    '123456'),
                                                                                ('Patricia Moto',  '22233344404', '11977770004', 'Moto',      'disponivel', 'patricia@email.com', '123456'),
                                                                                ('Thiago Bike',    '22233344405', '11977770005', 'Bicicleta', 'disponivel', 'thiago@email.com',   '123456');

-- =========================
-- PRODUTOS
-- =========================
INSERT INTO Produto (Nome, Descricao, Preco, Categoria, id_restaurante) VALUES
-- Burger House (id 1)
('X-Burguer',        'Hamburguer artesanal com queijo',  25.90, 'Lanche',  1),
('Batata Frita',     'Porção grande crocante',           18.00, 'Porção',  1),
('Refrigerante Cola','Lata 350ml gelada',                 6.50, 'Bebida',  1),
('Onion Rings',      'Anéis de cebola empanados',        16.00, 'Porção',  1),

-- Pizza Palace (id 2)
('Pizza Calabresa',  'Pizza grande de calabresa',        45.90, 'Pizza',   2),
('Pizza Mussarela',  'Pizza grande de mussarela',        42.50, 'Pizza',   2),
('Pizza Portuguesa', 'Pizza portuguesa especial',        49.90, 'Pizza',   2),
('Pizza Margherita', 'Tomate, mussarela e manjericão',   44.00, 'Pizza',   2),

-- Sushi Zen (id 3)
('Combo Sushi 20pç', 'Combo variado de sushi',           59.90, 'Japonesa',3),
('Temaki Salmão',    'Temaki grande de salmão',          28.00, 'Japonesa',3),
('Hot Roll 8pç',     'Porção de hot roll',               32.00, 'Japonesa',3),

-- Açaí do Norte (id 4)
('Açaí 500ml',       'Açaí com banana e granola',        22.00, 'Açaí',    4),
('Açaí 700ml',       'Açaí completo com complementos',   30.00, 'Açaí',    4),
('Vitamina Banana',  'Vitamina de banana com leite',     14.50, 'Bebida',  4),

-- Churrascaria Brasa (id 5)
('Picanha Completa', 'Picanha + arroz + fritas',         79.90, 'Churrasco',5),
('Costela BBQ',      'Costela no molho barbecue',        69.90, 'Churrasco',5),
('Frango Grelhado',  'Frango grelhado + salada',         45.00, 'Churrasco',5);

-- =========================
-- PEDIDOS
-- =========================
INSERT INTO Pedido (Status, id_cliente, id_restaurante, id_entregador) VALUES
                                                                           ('ENTREGUE',   1, 1, 1),  -- Carlos no Burger House, entregue pelo Roberto
                                                                           ('ENTREGUE',   2, 2, 2),  -- Ana na Pizza Palace, entregue pela Fernanda
                                                                           ('EM_ENTREGA', 3, 3, 3),  -- Pedro no Sushi Zen, Diego entregando
                                                                           ('PRONTO',     4, 4, NULL),-- Julia no Açaí do Norte, aguardando entregador
                                                                           ('PRONTO',     5, 5, NULL),-- Lucas na Churrascaria, aguardando entregador
                                                                           ('EM_PREPARO', 6, 1, NULL),-- Mariana no Burger House, em preparo
                                                                           ('Aguardando', 1, 2, NULL); -- Carlos na Pizza Palace, recém feito

-- =========================
-- ITENS DOS PEDIDOS
-- =========================
INSERT INTO ItemPedido (Quantidade, PrecoUnit, id_pedido, id_produto) VALUES
-- Pedido 1 (Carlos - Burger House)
(1, 25.90, 1, 1),  -- X-Burguer
(1, 18.00, 1, 2),  -- Batata Frita
(2,  6.50, 1, 3),  -- 2x Refrigerante

-- Pedido 2 (Ana - Pizza Palace)
(1, 45.90, 2, 5),  -- Pizza Calabresa
(1,  6.50, 2, 3),  -- Refrigerante

-- Pedido 3 (Pedro - Sushi Zen)
(1, 59.90, 3, 9),  -- Combo Sushi
(1, 28.00, 3, 10), -- Temaki Salmão

-- Pedido 4 (Julia - Açaí do Norte)
(1, 30.00, 4, 13), -- Açaí 700ml
(1, 14.50, 4, 14), -- Vitamina Banana

-- Pedido 5 (Lucas - Churrascaria)
(1, 79.90, 5, 15), -- Picanha Completa
(1, 69.90, 5, 16), -- Costela BBQ

-- Pedido 6 (Mariana - Burger House)
(2, 25.90, 6, 1),  -- 2x X-Burguer
(1, 16.00, 6, 4),  -- Onion Rings

-- Pedido 7 (Carlos - Pizza Palace)
(1, 44.00, 7, 8),  -- Pizza Margherita
(1, 42.50, 7, 6);  -- Pizza Mussarela