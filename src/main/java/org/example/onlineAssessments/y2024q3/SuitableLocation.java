package org.example.onlineAssessments.y2024q3;

import java.util.Arrays;

/**
 * ref: https://leetcode.com/discuss/interview-question/5773642/Amazon-OA-questions
 *
 * # The world is represented by a number line from -10° to 10°. There are n delivery centers, the * one at location center[i]. A location x is called a suitable location for a warehouse if it is possible to bring all the products to that point by traveling a distance of no more than d. At any one time, products can be brought from one delivery center and placed at point x.
 *
 * # Given the positions of n delivery centers, calculate the number of suitable locations in the world. That is, calculate the number of points x on the number line (-10^9 ≤ x ≤ 10^9) where the travel distance required to bring all the products to that point is less than or equal to d.
 *
 * # Note: The distance between point x and center[i] is |x - center[]|, their absolute difference.
 *
 * # Example
 * # Given n = 3, center = [-2, 1, 0], d = 8.
 * # The various locations along with the distance traveled to bring all treasures at that point are-
 * # • Locate the warehouse at x = -3: First bring products from center[0] = -2 covering a distance of |-3-(-2)| = 1 to reach the center and |-3-(-2)| = 1 to return. Similarly we bring products from centers 1 and 2 to point -3 for total distance of 1 + 1 + 4 + 4 + 3 + 3 = 16 which is > d. This is not a suitable location.
 * # • Locate the warehouse at x = 0, total distance traveled is 2*|0-(-2)| + 2*|0-1| + 2*|0-0| = 6 ≤ d. This is a suitable location
 * # • Locate the warehouse at x = -1, total distance traveled is 2*|-1-(-2)| + 2*|-1-1| + 2*|-1-0| = 8 ≤ d. This is a suitable location.
 * # • Locate the warehouse at x = 1, total distance traveled is 2*|1-(-2)| + 2*|1-1| + 2*|1-0| = 8 <= d. This is a suitable location.
 * # The only suitable locations are {-1, 0, 1}. Return 3.
 */
public class SuitableLocation {


    public int solve(int[] center, int d) {
        int lo = (int)Math.pow(10, 9)*(-1);
        int hi = (int)Math.pow(10,9);


        boolean exist = false;

        int first = -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            long distMid = getTotalDist(mid, center);
            long distMidNext = getTotalDist(mid+1, center);

            if(distMid <= d) {
                exist = true;
                first = mid;
                hi = mid - 1;
            }
            else if(distMid < distMidNext) {
                hi = mid - 1;
            }
            else {
                lo = mid + 1;
            }
        }

        if(!exist) return 0;


        lo = first;
        hi = (int) Math.max(10, 9);
        int last = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long distMid = getTotalDist(mid, center);
            long distMidNext = getTotalDist(mid + 1, center);

            if(distMid <= d) {
                last = mid;
                lo = mid + 1;
            }
            else if(distMid < distMidNext) {
                hi = mid - 1;
            }
            else {
                lo = mid + 1;
            }
        }

        return last - first + 1;
    }

    private long getTotalDist(int pos, int[] center) {
        return Arrays.stream(center).mapToLong(c -> 2 * Math.abs(c - pos)).sum();
    }

    public static void main(String[] args) {
        SuitableLocation s = new SuitableLocation();

        int[] c1 = {2,0,3,-4};
        int d = 22;

        int ans = s.solve(c1, d);
        System.out.println(ans);
    }
}
