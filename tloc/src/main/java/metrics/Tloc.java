package metrics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tloc {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("You must provide one file path");
            return;
        }

        String filePath = args[0];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int totalLines = 0;
            int emptyLines = 0;
            int commentaryLines = 0;
            boolean inComment = false;

            while ((line = reader.readLine()) != null) {
                totalLines++;

                if (line.trim().isEmpty()) {
                    emptyLines++;
                } else if (line.trim().startsWith("//")) {
                    commentaryLines++;
                } else if (line.trim().startsWith("/*")) {
                    commentaryLines++;
                    inComment = true;
                } else if (inComment) {
                    commentaryLines++;
                    if (line.trim().endsWith("*/")) {
                        inComment = false;
                    }
                }
            }

            reader.close();

            // System.out.println("Total number of lines: " + totalLines);
            // System.out.println("Number of empty lines: " + emptyLines);
            // System.out.println("Number of commentary lines: " + commentaryLines);
            
            System.out.println(totalLines - emptyLines - commentaryLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


