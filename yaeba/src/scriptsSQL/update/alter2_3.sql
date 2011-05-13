ALTER TABLE `operationvirementinterne` DELETE COLUMN `comptedebiteur`;
ALTER TABLE `operationvirementinterne` DELETE COLUMN `compterecepteur`;
ALTER TABLE `operationvirementinterne` DELETE COLUMN `dateeffective`;
ALTER TABLE `operationvirementinterne` ADD COLUMN `comptedistant` int(11) DEFAULT NULL;
ALTER TABLE `operationvirementinterne` ADD `operationvirementinterne_comptedistant` FOREIGN KEY (`comptedistant`) REFERENCES `compte` (`id`);