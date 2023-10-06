package metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Extractor {

    public static void extractInfo(File file, List<String> csvLines) {

        String filePath = file.getAbsolutePath();
        String fileName = file.getPath(); // Get the file name
        String packageName = "";
        String className = "";

        // Calculate tloc
        int tloc = Tloc.tloc(fileName);

        // Calculate tassert
        int tassert = Tassert.countTAsserts(fileName);

        if(tassert == 0) return;

        float tcmp = ((float) tloc / tassert); 

        // Extract package and class name from the file path
        String[] pathSegments = filePath.split(File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator);

        String[] packageAndClass = pathSegments[1].split(File.separator);

        List<String> filteredList = new ArrayList<>();

        // Iterate through the array and filter out strings
        for (String str : packageAndClass) {
            // Check if the string contains a space or any number of dots
            if (str.contains("java") || !str.contains(" ") && !str.matches(".*\\..*")) {
                filteredList.add(str);
            }
        }

        String[] newPackageAndClass = filteredList.toArray(new String[0]);

        if (newPackageAndClass.length >= 2) {

            packageName = String.join(".", Arrays.copyOf(newPackageAndClass, newPackageAndClass.length - 1));
            className = newPackageAndClass[newPackageAndClass.length - 1].replaceAll(".java$", "");
        }

        csvLines.add(fileName + ", " + packageName + ", " + className + ", "
                + String.valueOf(tloc) + ", " + String.valueOf(tassert) + ", " + String.valueOf(tcmp));
    }

}
