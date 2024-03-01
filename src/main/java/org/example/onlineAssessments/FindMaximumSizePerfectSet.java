package org.example.onlineAssessments;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a set of numbers
 * Find Maximum size of a perfect set
 * A perfect set of size n satifies the condition perfect[i+1]=perfect[i]*perfect[i]
 * where 1<=i<=n and n<=10^5
 * Perfect set should have atleast 2 numbers
 * [625,2,4,5,25] has 2 perfect sets [2,4] and [5,25,625]
 * Answer will be 3 in this case.
 *
 * sol1. sort & use disjoint set
 */

public class FindMaximumSizePerfectSet {

    public int getMaximumSetSize(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> perfectMap = new HashMap<>();
        int answer = 0;
        for(int num : nums) {
            int root = (int) Math.sqrt(num);
            if(root * root == num) {
                perfectMap.put(num, perfectMap.getOrDefault(root, 0) + 1);
                answer = Math.max(answer, perfectMap.get(num));
            }
            else {
                perfectMap.put(num, 1); // this is the base key
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{625,2,4,5,25};
        FindMaximumSizePerfectSet sol = new FindMaximumSizePerfectSet();
        int maximumSetSize = sol.getMaximumSetSize(arr1);

        System.out.println(maximumSetSize);

    }
}
