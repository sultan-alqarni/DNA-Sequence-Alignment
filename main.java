package csc311project;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean running = true;
        while (running) {

            // 1. Dataset Selection
            System.out.println("\n--- DNA Alignment Unified System ---");
            System.out.println("Choose Dataset:");
            System.out.println("1  - (AC, A)");
            System.out.println("2  - (ACC, A)");
            System.out.println("3  - (ACGT, ACT)");
            System.out.println("4  - (GATT, GTT)");
            System.out.println("5  - (ACGTACGTAGCTAGCTAGCTA, ACGTAGCTAGCTAGT)");
            System.out.println("6  - (AGT, ACT)");
            System.out.println("7  - (AAGG, GG)");
            System.out.println("8  - Enter your own input");
            System.out.println("0  - Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            String S1 = "";
            String S2 = "";

            if (choice == 1) {
                S1 = "AC";                      S2 = "A";
            } else if (choice == 2) {
                S1 = "ACC";                     S2 = "A";
            } else if (choice == 3) {
                S1 = "ACGT";                    S2 = "ACT";
            } else if (choice == 4) {
                S1 = "GATT";                    S2 = "GTT";
            } else if (choice == 5) {
                S1 = "ACGTACGTAGCTAGCTAGCTA";   S2 = "ACGTAGCTAGCTAGT";
            } else if (choice == 6) {
                S1 = "AGT";                     S2 = "ACT";
            } else if (choice == 7) {
                S1 = "AAGG";                    S2 = "GG";
            } else if (choice == 8) {
                System.out.print("Enter S1: ");
                S1 = sc.nextLine();
                System.out.print("Enter S2: ");
                S2 = sc.nextLine();
            } else {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            // 2. Input Scoring Parameters
            System.out.print("\nEnter Alpha (Mismatch cost): ");
            int alphaInput = sc.nextInt();
            System.out.print("Enter Beta (Gap cost): ");
            int betaInput = sc.nextInt();
            sc.nextLine();

            // Update alpha and beta in all classes
            DNAAlignmentBruteForce.alpha = alphaInput;
            DNAAlignmentBruteForce.beta  = betaInput;
            DNAAlignmentDP.alpha         = alphaInput;
            DNAAlignmentDP.beta          = betaInput;
            DNAAlignmentGreedy.alpha     = alphaInput;
            DNAAlignmentGreedy.beta      = betaInput;

            // 3. Algorithm Selection
            System.out.println("\nChoose Algorithm:");
            System.out.println("1 - Brute Force");
            System.out.println("2 - Greedy");
            System.out.println("3 - Dynamic Programming");
            System.out.println("4 - Run All Three");
            System.out.print("Choice: ");
            int algoChoice = sc.nextInt();
            sc.nextLine();

            System.out.println("\n" + "=".repeat(40));
            System.out.println("Sequence 1: " + S1);
            System.out.println("Sequence 2: " + S2);
            System.out.println("Alpha: " + alphaInput + "  |  Beta: " + betaInput);
            System.out.println("=".repeat(40));

            if (algoChoice == 1 || algoChoice == 4) {
                System.out.println("\n[ Brute Force ]");
                long startBF = System.nanoTime();
                int costBF = DNAAlignmentBruteForce.getMinCost(S1, S2, 0, 0);
                long endBF = System.nanoTime();
                double timeBF = (endBF - startBF) / 1_000_000.0;
                DNAAlignmentBruteForce.printAlignment(S1, S2);
                System.out.println("Total Alignment Cost (Brute Force): " + costBF);
                System.out.println("Execution Time: " + timeBF + " ms");
            }

            if (algoChoice == 2 || algoChoice == 4) {
                System.out.println("\n[ Greedy ]");
                long startGreedy = System.nanoTime();
                int costGreedy = DNAAlignmentGreedy.getMinCostGreedy(S1, S2);
                long endGreedy = System.nanoTime();
                double timeGreedy = (endGreedy - startGreedy) / 1_000_000.0;
                DNAAlignmentGreedy.printAlignment(S1, S2);
                System.out.println("Total Alignment Cost (Greedy): " + costGreedy);
                System.out.println("Execution Time: " + timeGreedy + " ms");
            }

            if (algoChoice == 3 || algoChoice == 4) {
                System.out.println("\n[ Dynamic Programming ]");
                long startDP = System.nanoTime();
                int costDP = DNAAlignmentDP.getMinCostDP(S1, S2);
                long endDP = System.nanoTime();
                double timeDP = (endDP - startDP) / 1_000_000.0;
                DNAAlignmentDP.printAlignment(S1, S2);
                System.out.println("Total Alignment Cost (DP): " + costDP);
                System.out.println("Execution Time: " + timeDP + " ms");
                System.out.println("\n[ DP Table ]");
                DNAAlignmentDP.showDPTable(S1, S2);
            }

            if (algoChoice == 4) {
                // Summary comparison table when running all three
                int costBF     = DNAAlignmentBruteForce.getMinCost(S1, S2, 0, 0);
                int costGreedy = DNAAlignmentGreedy.getMinCostGreedy(S1, S2);
                int costDP     = DNAAlignmentDP.getMinCostDP(S1, S2);
                System.out.println("\n" + "=".repeat(50));
                System.out.println(String.format("%-20s %-15s", "Algorithm", "Total Cost"));
                System.out.println("-".repeat(35));
                System.out.println(String.format("%-20s %-15d", "Brute Force",        costBF));
                System.out.println(String.format("%-20s %-15d", "Greedy",             costGreedy));
                System.out.println(String.format("%-20s %-15d", "Dynamic Programming", costDP));
                System.out.println("=".repeat(50));
            }

            if (algoChoice < 1 || algoChoice > 4) {
                System.out.println("Invalid algorithm choice.");
            }
        }

        sc.close();
    }
}