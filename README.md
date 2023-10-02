# TP1-Qualite-Logiciel

## Introduction

Travail pratique 1 du cours de Qualité de Logiciel et Métriques. Ce projet consiste en l'évaluation de diverses métriques pour les suites de tests de logiciels Java, en utilisant la bibliothèque JUnit.

## Comment Utiliser

Pour exécuter les programmes, il suffit de lancer les fichiers .jar situés dans le répertoire 'jars'.

### Tloc
```sh
java -jar jars/tloc.jar [chemin de la classe de test Java]
```

### Tls
```sh
java -jar jars/tls.jar [chemin du répertoire]
```

## Comment construire les projets

Si vous souhaitez créer le package vous-même, vous devez utiliser `mvn package`.

### Tloc

```sh
mvn clean package
```

Le fichier .jar résultant sera généré dans le répertoire `target/`.

### Tls
Pour TLS, vous devez d'abord installer la dépendance Tloc.

```sh
mvn install:install-file -Dfile=../jars/tloc.jar -DgroupId=ca.UdeM.Informatique -DartifactId=tloc -Dversion=0.1.0 -Dpackaging=jar
```

Ensuite, vous pouvez procéder à l'installation :

```sh
mvn clean package
```

