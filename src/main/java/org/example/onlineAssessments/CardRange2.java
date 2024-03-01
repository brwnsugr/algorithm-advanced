package org.example.onlineAssessments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardRange2 {

    class Card  {
        int front, back;

        Card(int a, int b){
            this.front = a;
            this.back = b;
        }
    }

    public int solve(int n, int m, int[] A, int[] B) {
        int INF = (int) 1e7;
        List<int[]> lRangeAfterKFlips = new ArrayList<>();
        lRangeAfterKFlips.add(new int[]{INF, 1});

        Card[] cards = new Card[n];
        for(int i = 0; i < n; i++) {
            cards[i] = new Card(A[i], B[i]);
        }

        Arrays.sort(cards, (a, b) -> a.front - b.front);

        int leftChangeLim = 0;
        int rightChangeLim = 0;

        int leftBMin = INF;
        int leftBMax = 1;

        //check from leftMost if flipping is beneficial
        for(int i = 0; i < m; i++) { // no flip
            if(cards[i].front >= cards[i].back || cards[i].front >= leftBMin) {
                break;
            }
            else { // flip
                leftChangeLim++;
                leftBMin = Math.min(leftBMin, cards[i].back);
                leftBMax = Math.max(leftBMax, cards[i].back);
                lRangeAfterKFlips.add(new int[]{leftBMin, leftBMax});
            }
        }

        int rightBMin = INF;
        int rightBMax = 1;

        List<int[]> rRangeAfterKFlips = new ArrayList<>();
        rRangeAfterKFlips.add(new int[]{INF, 1});

        for(int i = n - 1; i >= n - m; i--) {
            if(cards[i].front <= cards[i].back || rightBMax >= cards[i].front) {
                break;
            }
            else {
                rightChangeLim++;
                rightBMin = Math.min(rightBMin, cards[i].back);
                rightBMax = Math.max(rightBMax, cards[i].back);
                rRangeAfterKFlips.add(new int[]{rightBMin, rightBMax});
            }
        }

        // calculate the initial range without any flips
        int ans = cards[n - 1].front - cards[0].front;

        // start optimizing the range by considering flips using two pointers
        int l = leftChangeLim;
        int r = 0;

        while(l >= 0) {
            int baseMin = lRangeAfterKFlips.get(l)[0];
//            if(l < n) {
                baseMin = Math.min(baseMin, cards[l].front); // update current position too.
//            }
            int baseMax = lRangeAfterKFlips.get(l)[1];
            int currMin = Math.min(baseMin, rRangeAfterKFlips.get(r)[0]);
            int currMax = Math.max(baseMax, rRangeAfterKFlips.get(r)[1]);

//            if(n - r - 1 >= 0) {
                currMax = Math.max(currMax, cards[n-r-1].front);
//            }

            ans = Math.min(ans, currMax - currMin);

            // Attempt to improve the range by adjusting flips
            // for(int j = 0; j < Math.min(m-l, rightChangeLim) - r; j++) {
            for(int j = 0; j < Math.min(m-l, rightChangeLim - r); j++) {
                int nextMin = Math.min(baseMin, rRangeAfterKFlips.get(r+1)[0]);
                int nextMax = Math.max(baseMax, rRangeAfterKFlips.get(r+1)[1]);

                // if there's one more card to flip from the right side, we update it for nextMax.
                if (n - r - 2 >= 0) {
                    nextMax = Math.max(nextMax, cards[n - r - 2].front);
                }
                if (nextMax - nextMin <= currMax - currMin) {
                    currMin = nextMin;
                    currMax = nextMax;
                    ans = Math.min(ans, nextMax - nextMin);
                    r++;
                }
                else break;
            }
            l--;
        }
        return ans;
    }

    public static void main(String[] args) {
        CardRange2 solution = new CardRange2();
        int m0 = 2;
        int[] A0 = {10, 20, 30, 40};
        int[] B0 = {35, 25, 15, 5};
        int n0 = A0.length;

        int n = 7, m = 3;
        int[] A = {1, 2, 16, 9, 17, 3, 13};
        int[] B = {10, 12, 5, 6, 11, 8, 4};

        int n2 = 3, m2 = 3;
        int[] A2 = {1, 3, 6};
        int[] B2 = {101, 102, 103};

        int n3 = 5; int m3 = 2;
        int[] A3 = new int[]{1 ,2, 17, 16, 9};
        int[] B3 = new int[]{3, 4, 5, 6, 11};

        int n4 = 6; int m4 = 2;
        int[] A4 = new int[]{1,2,5,6,9,10};
        int[] B4 = new int[]{3,13,11,4,7,12}; // output 8

        int n5 = 5; int m5 = 2;
        int[] A5 = new int[]{1,2,17,16,9};
        int[] B5 = new int[]{3,4,5,6,11}; // output

        System.out.println(solution.solve(n5, m5, A5, B5));
    }

}
