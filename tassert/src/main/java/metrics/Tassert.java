package metrics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tassert {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Tassert <file-path>");
            System.exit(1);
        }

        String filePath = args[0];
        int tasserts = countTAsserts(filePath);
        System.out.println(tasserts);
    }

    public static int countTAsserts(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int asserts = 0;
            String line;

            // Use regex to find desired patterns
            Pattern patternAsserts = Pattern.compile("assert?[^\\s()]+\\(.*?\\)|fail\\(.*?\\)");

            while ((line = reader.readLine()) != null) {
                line = line.split("//")[0];// split a line if a comment is detected, process only the first half

                Matcher matcherAsserts = patternAsserts.matcher(line);

                while (matcherAsserts.find()) {
                    asserts++;
                }
            }

            return asserts;

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return 0;
        }
    }
}
