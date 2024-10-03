package com.interview.practice;

public class PalindromeCheck {
    boolean isAlphaNumeric(char ch) {
        if (Character.isAlphabetic(ch) || Character.isDigit(ch)) {
            return true;
        }
        return false;
    }
    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        while (l <= r) {
            if (!isAlphaNumeric(s.charAt(l))) {
                l++;
                continue;
            }

            if (!isAlphaNumeric(s.charAt(r))) {
                r--;
                continue;
            }


            if (Character.toLowerCase(s.charAt(l)) == Character.toLowerCase(s.charAt(r))) {
                l++;
                r--;
            } else {
                return false;
            }

        }
        return true;

    }
}
