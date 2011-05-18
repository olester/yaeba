UPDATE `operation` SET `compte_id` = 1, `libelle` = 'Compte courant -> PEL (mars)', `montant` = '-1000' WHERE `id` = 5;
UPDATE `operation` SET `libelle` = 'PEL -> Compte courant (avril)', `montant` = '-1400' WHERE `id` = 6;
UPDATE `operationvirementinterne` SET `comptedistant` = '2' WHERE `id` = 5;
UPDATE `operationvirementinterne` SET `comptedistant` = '1' WHERE `id` = 6;

INSERT INTO `operation` (`id`, `compte_id`, `datecreation`, `libelle`, `montant`) VALUES
(38, 2, '2011-03-20 00:00:00', 'Compte courant -> PEL (mars)', 1000),
(39, 1, '2011-04-20 00:00:00', 'PEL -> Compte courant (avril)', 1400);
INSERT INTO `operationvirementinterne` (`id`, `comptedistant`) VALUES
(38, 1),
(39, 2);

DELETE FROM `operationcartebancaire` WHERE `id` > 18;

UPDATE `operationcartebancaire` SET `datecreation` = '2011-05-04 00:00:00' WHERE `id` > 12;

INSERT INTO `operationcheque` (`id`, `numerocheque`) VALUES
(19, 19000),
(20, 20000),
(21, 21000),
(22, 22000),
(23, 23000),
(24, 24000),
(25, 25000),
(26, 26000),
(27, 27000),
(28, 28000),
(29, 29000),
(30, 30000),
(31, 31000),
(32, 32000),
(33, 33000),
(34, 34000),
(35, 35000),
(36, 36000),
(37, 37000);