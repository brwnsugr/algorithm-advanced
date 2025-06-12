package org.example.onlineAssessments.ppay;

import java.util.*;

public class BrandInvestment {
    public List<String> processInvestments(List<String> input) {
        Map<String, Double> rates = new HashMap<>();
        Map<Integer, InvestMentInfo> investments = new HashMap<>();

        return new ArrayList<>();
    }


    public static void main(String[] args) {
        BrandInvestment brandInvestment = new BrandInvestment();

        List<String> input = Arrays.asList("A,3", "1,A,1000", "1,500", "calculation");
    }
}

class InvestMentInfo {
    String brandID;
    int investment;
    int withdrawal;

    public InvestMentInfo(
            String brandID, int investment
    ) {
        this.brandID = brandID;
        this.investment = investment;
        this.withdrawal = 0;
    }
}
