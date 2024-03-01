package org.example.onlineAssessments;

/**
 * The algorithm rearranges the characters to have
 * the following characteristics:
 * « Itis a rearrangement of the original palindromic
 * password.
 * Itis also a palindrome.
 *
 * Among all such palindromic rearrangements, it is.
 * the lexicographically smallest.
 * Given the original palindromic password that
 * consists of lowercase English characters only, find
 * the encrypted password.
 * A string s is considered to be lexicographically
 * smaller than the string ¢ of the same length if the
 * first character in s that differs from that in tis
 * smaller. For example, "abcd" is lexicographically
 * smaller than "abdc" but larger than "abad"
 *
 * Note that the encrypted password might be the
 * same as the original password if it is already
 * lexicographically smallest.
 *
 * Example:
 *
 * The original password is password = "babab".
 *
 * This can be rearranged to form abbba, which is
 * both a rearrangement of the original password and
 * the lexicographically smallest palindrome.
 *
 * It satisfies all the requirements so return the string
 * abbba.
 *
 * Returns
 * string: the encrypted password
 * Constraints
 * 1< [password] < 10^5
 *
 * password consists of lowercase English letters only.
 *
 * passwordis a palindrome.
 *
 * ref: https://leetcode.com/discuss/interview-question/4569945/Amazon-Oa
 */

public class SmallestLexicographicalPalindrome {

    public String getSmallestPalindromeStr(String original) {
        int[] counts = new int[26];

        int half = original.length() / 2;

        for(int i = 0; i < half; i++) {
            char c = original.charAt(i);
            counts[c - 'a']++;
        }
        StringBuilder halfStrBuilder = new StringBuilder();
        for(int i = 0; i < 26; i++) {
            while(counts[i] > 0) {
                halfStrBuilder.append((char)(i + 'a'));
                counts[i]--;
            }
        }
        char midChar = '\0';
        StringBuilder ansBuilder = new StringBuilder();
        ansBuilder.append(halfStrBuilder);
        if(original.length() % 2 == 1) {
            midChar = original.charAt(half);
            ansBuilder.append(midChar);
        }
        ansBuilder.append(halfStrBuilder.reverse());
        return ansBuilder.toString();
    }

    public static void main(String[] args) {
        SmallestLexicographicalPalindrome sol = new SmallestLexicographicalPalindrome();
        String test1 = "baabaab";
        String test2 = "babab";
        String test3 = "xzxyxzx";
        String ans = sol.getSmallestPalindromeStr(test3);
        System.out.println(ans);
    }
}
