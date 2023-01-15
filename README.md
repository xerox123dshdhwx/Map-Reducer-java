# Map Reduce En Java 

## Introdution
Nous avons décidé de réaliser notre map reduce multi-threading en Java, car c'est un langage qui gère bien les threads. De plus, l'orienté objet se prête bien au projet.
Nous avons donc séparé les différentes entités du map réducer dans plusieurs classes : **FileSpliter**, **Mapper**, **Shuffler**, **Reducer**, **DataStorage**.
La classe **DataStorage** sert base de donnée au programme. Les différentes classes y stockent leurs résultats et vont y chercher les données dont elles ont besoin.
Cela donne la possibilité de délocaliser les mapper ou les réducers sur d'autres machines sans causer de soucis de transfers de données puisque **DataStorage** joue le rôle d'intermédiaire entre les deux. 

## Partie Mapper
Nous commençons par faire un choix du nombre ***n*** de réducer (qui sera le même que le nombre de mapper) au début du main puis on split le fichier texte en n fichiers de tailles identiques.
Chaque mapper récupère en parallèle des autres mapper un fichier texte splité et compte les mots qui s'y trouvent avant de renvoyer son résultat à **DataStorage**.

## Partie Shuffler
Au début, nous voulions shuffle les mots en fonction du taux d'apparition de leur première lettre dans le texte. Nous aurions attribué à chaque réducer la liste des premières lettres dont la somme des pourcentages d'apparition est la plus proche possible de *100/nbReducer*. Cela nous aurait permis de mettre dans chaque réducer un nombre équivalent de mots et ainsi profiter au mieux du multi-threading en répartissant la charge de calcul.
Le problème de cette méthode est qu'il faut faire un pré-traitement du texte pour calculer des taux d'apparitions des lettres. Finalement, le temps gagné par l'optimisation est perdu au pré-traitement c'est pourquoi nous avons abandonné cette méthode (les traces de nos recherches se trouvent dans la classe **ShufflerWithFirstLetter**).

Nous avons donc préféré opter pour un shuffling plus classique. Notre shuffler va utiliser une liste prédéfinie avec un nombre ***N***
de **Map** vide. Chaque map ***N*** sera ensuite récupérer par le reducer ***N***.
On prend la taille d'un mot modulo le nombre de reducer existant et le résultat de ce calcul nous donne un 
chiffre. On décide ensuite d'affecter ce mot a la Map qui a le numéro identique à ce résultat.
Ainsi, on a une repartition plus ou moin homogène des mots pour chaque reducer et adaptable pour toutes 
les langues existantes ou sujet existant.

## Partie Reducer
Une fois le shuffling terminé, le reducer ***N*** récupère sa map à l'indice N de la liste dans **DataStorage**, et commencer à la traiter.
Le traitement de la map consiste à additionner la valeur d'apparition des mots.
Quand le traitement est terminé chaque reducer renvoie sa nouvelle map au dataStorage, qui va ensuite concaténer toutes les maps puis trier par ordre décroissant les mots.


## Temps d'exécution
Nous démarrons un chrono au début du programme qui s'arrête quand le dernier reducer a terminé de travailler. Cela nous permet de connaitre le temps d'exécution du programme en fonction de la taille du texte et du nombre de réducer que l'on choisi.