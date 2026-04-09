public class RiskThreshold {


    static int linearSearch(int[] bands, int target) {
        int comparisons = 0;

        for (int i = 0; i < bands.length; i++) {
            comparisons++;
            if (bands[i] == target) {
                System.out.println("Linear: Found " + target + " at index " + i + " | Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear: " + target + " not found | Comparisons: " + comparisons);
        return -1;
    }

    // Binary search floor (largest ≤ target)
    static int binaryFloor(int[] bands, int target) {
        int low = 0, high = bands.length - 1;
        int floorIndex = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (bands[mid] == target) {
                System.out.println("Binary Floor: Found exact " + target + " at index " + mid + " | Comparisons: " + comparisons);
                return mid;
            }
            else if (bands[mid] < target) {
                floorIndex = mid;
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        if (floorIndex == -1) {
            System.out.println("Binary Floor: No floor ≤ " + target + " | Comparisons: " + comparisons);
        } else {
            System.out.println("Binary Floor: Value " + bands[floorIndex] + " at index " + floorIndex + " | Comparisons: " + comparisons);
        }
        return floorIndex;
    }


    static int binaryCeiling(int[] bands, int target) {
        int low = 0, high = bands.length - 1;
        int ceilingIndex = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
            if (bands[mid] == target) {
                System.out.println("Binary Ceiling: Found exact " + target + " at index " + mid + " | Comparisons: " + comparisons);
                return mid;
            }
            else if (bands[mid] > target) {
                ceilingIndex = mid;
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        if (ceilingIndex == -1) {
            System.out.println("Binary Ceiling: No ceiling ≥ " + target + " | Comparisons: " + comparisons);
        } else {
            System.out.println("Binary Ceiling: Value " + bands[ceilingIndex] + " at index " + ceilingIndex + " | Comparisons: " + comparisons);
        }
        return ceilingIndex;
    }

    public static void main(String[] args) {
        int[] unsortedBands = {50, 10, 100, 25};
        int[] sortedBands = {10, 25, 50, 100};
        int threshold = 30;

        System.out.println("Linear Search on Unsorted:");
        linearSearch(unsortedBands, threshold);

        System.out.println("\nBinary Search on Sorted:");
        binaryFloor(sortedBands, threshold);
        binaryCeiling(sortedBands, threshold);
    }
}

