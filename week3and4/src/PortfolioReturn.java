import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ": return=" + returnRate + "%, vol=" + volatility;
    }
}

class MergeSort {

    public static void sort(Asset[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(Asset[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].returnRate <= arr[j].returnRate) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }
}

class QuickSort {

    private static final int INSERTION_THRESHOLD = 10;

    public static void sort(Asset[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(Asset[] arr, int low, int high) {
        if (high - low <= INSERTION_THRESHOLD) {
            insertionSort(arr, low, high);
            return;
        }

        if (low < high) {
            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static int compare(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return Double.compare(b.returnRate, a.returnRate);
        }
        return Double.compare(a.volatility, b.volatility);
    }

    private static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        if (compare(arr[low], arr[mid]) > 0) swap(arr, low, mid);
        if (compare(arr[low], arr[high]) > 0) swap(arr, low, high);
        if (compare(arr[mid], arr[high]) > 0) swap(arr, mid, high);

        return mid;
    }

    private static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

public class PortfolioReturn {

    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12.0, 0.30),
                new Asset("TSLA", 8.0, 0.60),
                new Asset("GOOG", 15.0, 0.25),
                new Asset("MSFT", 12.0, 0.20)
        };

        Asset[] mergeArr = Arrays.copyOf(assets, assets.length);
        MergeSort.sort(mergeArr);

        System.out.println("Merge Sort (Ascending Return):");
        for (Asset a : mergeArr) {
            System.out.println(a);
        }

        Asset[] quickArr = Arrays.copyOf(assets, assets.length);
        QuickSort.sort(quickArr);

        System.out.println("\nQuick Sort (Desc Return + Low Volatility):");
        for (Asset a : quickArr) {
            System.out.println(a);
        }
    }
}

