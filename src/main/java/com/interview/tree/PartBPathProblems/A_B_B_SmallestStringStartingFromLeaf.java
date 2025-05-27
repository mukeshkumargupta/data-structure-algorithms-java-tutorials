package com.interview.tree.PartBPathProblems;

public class A_B_B_SmallestStringStartingFromLeaf {
    //Related: https://leetcode.com/problems/smallest-string-starting-from-leaf/submissions/
    //Related: https://leetcode.com/problems/reverse-only-letters/ Easy

    //https://leetcode.com/problems/kth-ancestor-of-a-tree-node/ Hard
    //https://leetcode.com/problems/number-of-ways-to-reconstruct-a-tree/ Hard
    String smallestString = "";//If you want to pass as argumement then you need to take string builder to keep as memory

    void smallestFromLeafUtilMethod1(A_A_PathSumII.TreeNode root, String currentString) {//faster than 100% of Java online submissions for Smallest String Starting From Leaf.
        if (root == null) {
            return;
        }

        currentString = "" + (char) ('a' + root.val) + currentString;

        if (root.left == null && root.right == null) {
            if (smallestString.isEmpty() || currentString.compareTo(smallestString) < 0) {
                smallestString = currentString;
            }
            return;
        }
        smallestFromLeafUtilMethod1(root.left, currentString);
        smallestFromLeafUtilMethod1(root.right, currentString);
    }

    public String smallestFromLeafMethod1(A_A_PathSumII.TreeNode root) {
        //runtime 12.13 %, since sting manupulation is used so solow, so use String.valueOf, that is method 2.
        smallestFromLeafUtilMethod1(root, "");
        return smallestString;

    }

    public String smallestFromLeafMethod2(A_A_PathSumII.TreeNode root) {
        /*
         * Runtime: 1 ms, faster than 99.72% of Java online submissions for Smallest String Starting From Leaf.
Memory Usage: 38.9 MB, less than 62.20% of Java online submissions for Smallest String Starting From Leaf.
Note: Verfast because String.valueOf is used
         */
        getSmallestLeafMethod2(root, "");
        return smallestString;
    }

    public void getSmallestLeafMethod2(A_A_PathSumII.TreeNode root, String currentString) {

        if (root == null) return;
        currentString = getChar(root.val) + currentString;

        if (root.left == null && root.right == null) {
            if (smallestString.isEmpty() || currentString.compareTo(smallestString) < 0) {
                smallestString = currentString;
            }
            return;
        }

        getSmallestLeafMethod2(root.left, currentString);
        getSmallestLeafMethod2(root.right, currentString);

    }

    public String getChar(int alphabetVal) {
        return String.valueOf((char) (alphabetVal + 'a'));
    }

    public String smallestFromLeafMethod3(A_A_PathSumII.TreeNode root) {
        /*
         * Runtime: 4 ms, faster than 53.74% of Java online submissions for Smallest String Starting From Leaf.
Memory Usage: 41.7 MB, less than 13.68% of Java online submissions for Smallest String Starting From Leaf.
         */

        StringBuilder ds = new StringBuilder();
        getSmallestLeafMethod3(root, ds);
        return smallestString;
    }

    public void getSmallestLeafMethod3(A_A_PathSumII.TreeNode root, StringBuilder ds) {

        if (root == null) return;


        if (root.left == null && root.right == null) {
            ds.append((char) (root.val + 'a'));
            StringBuilder sb = new StringBuilder(ds.toString());
            String revString = sb.reverse().toString();
            if (smallestString.isEmpty() || revString.compareTo(smallestString) < 0) {
                smallestString = revString;
            }
            //System.out.println("leaf-> " + ds.toString());
            ds.deleteCharAt(ds.length() - 1);
            return;
        }
        ds.append((char) (root.val + 'a'));
        getSmallestLeafMethod3(root.left, ds);
        getSmallestLeafMethod3(root.right, ds);
        //System.out.println("End-> " + ds.toString());
        ds.deleteCharAt(ds.length() - 1);


    }
}
