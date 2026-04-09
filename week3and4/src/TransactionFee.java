 import java.util.*;
        import java.time.LocalTime;

// -------------------- Transaction Class --------------------
class Transaction {
    String id;
    double fee;
    LocalTime timestamp;

    public Transaction(String id, double fee, String ts) {
        this.id = id;
        this.fee = fee;
        this.timestamp = LocalTime.parse(ts);
    }

    @Override
    public String toString() {
        return id + ": fee=" + fee + ", ts=" + timestamp;
    }
}

// -------------------- Bubble Sort --------------------
class BubbleSorter {

    public static void sortByFee(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // Early exit
        }

        System.out.println("Bubble Sort -> Passes: " + passes + ", Swaps: " + swaps);
    }
}

// -------------------- Insertion Sort --------------------
class InsertionSorter {

    public static void sortByFeeAndTime(List<Transaction> list) {
        int shifts = 0;

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    (list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).timestamp.isAfter(key.timestamp)))) {

                list.set(j + 1, list.get(j)); // shift right
                shifts++;
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort -> Total shifts: " + shifts);
    }
}

// -------------------- Outlier Detector --------------------
class OutlierDetector {

    public static List<Transaction> findHighFee(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50) {
                outliers.add(t);
            }
        }

        return outliers;
    }
}

// -------------------- Main Audit System --------------------
public class TransactionFee {

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));
        transactions.add(new Transaction("id4", 60.0, "11:00")); // outlier
        transactions.add(new Transaction("id5", 25.0, "08:45")); // duplicate fee

        int size = transactions.size();

        List<Transaction> sortedList = new ArrayList<>(transactions);

        // -------- Algorithm Selection --------
        if (size <= 100) {
            System.out.println("\nUsing Bubble Sort (Small Batch)");
            BubbleSorter.sortByFee(sortedList);

        } else if (size <= 1000) {
            System.out.println("\nUsing Insertion Sort (Medium Batch)");
            InsertionSorter.sortByFeeAndTime(sortedList);

        } else {
            System.out.println("\nLarge dataset detected (>1000). Consider Merge/Quick Sort.");
            return;
        }

        // -------- Output Sorted Transactions --------
        System.out.println("\nSorted Transactions:");
        for (Transaction t : sortedList) {
            System.out.println(t);
        }

        // -------- Detect Outliers --------
        List<Transaction> outliers = OutlierDetector.findHighFee(transactions);

        System.out.println("\nHigh-Fee Outliers (>50):");
        if (outliers.isEmpty()) {
            System.out.println("None");
        } else {
            for (Transaction t : outliers) {
                System.out.println(t);
            }
        }
    }
}


