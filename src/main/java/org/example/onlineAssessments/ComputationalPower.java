package org.example.onlineAssessments;

/**
 * application. The goal is to have the computational
 * power of the servers in non-decreasing order. To do
 * 50, you can increase the computational power of
 * each server in any contiguous segment by x.
 *
 * Choose the values of x such that after the
 * computational powers are in non-decreasing order,
 * the sum of the x values is minimum.
 *
 * Example
 *
 * There are n = 5 servers and their computational
 * power =[3, 4, 1, 6, 2].
 *
 * Add 3 units to the subarray (2, 4) and 4 units to the
 * subarray (4, 4). The final arrangement of the servers
 * is:[3,4,4,9,9]. Theansweris3+4=7.
 *
 * ref: https://leetcode.com/discuss/interview-question/4470176/Amazon-OA
 */
public class ComputationalPower {

    public int makePowerNonDecreasing(int[] power) {
        int margin = 0;
        for(int i = 1; i < power.length; i++) {
            int prev = power[i-1];
            int curr = power[i];
            if(prev > curr + margin) {
                int extraMargin = prev - curr - margin;
                margin += extraMargin;
            }
            power[i] += margin;
        }

        return margin;
    }

    public static void main(String[] args) {
        ComputationalPower sol = new ComputationalPower();

        int i = sol.makePowerNonDecreasing(new int[]{3, 4, 1, 6, 2});

        System.out.println(i);

    }
}
