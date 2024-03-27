package org.example.designImpl;

public class AddTwoBigNumbers {

    public String addTwoLongNumbers(String num1, String num2) {
        int d1 = num1.indexOf('.');
        // 11.11222 8-2
        int decimalPlaces1 = num1.length() - d1;
        int d2 = num2.indexOf('.');
        int decimalPlaces2 = num2.length() - d2;

        // n1 = 11.11 , n2 = 112.345
        while(decimalPlaces1 > decimalPlaces2) {
            num2 += "0";
            decimalPlaces2++;
        }
        while(decimalPlaces1 < decimalPlaces2) {
            num1 += "0";
            decimalPlaces1++;
        }

        char[] num1Array = new StringBuilder(num1).reverse().toString().toCharArray();
        char[] num2Array = new StringBuilder(num2).reverse().toString().toCharArray();



        StringBuilder strBuilder = new StringBuilder();
        int carry = 0;
        for(int i = 0; i < Math.min(num1Array.length, num2Array.length); i++) {
            if(num1Array[i] == '.') {
                strBuilder.append('.');
                continue;
            }

            int temp = (num1Array[i] -'0') + (num2Array[i] - '0') + carry;
            strBuilder.append(temp % 10);
            carry = temp / 10;
        }

        for (int i = Math.min(num1Array.length, num2Array.length); i < num1Array.length; i++) {
            if (num1Array[i] == '.') {
                strBuilder.append(".");
                continue;
            }
            int temp = (num1Array[i] - '0') + carry;
            strBuilder.append(temp % 10);
            carry = temp / 10;
        }

        for (int i = Math.min(num1Array.length, num2Array.length); i < num2Array.length; i++) {
            if (num2Array[i] == '.') {
                strBuilder.append(".");
                continue;
            }
            int temp = (num2Array[i] - '0') + carry;
            strBuilder.append(temp % 10);
            carry = temp / 10;
        }

        if (carry > 0) {
            strBuilder.append(carry);
        }

        return strBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        AddTwoBigNumbers addTwoBigNumbers = new AddTwoBigNumbers();
        String s = addTwoBigNumbers.addTwoLongNumbers("123.456", "789.12");
    }
}
