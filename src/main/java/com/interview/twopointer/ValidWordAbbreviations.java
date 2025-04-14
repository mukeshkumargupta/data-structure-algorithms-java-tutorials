package com.interview.twopointer;

/*
✅ Category: Facebook, FAANG, Tricky, Easy
📌 Problem Link: https://www.youtube.com/watch?v=QTnlr9zg5cE

📝 Problem Statement:
You are given a string `STR` consisting of English lowercase letters.
You are also given another string `ABBR` consisting of lowercase letters and digits.

We say `ABBR` is a valid abbreviation of `STR` if it fulfills the following rule:
👉 While matching, if we encounter a number `X` in `ABBR`, then we skip `X` characters in `STR`.

🔍 Example:
For `STR = "abc"`, all valid abbreviations (`ABBR`) include:
[“abc”, “1bc”, “1b1”, “2c”, “3”, “a1c”, “a2”, “ab1”]

🎯 Objective:
Return TRUE if `ABBR` is a valid abbreviation of `STR`, otherwise return FALSE.

🔁 Explanation:
Use two pointers to traverse `STR` and `ABBR`.

1. If the current character in `ABBR` is a letter:
   - It must match the corresponding character in `STR`.
2. If it's a digit:
   - Convert the sequence of digits into a number `num`.
   - Skip `num` characters in `STR`.

⚠️ Edge Case:
- Numbers in `ABBR` must not start with `0` (leading zeros are invalid).
- Ensure both pointers reach the end simultaneously.

📦 Sample Input 1:
STR = "abbreviations", ABBR = "a11s"
✅ Output: YES

📦 Sample Input 2:
STR = "xyzzy", ABBR = "4"
❌ Output: NO

📦 Sample Input 3:
STR = "ninja", ABBR = "8inja"
❌ Output: NO
*/

public class ValidWordAbbreviations {

    public static boolean isDigit(char ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        } else {
            return false;

        }
    }
    public static boolean validAbbreviation(String p,String s)
    {
        //    Write your code here.
        return validWordAbbreviation(p, s);
    }

    public static boolean validWordAbbreviation(String word, String abbr) {
        //Two pointer approach
        int wordIndex = 0, abbrIndex = 0;
        while (wordIndex < word.length() && abbrIndex < abbr.length()) {
            //if (Character.isDigit(abbr.charAt(abbrIndex))) {
            if (isDigit(abbr.charAt(abbrIndex))) {
                if (abbr.charAt(abbrIndex) == '0') {
                    return false; // leading zeros are not allowed
                }
                int num = 0;
                while (abbrIndex < abbr.length() && isDigit(abbr.charAt(abbrIndex))) {
                    num = num * 10 + (abbr.charAt(abbrIndex) - '0');
                    abbrIndex++;
                }
                wordIndex += num;
            } else {
                if (word.charAt(wordIndex) != abbr.charAt(abbrIndex)) {
                    return false;
                }
                wordIndex++;
                abbrIndex++;
            }
        }
        return wordIndex == word.length() && abbrIndex == abbr.length();
    }
}
