package org.example.onlineAssessments.smartn;

public class LargestPalindrome {
    public String largestPalindromic(String num) {
        int[] counts = new int[10];
        int n = num.length();
        for(char c : num.toCharArray()) {
            counts[c-'0']++;
        }
        StringBuilder first = new StringBuilder();
        boolean middleFlag = false;

        if(counts[0] == n) return "0";
        char middle = 'a';
        for(int i = 9; i >= 0; i--) {
            if(!middleFlag && counts[i] % 2 == 1) {
                middle = (char)(i + '0');
                middleFlag = true;
            }

            int freq = counts[i] / 2;
            while(freq > 0) {
                freq--;
                first.append(i);
            }
        }


        if(first.length() > 0 && first.charAt(0) == '0') return String.valueOf(middle);

        StringBuilder second = new StringBuilder(first).reverse();

        if(middleFlag) {
            first.append(middle);
        }

        return first.append(second).toString();
    }
}
