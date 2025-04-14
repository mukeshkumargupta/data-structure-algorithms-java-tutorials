package com.interview.simulation;
import java.util.*;
/*
https://leetcode.com/problems/text-justification/description/?envType=study-plan-v2&envId=top-interview-150
Category: Hard, top150, tricky
https://www.youtube.com/watch?v=jpU2LVaDa4g&t=1522s
Related:
https://leetcode.com/problems/rearrange-spaces-between-words/ Easy
https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/ Easy
https://leetcode.com/problems/split-message-based-on-limit/ Hard

Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.


Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]


Constraints:

1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] consists of only English letters and symbols.
1 <= maxWidth <= 100
words[i].length <= maxWidth
 */
/*
✅ Time Complexity

Let W be the number of words and L be the average length of each word.

Step-by-step analysis:

1. Outer while loop (currentIndex < totalWords):
   - Each word is processed exactly once.
   - Hence, this loop runs in O(W) time.

2. Inner while loop (nextIndex < totalWords ...):
   - Determines how many words can fit in the current line.
   - Although it can seem nested, each word is only considered once in total.
   - So, total work done by this loop is also O(W).

3. String construction in formatLine():
   - For each line, we construct a string with words and spaces up to maxWidth.
   - Across all lines, this results in appending all characters once.
   - So, O(N) time where N is the total number of characters in all words and spaces.

🔹 Therefore, total time complexity = **O(W × L)** or more generally **O(N)**
   (where N = total characters across all input words)

✅ Space Complexity

1. Output List (`justifiedText`):
   - Stores L lines, each of up to `maxWidth` length.
   - So space = O(L × maxWidth)

2. StringBuilder in formatLine():
   - Used temporarily for building each line.
   - Maximum space per line = O(maxWidth), reused for each line.

3. Other variables:
   - Just a few integers and references → O(1) auxiliary space.

🔚 Final Complexity:

⏱ Time: **O(W × L)** or **O(N)**
🧠 Space: **O(L × maxWidth)** for output + **O(1)** auxiliary
*/
public class TextJustification {

    private int maxLineWidth;

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> justifiedText = new ArrayList<>();
        maxLineWidth = maxWidth;

        int totalWords = words.length;
        int currentIndex = 0;

        while (currentIndex < totalWords) {
            int lineLetterCount = words[currentIndex].length();
            int nextIndex = currentIndex + 1;
            int numberOfSpaceSlots = 0;

            while (nextIndex < totalWords &&
                    numberOfSpaceSlots + lineLetterCount + words[nextIndex].length() + 1 <= maxWidth) {
                lineLetterCount += words[nextIndex].length();
                numberOfSpaceSlots++;
                nextIndex++;
            }

            int remainingSpaces = maxWidth - lineLetterCount;
            int spacesBetweenWords = numberOfSpaceSlots == 0 ? 0 : remainingSpaces / numberOfSpaceSlots;
            int extraSpaces = numberOfSpaceSlots == 0 ? 0 : remainingSpaces % numberOfSpaceSlots;

            // Check if it's the last line for left justification
            if (nextIndex == totalWords) {
                spacesBetweenWords = 1;
                extraSpaces = 0;
            }

            justifiedText.add(formatLine(currentIndex, nextIndex, spacesBetweenWords, extraSpaces, words));
            currentIndex = nextIndex;
        }

        return justifiedText;
    }

    private String formatLine(int start, int end, int spaceWidth, int extraSpaces, String[] words) {
        StringBuilder lineBuilder = new StringBuilder();

        for (int wordIndex = start; wordIndex < end; wordIndex++) {
            lineBuilder.append(words[wordIndex]);

            if (wordIndex == end - 1) continue;

            for (int s = 0; s < spaceWidth; s++) {
                lineBuilder.append(' ');
            }

            if (extraSpaces > 0) {
                lineBuilder.append(' ');
                extraSpaces--;
            }
        }

        // Padding for last line if needed
        while (lineBuilder.length() < maxLineWidth) {
            lineBuilder.append(' ');
        }

        return lineBuilder.toString();
    }
}
