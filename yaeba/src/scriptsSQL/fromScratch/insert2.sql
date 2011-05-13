INSERT INTO `role` (`id`, `type`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

INSERT INTO `utilisateur` (`id`, `adresse`, `login`, `password`, `nom`, `prenom`) VALUES
(1, 'En france', 'user', 'b14361404c078ffd549c03db443c3fede2f3e534d73f78f77301ed97d4a436a9fd9db05ee8b325c0ad36438b43fec8510c204fc1c1edb21d0941c00e9e2c1ce2', 'Virenque', 'Richard'),
(2, 'Pyong Yang', 'admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 'Jong-il', 'Kim');

INSERT INTO `roleutilisateur` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(2, 2);

INSERT INTO `compte` (`id`, `datecreation`, `libelle`, `numerocompte`, `soldecourant`, `utilisateur_id`,`cards`) VALUES
(1, '2010-11-30 00:00:00', 'Compte courant', '123777', 258.586, 1 ,1),
(2, '2010-11-30 00:00:00', 'PEL', '789333', 24200, 1,0),
(3, '2010-02-01 00:00:00', 'Compte jeune', '666', 1000252.2, 2,0);

INSERT INTO `operation` (`id`, `compte_id`, `datecreation`, `libelle`, `montant`, `discriminator`) VALUES
(1, 1, '2011-01-10 00:00:00', 'Achat velo', -1735.42, 'OPERATIONCARTEBANCAIRE'),
(2, 1, '2011-03-14 00:00:00', 'Achat EPO', -254.2, 'OPERATIONCARTEBANCAIRE'),
(3, 1, '2011-01-24 00:00:00', 'Cadeaux', -550.4, 'OPERATIONCHEQUE'),
(4, 2, '2011-01-24 00:00:00', 'Cadeaux', -550.4, 'OPERATIONCHEQUE'),
(5, 2, '2011-03-20 00:00:00', 'Virement PEL Mars', -1200, 'OPERATIONVIREMENTINTERNE'),
(6, 2, '2011-04-20 00:00:00', 'Virement PEL Avril', -1400, 'OPERATIONVIREMENTINTERNE'),
(7, 3, '2011-05-04 00:00:00', 'Achat missiles', -420000, 'OPERATIONCARTEBANCAIRE'),
(8, 3, '2011-04-08 00:00:00', 'Mac Do', -12.9, 'OPERATIONCARTEBANCAIRE'),
(9, 3, '2011-03-21 00:00:00', 'Course carrefour', -550.4, 'OPERATIONCHEQUE'),
(10, 3, '2011-02-01 00:00:00', 'Ak47', -125000, 'OPERATIONCHEQUE'),
(11, 1, '2011-01-01 00:00:00', 'Paye janvier', 1000, 'OPERATIONVIREMENTINTERNE'),
(12, 1, '2011-02-01 00:00:00', 'Paye février', 1010, 'OPERATIONVIREMENTINTERNE'),
(13, 1, '2011-03-01 00:00:00', 'Paye mars', 1020, 'OPERATIONVIREMENTINTERNE'),
(14, 1, '2011-04-01 00:00:00', 'Paye avril', 1030, 'OPERATIONVIREMENTINTERNE'),
(15, 1, '2011-05-01 00:00:00', 'Paye mai', 1040, 'OPERATIONVIREMENTINTERNE'),
(16, 1, '2011-01-10 00:00:00', 'Mac Do 1', -11, 'OPERATIONCARTEBANCAIRE'),
(17, 1, '2011-01-15 00:00:00', 'Mac Do 2', -17.35, 'OPERATIONCARTEBANCAIRE'),
(18, 1, '2011-02-14 00:00:00', 'Mac Do 3', -9.80, 'OPERATIONCARTEBANCAIRE'),
(19, 1, '2011-02-18 00:00:00', 'Mac Do 4', -5.56, 'OPERATIONCARTEBANCAIRE'),
(20, 1, '2011-03-14 00:00:00', 'Mac Do 5', -11.4, 'OPERATIONCARTEBANCAIRE'),
(21, 1, '2011-03-10 00:00:00', 'Mac Do 6', -25.1, 'OPERATIONCARTEBANCAIRE'),
(22, 1, '2011-04-14 00:00:00', 'Mac Do 7', -9.99, 'OPERATIONCARTEBANCAIRE'),
(23, 1, '2011-05-01 00:00:00', 'Opé mai 1', -1, 'OPERATIONCARTEBANCAIRE'),
(24, 1, '2011-05-01 00:00:00', 'Opé mai 2', -1, 'OPERATIONCARTEBANCAIRE'),
(25, 1, '2011-05-01 00:00:00', 'Opé mai 3', -1, 'OPERATIONCARTEBANCAIRE'),
(26, 1, '2011-05-01 00:00:00', 'Opé mai 4', -1, 'OPERATIONCARTEBANCAIRE'),
(27, 1, '2011-05-01 00:00:00', 'Opé mai 5', -1, 'OPERATIONCARTEBANCAIRE'),
(28, 1, '2011-05-01 00:00:00', 'Opé mai 6', -1, 'OPERATIONCARTEBANCAIRE'),
(29, 1, '2011-05-01 00:00:00', 'Opé mai 7', -1, 'OPERATIONCARTEBANCAIRE'),
(30, 1, '2011-05-01 00:00:00', 'Opé mai 8', -1, 'OPERATIONCARTEBANCAIRE'),
(31, 1, '2011-05-01 00:00:00', 'Opé mai 9', -1, 'OPERATIONCARTEBANCAIRE'),
(32, 1, '2011-05-01 00:00:00', 'Opé mai 10', -1, 'OPERATIONCARTEBANCAIRE'),
(33, 1, '2011-05-01 00:00:00', 'Opé mai 11', -1, 'OPERATIONCARTEBANCAIRE'),
(34, 1, '2011-05-01 00:00:00', 'Opé mai 12', -1, 'OPERATIONCARTEBANCAIRE'),
(35, 1, '2011-05-01 00:00:00', 'Opé mai 13', -1, 'OPERATIONCARTEBANCAIRE'),
(36, 1, '2011-05-01 00:00:00', 'Opé mai 14', -1, 'OPERATIONCARTEBANCAIRE'),
(37, 1, '2011-05-01 00:00:00', 'Opé mai 15', -1, 'OPERATIONCARTEBANCAIRE');

-- INSERT Carte Bancaire
INSERT INTO `operationcartebancaire` (`id`, `dateeffective`) VALUES
(1, '2011-01-10 00:00:00'),
(2, '2011-03-14 00:00:00'),
(7, '2011-05-04 00:00:00'),
(8, '2011-04-08 00:00:00'),
(11, '2011-01-14 00:00:00'),
(12, '2011-01-10 00:00:00'),
(13, '2011-01-14 00:00:00'),
(14, '2011-01-10 00:00:00'),
(15, '2011-01-14 00:00:00'),
(16, '2011-01-10 00:00:00'),
(17, '2011-01-15 00:00:00'),
(18, '2011-02-14 00:00:00'),
(19, '2011-02-18 00:00:00'),
(20, '2011-03-14 00:00:00'),
(21, '2011-03-10 00:00:00'),
(22, '2011-04-14 00:00:00'),
(23, '2011-05-01 00:00:00'),
(24, '2011-05-01 00:00:00'),
(25, '2011-05-01 00:00:00'),
(26, '2011-05-01 00:00:00'),
(27, '2011-05-01 00:00:00'),
(28, '2011-05-01 00:00:00'),
(29, '2011-05-01 00:00:00'),
(30, '2011-05-01 00:00:00'),
(31, '2011-05-01 00:00:00'),
(32, '2011-05-01 00:00:00'),
(33, '2011-05-01 00:00:00'),
(34, '2011-05-01 00:00:00'),
(35, '2011-05-01 00:00:00'),
(36, '2011-05-01 00:00:00'),
(37, '2011-05-01 00:00:00');

-- INSERT Cheque
INSERT INTO `operationcheque` (`id`, `numerocheque`) VALUES
(3, 1000122135),
(4, 1052454135),
(9, 3546541),
(10, 346456543);


-- INSERT Virement Interne
INSERT INTO `operationvirementinterne` (`id`, `dateeffective`, `comptedebiteur`, `compterecepteur`) VALUES
(5, '2011-03-20 00:00:00', 1, 2),
(6, '2011-04-20 00:00:00', 1, 2);