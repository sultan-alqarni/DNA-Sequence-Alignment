# DNA Sequence Alignment Analyzer 🧬💻

An interactive Java application designed to solve the classic bioinformatics problem of DNA Sequence Alignment. This project serves as a comprehensive algorithmic study, implementing and benchmarking three distinct algorithm design paradigms to compare their performance, accuracy, and time complexity.

## ✨ Core Features
* **Multi-Algorithm Implementation:** 
  * **Brute Force:** Recursively explores all possible alignment paths.
  * **Dynamic Programming (DP):** Guarantees optimal alignment with $O(n \times m)$ complexity. Includes full DP table visualization.
  * **Greedy Algorithm:** A fast, heuristic-based approach with 1-step lookahead for linear time $O(n+m)$ approximation.
* **Performance Benchmarking:** Real-time calculation of execution time (in milliseconds) and total alignment cost (Match/Mismatch/Gap) for algorithm comparison.
* **Customizable Parameters:** Users can dynamically adjust the penalty costs for Mismatches ($\alpha$) and Gaps ($\beta$) during runtime.
* **Interactive CLI:** A robust unified menu offering 7 preset DNA datasets and an option for custom sequence inputs.

## 🛠️ Technologies & Concepts
* **Language:** Java
* **Concepts:** Algorithm Analysis, Dynamic Programming (Memoization/Tabulation), Greedy Strategy, Recursion, Bioinformatics.

## 🚀 How to Run
1. Clone this repository.
2. Compile all the Java files:
   `javac *.java`
3. Run the main program:
   `java main`
4. Follow the interactive menu to select datasets, set gap/mismatch penalties, and choose which algorithms to benchmark.
