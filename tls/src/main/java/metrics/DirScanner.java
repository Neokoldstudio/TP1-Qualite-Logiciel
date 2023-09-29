package metrics;

import java.io.File;
import java.util.List;

public class DirScanner {

    public static void traverseFolder(String folder, List<String> linesCSV) {

        File currentFolder = new File(folder);
        File[] files = currentFolder.listFiles();

        // If there is another folder, keep traversing, if not extract file's information
        if (files != null) {
            for (File fichier : files) {
                if (fichier.isDirectory()) {
                    traverseFolder(fichier.getPath(), linesCSV);
                } else if (fichier.isFile() && fichier.getName().endsWith(".java")) {
                    Parser.extractInfo(fichier, linesCSV);
                }
            }
        }
    }
}
