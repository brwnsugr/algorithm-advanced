package org.example.onlineAssessments.y2024q3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BeautyArray {
    public int getBeautifulArr(int[] arr, int[][] pairs) {
        int n = arr.length;

        List<Integer> beautifulArr = new ArrayList<>();
        boolean[] used = new boolean[n];

        for(int i = 0; i < pairs.length; i++) {
            int idx1 = pairs[i][0];
            int idx2 = pairs[i][1];
            for(int j = idx1; j <= idx2; j++) {
                beautifulArr.add(arr[j]);
                used[j] = true;
            }
        }

        Collections.sort(beautifulArr);

        int finalCount = 0;

        for(int i = 0; i < n; i++) {
            if(!used[i]) {
                int count = getLowerBound(
                        beautifulArr.stream().mapToInt(Integer::intValue).toArray()
                        ,arr[i]);
                finalCount += count;
            }
        }
        return finalCount;
    }

    private int getLowerBound(int[] arr, int key) {
        int idx = Arrays.binarySearch(arr, key);

        if(idx < 0) {
            return Math.abs(idx) - 1;
        }

        else {
            while(idx > 0) {
                if(arr[idx - 1] == key) {
                    idx--;
                }
                else return idx;
            }
            return idx;
        }
    }

    public static void main(String[] args) {
        BeautyArray solution = new BeautyArray();
        int beautifulArr = solution.getBeautifulArr(new int[]{1, 2, 3, 2, 4, 5},
                new int[][]{{0, 1}, {3, 4}, {0, 0}, {3, 4}});

        System.out.println(beautifulArr);
    }
}
