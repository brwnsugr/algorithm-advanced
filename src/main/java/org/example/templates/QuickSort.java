package org.example.templates;

/**
 * partition function? -> get a pivot index where all left part is smaller than pivot
 * // important -> ( Left part) p (right part) : Left part is strictly smaller(not equal) than pivot indexed value.
 * // important ->
 */

public class QuickSort {

    public void qsort(int[] nums) {
        qsort(nums, 0, nums.length - 1);
    }

    public void qsort(int[] nums, int l, int r) {
        if (l >= r) return;
        int p = partition(nums, l, r);
        qsort(nums, l, p - 1);
        qsort(nums, p + 1, r);
    }

    private int partition(int[] nums, int l, int r) {
        int pivotValue = nums[r]; // pivot
        int p = r;
        for(int i = l; i < p; i++) {
            if(nums[i] >= pivotValue) {
                swap(nums, i, p - 1);
                swap(nums, p - 1, p);
                i--;
                p--;
            }
        }
        return p;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
