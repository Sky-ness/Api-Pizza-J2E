drop table if exists pizzaingredients;
drop table if exists commandepizzas;
drop table if exists pizza;
drop table if exists ingredient;
drop table if exists commande;
Drop table if exists users;

create Table users(idU int,login text,pwd text,
primary key(idU));

create table ingredient(id int, name text, prix float,
primary key(id));

create table pizza(idP int ,name text,typePate text, prixBase float,
Primary Key(idP));

create Table commande(idC int,dateC date,
Primary key (idC));

create table pizzaingredients(idP int,id int,
Primary key (idP,id),
foreign key(idP) references pizza(idP) ON DELETE CASCADE,
foreign key(id) references ingredient(id) ON DELETE CASCADE);

create Table commandepizzas(idC int,idU int,idP int,
Primary key (idC,idU,idP),
foreign key(idC) references commande(idC) ON DELETE CASCADE,
foreign key(idU) references users(idU) ON DELETE CASCADE,
foreign key(idP) references pizza(idP) ON DELETE CASCADE);
