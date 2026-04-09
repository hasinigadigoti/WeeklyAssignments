import java.util.*;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

class MergeSorter {

    public static void sort(Trade[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(Trade[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].volume <= arr[j].volume) {
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

class QuickSorter {

    public static void sort(Trade[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(Trade[] arr, int low, int high) {
        Trade pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot.volume) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Trade[] arr, int i, int j) {
        Trade temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

class TradeMerger {

    public static Trade[] mergeSorted(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];

        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }
}

class TradeUtils {

    public static long totalVolume(Trade[] arr) {
        long sum = 0;
        for (Trade t : arr) {
            sum += t.volume;
        }
        return sum;
    }
}

public class HistoricalTrade {

    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        Trade[] mergeArr = Arrays.copyOf(trades, trades.length);
        MergeSorter.sort(mergeArr);

        System.out.println("Merge Sort (Ascending):");
        for (Trade t : mergeArr) {
            System.out.println(t);
        }

        Trade[] quickArr = Arrays.copyOf(trades, trades.length);
        QuickSorter.sort(quickArr);

        System.out.println("\nQuick Sort (Descending):");
        for (Trade t : quickArr) {
            System.out.println(t);
        }

        Trade[] morning = {
                new Trade("m1", 100),
                new Trade("m2", 300)
        };

        Trade[] afternoon = {
                new Trade("a1", 200),
                new Trade("a2", 400)
        };

        MergeSorter.sort(morning);
        MergeSorter.sort(afternoon);

        Trade[] merged = TradeMerger.mergeSorted(morning, afternoon);

        System.out.println("\nMerged Trades:");
        for (Trade t : merged) {
            System.out.println(t);
        }

        long total = TradeUtils.totalVolume(merged);
        System.out.println("\nTotal Volume: " + total);
    }
}

