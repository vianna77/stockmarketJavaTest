create table hibernate_sequence (next_val bigint) engine=InnoDB
insert into hibernate_sequence values ( 1 )
create table quote (id bigint not null, date datetime(6), value integer, stock_quote_stock_id varchar(255), primary key (id)) engine=InnoDB
create table stock_quote (id varchar(255) not null, stock_id varchar(255), primary key (id)) engine=InnoDB
alter table stock_quote add constraint UK_fqgdb59m7fn287rq2936a88a0 unique (stock_id)
alter table quote add constraint FK3c0673kfmt51ipg4dhr0gja6l foreign key (stock_quote_stock_id) references stock_quote (stock_id)
