package org.example.onlineAssessments.ppay;

import java.util.HashMap;
import java.util.Map;

public class PalindromeMove {

    public boolean validatePalindromeMove(String[] arr) {
        int i = 0;
        int j = arr.length - 1;

        while(i <= j) {
            if(!compareAndMakeString(i, j, arr)) return false;
            i++;
            j--;
        }

        return true;
    }

    private boolean compareString(String a, String b) {
        return a.equals(b);
    }

    private boolean compareAndMakeString(int i, int j, String[] arr) {
        if(i == j) return true;

        StringBuilder strB = new StringBuilder();

        if(arr[i].length() < arr[j].length()) {
            strB.append(arr[i]).append(arr[i+1].charAt(0));
            if(compareString(strB.toString(), arr[j])) {
                arr[i] = arr[i] + arr[i+1].charAt(0);
                arr[i+1] = arr[i+1].substring(1);
                return true;
            }
        }
        else if(arr[j].length() < arr[i].length()) {
            strB.append(arr[j-1].charAt(arr[j-1].length()-1)).append(arr[j]);
            if(compareString(strB.toString(), arr[i])) {
                arr[j] = arr[j-1].charAt(arr[j-1].length() - 1) + arr[j];
                arr[j-1] = arr[j-1].substring(1);
                return true;
            }
        }
        else {
            if(arr[i].equals(arr[j])) return true;
            else {
                strB.append(arr[i]).append(arr[i+1].charAt(0));
                StringBuilder strB2 = new StringBuilder();
                strB2.append(arr[j-1].charAt(arr[j-1].length() - 1)).append(arr[j]);
                if(strB.toString().equals(strB2.toString())) {
                    arr[i] = arr[i] + arr[i+1].charAt(0);
                    arr[i+1] = arr[i+1].substring(1);

                    arr[j] = arr[j-1].charAt(arr[j-1].length() - 1) + arr[j];
                    arr[j-1] = arr[j-1].substring(0, arr[j-1].length() - 1);
                    return true;
                }

                strB = new StringBuilder();
                strB.append(arr[i].substring(0, arr[i].length() - 1));
                strB2 = new StringBuilder();
                strB2.append(arr[j].substring(1));
                if(strB.toString().equals(strB2.toString())) {
                    arr[i] = arr[i].substring(0, arr[i].length() - 1);
                    arr[i+1] = arr[i].charAt(arr[i].length() - 1) + arr[i+1];

                    arr[j] = arr[j].substring(1);
                    arr[j-1] = arr[j-1] + arr[j].charAt(0);
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        PalindromeMove sol = new PalindromeMove();
        String[] arr = {"aa", "bab", "cde", "aba", "ab"};
        String[] arr1 = {"aa", "bab", "cde", "aba", "ab"};
        String[] arr4 = {"aab", "bb", "baa"};
        String[] arr2 = {"dfwkfjwkl"};
        String[] arr3 = {"aaaaaa", "aa"};
        boolean b = sol.validatePalindromeMove(arr4);

        System.out.println(b);
    }

//    private boolean

}
