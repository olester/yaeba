Conventions et Normalisation - Projet Yaeba

Les développeurs participants au projet suivront les conventions suivantes


Normalisation de l’éditeur Eclipse :
Pour automatiser le formatage du code dans eclipse:
Dans : Window > Preferences >Java > Editor > Save Actions

Cocher ”Perform the selected actions on save”

Puis cocher “Format source code”

Telecharger le profil de formattage de code : http://www.google.com/url?q=http%3A%2F%2Fyaeba.googlecode.com%2Ffiles%2Feclipse_formatter_settings.xml

Dans : - Window > Preferences >Java > Code Style > Formatter
> Cliquer sur “Import”, puis choisissez le fichier xml précedemment téléchargé.
> Cliquer sur “Apply” et “Ok”

Pour les fichiers XML :
> - Window > Preferences > XML > XML Files > Editor > Line Width à 120

Pour les fichiers JSP et HTML :
> - Window > Preferences > Web > HTML Files > Editor > Line Width à 120
> - Les pages JSP héritent leur configuration des pages HTML.



Convention de nommage Java :
Module:
Toujours en minuscules et sans caractères spéciaux (sauf -)
Doit se présenter sous la forme “nom du projet”-”Nom du Module”.
Ex : yaeba-dao-api
Package:
Toujours en minuscules et sans caractères spéciaux (donc pas de - ni  _)
Doit toujours commencer par “com.excilys.formation.yaeba”.
Ex : com.excilys.formation.yaeba.monpackage
Classes:
1ère lettre en majuscule et 1ère lettre de chaque mot en majuscule.
Pas de caractères spéciaux (donc pas de - ni_ ).

Ex : class MaClasseBienNommee

- Les noms des classes Data Access Objects doivent comporter le suffixe “DaoImpl”.
- Les noms des classes Services doivent comporter le suffixe “ServiceImpl”.
- Les classes Métiers doivent être en Français.
Interfaces:
Même convention de nommage que pour les classes, pas de “I” ou autres signe distinctif devant le nom de la classe.
Pour les implémentation on ajoute “Impl” en fin de nom de classe.

Ex : interface MonInterface
class MonInterfaceImpl

- Les noms des Interfaces Data Access Objects doivent comporter le suffixe “Dao”.
- Les noms des Interfaces Services doivent comporter le suffixe “Service”.
Méthodes:
1ère lettre en minuscule et 1ère lettre de chaque mot en majuscule.
Pas de caractère spéciaux (- ou _)._

Ex : public void maMethodeBienNommee( )

Une méthode doit se décomposer en plusieurs mots explicites. Tout mot ayant une signification fonctionnelle doit être en Anglais (Add, Delete, Read, …) à l’inverse des termes métier qui doivent être en français pour rester dans le jargon du client.

Ex : public Compte getCompte()

En cas de retour de plusieurs objets, ne pas oublier d’utiliser le pluriel dans la méthode.

Ex: public List

&lt;Compte&gt;

 getComptes()
Variables :
1ère lettre en minuscule et 1ère lettre de chaque mot en majuscule.
Pas de caractère spéciaux (- ou _)._

Ex : int maVariableBienNommee

Pour les paramètres des méthodes, précédez la variable de la lettre “p”.

Ex : int pMaVariableBienNommee
Constantes :
Toutes les lettres en majuscule, chaque mot étant séparé par un “_”_

Ex : final static int UNE\_CONSTANTE
Commentaires :
Commenter les classes/méthodes/attributs à la manière javadoc :
/
**….**/

Ne pas oublier d’utiliser les tags de la javadoc disponible ici.
Pages Web :
- Java Server Pages (JSP).
- Noms courts et intuitifs en conservant la logique métier.

Ex: index.jsp, detailsCompte.jsp, listComptes.jsp, …

- structurer les pages par dossier / module.
Ex : Compte, Virements, Errors, Templates, Ressources (Css et Js), etc etc …
SQL :
- Nom de table = Nom de la classe correspondante
- Nom des tables et colonnes en minuscules