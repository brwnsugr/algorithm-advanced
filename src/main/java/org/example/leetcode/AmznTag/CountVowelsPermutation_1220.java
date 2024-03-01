package org.example.leetcode.AmznTag;

/**
 * TC: O(N) where N is n
 * ref: https://leetcode.com/problems/count-vowels-permutation/
 */
public class CountVowelsPermutation_1220 {
    public int countVowelPermutation(int n) {
        // 'a', 'e', 'i', 'o', 'u'
        // we can think of the order of vowel as directed graph.
        // 1. e,i,u -> a
        // 2. a,i -> e
        // 3. e,o -> i
        // 4. i -> o
        // 5. i,o -> u
        final int MOD = 1_000_000_007;

        long[] aVowelCount = new long[n];
        long[] eVowelCount = new long[n];
        long[] iVowelCount = new long[n];
        long[] oVowelCount = new long[n];
        long[] uVowelCount = new long[n];

        // can be init first index by 1. Because we can let each vowels for the first index, which can be 1 count.

        aVowelCount[0] = 1;
        eVowelCount[0] = 1;
        iVowelCount[0] = 1;
        oVowelCount[0] = 1;
        uVowelCount[0] = 1;
        long answer = 0;

        for(int i = 1; i < n; i++) {
            aVowelCount[i] = (eVowelCount[i - 1] + iVowelCount[i - 1] + uVowelCount[i - 1]) % MOD;
            eVowelCount[i] = (aVowelCount[i - 1] + iVowelCount[i - 1]) % MOD;
            iVowelCount[i] = (eVowelCount[i - 1] + oVowelCount[i - 1]) % MOD;
            oVowelCount[i] = iVowelCount[i - 1] % MOD;
            uVowelCount[i] = (iVowelCount[i - 1] + oVowelCount[i - 1]) % MOD;
        }

        // before % MOD, answer can handle the long type.
        // after % MOD, we can cast to int type.
        answer = (aVowelCount[n - 1] + eVowelCount[n - 1] + iVowelCount[n - 1] + oVowelCount[n - 1] + uVowelCount[n - 1]) % MOD;

        return (int) answer;
    }
}
