package by.radeflex.musiccatalog.lib;

import java.util.Arrays;
import java.util.Comparator;

import static java.util.Objects.compare;

public class MergeSort {
    private static <T> void merge(T[] a, int lo, int mid, int hi, Comparator<? super T> c) {
        T[] temp = Arrays.copyOfRange(a, 0, a.length);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                temp[k] = a[j++];
            } else if (j > hi) {
                temp[k] = a[i++];
            } else if (compare(a[i], a[j], c) <= 0) {
                temp[k] = a[i++];
            } else {
                temp[k] = a[j++];
            }
        }
        System.arraycopy(temp, 0, a, 0, temp.length);
    }
    public static <T> void sort(T[] arr, int left, int right, Comparator<? super T> c) {
        if (arr == null || left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(arr, left, mid, c);
        sort(arr, mid + 1, right, c);
        merge(arr, left, mid, right, c);
    }
}
