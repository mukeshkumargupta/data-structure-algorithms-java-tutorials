package com.companies.twillio;

import java.util.*;

public class FormatDate {
    public static String formatDate(String input) {
        Map<String, String> lookup = new HashMap<>();
        lookup.put("Jan", "01");
        lookup.put("Feb", "02");
        lookup.put("Mar", "03");
        lookup.put("Apr", "04");
        lookup.put("May", "05");
        lookup.put("Jun", "06");
        lookup.put("Jul", "07");
        lookup.put("Aug", "08");
        lookup.put("Sep", "09");
        lookup.put("Oct", "10");
        lookup.put("Nov", "11");
        lookup.put("Dec", "12");
        String[] dateFormat = input.split(" ");
        String day = dateFormat[0];
        int makeDay = 0;
        for (int i = 0; i < 2; i++) {
            Character ch = day.charAt(i);
            int number = ch - '0';
            if (number >= 0 && number <= 9) {
                makeDay = makeDay*10 + number;
            }
        }
        String month = dateFormat[1];
        String year = dateFormat[2];
        StringBuffer sb = new StringBuffer();
        sb.append(year);
        sb.append("-");
        sb.append(lookup.get(month));
        sb.append("-");
        if (makeDay < 10) {
            sb.append("0" + makeDay);
        } else {
            sb.append(makeDay);
        }
        return sb.toString();
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        formatDate("1st Mar 1974");
        formatDate("22nd Jan 2013");
    }
    
}
