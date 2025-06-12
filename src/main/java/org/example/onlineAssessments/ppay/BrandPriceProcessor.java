package org.example.onlineAssessments.ppay;

import java.util.*;

public class BrandPriceProcessor {
    public List<String> processBrands(List<String> inputs) {
        Map<String, int[]> brandMap = new HashMap<>();

        for(String input : inputs) {
            String[] arr = input.split(",");
            String brand = arr[0];

            int price = Integer.parseInt(arr[1]);

            if(!brandMap.containsKey(brand)) {
                brandMap.put(brand, new int[]{price, price, price});
            }
            else {
                int[] prices = brandMap.get(brand);

                // set newest price
                prices[1] = price;
                // lowest price
                if(price < prices[0]) {
                    prices[0] = price;
                }
                if(price > prices[1]) {
                    prices[2] = price;
                }
            }
        }
        List<String> res = new ArrayList<>();

        for(Map.Entry<String, int[]> entry : brandMap.entrySet()) {
            String brandID = entry.getKey();
            int[] prices = entry.getValue();
            String result = String.format("%s %d %d %d", brandID, prices[0],prices[1],prices[2]);
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(brandID).append(' ').append(prices[0]).append(' ').append(prices[1]);
            String string = strBuilder.toString();
            res.add(result);
        }

        return res;
    }

    public static void main(String[] args) {


        BrandPriceProcessor solution = new BrandPriceProcessor();

        List<String> input = Arrays.asList("A,100", "B,200", "A,150", "A,80", "B,250");

        solution.processBrands(input);
    }
}
