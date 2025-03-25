package com.interview.stackqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Category: Medium, Facebook, FAANG
You are given an absolute path for a Unix-style file system, which always begins with a slash '/'. Your task is to transform this absolute path into its simplified canonical path.

The rules of a Unix-style file system are as follows:

A single period '.' represents the current directory.
A double period '..' represents the previous/parent directory.
Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
Any sequence of periods that does not match the rules above should be treated as a valid directory or file name. For example, '...' and '....' are valid directory or file names.
The simplified canonical path should follow these rules:

The path must start with a single slash '/'.
Directories within the path must be separated by exactly one slash '/'.
The path must not end with a slash '/', unless it is the root directory.
The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
Return the simplified canonical path.



Example 1:

Input: path = "/home/"

Output: "/home"

Explanation:

The trailing slash should be removed.

Example 2:

Input: path = "/home//foo/"

Output: "/home/foo"

Explanation:

Multiple consecutive slashes are replaced by a single one.

Example 3:

Input: path = "/home/user/Documents/../Pictures"

Output: "/home/user/Pictures"

Explanation:

A double period ".." refers to the directory up a level (the parent directory).

Example 4:

Input: path = "/../"

Output: "/"

Explanation:

Going one level up from the root directory is not possible.

Example 5:

Input: path = "/.../a/../b/c/../d/./"

Output: "/.../b/d"

Explanation:

"..." is a valid name for a directory in this problem.



Constraints:

1 <= path.length <= 3000
path consists of English letters, digits, period '.', slash '/' or '_'.
path is a valid absolute Unix path.
 */
public class PartC_D_SimplifyPath {
    /*
    1️⃣ Brute Force Approach (String Manipulation)
    - Split the path by "/".
    - Process each component manually using a list.
    - Handle ".." by removing the last valid directory.
    - Handle "." and empty components by skipping them.
    - Reconstruct the result.

    🔹 Time Complexity: O(N)
    🔹 Space Complexity: O(N)
    */
    private static class BruitForce {
    public static String simplifyPath(String path) {
        String[] parts = path.split("/");
        List<String> list = new ArrayList<>();

        for (String dir : parts) {
            if (dir.equals("..")) {
                if (!list.isEmpty()) {
                    list.remove(list.size() - 1); // Go back one directory
                }
            } else if (!dir.isEmpty() && !dir.equals(".")) {
                list.add(dir); // Add valid directory
            }
        }
        return "/" + String.join("/", list);
    }

    public static void main(String[] args) {
        String path = "/home/../usr//bin/";
        System.out.println(simplifyPath(path)); // Output: "/usr/bin"
    }
    }

    /*
    2️⃣ Optimized Approach (Using Stack)
    - Use a stack to store valid directories.
    - If ".." is found, pop from the stack.
    - If "." or empty, ignore.
    - Construct the result from the stack.

    🔹 Time Complexity: O(N)
    🔹 Space Complexity: O(N)

    📌 Comparison of Approaches:
    ┌───────────────────────────────┬────────────────┬────────────────┬───────────────────────────────┐
    │ Approach                      │ Time Complexity │ Space Complexity │ Notes                         │
    ├───────────────────────────────┼────────────────┼────────────────┼───────────────────────────────┤
    │ Brute Force (String Manipulation) │ O(N)          │ O(N)          │ Uses List for processing      │
    │ Optimized (Stack)              │ O(N)          │ O(N)          │ More readable and efficient  │
    └───────────────────────────────┴────────────────┴────────────────┴───────────────────────────────┘

    ✅ Recommendation: Use the optimized stack-based approach for better readability and efficiency! 🚀
    */
    private static class Optimized {
        public static String simplifyPath(String path) {
            Stack<String> stack = new Stack<>();
            String[] parts = path.split("/");

            for (String dir : parts) {
                if (dir.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop(); // Go back one directory
                    }
                } else if (!dir.isEmpty() && !dir.equals(".")) {
                    stack.push(dir); // Add valid directory
                }
            }
            return "/" + String.join("/", stack);
        }

        public static void main(String[] args) {
            String path = "/home/../usr//bin/";
            System.out.println(simplifyPath(path)); // Output: "/usr/bin"
        }
    }
}
