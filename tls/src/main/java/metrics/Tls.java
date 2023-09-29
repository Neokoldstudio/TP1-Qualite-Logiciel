package metrics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Tls {

    public static void main(String[] args){

        if (args.length != 1) {
            System.out.println("You must provide one folder path");
            return;
        }

        String dossier = args[0];
        List<String> lignesCSV = new ArrayList<>();

        lignesCSV.add("Chemin du fichier,Nom du paquet,Nom de la classe");

        DirScanner.traverseFolder(dossier, lignesCSV);

        try {
            Files.write(Paths.get("resultat.csv"), lignesCSV);
            System.out.println("Le fichier CSV 'resultat.csv' a été généré avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
