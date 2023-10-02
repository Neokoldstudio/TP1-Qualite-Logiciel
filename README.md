# TP1-Qualite-Logiciel

**Membres du groupe**:
- André Meneses
- Paul Godbert

## Introduction

Ce projet représente le travail pratique 1 du cours de Qualité de Logiciel et Métriques. L'objectif principal est d'évaluer diverses métriques pour les suites de tests de logiciels Java utilisant la bibliothèque JUnit.

## Utilisation

Pour exécuter les programmes, vous pouvez suivre les instructions ci-dessous.

### Tloc

Pour exécuter le programme Tloc, utilisez la commande suivante:

```sh
java -jar jars/tloc.jar [chemin de la classe de test Java]
```

### Tassert

Pour exécuter le programme Tassert, utilisez la commande suivante:

```sh
java -jar jars/tassert.jar [chemin de la classe de test Java]
```

### Tls

Pour exécuter le programme Tls, utilisez la commande suivante:

```sh
java -jar jars/tls.jar -o [chemin du fichier CSV de sortie] [chemin du répertoire]
```

## Compilation avec Maven

Si vous souhaitez construire les projets vous-même, vous pouvez utiliser Maven. Voici comment procéder pour chaque projet:

### Tloc

Pour construire le projet Tloc, exécutez la commande suivante:

```sh
mvn package
```

### Tassert

Pour construire le projet Tassert, exécutez la commande suivante:

```sh
mvn package
```

### Tls

Pour construire le projet Tls, vous devez d'abord installer les dépendances Tloc et Tassert. Utilisez les commandes suivantes:

```sh
mvn install:install-file -Dfile=../jars/tloc.jar -DgroupId=ca.UdeM.Informatique -DartifactId=tloc -Dversion=0.1.0 -Dpackaging=jar
mvn install:install-file -Dfile=../jars/tassert.jar -DgroupId=ca.UdeM.Informatique -DartifactId=tassert -Dversion=0.1.0 -Dpackaging=jar
```

Ensuite, vous pouvez procéder à la construction du projet Tls avec la commande suivante:

```sh
mvn package
```

Dans tous les cas, le fichier .jar résultant sera généré dans le répertoire `target/`.
