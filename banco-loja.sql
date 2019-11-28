select * from loja_cidade;

insert into loja_cidade
(nome, estado) values ('Sao Leopoldo', 'RS');

commit;

rollback;

create table bkp_loja_cidade 
as select * from loja_cidade;

select * from bkp_loja_cidade order by nome;

