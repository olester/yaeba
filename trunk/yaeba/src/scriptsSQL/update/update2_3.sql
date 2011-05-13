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