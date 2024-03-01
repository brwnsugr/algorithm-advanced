package org.example.onlineAssessments;

import java.util.*;

/**
 * Given an array, with numbers, every number in the array represents an ID, At every position, there is a number, this means that ID was added or removed, if added, it increases the count of that ID upto that point, and if removed, it reduces the count of that ID upto that point. If the number is positive, means, it means an ID was added, if it is negative it means it was removed.
 *
 * Lets take an example, Given a array like [6,6,3,-6].
 * Position 0 - we have a 6, means we added a ID 6
 * Position 1 - we have a 6, means we added a ID 6
 * Position 2 - we have a 3, means we added ID 3
 * Position 3 - we have a -6, mean we removed ID 6
 *
 * So, the request is at every index, we need to find the number of maximum IDs in the array upto that point.
 *
 * Lets take an example
 *
 * Given an array like [6,6,3,-6]
 * The response should be [1,2,2,1]
 * This mean, at Position 0, we have added a 6, means, we so far have only one 6, so, the maximum IDs we have so far seen is 1. So at position 0, we have 1 in our answer result.
 * When we add the second 6, we shall have two 6s, so at position 1 in our answer result, we have a 2.
 * When we add a 3, we shall have two 6s and one 3, so, at position 2, our answer result will be a 2.
 * When we add a -6, we shall have removed one 6, means, the number of 6s has reduced by 1, so our maximum at that point will be 1, since we shall have one 6 and one 3.
 *
 * If our request array is [6,6,3,-6,-3,-6]
 * Our result array will be [1,2,2,1,1,0]
 *
 * If our request array is [1, 2, 1, 1, 1, 2, -1, -1, 2, -1, 2, -1, -2, -2, -2, -2]
 * Our result array is [1,1,2,3,4,4,3,2,3,3,4,4,3,2, 1, 0]
 *
 * NOTE: Anything slower than O(n) will result into into a TLE
 *
 * Constraints
 * Its guaranteed arr[i] will never be 0
 * 1<=arr.length>=10^7
 *
 * ref: https://www.reddit.com/r/leetcode/comments/17vo5tt/amazon_oa_problem_how_to_do_it_in_on/
 */
public class NumberOfMaximumId {
    private Map<Integer, Integer> numberToFrequencyMapper;
    private HashMap<Integer, Set<Integer>> frequencyToNumberMapper;
    private int maxFrequency;

    // Constructor
    public NumberOfMaximumId() {
        this.numberToFrequencyMapper = new HashMap<>();
        this.frequencyToNumberMapper = new HashMap<>();
        this.maxFrequency = 0;
    }

    // Add method
    public void add(int number) {
        int frequency = numberToFrequencyMapper.getOrDefault(number, 0) + 1;
        numberToFrequencyMapper.put(number, frequency);

        if (frequency > 1) {
            frequencyToNumberMapper.get(frequency - 1).remove(number);
        }
        frequencyToNumberMapper.computeIfAbsent(frequency, k -> new HashSet<>()).add(number);

        this.maxFrequency = Math.max(maxFrequency, frequency);
    }

    // Remove method
    public void remove(int number) {
        int frequency = numberToFrequencyMapper.get(number);

        numberToFrequencyMapper.put(number, frequency - 1);

        frequencyToNumberMapper.get(frequency).remove(number);

        if (frequency > 1) {
            frequencyToNumberMapper.get(frequency - 1).add(number);
        }
        if (frequency == this.maxFrequency && frequencyToNumberMapper.get(frequency).isEmpty()) {
            this.maxFrequency -= 1;
            frequencyToNumberMapper.remove(frequency);
        }
    }

    // getHighestFrequencyNumber method
    public int getHighestFrequencyNumber() {
        return this.maxFrequency;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 1, 1, 2, -1, -1, 2, -1, 2, -1, -2, -2, -2, -2};
        NumberOfMaximumId tracker = new NumberOfMaximumId();
        List<Integer> result = new ArrayList<>();

        for (int num : nums) {
            if (num > 0) {
                tracker.add(num);
            } else {
                tracker.remove(-num);
            }

            result.add(tracker.getHighestFrequencyNumber());
        }

        System.out.println(Arrays.toString(nums));
        System.out.println(result);
        assert(result.equals(Arrays.asList(1,1,2,3,4,4,3,2,3,3,4,4,3,2, 1, 0)));
    }
}
