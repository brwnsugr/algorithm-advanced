package org.example.onlineAssessments;

/**
 * The residents of HackerLand are participating in Sports Week and they need to form a team, Given n people in the city standing in a line, an array of each person's efficiency, and an integer k (1 ≤ k≤ n), find the number of subarrays of odd lengths that have a median equal to efficiency[k].
 * Note:
 * • A subarray is a sequence of consecutive elements of the array.
 * • The median of an array of odd length, say n, is the (n + 1)/2th element of the array if sorted in non-decreasing order. For example, the median of [2, 5, 4, 1, 1, 1, 6] of length 7 is 2, since upon sorting, the array becomes [1, 1, 1, 2, 4, 5, 6] and the (7+1)/2 = 4th element is 2.
 *
 * Example
 * efficiency = [5, 3, 1, 4, 7, 7], k= 4
 * efficiency[4] = 4
 * There are 4 odd length subarrays with 4 as their median: [4], [1, 4, 7], [5, 3, 1, 4, 7],
 * [3, 1, 4, 7, 7].
 * Return 4.
 *
 * Function Description
 * Complete the function getMedianSubarrays in the editor below.
 * getMedianSubarrays has the following parameters:
 * int efficiency[n]: efficiencies of people
 * int k: the index of the required median value
 *
 * Returns
 * long: the number of odd length subarrays where arr[k] is the median
 * Constraints
 * • The value at efficiency[k] occurs only once in the array.
 * •1≤n≤3*10^5
 * • 1 ≤ efficiency[i] ≤ 10^9
 * • 1≤ k ≤n
 *
 * ref: https://leetcode.com/discuss/interview-question/4693944/Amazon-Seattle-Hackerrank-(Bombed)
 */
public class HackerLandSportsWeek {

    public int getMedianSubarrays(int[] efficiency, int k){

        return 1;
    }
}
