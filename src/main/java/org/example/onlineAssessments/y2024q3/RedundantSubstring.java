package org.example.onlineAssessments.y2024q3;

/**
 * https://www.reddit.com/r/leetcode/comments/1e8zy3m/amazon_2024_online_assessment_need_optimized/
 * https://leetcode.com/discuss/interview-question/5585033/Amazon-OA-August-2024%3A-Need-help
 *
 */

import java.util.*;

public class RedundantSubstring {

//    private Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'i', 'e', 'o','u'));
    public int solution(int a, int b, String s) {
        int x = 0;
        int y = 0;
        int ans = 0;

        Set<Character> vowelSet = new HashSet<>(Arrays.asList('a','i','e','o','u'));
        Map<Integer, Integer> cumulativeCostCountMap = new HashMap<>();

        cumulativeCostCountMap.put(0, 1);

        for(int i = 0; i < s.length(); i++) {
            if(vowelSet.contains(s.charAt(i))) {
                x++;
            }
            else {
                y++;
            }

            int cost = x * a + y * b;

            if(cumulativeCostCountMap.containsKey(cost - i - 1)) {
                ans += cumulativeCostCountMap.get(cost - i - 1);
            }

            cumulativeCostCountMap.put(cost - i - 1, cumulativeCostCountMap.getOrDefault(cost-i-1, 0) + 1);
        }

        return ans;
    }


    public static void main(String[] args) {
        RedundantSubstring r = new RedundantSubstring();
        String s1 = "afejipl";
        int a1 = 4;
        int b1 = -2;

        String s2 = "akljfs";
        int a2 = -2;
        int b2 = 1;

        String s3 = "abbacc";
        int a3 = -1;
        int b3 = 2;

        int abbacc = r.solution(a3, b3, s3);
        System.out.println(abbacc);
    }
}
