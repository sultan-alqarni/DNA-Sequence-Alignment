package csc311project;

public class DNAAlignmentDP {

    public static int alpha = 2; // Cost for Mismatch
    public static int beta = 3;  // Cost for Gap

    public static int getMinCostDP(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i * beta;
        for (int j = 0; j <= m; j++) dp[0][j] = j * beta;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int matchMismatchCost;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    matchMismatchCost = 0;
                } else {
                    matchMismatchCost = alpha;
                }

                int costDiagonal = dp[i - 1][j - 1] + matchMismatchCost;
                int costGapS2 = dp[i - 1][j] + beta;
                int costGapS1 = dp[i][j - 1] + beta;

                dp[i][j] = Math.min(costDiagonal, Math.min(costGapS2, costGapS1));
            }
        }
        return dp[n][m];
    }

    public static void printAlignment(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i * beta;
        for (int j = 0; j <= m; j++) dp[0][j] = j * beta;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int matchMismatchCost;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    matchMismatchCost = 0;
                } else {
                    matchMismatchCost = alpha;
                }
                int costDiagonal = dp[i - 1][j - 1] + matchMismatchCost;
                int costGapS2    = dp[i - 1][j] + beta;
                int costGapS1    = dp[i][j - 1] + beta;
                dp[i][j] = Math.min(costDiagonal, Math.min(costGapS2, costGapS1));
            }
        }

        StringBuilder aligned1 = new StringBuilder();
        StringBuilder aligned2 = new StringBuilder();
        int i = n;
        int j = m;

        while (i > 0 && j > 0) {
            int matchMismatchCost;
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                matchMismatchCost = 0;
            } else {
                matchMismatchCost = alpha;
            }

            if (dp[i][j] == dp[i - 1][j - 1] + matchMismatchCost) {
                aligned1.insert(0, s1.charAt(i - 1));
                aligned2.insert(0, s2.charAt(j - 1));
                i--;
                j--;
            } else if (dp[i][j] == dp[i - 1][j] + beta) {
                aligned1.insert(0, s1.charAt(i - 1));
                aligned2.insert(0, '-');
                i--;
            } else {
                aligned1.insert(0, '-');
                aligned2.insert(0, s2.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            aligned1.insert(0, s1.charAt(i - 1));
            aligned2.insert(0, '-');
            i--;
        }

        while (j > 0) {
            aligned1.insert(0, '-');
            aligned2.insert(0, s2.charAt(j - 1));
            j--;
        }

        System.out.println("Aligned S1: " + aligned1);
        System.out.println("Aligned S2: " + aligned2);
    }

    public static void printDPTable(String s1, String s2, int[][] dp) {
        System.out.print("     ");
        System.out.printf("%-5s", "-");
        for (char c : s2.toCharArray()) System.out.printf("%-5c", c);
        System.out.println();

        for (int i = 0; i <= s1.length(); i++) {
            if (i == 0) {
                System.out.printf("%-5s", "-");
            } else {
                System.out.printf("%-5c", s1.charAt(i - 1));
            }
            for (int j = 0; j <= s2.length(); j++) {
                System.out.printf("%-5d", dp[i][j]);
            }
            System.out.println();
        }
    }

    // Helper method to show DP table from Main
    public static void showDPTable(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i * beta;
        for (int j = 0; j <= m; j++) dp[0][j] = j * beta;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int matchMismatchCost;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    matchMismatchCost = 0;
                } else {
                    matchMismatchCost = alpha;
                }
                dp[i][j] = Math.min(dp[i - 1][j - 1] + matchMismatchCost,
                           Math.min(dp[i - 1][j] + beta, dp[i][j - 1] + beta));
            }
        }
        printDPTable(s1, s2, dp);
    }
}