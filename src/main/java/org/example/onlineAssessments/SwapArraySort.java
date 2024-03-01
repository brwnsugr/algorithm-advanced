package org.example.onlineAssessments;

/**
 * Given an array of weights ,find the number of swapping required to arrange it in an order such that lightest weight is at the front and heaviest weight is at the last .
 * I forgot the constraints , it was easy single array traversal .
 * Swapping can only be done between adjacent element .
 * Eg1 - input array - [2,4,3,1,6]
 * 		Explaination - 6 is already at its place ,
 * 								3 <-> 1  --- array changes to --> [2,4,1,3,6]  swap_count=1
 * 								4 <-> 1  --- array changes to --> [2,1,4,3,6]  swap_count=2
 * 								2 <-> 1  --- array changes to --> [1,2,4,3,6]  swap_count=3
 * 		Ouput should be like - [1,2,4,3,6]
 * 		Number of operations needed - 3
 * 	Note: Just one lightest element leftMost & just one heaviest rightMost
 */
public class SwapArraySort {

    public int getNeededOperations(int[] nums) {
        /**
         * find min,
         * leftMost idx of min
         *
         * find max,
         * rightMost idx of max
         *
         * if minIdx > maxIdx, duplicated swap so subtract 1 more
         * else just get dist maxIdx to the rightEnd , minIdx to the leftStart
         */

        return 0;
    }
}
