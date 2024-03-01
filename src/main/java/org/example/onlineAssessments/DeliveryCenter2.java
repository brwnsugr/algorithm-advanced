package org.example.onlineAssessments;

import java.util.Arrays;

public class DeliveryCenter2 {
    private boolean isCenterExist;

    public int findSuitableLocations2(int[] arr, int d) {
        int start = Arrays.stream(arr).min().getAsInt() - d;
        int end = Arrays.stream(arr).max().getAsInt() + d;
        isCenterExist = false;
        int leftMostLocation = leftBinarySearch(start, end, d, arr);
        int rightMostLocation = rightBinarySearch(start, end, d, arr);

        // return all suitable locations that are between the leftMost and rightMost (inclusive)
        return isCenterExist ? rightMostLocation - leftMostLocation + 1 : 0;
    }

    private int leftBinarySearch(int l, int r, int d, int[] centers) {
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (getTotalDist(centers, m) <= d) {
                isCenterExist = true;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return r + 1;
    }

    private int rightBinarySearch(int l, int r, int d, int[] centers) {
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (getTotalDist(centers, m) <= d) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l - 1;
    }

    private int getTotalDist(int[] arr, int mid) {
        int ans = 0;
        for (int j : arr) {
            ans += Math.abs(j - mid) * 2;
        }
        return ans;
    }
}
