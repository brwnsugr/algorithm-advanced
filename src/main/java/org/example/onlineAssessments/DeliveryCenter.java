package org.example.onlineAssessments;

import java.util.Arrays;

/**
 * Amazon has multiple delivery centers and delivery warehouses all over the world!
 * The world is represented by a number line from -10^9 to 10^9. There are n delivery centers, the ith one at location center[i].
 * A location x is called a suitable location for a warehouse if it is possible to bring all the products to that point by traveling a distance of no more than d.
 * At any one time, products can be brought from one delivery center and placed at point x.
 * Given the positions of n delivery centers, calculate the number of suitable locations in the world.
 * That is, calculate the number of points x on the number line (- 10 ^ 9 <= x <= 10 ^ 9) where the travel distance required to bring all the products to that point is less than or equal to d.
 *
 * Note: The distance between point x and center[i] is x-center[i], their absolute difference.
 *
 * Example
 * Given n = 3, center = [-2, 1, 0], d = 8.
 *
 * The various locations along with the distance traveled to bring all treasures at that point are-
 * • Locate the warehouse at x=-3: First bring products from center[0] = -2 covering a distance of |-3-(-2)| = 1 to reach the center and |-3-(-2)|=1 to return. Similarly we bring products from centers 1 and 2 to point -3 for
 *
 * total distance of 1+1+4 +4 +3+3 = 16 which is > d. This is not a suitable location.
 *
 * Locate the warehouse at x = 0, total distance traveled is 2* |0-(-2)| +2* |0 -1|+2*|0-0| = 6 <=d. This is a suitable location.
 *
 * Locate the warehouse at x=-1, total distance traveled is 2* |-1-(-2)| +2* |-1-1| + 2*|-1-0| = 8 <=d. This is a suitable location.
 *
 * Locate the warehouse at x = 1, total distance traveled is 2* | 1-(-2)| +2* |1 - 1 |+ 2 * |1 - 0| = 8 <= dThis is a suitable location.
 *
 * The only suitable locations are (-1, 0, 1} Return 3.
 * Here the answer would be 3 because suitable locations are -1,0,1
 *
 * Example
 * Given n=4, center = 2,0,3,-4 and d =22
 * Answer would be 5 because the locations are -1,0,1,2,3
 *
 * Example
 * Given n=3 center = -3,2,2 and d=8
 * Answer would be 0
 *
 * ref: https://leetcode.com/company/amazon/discuss/4382094/Amazon-or-OA-or-Delivery-centers
 *
 * ref: https://leetcode.com/company/amazon/discuss/4374685/Amazon-OA-or-SDE-2-or-USA-or-Q1-%2B-Q2
 */
public class DeliveryCenter {
    /**
     * Appr: Set leftMost suitable Location & rightMost suitable Location respectively.
     * Between leftMost&rightMost, it might be suitable location too
     * O(NlogN)
     */

    private boolean isCenterExist = true;

    public int findSuitableLocations2(int[] arr, int d) {
        int start = Arrays.stream(arr).min().getAsInt() - d;
        int end = Arrays.stream(arr).max().getAsInt() + d;

        int leftMostLocation = leftBinarySearch(start, end, d, arr);
        int rightMostLocation = rightBinarySearch(start, end, d, arr);

        // return all suitable locations that are between the leftMost and rightMost (inclusive)
        return rightMostLocation - leftMostLocation;
    }

    private int leftBinarySearch(int l, int r, int d, int[] centers) {
        boolean found = false;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (getTotalDist(centers, m) <= d) {
                found = true;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return found ? l : 0;
    }

    private int rightBinarySearch(int l, int r, int d, int[] centers) {
        boolean found = false;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (getTotalDist(centers, m) <= d) {
                found = true;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return found ? l : 0;
    }

    private int getTotalDist(int[] arr, int mid) {
        int ans = 0;
        for (int j : arr) {
            ans += Math.abs(j - mid) * 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        DeliveryCenter2 sol = new DeliveryCenter2();
//        DeliveryCenter sol = new DeliveryCenter();
        int[] c1 = new int[]{-2,1,0}; int d1 = 7;
        int[] c2 = new int[]{-2,1,0}; int d2 = 6;
        int[] c3 = new int[]{-2,1,0}; int d3 = 8;
        int[] c4 = new int[]{2,0,3, -4}; int d4 = 22;
        int[] c5 = new int[]{-3,2,2}; int d5 = 8;
        int[] c6 = new int[]{-3,2,2}; int d6 = 100;
        int[] c7 = new int[]{1,2,3,4,5}; int d7 = 20;
//        int[] c8 = new int[]{1,2,3,4,5}; int d8 = 20;
//        int[] c9 = new int[]{1,2,3,4,5}; int d9 = 20;

        System.out.println(sol.findSuitableLocations2(c1, d1));
        System.out.println(sol.findSuitableLocations2(c2, d2));
        System.out.println(sol.findSuitableLocations2(c3, d3));
        System.out.println(sol.findSuitableLocations2(c4, d4));
        System.out.println(sol.findSuitableLocations2(c5, d5));
        System.out.println(sol.findSuitableLocations2(c6, d6));
        System.out.println(sol.findSuitableLocations2(c7, d7));
    }

}
