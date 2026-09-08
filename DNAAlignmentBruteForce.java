package csc311project;

import java.util.Scanner;

public class DNAAlignmentBruteForce {
    public static int alpha = 2; // Cost for Mismatch
    public static int beta = 3;  // Cost for Gap
    // Variables to store the best alignment strings
    public static String bestS1 = "";
    public static String bestS2 = "";
    public static int minCostTracker = Integer.MAX_VALUE;

    public static int getMinCost(String s1, String s2, int i, int j) {
        if (i == s1.length()) return (s2.length() - j) * beta;
        if (j == s2.length()) return (s1.length() - i) * beta;

        int currentCost;
        if (s1.charAt(i) == s2.charAt(j)) {
            currentCost = 0;
        } else {
            currentCost = alpha;
        }

        int costMatchMismatch = currentCost + getMinCost(s1, s2, i + 1, j + 1);
        int costGapS2 = beta + getMinCost(s1, s2, i + 1, j);
        int costGapS1 = beta + getMinCost(s1, s2, i, j + 1);
        return Math.min(costMatchMismatch, Math.min(costGapS2, costGapS1));
    }

    // Helper method to find and store the alignment visually
    private static void findAlignmentPaths(String s1, String s2, int i, int j, String a1, String a2, int currentCost) {
        if (i == s1.length() && j == s2.length()) {
            if (currentCost < minCostTracker) {
                minCostTracker = currentCost;
                bestS1 = a1;
                bestS2 = a2;
            }
            return;
        }
        if (i == s1.length()) {
            findAlignmentPaths(s1, s2, i, j + 1, a1 + "-", a2 + s2.charAt(j), currentCost + beta);
            return;
        }
        if (j == s2.length()) {
            findAlignmentPaths(s1, s2, i + 1, j, a1 + s1.charAt(i), a2 + "-", currentCost + beta);
            return;
        }

        int mmCost;
        if (s1.charAt(i) == s2.charAt(j)) {
            mmCost = 0;
        } else {
            mmCost = alpha;
        }

        findAlignmentPaths(s1, s2, i + 1, j + 1, a1 + s1.charAt(i), a2 + s2.charAt(j), currentCost + mmCost);
        findAlignmentPaths(s1, s2, i + 1, j, a1 + s1.charAt(i), a2 + "-", currentCost + beta);
        findAlignmentPaths(s1, s2, i, j + 1, a1 + "-", a2 + s2.charAt(j), currentCost + beta);
    }

    public static void printAlignment(String s1, String s2) {
        // Reset trackers before running
        bestS1 = "";
        bestS2 = "";
        minCostTracker = Integer.MAX_VALUE;
        findAlignmentPaths(s1, s2, 0, 0, "", "", 0);
        System.out.println("Aligned S1: " + bestS1);
        System.out.println("Aligned S2: " + bestS2);
    }
}