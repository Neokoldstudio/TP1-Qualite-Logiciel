package metrics;

import java.io.File;
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
        float tcmp = (float) (tloc / (tassert + 0.0001)); 

        // Extract package and class name from the file path
        String[] pathSegments = filePath.split("/src/test/java/");

        String[] packageAndClass = pathSegments[1].split("/");

        if (packageAndClass.length >= 2) {
            packageName = String.join(".", Arrays.copyOf(packageAndClass, packageAndClass.length - 1)).replaceAll("[\\s.]+$", "");
            className = packageAndClass[packageAndClass.length - 1].replaceAll(".java$", "");
        }

        csvLines.add(fileName + ", " + packageName + ", " + className + ", " 
                    + String.valueOf(tloc)+ ", " + String.valueOf(tassert) +", " + String.valueOf(tcmp));
    }
        
}
