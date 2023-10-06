# TP1-Qualite-Logiciel

**Membres du groupe**:
- André Meneses
- Paul Godbert

## Introduction

Ce projet représente le travail pratique 1 du cours de Qualité de Logiciel et Métriques. L'objectif principal est d'évaluer diverses métriques pour les suites de tests de logiciels Java utilisant la bibliothèque JUnit.

## Structure des programmes

### Tloc

Le programme Tloc prend en paramètre le chemin d'accès du fichier Java à analyser. Pour lire le contenu du fichier, nous utilisons un objet *BufferedReader*. Chaque ligne du programme est examinée, et finalement, nous renvoyons la valeur de *totalLines - emptyLines - commentaryLines*, ce qui représente le Tloc (Total Lines of Code).

#### Utilisation

Pour exécuter tloc, il suffit d'utiliser le commande suivante:

```sh
java -jar jars/tloc.jar <chemin de la classe de test Java>
```

La réponse sera dans la ligne de commande. 

Si vous souhaitez construire les projets vous-même, vous pouvez utiliser Maven:

```sh
cd tloc
mvn package
```

Le fichier .jar résultant sera dans le dossier `./tloc/target/`

### Tassert
Le programme Tassert prend en paramètre de la même manière que Tloc, le chemin d'accès de la classe de test Java à analyser. De manière similaire, elle utilise un objet *BufferedReader* pour examiner le contenu du fichier. Pour repérer les "assertions", nous utilisons des expressions régulières pour identifier les mots qui correspondent au motif 'assert' ou 'fail'. Nous tentons d'ignorer les commentaires en divisant les lignes contenant "//" et en analysant uniquement la première partie de la division. À la fin, nous renvoyons la valeur de *tassert*, qui représente la quantité de motifs regex que nous avons trouvés.

#### Utilisation

Pour exécuter tassert, il suffit d'utiliser le commande suivante:
```sh
java -jar jars/tassert.jar <chemin de la classe de test Java>
```
La réponse sera dans la ligne de commande. 

Si vous souhaitez construire le projet vous-même, vous pouvez utiliser Maven:
```sh
cd tassert
mvn package
```
Le fichier .jar résultant sera dans le dossier `./tassert/target/`

### Tls

Le Tls prend en paramètre le chemin du dossier de test en entrée, ainsi qu'un paramètre optionnel correspondant au chemin du fichier CSV de sortie. Pour la sortie, il crée une ArrayList de chaînes de caractères et ajoute les noms des colonnes à la première ligne de cette liste. Ensuite, il appelle la méthode traverseFolder de la classe Scanner pour parcourir le dossier et remplir l'ArrayList avec les métriques relatives à chaque fichier. L'analyse du dossier d'entrée est effectuée de manière récursive, donc s'il y a d'autres sous-dossiers, la méthode traverseFolder est également appelée pour les explorer. Sinon, pour chaque fichier rencontré, la méthode extractInfo est appelée pour extraire des informations qui sont ajoutées à l'ArrayList de strings.

Pour obtenir le nom de la classe et du fichier, on utilise l'analyse du chemin absolu de chaque fichier. On suppose que le nom du fichier .java correspond au nom de la classe, et les parties du chemin qui se trouvent après "src/java/test" constituent le nom du package. On a opté pour cette approche car elle est plus efficace et moins complexe que l'analyse du contenu de chaque fichier pour extraire ces informations.

Le calcul de tloc et tassert est basé sur les dépendances 'tloc' et 'tassert', c'est-à-dire les JARs que nous avons créés précédemment. Pour les fichiers dont la valeur de tassert est nulle, ils sont ignorés car ils ne contiennent pas de tests.

Enfin, le contenu de l'ArrayList est affiché dans la ligne de commande ou sauvegardé dans le fichier CSV, en fonction de l'option choisie en entrée.

#### Utilisation
```sh
java -jar jars/tls.jar -o <chemin du fichier CSV de sortie> <chemin du répertoire de test Java>
```

Pour construire le projet, il faut d'abord installer les dépendences, c'est-à-dire les jars 'tloc' et 'tassert'.

```sh
cd tls/
mvn install:install-file -Dfile=../jars/tloc.jar -DgroupId=ca.UdeM.Informatique -DartifactId=tloc -Dversion=0.1.0 -Dpackaging=jar
mvn install:install-file -Dfile=../jars/tassert.jar -DgroupId=ca.UdeM.Informatique -DartifactId=tassert -Dversion=0.1.0 -Dpackaging=jar
```
Ensuite, la procedure est la même:
```sh
mvn package
```
Et le fichier résultant sera dans `target/`

### Tropcomp
Pour le programme "tropcomp", nous avons choisi de ne pas utiliser de dépendances externes, et nous avons copié le code du programme "tls" directement dans "tropcomp". Par conséquent, ces deux programmes fonctionnent de manière indépendante. Les arguments d'entrée pour "tropcomp" sont les mêmes que ceux de "tls", et l'analyse des métriques pour les classes Java est effectuée à partir de la classe Analyser.

#### Utilisation
```sh
java -jar jars/tropcomp.jar -o <chemin du fichier CSV de sortie> <chemin du répertoire de test Java>
```
Si vous souhaitez construire le projet vous-même, vous pouvez utiliser Maven:
```sh
cd tropcomp
mvn package
```
Le fichier .jar résultant sera dans le dossier `./tropcomp/target/`

