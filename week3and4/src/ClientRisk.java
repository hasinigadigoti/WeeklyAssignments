import java.util.*;


class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + ": risk=" + riskScore + ", balance=" + accountBalance;
    }
}


class BubbleSort {

    public static void sortByRiskAsc(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        System.out.println("\n--- Bubble Sort Visualization ---");

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {

                    // Print swap visualization
                    System.out.println("Swapping " + arr[j] + " <-> " + arr[j + 1]);

                    // Swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // early termination
        }

        System.out.println("Total swaps: " + swaps);
    }
}


class InsertionSort {

    public static void sortByRiskDesc(Client[] arr) {
        int shifts = 0;

        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                    (arr[j].riskScore < key.riskScore ||
                            (arr[j].riskScore == key.riskScore &&
                                    arr[j].accountBalance < key.accountBalance))) {

                arr[j + 1] = arr[j]; // shift right
                shifts++;
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort -> Total shifts: " + shifts);
    }
}


class RiskAnalyzer {

    public static void printTopN(Client[] arr, int n) {
        System.out.println("\nTop " + n + " Highest Risk Clients:");

        for (int i = 0; i < Math.min(n, arr.length); i++) {
            System.out.println(arr[i]);
        }
    }
}


public class ClientRisk {

    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 5000),
                new Client("clientA", 20, 10000),
                new Client("clientB", 50, 7000),
                new Client("clientD", 80, 12000), // same risk, higher balance
                new Client("clientE", 30, 4000)
        };


        Client[] bubbleArr = Arrays.copyOf(clients, clients.length);
        BubbleSort.sortByRiskAsc(bubbleArr);

        System.out.println("\nBubble Sorted (Ascending Risk):");
        for (Client c : bubbleArr) {
            System.out.println(c);
        }


        Client[] insertionArr = Arrays.copyOf(clients, clients.length);
        InsertionSort.sortByRiskDesc(insertionArr);

        System.out.println("\nInsertion Sorted (Descending Risk + Balance):");
        for (Client c : insertionArr) {
            System.out.println(c);
        }


        RiskAnalyzer.printTopN(insertionArr, 10);
    }
}


