package csc311project;
public class DNAAlignmentGreedy {

    public static int alpha = 2; // Cost for Mismatch
    public static int beta = 3;  // Cost for Gap

    public static int getMinCostGreedy(String s1, String s2) {
        int i = 0, j = 0, totalCost = 0;

        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                totalCost += 0; // Perfect match
                i++; j++;
            } else {
                if (alpha <= beta) {
                    totalCost += alpha; // Mismatch is cheaper or equal
                    i++; j++;
                } else {
                    // Gap is cheaper. Try both directions (Lookahead 1 step)
                    if (i + 1 < s1.length() && s1.charAt(i + 1) == s2.charAt(j)) {
                        totalCost += beta; 
                        i++; // Insert Gap in S2
                    } else if (j + 1 < s2.length() && s1.charAt(i) == s2.charAt(j + 1)) {
                        totalCost += beta; 
                        j++; // Insert Gap in S1
                    } else {
                        totalCost += alpha; // Default to mismatch to progress
                        i++; j++;
                    }
                }
            }
        }

        // Add remaining as gaps
        while (i < s1.length()) { totalCost += beta; i++; }
        while (j < s2.length()) { totalCost += beta; j++; }

        return totalCost;
    }

    public static void printAlignment(String s1, String s2) {
        int i = 0, j = 0;
        StringBuilder aligned1 = new StringBuilder();
        StringBuilder aligned2 = new StringBuilder();

        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                aligned1.append(s1.charAt(i++));
                aligned2.append(s2.charAt(j++));
            } else {
                if (alpha <= beta) {
                    aligned1.append(s1.charAt(i++));
                    aligned2.append(s2.charAt(j++));
                } else {
                    if (i + 1 < s1.length() && s1.charAt(i + 1) == s2.charAt(j)) {
                        aligned1.append(s1.charAt(i++));
                        aligned2.append('-');
                    } else if (j + 1 < s2.length() && s1.charAt(i) == s2.charAt(j + 1)) {
                        aligned1.append('-');
                        aligned2.append(s2.charAt(j++));
                    } else {
                        aligned1.append(s1.charAt(i++));
                        aligned2.append(s2.charAt(j++));
                    }
                }
            }
        }

        while (i < s1.length()) {
            aligned1.append(s1.charAt(i++));
            aligned2.append('-');
        }
        while (j < s2.length()) {
            aligned1.append('-');
            aligned2.append(s2.charAt(j++));
        }

        System.out.println("Aligned S1: " + aligned1);
        System.out.println("Aligned S2: " + aligned2);
    }
} 