drop table if exists pizzaingredients;
drop table if exists pizza;
drop table if exists ingredients;
Drop table if exists users;

create Table users(login text,pwd text);

create table ingredients (id int, name text, prix float,
primary key(id));

create table pizza(idP int ,name text,typePate text, prixBase float,
Primary Key(idP));

create Table commande(idC int,datec date,
Primary key (idC));

create table pizzaingredients(id int,idP int,
Primary key (idP,id),
foreign key(id) references ingredients(id) ON DELETE CASCADE,
foreign key(idP) references pizza(idP) ON DELETE CASCADE);

create Table commandepizzas(idC,idP)
Primary key (idC,idP),
foreign key(idC) references commande(idC) ON DELETE CASCADE,
foreign key(idP) references pizza(idP) ON DELETE CASCADE);

