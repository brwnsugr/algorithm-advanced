package org.example.onlineAssessments;


/**
 * Amazon Web Services stores grayscale images as an array of white and black pixels. The image is stored as a binary string where a white pixel is represented by "1". and a black pixel is represented by "0". The reverse of the image is represented as the reverse of this binary representation . For example, the reverse of "11010" is "01011". They want to store the reverse of each image as a backup. In order to reproduce the reverse from the original, the following operation can be preformed any number of timese: remove any cahracter from th estring and append it to the end of the strin, i.e, choose any pixel and shift it to the end of the string.
 *
 * Given the binary represetnation of pixels denoted by image, find the minimum number of operations needed to produce its reverse.
 *
 * Example
 * The pixel representation is image = "0100110".
 *
 * The reverse of the image, i.e, the reverse of the following sequence operations
 * The string cannot be reversed in fewer than 3 operations. Return 3
 *
 * Function description
 * Complete the function findMinimumOperations. The function takes the following parameter:
 * string image: a binary string that represents an image.
 *
 * Returns
 * int: the minimum number of operations required to produce a revese, i.e to revese the string.
 *
 * Constraints
 *
 * 1 <= length of image <= 10^5
 *
 * Example Case 1
 * Input : "00110101"
 * Output: 3
 * Explaination:
 * The string can be reveresd in minimum of 3 moves using the following sequence:
 *
 * 00110101 -> 00101011
 * 00101011 -> 01010110
 * 01010110 -> 10101100
 * Example Case 2
 * Input: 101011
 * Output: 2
 * Explaination:
 *
 * 101011 -> 110110
 * 110110 -> 110101
 *
 * ref: https://leetcode.com/discuss/interview-question/4607831/Amazon-OA-Question
 */
public class ReverseBinaryImage {

    public int findMinimumOperations(String s) {
        StringBuilder reversed = new StringBuilder(s);
        String revStr = reversed.reverse().toString();
        int j = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == revStr.charAt(j)) {
                j++;
            }
        }
        System.out.println(s.length() - j);
        return 1;
    }

    public static void main(String[] args) {
        new ReverseBinaryImage().testCase1();
    }

    public void testCase1() {
        String s = "00110101";
        int expected = 0;
        int result = findMinimumOperations(s);
        assert(result == expected);
    }
}
