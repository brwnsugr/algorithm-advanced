package org.example.onlineAssessments.y2024q3;

import java.util.Arrays;

public class MinimumItemRemoval {

    public int getMinimumNumberOfItemsToBeRemoved(int[] prices, int k, int threshold) {
        prices = Arrays.stream(prices).sorted().toArray();

        int l = 0;
        int r = 0;
        int currSum = 0;

        while(r < k) {
            currSum += prices[r];
            if(currSum > threshold) {
                return prices.length - r;
            }
            r++;
        }

        while(r < prices.length) {
            currSum += prices[r];
            currSum -= prices[l];

            if(currSum > threshold) {
                return prices.length - r;
            }
            r++;
            l++;
        }

        return 0;
    }

    public static void main(String[] args) {
        MinimumItemRemoval mir = new MinimumItemRemoval();
        int minimumNumberOfItemsToBeRemoved = mir.getMinimumNumberOfItemsToBeRemoved(new int[]{4, 5, 1, 9, 1}, 2, 10);
        System.out.println(minimumNumberOfItemsToBeRemoved);
    }


}
