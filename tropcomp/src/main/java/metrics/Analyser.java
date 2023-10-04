package metrics;

import java.util.ArrayList;
import java.util.List;

public class Analyser {
    public static void analyseProject(String folder, int threshold, List<String> linesCSV) {
        List<String> scannedFiles = new ArrayList<>();
        Scanner.traverseFolder(folder, scannedFiles); 
    }
}
