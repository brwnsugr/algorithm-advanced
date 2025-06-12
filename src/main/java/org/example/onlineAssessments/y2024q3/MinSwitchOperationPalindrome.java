package org.example.onlineAssessments.y2024q3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ref: https://leetcode.com/discuss/interview-question/5798402/Amazon-OA-questions
 * Given an array, you can perform following operation:
 * Choose integers x and y, and change every occurrence of x to y.
 * Return minimum no of operations to make the array palindromic.
 *
 */

public class MinSwitchOperationPalindrome {

    public int minOpsToPalindrome(int[] arr) {
        int n = arr.length;

        // i th -ith find -> same group -> save 1 op
        // diff group -> op++ and union

        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);

        Map<Integer, Integer> compressedMap = new HashMap<>();
        int compVal = 0;
        for(int val : sortedArr) {
            if(!compressedMap.containsKey(val)) {
                compressedMap.put(val, compVal++);
            }
        }

        int[] compArr = new int[arr.length];

        for(int i = 0; i < arr.length; i++) {
            compArr[i] = compressedMap.get(arr[i]);
        }
        // n = 3
        int ops = 0;
        UnionFind uf = new UnionFind(arr.length);
        for(int i = 0; i < n / 2; i++) {
            if(uf.find(compArr[i]) != uf.find(compArr[n-i-1])) {
                ops++;
                uf.union(compArr[i], compArr[n-i-1]);
            }
        }
        return ops;
    }

    public static void main(String[] args) {
        MinSwitchOperationPalindrome min = new MinSwitchOperationPalindrome();
        int[] arr1 = {1,3,2,3,1,2};
        int[] arr2 = {1,1,1,2,2,100};
        int i = min.minOpsToPalindrome(arr2);
        System.out.println(i);
    }
}

class UnionFind {
    private int[] root;

    public UnionFind(int size) {
        root = new int[size];

        for(int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    public int find(int x) {
        if(root[x] != x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX != rootY) {
            root[rootY] = rootX;
        }
    }
}