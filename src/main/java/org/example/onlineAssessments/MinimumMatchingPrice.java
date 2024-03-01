package org.example.onlineAssessments;

import java.util.HashMap;
import java.util.Map;

/**
 * There are n products being sold on a shopping app. The price of the ith product is price[i].
 * The developers decided to give special gift cards to innovative customers.
 * A gift card will be given if any customer buys a contiguous subsegment of products and at least 2 of the products have a matching price.
 * Find the minimum amount of money a customer needs to spend in order to get the gift card.
 * If it is not possible for any customer to get a gift card, return -1.
 *
 * Example:
 * price = [1, 2, 3, 1, 2, 1]
 *
 * In the above example, the number of products n = 6. The subsegments where not all prices are distinct are (in increasing order of starting positions):
 *
 * Subsegment	Remarks	Total Cost
 * [1, 2, 3, 1]	1 has a matching price	7
 * [1, 2, 3, 1, 2]	1 and 2 have a matching price	9
 * [1, 2, 3, 1, 2, 1]	1 and 2 have a matching price	10
 * [2, 3, 1, 2]	2 has a matching price	8
 * [3, 1, 2, 1]	1 has a matching price	7
 * [1, 2, 1]	1 has a matching price	4
 * For all the other subsegments, all prices are distinct. The subsegment with the minimum price is from index 3 to 5 which costs 1 + 2 + 1 = 4. Return 4.
 *
 * Parameters:
 * int price[n]: The prices of the products
 *
 * Returns:
 * int: The minimum amount of money to be spent or -1
 *
 * Constraints:
 *
 * 1 <= n <= 5 · 105
 * 1 <= price[i] <= 106, 0 <= i <= n.
 * Other Cases:
 * Case 1:
 * price = [1, 2, 1, 2]
 * Output: 4
 * Explanation: The subsegments where not all prices are distinct are [1, 2, 1], [2, 1, 2] and [1, 2, 1, 2]. Of these, the minimum total price is 4.
 *
 * Case 2:
 * price = [1, 100, 1, 7, 7]
 * Output: 14
 * Explanation: The subsegments where not all prices are distinct are: [1, 100, 1], [7, 7] and [1, 100, 1, 7, 7].
 */
public class MinimumMatchingPrice {

    public int getMinimumPrice(int[] prices) {
        Map<Integer, Integer> lastPosMap = new HashMap<>();
        // (1 xxx 1 xxxx1 )
        //  i --->i-----curr   when we meet new 1, get range sum between last position and current.
        // range Sum -> store prefixSum
        int n = prices.length;
        int[] prefixSum = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            prefixSum[i] = prices[i - 1] + prefixSum[i - 1];
        }
        int answer = Integer.MAX_VALUE;
        for(int curr = 0; curr < n; curr++) {
            if(!lastPosMap.containsKey(prices[curr])) {
                lastPosMap.put(prices[curr], curr);
            }
            else {
                int lastPos = lastPosMap.get(prices[curr]);
                int rangeSum = prefixSum[curr + 1] - prefixSum[lastPos];
                answer = Math.min(rangeSum, answer);
                lastPosMap.put(prices[curr], curr);
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public static void main(String[] args) {
        MinimumMatchingPrice minimumMatchingPrice = new MinimumMatchingPrice();
        int[] price1 = new int[]{1, 2, 1, 2};
        int[] price2 = new int[]{ 1, 100, 1, 7, 7};
        int[] price3 = new int[]{ 1, 2, 3, 4, 5, 5};

        int minimumPrice = minimumMatchingPrice.getMinimumPrice(price3);
        System.out.println(minimumPrice);
    }
}
