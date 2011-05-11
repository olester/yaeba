SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
--
-- Database: `yaeba`
--
--
-- Table structure for table `compte`
--
CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreation` datetime NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `numerocompte` varchar(255) NOT NULL,
  `soldecourant` double NOT NULL,
  `utilisateur_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `numerocompte` (`numerocompte`),
  KEY `compte_utilisateur` (`utilisateur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;
--
-- Dumping data for table `compte`
--


-- --------------------------------------------------------

--
-- Table structure for table `operation`
--

CREATE TABLE IF NOT EXISTS `operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datecreation` datetime NOT NULL,
  `discriminator` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) NOT NULL,
  `montant` double NOT NULL,
  `compte_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `operation_compte` (`compte_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `operation`
--


-- --------------------------------------------------------

--
-- Table structure for table `operationcartebancaire`
--

CREATE TABLE IF NOT EXISTS `operationcartebancaire` (
  `dateeffective` datetime NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `operationcartebancaire_operation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `operationcartebancaire`
--


-- --------------------------------------------------------

--
-- Table structure for table `operationcheque`
--

CREATE TABLE IF NOT EXISTS `operationcheque` (
  `numerocheque` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `operationcheque_operation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `operationcheque`
--


-- --------------------------------------------------------

--
-- Table structure for table `operationvirementinterne`
--

CREATE TABLE IF NOT EXISTS `operationvirementinterne` (
  `dateeffective` datetime NOT NULL,
  `id` int(11) NOT NULL,
  `comptedebiteur` int(11) DEFAULT NULL,
  `compterecepteur` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `operationvirementinterne_operation` (`id`),
  KEY `operationvirementinterne_compterecepteur` (`compterecepteur`),
  KEY `operationvirementinterne_comptedebiteur` (`comptedebiteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `operationvirementinterne`
--


-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `role`
--


-- --------------------------------------------------------

--
-- Table structure for table `roleutilisateur`
--

CREATE TABLE IF NOT EXISTS `roleutilisateur` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `roleutilisateur_role` (`role_id`),
  KEY `roleutilisateur_utilisateur` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `roleutilisateur`
--


-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) DEFAULT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `utilisateur`
--


--
-- Constraints for dumped tables
--

--
-- Constraints for table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `compte_utilisateur` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Constraints for table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `operation_compte` FOREIGN KEY (`compte_id`) REFERENCES `compte` (`id`);

--
-- Constraints for table `operationcartebancaire`
--
ALTER TABLE `operationcartebancaire`
  ADD CONSTRAINT `operationcartebancaire_operation` FOREIGN KEY (`id`) REFERENCES `operation` (`id`);

--
-- Constraints for table `operationcheque`
--
ALTER TABLE `operationcheque`
  ADD CONSTRAINT `operationcheque_operation` FOREIGN KEY (`id`) REFERENCES `operation` (`id`);

--
-- Constraints for table `operationvirementinterne`
--
ALTER TABLE `operationvirementinterne`
  ADD CONSTRAINT `operationvirementinterne_comptedebiteur` FOREIGN KEY (`comptedebiteur`) REFERENCES `compte` (`id`),
  ADD CONSTRAINT `operationvirementinterne_operation` FOREIGN KEY (`id`) REFERENCES `operation` (`id`),
  ADD CONSTRAINT `operationvirementinterne_compterecepteur` FOREIGN KEY (`compterecepteur`) REFERENCES `compte` (`id`);

--
-- Constraints for table `roleutilisateur`
--
ALTER TABLE `roleutilisateur`
  ADD CONSTRAINT `roleutilisateur_utilisateur` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `roleutilisateur_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
