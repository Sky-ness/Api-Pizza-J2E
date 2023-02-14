drop table if exists pizzaingredients;
drop table if exists pizza;
drop table if exists ingredients;

create table ingredients (id int, name text, prix float,
primary key(id));

create table pizza(idP int ,name text,typePate text, prixBase float,
Primary Key(idP));

create table pizzaingredients(id int,idP int,
foreign key(id) references ingredients(id),
foreign key(idP) references pizza(idP));