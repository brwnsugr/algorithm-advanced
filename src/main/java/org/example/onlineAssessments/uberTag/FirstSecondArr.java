package org.example.onlineAssessments.uberTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class FirstSecondArr {

    public List<Integer> distribute(List<Integer> arr) {
        int n = arr.size();
        TreeSet<Integer> firstSet = new TreeSet<>();
        TreeSet<Integer> secondSet = new TreeSet<>();
        List<Integer> firstArr = new ArrayList<>();
        List<Integer> secondArr = new ArrayList<>();

        firstSet.add(arr.get(0));
        firstArr.add(arr.get(0));
        secondSet.add(arr.get(1));
        secondArr.add(arr.get(1));

        for(int i = 2; i < n; i++) {
            int g1 = firstSet.tailSet(arr.get(i)).size();
            int g2 = secondSet.tailSet(arr.get(i)).size();
            if(g1 > g2) {
                firstSet.add(arr.get(i));
                firstArr.add(arr.get(i));
            }
            else if(g1 == g2) {
                if(firstSet.size() > secondSet.size()) {
                    secondSet.add(arr.get(i));
                    secondArr.add(arr.get(i));
                }
                else {
                    firstSet.add(arr.get(i));
                    firstArr.add(arr.get(i));
                }
            }
            else {
                secondSet.add(arr.get(i));
                secondArr.add(arr.get(i));
            }
        }

        List<Integer> ans = new ArrayList<>();
        ans.addAll(firstArr);
        ans.addAll(secondArr);

        return ans;
    }

    public List<Integer> distribute2(int[] arr) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        first.add(arr[0]);
        second.add(arr[1]);
        TreeSet<Integer> firstSet = new TreeSet<>();
        TreeSet<Integer> secondSet = new TreeSet<>();

        for(int i = 2; i < arr.length; i++) {
//            int g1 =
        }


        return null;
    }

    public static void main(String[] args) {
        FirstSecondArr distribution = new FirstSecondArr();
        List<Integer> distribute = distribution.distribute(Arrays.asList(5, 2, 3, 7, 1));

        System.out.println(distribute);
    }
}
