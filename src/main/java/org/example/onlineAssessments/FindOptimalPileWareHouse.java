package org.example.onlineAssessments;

/**
 * Amazon has several warehouses that store piles of boxes containing goods to be shipped.
 *
 * In one such warehouse, there are a total of n piles numbered 1, 2, .., n, where the ith pile has boxes[i] number of boxes.
 * To have an even distribution of boxes, the caretaker can do the following operation any number of times (possibly zero):
 *
 * Choose two distinct piles, /and /(1 si, j, s n), such that boxes[i] > 0.
 *
 * Remove one box from pile / and place it on pile j. More formally, increment boxes[i] by 1 and decrement boxes[i] by 1.
 *
 * For even distribution, the caretaker wishes to minimize the difference between the maximum and the minimum number of boxes in the piles.
 * Call the minimum difference achievable d. The goal is to find the minimum number of operations required to achieve the difference d.
 *
 * [Example]
 * Consider the number of piles to be n = 4 and the boxes
 * in them are boxes = [5, 5, 8, 7]. The minimum possible
 * difference that can be achieved is 1 by transforming the piles into [6, 6, 7, 6] as follows:
 * [slika]
 *
 * Hence, the answer is 2. Note that there are multiple optimal pile configurations such as [6, 6, 7, 6], [6, 6, 6, 7].
 *
 * Function Description
 * Complete the function findMinimumOperations in the editor below.
 *
 * findMinimumOperations has the following parameter:
 * int boxes[n]: the number of boxes in each pile.
 *
 * ref: https://www.reddit.com/r/leetcode/comments/191id7q/amazon_oa_question/
 */
public class FindOptimalPileWareHouse {
    public int findMinimumOperations(int[] boxes) {
        // -> sort -> 5,5,7,8
        int total = 0;
        for(int box : boxes) total += box;
        int n = boxes.length;
        int avgWeight = total / n;
        int extraPiles = total % n ;

        int operations = 0;
        for(int i = 0; i < n; i++) {
            int curr = boxes[i];
            if(curr > avgWeight) operations += (curr - avgWeight);

            if(extraPiles > 0) {
                extraPiles--;
                operations--;
            }
        }
        return operations;
    }

    public static void main(String[] args) {
        FindOptimalPileWareHouse solution = new FindOptimalPileWareHouse();
        int minimumOperations = solution.findMinimumOperations(new int[]{5, 5, 8, 7});
        System.out.println(minimumOperations);
    }
}
