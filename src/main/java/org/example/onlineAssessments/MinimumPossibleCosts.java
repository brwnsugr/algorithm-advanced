package org.example.onlineAssessments;

import java.util.PriorityQueue;

/**
 * Q2: Determine the minimum possible cost to purchase exactly m items
 *
 * Example:
 * given
 *
 * - a=[2,1,1] as list
 * - b=[1,2,3] as list
 * - m=4 as integer
 * The optimal types to buy are:
 *
 * choose type i=1. This is the first purchase of this type, so j=1. This item costs a[1]+(j-1)*b[i]= 1+(1-1)*2=1
 * choose type i=2. This is the first purchase of this type, so j=1. This item costs 1+(1-1)*3 = 1
 * choose type i=0. This is the first purchase of this type. so j=1. This item costs 2+(1-1)*1=2
 * when second item of any type is purchased, j=2. The cost of the item for each type will be:
 * Type i=0, costs a[0]+(j-1)*b[0]=2+(2-1)*1=3
 * Type i=1, costs 1+1*2=3
 * Type i=2, costs 1+1*3=4
 * Chose either the first or second type since they cost the least
 * The total cost to purchase is 1+1+2+3=7
 *
 * ref: https://leetcode.com/company/amazon/discuss/4660709/Amazon-OA-recent-questions-Sharing-for-the-community-less3
 */

public class MinimumPossibleCosts {


    public int getMinimumCostMItems(int[] a, int[] b, int m) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> a[x[0]] + (x[1] - 1) * b[x[0]] - a[y[0]] - (y[1] - 1) * b[(y[0])]);
        for(int i = 0; i < a.length; i++) {
            pq.add(new int[]{i, 1});
        }

        int totalCost = 0;

        while(m > 0) {
            int[] curr = pq.poll();
            int i = curr[0];
            int j = curr[1];
            int currMinCost = (a[i] + (j - 1) * b[i]);
            totalCost += currMinCost;
            pq.add(new int[]{i, j + 1});
            m--;
        }

        return totalCost;
    }

    public static void main(String[] args) {
        MinimumPossibleCosts sol = new MinimumPossibleCosts();
        int[] a1 = new int[]{2, 1, 1};
        int[] b1 = new int[]{1, 2, 3};
        int m1 = 4;
        int minimumCostMItems = sol.getMinimumCostMItems(a1, b1, m1);
        System.out.println(minimumCostMItems);
    }
}
