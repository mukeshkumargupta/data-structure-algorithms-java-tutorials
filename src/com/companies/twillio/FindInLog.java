package com.companies.twillio;

import java.util.*;
public class FindInLog {
    public static List<String> findName(String[] input) {
        Set<String> nameSet = new HashSet<>();
        List<String> results = new ArrayList<String>();
        String name = "";
        String GIFImage = "";
        for (int i = 0; i < input.length; i++) {
            if (input[i].indexOf("GET") > 0 && input[i].indexOf(" 200 ") > 0) {
                String[] split = input[i].split("/");
                int length = split.length;
                name = split[length-2];
                name = name.replace(" HTTP", "");
                if (name.indexOf("GIF") > 0 || name.indexOf("gif") > 0) {
                    GIFImage = name;
                    String[] onlyName = GIFImage.split("\\.");
                    if (!nameSet.contains(onlyName[0])) {
                        nameSet.add(onlyName[0]);
                    }
                }
            }
        }


        for (String n : nameSet) {
            results.add(n);
        }

        return results;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Set<String> nameSet = new HashSet<>();
        List<String> results = new ArrayList<String>();
        String[] input = {" ACDD GET / ADSAD/SDFDS/ABC.GIF HTTP/1.0\" 200 0", " ACDD GET / ADSAD/SDFDS/ABC.gif HTTP/1.0\" 200 0", " ACDD GET / ADSAD/SDFDS/BCD.gif HTTP/1.0\" 204 0", " ACDD GET / ADSAD/SDFDS/BCE.gif HTTP/1.0\" 200 0"};
        List<String> result = findName(input);
        
    }
    
}
