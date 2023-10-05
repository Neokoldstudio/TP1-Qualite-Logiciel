package metrics;

import java.util.ArrayList;
import java.util.List;

public class Analyser {
    public static void analyseProject(String folder, int threshold, List<String> suspectClass) {
        List<String> scannedClass = new ArrayList<>();
        Scanner.traverseFolder(folder, scannedClass);

        for (String classes : scannedClass) {
            String[] infos = classes.split(",");
            double tloc = Double.parseDouble(infos[infos.length - 2].trim());
            double tcmp = Double.parseDouble(infos[infos.length - 1].trim());

            if (isSuspect(tloc, tcmp, scannedClass, (double) threshold)) {
                suspectClass.add(classes);
            }
        }
    }

    private static boolean isSuspect(double tloc, double tcmp, List<String> scannedClass, double threshold) {
        List<String> sortedClass = new ArrayList<>(scannedClass);
        sortedClass.sort((c1, c2) -> {
            String[] infos1 = c1.split(",");
            double tloc1 = Double.parseDouble(infos1[infos1.length - 2].trim());
            double tcmp1 = Double.parseDouble(infos1[infos1.length - 1].trim());

            String[] infos2 = c2.split(",");
            double tloc2 = Double.parseDouble(infos2[infos2.length - 2].trim());
            double tcmp2 = Double.parseDouble(infos2[infos2.length - 1].trim());

            double ratio1 = tloc1 / tcmp1;
            double ratio2 = tloc2 / tcmp2;

            return Double.compare(ratio2, ratio1);
        });

        int seuilIndex = (int) Math.ceil(sortedClass.size() * (1.0 - threshold / 100.0));

        String[] seuilInfos = sortedClass.get(seuilIndex).split(",");
        double seuilTLOC = Double.parseDouble(seuilInfos[seuilInfos.length - 2].trim());
        double seuilTCMP = Double.parseDouble(seuilInfos[seuilInfos.length - 1].trim());

        return tloc >= seuilTLOC && tcmp >= seuilTCMP;
    }
}
