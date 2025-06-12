package org.example.onlineAssessments.y2024q3;

/**
 * ref: https://leetcode.com/discuss/interview-question/5619722/Amazon-or-Online-Assessment-or-2024
 *
 */

public class MaximumConsecutiveOn {
    public int getMaxConsecutiveOn(String states, int k ) {
        int l = 0;
        int r = 0;
        int currK = 0;
        int answer = 0;

        while(r < states.length()) {
            if(states.charAt(r) == '0') {
                currK++;
                while(r < states.length() && states.charAt(r) == '0') {
                    r++;
                }
            }
            // move left pointer
            while(currK > k) {
                if(states.charAt(l++) == '0') {
                    while(states.charAt(l) == '0') {
                        l++;
                    }
                    currK--;
                }
            }
            answer = Math.max(answer, r - l + 1);
            r++;
        }
        return answer;
    }

    public static void main(String[] args) {
        MaximumConsecutiveOn solution = new MaximumConsecutiveOn();

        int maxConsecutiveOn = solution.getMaxConsecutiveOn("11101010110011", 2);
        System.out.println(maxConsecutiveOn);
    }
}
