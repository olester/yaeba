SCRIPT SQL


INSERT INTO role (type) VALUES ("ROLE\_USER");
INSERT INTO role (type) VALUES ("ROLE\_ADMIN");

INSERT INTO utilisateur (adresse, login, password, nom, prenom) VALUES ("43 rue de la joie", "user", "user", "Utilisateur", "Lambda");
INSERT INTO utilisateur (adresse, login, password, nom, prenom) VALUES ("102 missile fixe", "admin", "admin", "Utilisateur", "Administrateur");

INSERT INTO role\_utilisateur (user\_id, role\_id) VALUES (1, 1);
INSERT INTO role\_utilisateur (user\_id, role\_id) VALUES (2, 1);
INSERT INTO role\_utilisateur (user\_id, role\_id) VALUES (2, 2);

UPDATE utilisateur SET password = "b14361404c078ffd549c03db443c3fede2f3e534d73f78f77301ed97d4a436a9fd9db05ee8b325c0ad36438b43fec8510c204fc1c1edb21d0941c00e9e2c1ce2" WHERE login='user';

UPDATE utilisateur SET password = "c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec" WHERE login='admin';

+

I18N