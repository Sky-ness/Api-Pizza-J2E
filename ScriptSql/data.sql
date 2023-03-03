INSERT INTO ingredient values
(1,'pomme de terre',0.8),
(2,'oignons',0.5),
(3,'poivrons',0.6),
(4,'basilic',0.4),
(5,'miel',1.2),
(6,'champignons',0.85),
(7,'olives',0.65),
(8,'artichaut',0.63),
(9,'roquette',0.45),
(10,'tomate',0.3),
(11,'oeuf',0.8),
(12,'citron',0.4),
(13,'origan',0.3),
(14,'aubergine',0.98),
(15,'anchois',1.5),
(16,'jambon',1.8),
(17,'lardons',1.9),
(18,'poulet',1.4),
(19,'saumon',1.9),
(20,'magret',2.5),
(21,'merguez',1.6),
(22,'chorizo',1.4),
(23,'pepperoni',1.4),
(24,'mozzarella',0.6),
(25,'chevre',1.2),
(26,'gorgonzola',0.9),
(27,'fromage',0.85),
(28,'parmesan',0.7),
(29,'brie',0.9);

INSERT INTO pizza VALUES
(1,'Margarita','Napolitaine',1.5),
(2,'Pesto','Romaine',2),
(3,'4Fromage','Chicago',1.75),
(4,'Reine','Napolitaine',1.5),
(5,'4Saison','Crousti',2.25),
(6,'Sicilienne','Foccacia',2),
(7,'Calzone','Napolitaine',1.5),
(8,'Peperonni','Romaine',2);

INSERT INTO pizzaingredients VALUES
(1,10),
(1,24),
(1,4),
(3,24),
(3,25),
(3,26),
(3,28),
(4,6),
(4,10),
(4,16),
(4,24);

Insert into commande values
('1','2023-01-25'),
('2','2023-01-23'),
('3','2023-01-30'),
('4','2023-01-29'),
('5','2023-01-28'),
('6','2023-01-25'),
('7','2023-01-30'),
('8','2023-01-28'),
('9','2023-02-01'),
('10','2023-02-03'),
('11','2023-01-27');

Insert into users values
('1','Jean','1234','user'),
('2','Benoit','1234','user'),
('3','Antonin','1234','admin'),
('4','Mehdi','1234','user'),
('5','Gwenael','1234','user'),
('6','Cedric','1234','user');

Insert into commandepizzas values
('1','1'),
('1','2'),
('1','3'),
('4','3'),
('3','1'),
('3','4'),
('5','3'),
('5','1'),
('6','4'),
('6','3');

