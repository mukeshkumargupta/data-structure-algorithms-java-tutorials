package com.interview.companies;

public class howManyWordCount_Cisco {
    static boolean isValid(Character ch) {
        if (Character.isLetter(ch) || ch == '.' || ch == ',' || ch == '?' || ch == '!') {
            return true;
        }
        return false;
        
    }
    public static int howMany_Old(String str) {

        str = str.replaceAll("-", "");
        str = str.replaceAll(".", " ");
        str = str.replaceAll(",", " ");
        str = str.replaceAll("?", " ");
        str = str.replaceAll("!", " ");

        if (str.isEmpty())
            return 0;

        int wordCount = 0;

        boolean isWord = false;
        int endOfLine = str.length() - 1;

        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (isValid(ch[i]) && i != endOfLine) {
                isWord = true;
            } else if (!isValid(ch[i]) && isWord) {
                wordCount++;
                isWord = false;
            } else if (isValid(ch[i]) && i == endOfLine) {
                wordCount++;
            }
        }
        return wordCount;
    }
    
    public static int howMany(String str) {

        str = str.replaceAll("-", "");
        str = str.replaceAll("\\.", " ");
        str = str.replaceAll(",", " ");
        str = str.replaceAll("\\?", " ");
        str = str.replaceAll("!", " ");
        String[] splittedArr = str.split("\\s+");
        int wordCount = 0;
        for (String elm : splittedArr) {
            boolean isWord = true;
            for (int i = 0; i < elm.length(); i++) {
                if (Character.isLetter(elm.charAt(i))) {
                    continue;
                } else {
                    isWord = false;
                    break;
                }
            }
            if (isWord) {
                wordCount++;
            }
            
            
        }

        return wordCount;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        howManyWordCount_Cisco instance = new howManyWordCount_Cisco();
        String input = "abc? deg. 58abc, ab67c abc67 abc$abc pqr, pqr! pqr? done-done. 865";
        System.out.println(howMany(input));
    }
    
}
