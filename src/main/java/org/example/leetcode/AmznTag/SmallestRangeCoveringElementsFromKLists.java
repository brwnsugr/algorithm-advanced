package org.example.leetcode.AmznTag;

import java.util.LinkedList;
import java.util.List;

/**
 *  TC:
 *  https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/description/
 */

public class SmallestRangeCoveringElementsFromKLists {
    public int[] smallestRange(List<List<Integer>> nums) {
        // nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
        //
        // range = [20, 24]
        // pick every pair of elements among the lists.
        // pair of elements can be from other list because we have to compare all the pairs with min and max
        int minx = 0; int miny = Integer.MAX_VALUE;
        for(int i = 0; i < nums.size(); i++) {
            for(int j = 0; j < nums.get(i).size(); j++){
                for(int k = i; k < nums.size(); k++) {
                    for(int l = (k == i ? j : 0); l < nums.get(k).size(); l++) {
                        // for every pair, we get currMin & currMax
                        int currMin = Math.min(nums.get(i).get(j), nums.get(k).get(l));
                        int currMax = Math.max(nums.get(i).get(j), nums.get(k).get(l));

                        int n, m;
                        // for any element among all the list in lists, we check at least one element contains in this (currMin~currMax) range for every list.
                        for(m = 0; m < nums.size(); m++) {
                            for(n = 0; n < nums.get(m).size(); n++) {
                                // if any element in list in the range, we break and keep going other list
                                if(nums.get(m).get(n) >= currMin && nums.get(m).get(n) <= currMax) {
                                    break;
                                }
                            }

                            // if we can't find any element in range, break the loop and not consider update.
                            if(n == nums.get(m).size()) {
                                break;
                            }
                        }
                        // it means we satisfies all list can include at least one element in that currMin~currMax range
                        if(m == nums.size()) {
                            // then we update currMin&currMax to minx &miny
                            if(miny - minx > currMax - currMin || (miny-minx == currMax - currMin && minx > currMin)) {
                                miny = currMax;
                                minx = currMin;
                            }

                        }
                    }
                }
            }
        }
        return new int[]{minx, miny};
    }

    public static void main(String[] args) {
        LinkedList<Integer> aa = new LinkedList<>();

        aa.addFirst(2);
        aa.addFirst(3);
        aa.addFirst(4);
        aa.addFirst(5);
        System.out.println(aa.peekFirst());
    }
}
