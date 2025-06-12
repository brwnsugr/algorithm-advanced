package org.example.onlineAssessments.y2024q3;

import java.util.ArrayList;
import java.util.List;

/**
 * ref: https://leetcode.com/discuss/interview-question/5700189/Amazon-Online-Assessment
 *
 *
 */
public class NextStringGeneration {

    public String generateNextStr(String s) {
        char[] charArr = s.toCharArray();

        if(s == null || s.isEmpty()) return "-1";

        int n = s.length() - 1;

        while(s.charAt(n) == 'z' && n >= 0) {
            n--;
        }

        if(n < 0) return "-1";

        charArr[n] = (char)((int)charArr[n] + 1);
        boolean zFlag = false;
        if(charArr[n] == 'z'){
            zFlag = true;
//            charArr[n] = (char)((int) +charArr[n] + 1);
            for(int i = n + 1; i < charArr.length; i++) {
                int r = (i - n) % 2;
                if(r == 1) charArr[i] = 'a';
                else charArr[i] = 'b';
            }
            return new String(charArr);
        }
//        int changedIdx = n;
//
//        for(int i = changedIdx + 1; i < charArr.length; i++) {
//            int r = (i - changedIdx) % 2;
//            if(r % 2 == 1) charArr[i] = 'a';
//            else charArr[i] = 'b';
//        }
//
//        return new String(charArr);

        List<Integer> repeatIndex = new ArrayList<>();

        for(int i = charArr.length -1; i>0;i--){
            if(charArr[i] == charArr[i-1] ){
                if(charArr[i] != 'z'){
                    repeatIndex.add(i);
                }else{
                    return "-1";
                }

            }
        }

//        if(repeatIndex.isEmpty() && zFlag) {
//            for(int i = 0; i < charArr.length; i++) {
//                if(charArr[i] == '{') {
//                    repeatIndex.add(i+1);
//                    break;
//                }
//            }
//        }

        if(!repeatIndex.isEmpty()){
            int lastIndex = repeatIndex.get(repeatIndex.size() -1);
            charArr[lastIndex] = (char)((int)charArr[lastIndex] + 1);
            int i= lastIndex + 1;

            for(int j = i ;j<s.length(); j++){

                if(charArr[j-1]=='a'){
                    charArr[j] = 'b';
                }else if(charArr[j-1]=='b'){
                    charArr[j] = 'a';
                }else{
                    charArr[j] = 'a';
                }
            }
        }

        return new String(charArr);

//        if (word == null || word.isEmpty()) {
//            return "-1";
//        }
//
//        StringBuilder stack = new StringBuilder();
//        int n = word.length();
//        boolean op = false;
//
//        for (int i = 0; i < n; i++) {
//            if (stack.length() == 0 || stack.charAt(stack.length() - 1) != word.charAt(i)) {
//                stack.append(word.charAt(i));
//            } else if (stack.length() > 0 && stack.charAt(stack.length() - 1) == word.charAt(i)) {
//                op = true;
//
//                if (stack.charAt(stack.length() - 1) == 'z') {
//                    while (stack.length() > 0 && stack.charAt(stack.length() - 1) == 'z') {
//                        stack.deleteCharAt(stack.length() - 1);
//                    }
//                    if (stack.length() > 0) {
//                        char letter = stack.charAt(stack.length() - 1);
//                        stack.setCharAt(stack.length() - 1, (char) (letter + 1));
//                    } else {
//                        return "-1";
//                    }
//                } else {
//                    stack.append((char) (word.charAt(i) + 1));
//                }
//                break;
//            }
//        }
//
//        if (!op) {
//            if (stack.length() > 0 && stack.charAt(stack.length() - 1) != 'z') {
//                char letter = stack.charAt(stack.length() - 1);
//                stack.setCharAt(stack.length() - 1, (char) (letter + 1));
//            } else if (stack.length() > 0 && stack.charAt(stack.length() - 1) == 'z') {
//                while (stack.length() > 0 && stack.charAt(stack.length() - 1) == 'z') {
//                    stack.deleteCharAt(stack.length() - 1);
//                }
//            }
//        }
//
//        int i = 0;
//        StringBuilder res = new StringBuilder(stack);
//        while (i < (n - stack.length())) {
//            if (i % 2 == 0) {
//                res.append('a');
//            } else {
//                res.append('b');
//            }
//            i++;
//        }
//
//        return res.toString();
    }

    public static void main(String[] args) {
        NextStringGeneration ng = new NextStringGeneration();
        String abccss = ng.generateNextStr("zzab");
        System.out.println(abccss);
    }
}
