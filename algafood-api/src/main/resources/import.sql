insert into cozinha (id,nome) values (1,'Tailandesa');
insert into cozinha (id, nome) values (2,'Indiana');

insert into restaurante (nome, taxa_frete,cozinha_id) values ('Thai Dai Restaurante', 0.5, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Indy Car Resto', 10.2, 2);

insert into estado (id, nome) values (1, 'Pará');
insert into estado (id, nome) values (2, 'Maranhão');

insert into cidade (id, nome, estado_id) values (1,'Belém',1);
insert into cidade (id, nome, estado_id) values (2,'São Luís',2);

insert into forma_pagamento (id, descricao) values (1,'Dinheiro');
insert into forma_pagamento (id, descricao) values (2,'Crédito');
insert into forma_pagamento (id, descricao) values (3,'Débito');
insert into forma_pagamento (id, descricao) values (4,'Pix');

insert into permissao (id,nome,descricao) values (1,'ALT_RESTAUR', 'Alterar Restaurantes');
insert into permissao (id,nome,descricao) values (2,'DEL_RESTAUR', 'Excluir Restaurantes');





