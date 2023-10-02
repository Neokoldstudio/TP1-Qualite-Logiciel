package metrics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Tls {

    public static void main(String[] args) {

        String outputFilePath = null;
        List<String> arguments = new ArrayList<>();

        // Parse command line arguments manually
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if ("-o".equals(arg) && i < args.length - 1) {
                outputFilePath = args[i + 1];
                i++; // Skip the next argument (the output file path)
            } else {
                arguments.add(arg);
            }
        }

        if (arguments.isEmpty()) {
            System.err.println("Usage: Tls [-o outputFilePath] folderPath");
            return;
        }

        String folderPath = arguments.get(0);
        List<String> csvLines = new ArrayList<>();
        csvLines.add("Chemin du fichier,Nom du paquet,Nom de la classe,Tloc");

        // Populate csvLines with the required metrics
        Scanner.traverseFolder(folderPath, csvLines);

        // Either display the result on the command line or save a CSV file.
        if (outputFilePath != null) {
            enregistrerDansFichierCSV(outputFilePath, csvLines);
            System.out.println("Le fichier CSV a été généré avec succès : " + outputFilePath);
        } else {
            afficherSurLaConsole(csvLines);
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
