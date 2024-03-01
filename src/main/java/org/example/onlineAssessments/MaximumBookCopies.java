package org.example.onlineAssessments;

import java.util.*;

public class MaximumBookCopies {
    Map<Integer, Integer> bookCopiesMap = new HashMap<>();
    Map<Integer, Set<Integer>> copiesBookSetMap = new HashMap<>();
    public int[] getMaximumCopies(int[] portalUpdate, int n) {
        int[] answer = new int[n];

        int currMaxCopies = 0;
        for(int i = 0; i < n; i++) {
            int update = portalUpdate[i];
            int bookId = Math.abs(update);
            if(update > 0) {
                int prevCopies = bookCopiesMap.getOrDefault(bookId, 0);
                // remove prev copies from set
                if(copiesBookSetMap.containsKey(prevCopies)) {
                    copiesBookSetMap.get(prevCopies).remove(bookId);
                }

                bookCopiesMap.put(bookId, bookCopiesMap.getOrDefault(bookId, 0) + 1);
                int currCopies = bookCopiesMap.get(bookId);
                if(currCopies > currMaxCopies) {
                    currMaxCopies = currCopies;
                }
                answer[i] = currMaxCopies;

                // add curr copies to set
                if(!copiesBookSetMap.containsKey(currCopies)) {
                    copiesBookSetMap.put(currCopies, new HashSet<>());
                }
                copiesBookSetMap.get(currCopies).add(bookId);
            }
            else if(update < 0) {
                int prevCopies = bookCopiesMap.get(bookId);
                copiesBookSetMap.get(prevCopies).remove(bookId);
                int currCopies = prevCopies - 1;
                bookCopiesMap.put(bookId, currCopies);
                copiesBookSetMap.getOrDefault(currCopies, new HashSet<>()).add(bookId);
                if(prevCopies == currMaxCopies) {
                    currMaxCopies = copiesBookSetMap.get(prevCopies).size() == 0 ? currCopies : prevCopies;
                }
                answer[i] = currMaxCopies;
            }

        }



        return answer;
    }

    public static void main(String[] args) {
        int[] test1 = new int[]{6, 6, 2, -6, -2, -6};
        int[] test2 = new int[]{1, 2, -1, 2};
        new MaximumBookCopies().getMaximumCopies(test2, test2.length);
    }
}
