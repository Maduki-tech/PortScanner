package de.schlueter;

/**
 * Utils
 */
public class Utils {

    public static void printSeparator() {
        System.out.println("--------------------------------------------------");
    }

    public static void timeTaken(long start) {
        long endTime = System.currentTimeMillis() - start;
        if(endTime > 60000) {
            System.out.println("Scanning took " + (endTime / 60000) + "m");
        } else if (endTime > 1000) {
            System.out.println("Scanning took " + (endTime / 1000) + "s");
        } else {
            System.out.println("Scanning took " + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
