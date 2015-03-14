# Introduction #

il ne s'agit que d'un dump genere par phpmyadmin


# Details #

-- phpMyAdmin SQL Dump
-- version 3.2.2.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: May 05, 2011 at 06:13 PM
-- Server version: 5.1.37
-- PHP Version: 5.2.10-2ubuntu6.10

SET SQL\_MODE="NO\_AUTO\_VALUE\_ON\_ZERO";

--
-- Database: `yaeba`
--

-- --------------------------------------------------------

--
-- Table structure for table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
> `id` int(11) NOT NULL AUTO\_INCREMENT,
> `datecreation` datetime NOT NULL,
> `libelle` varchar(255) NOT NULL,
> `numerocompte` varchar(255) NOT NULL,
> PRIMARY KEY (`id`),
> UNIQUE KEY `id` (`id`),
> UNIQUE KEY `numerocompte` (`numerocompte`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO\_INCREMENT=1 ;

--
-- Dumping data for table `compte`
--


-- --------------------------------------------------------

--
-- Table structure for table `compte_utilisateur`
--

CREATE TABLE IF NOT EXISTS `compte_utilisateur` (
> `user_id` int(11) NOT NULL,
> `compte_id` int(11) NOT NULL,
> PRIMARY KEY (`user_id`,`compte_id`),
> UNIQUE KEY `compte_id` (`compte_id`),
> KEY `FK835F27841D6BCD90` (`user_id`),
> KEY `FK835F2784E0DDE6FC` (`compte_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `compte_utilisateur`
--


-- --------------------------------------------------------

--
-- Table structure for table `operation`
--

CREATE TABLE IF NOT EXISTS `operation` (
> `id` int(11) NOT NULL AUTO\_INCREMENT,
> `datecreation` datetime NOT NULL,
> `libelle` varchar(255) NOT NULL,
> `montant` int(11) NOT NULL,
> PRIMARY KEY (`id`),
> UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO\_INCREMENT=1 ;

--
-- Dumping data for table `operation`
--


-- --------------------------------------------------------

--
-- Table structure for table `operation_compte`
--

CREATE TABLE IF NOT EXISTS `operation_compte` (
> `operation_id` int(11) NOT NULL,
> `compte_id` int(11) NOT NULL,
> PRIMARY KEY (`operation_id`,`compte_id`),
> UNIQUE KEY `compte_id` (`compte_id`),
> KEY `FK4243CB985F1DCCDF` (`compte_id`),
> KEY `FK4243CB9823B5D1F5` (`operation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `operation_compte`
--


-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
> `id` int(11) NOT NULL AUTO\_INCREMENT,
> `type` varchar(255) NOT NULL,
> PRIMARY KEY (`id`),
> UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO\_INCREMENT=3 ;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `type`) VALUES
(1, 'ROLE\_USER'),
(2, 'ROLE\_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `role_utilisateur`
--

CREATE TABLE IF NOT EXISTS `role_utilisateur` (
> `user_id` int(11) NOT NULL,
> `role_id` int(11) NOT NULL,
> PRIMARY KEY (`user_id`,`role_id`),
> KEY `FK845D113A12ACBBC` (`role_id`),
> KEY `FK845D113A1D6BCD90` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role_utilisateur`
--

INSERT INTO `role_utilisateur` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
> `id` int(11) NOT NULL AUTO\_INCREMENT,
> `adresse` varchar(255) DEFAULT NULL,
> `login` varchar(255) NOT NULL,
> `password` varchar(255) NOT NULL,
> `nom` varchar(255) NOT NULL,
> `prenom` varchar(255) NOT NULL,
> PRIMARY KEY (`id`),
> UNIQUE KEY `id` (`id`),
> UNIQUE KEY `login` (`login`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO\_INCREMENT=100 ;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `adresse`, `login`, `password`, `nom`, `prenom`) VALUES
(1, '43 rue de la joie', 'user', 'b14361404c078ffd549c03db443c3fede2f3e534d73f78f77301ed97d4a436a9fd9db05ee8b325c0ad36438b43fec8510c204fc1c1edb21d0941c00e9e2c1ce2', 'Utilisateur', 'Lambda'),
(2, '102 missile fixe', 'admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 'Utilisateur', 'Administrateur');