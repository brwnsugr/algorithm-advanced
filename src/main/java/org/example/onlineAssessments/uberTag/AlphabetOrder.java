package org.example.onlineAssessments.uberTag;

public class AlphabetOrder {

    public static void main(String[] args) {
        AlphabetOrder alphabetOrder = new AlphabetOrder();
        String s1 = "ack";
        String s2 = "bdl";

        String s3 = "ackz";
        String s4 = "bdla";

        String s5 = "ack";
        String s6 = "bdl";

        boolean b = alphabetOrder.nextSame(s5, s6);
        System.out.println(b);
    }

    public boolean nextSame(String a, String b) {
        int len = a.length();
        int alphabetNum = 26;

        for(int i = 0; i < len; i++) {
            int pos = (int)(a.charAt(i) - 'a');
            pos = (pos + 1) % alphabetNum;
            if(('a' + pos) != b.charAt(i)) return false;
        }

        return true;
    }


}
