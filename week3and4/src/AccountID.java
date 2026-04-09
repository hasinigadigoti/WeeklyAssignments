import java.util.Arrays;

public class AccountID {

    static int linearFirst(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First Index: " + i + " | Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear First: Not Found | Comparisons: " + comparisons);
        return -1;
    }

    static int linearLast(String[] arr, String target) {
        int comparisons = 0;
        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                index = i;
            }
        }

        System.out.println("Linear Last Index: " + index + " | Comparisons: " + comparisons);
        return index;
    }

    static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                System.out.println("Binary Found at Index: " + mid + " | Comparisons: " + comparisons);
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary: Not Found | Comparisons: " + comparisons);
        return -1;
    }

    static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        if (first == -1) return 0;

        int last = lastOccurrence(arr, target);
        return last - first + 1;
    }

    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        System.out.println("---- Linear Search ----");
        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        Arrays.sort(logs);

        System.out.println("\nSorted Logs: " + Arrays.toString(logs));

        System.out.println("\n---- Binary Search ----");
        binarySearch(logs, "accB");

        int count = countOccurrences(logs, "accB");
        System.out.println("Total Occurrences: " + count);

        System.out.println("\nTime Complexity:");
        System.out.println("Linear Search: O(n)");
        System.out.println("Binary Search: O(log n)");
    }
}

