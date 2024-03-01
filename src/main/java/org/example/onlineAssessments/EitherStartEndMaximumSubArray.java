package org.example.onlineAssessments;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * Given a list of integers, count the number of subarrays that either the start and the end of a subarray is the maximum in the subarray
 *
 * e.g.1
 * input = [2, 3, 2]
 * result = 5
 *
 * Because the subarrays are:
 * [2],[2,3],
 * [3],[3,2],
 * [2]
 *
 * e.g.2
 * input = [3, 1, 3, 5]
 * result = 10
 *
 * Because the subarrays are:
 * [3],[3,1],[3,1,3],[3,1,3,5],
 * [1],[1,3],[1,3,5],
 * [3],[3,5],
 * [5]
 *
 * ref: https://leetcode.com/discuss/interview-question/4089549/Amazon-OA
 */
public class EitherStartEndMaximumSubArray {

    public int getCountOfSubArray(int[] nums){
        int n = nums.length;
        int[] prevEqGreater = new int[n];
        int[] nextGreater = new int[n];
        Arrays.fill(prevEqGreater, -1);
        Arrays.fill(nextGreater, n);
        Stack<Integer> st = new Stack<>();
        for(int i = 0; i < n; i++) {
            while(!st.isEmpty() && nums[st.peek()] < nums[i]) {
                nextGreater[st.peek()] = i;
                st.pop();
            }
            st.add(i);
        }
        st.clear();
        for(int i = 0; i < n; i++) {
            while(!st.isEmpty() && nums[st.peek()] < nums[i]) {
                st.pop();
            }
            if(!st.isEmpty()) prevEqGreater[i] = st.peek();
            st.add(i);
        }
        int answer = 0;
        for(int i = 0; i < n; i++) {
            answer += (i - prevEqGreater[i]) + (nextGreater[i] - i) - 1;
        }

        return answer;
    }

    public int countEndsMax(int[] nums) {
        int n = nums.length;
        int[] left = new int[n], right = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=0; i<n; i++) {
            while(!dq.isEmpty() && nums[dq.peekLast()]<nums[i]) {
                dq.pollLast();
            }
            left[i] = dq.isEmpty()? -1 : dq.peekLast();
            dq.offerLast(i);
        }
        dq = new ArrayDeque<>();
        for(int i=n-1; i>=0; i--) {
            while(!dq.isEmpty() && nums[dq.peekLast()]<=nums[i]) {
                dq.pollLast();
            }
            right[i] = dq.isEmpty()? n : dq.peekLast();
            dq.offerLast(i);
        }
        int ans = 0;
        for(int i=0; i<n; i++) {
            ans+=(i-left[i]) + (right[i]-i) -1; //subtact 1 for double counting itself
        }
        return ans;
    }

    public int findWaysMaximumProfitableStock(int[] stockPrices) {

        return 1;
    }

    public static void main(String[] args) {
        EitherStartEndMaximumSubArray sol = new EitherStartEndMaximumSubArray();
        int[] test2 = new int[]{3,1,3,5};
        //  {3}, {3,1}, {3,1,3}, {3,1,3,5},
        //  {3,1}
        int countOfSubArray = sol.getCountOfSubArray(test2);
        int i = sol.countEndsMax(test2);
        System.out.println(countOfSubArray);
        System.out.println(i);
    }
}
