package metrics;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Parser {
    
    public static void extractInfo(File fichier, List<String> lignesCSV) {

    String cheminFichier = fichier.getAbsolutePath();
    System.out.println(cheminFichier);
    String nomFichier = fichier.getPath(); // Get the file name
    String nomPaquet = "";
    String nomClasse = "";

    // Extract package and class name from the file path
    String[] pathSegments = cheminFichier.split("/src/test/java/");
    if (pathSegments.length == 2) {
        String[] packageAndClass = pathSegments[1].split("/");
        if (packageAndClass.length >= 2) {
            nomPaquet = String.join(".", Arrays.copyOf(packageAndClass, packageAndClass.length - 1));
            nomClasse = packageAndClass[packageAndClass.length - 1].replaceAll(".java$", "");
        }
    }

    lignesCSV.add(nomFichier + "," + nomPaquet + "," + nomClasse);
    }
        
}
