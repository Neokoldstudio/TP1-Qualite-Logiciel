package metrics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


public class Tls {

    public static void main(String[] args){

        Options options = new Options();
        options.addOption("o", true, "Chemin de sortie du fichier CSV");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            String dossier = cmd.getArgs()[0];
            List<String> lignesCSV = new ArrayList<>();
            lignesCSV.add("Chemin du fichier,Nom du paquet,Nom de la classe");

            DirScanner.traverseFolder(dossier, lignesCSV);

            if (cmd.hasOption("o")) {
                String cheminSortie = cmd.getOptionValue("o");
                enregistrerDansFichierCSV(cheminSortie, lignesCSV);
                System.out.println("Le fichier CSV a été généré avec succès : " + cheminSortie);
            } else {
                afficherSurLaConsole(lignesCSV);
            }
        } catch (ParseException e) {
            System.err.println("Erreur lors de l'analyse des arguments de ligne de commande.");
            e.printStackTrace();
        }
        
    }

    private static void enregistrerDansFichierCSV(String cheminSortie, List<String> lignesCSV) {
        try {
            Files.write(Paths.get(cheminSortie), lignesCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void afficherSurLaConsole(List<String> lignesCSV) {
        for (String ligne : lignesCSV) {
            System.out.println(ligne);
        }
    }

}
