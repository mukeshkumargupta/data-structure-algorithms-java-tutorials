package com.interview.systemdesign.lowleveldesign;

import java.util.ArrayList;

/**
 * Date: 05/08/2020
 * Author: Mukesh Kumar Gupta
 * Reference: https://leetcode.com/problems/design-in-memory-file-system/
 * All Google question: https://www.youtube.com/watch?v=SJmYTJEVa_U
 * Reference: https://www.geeksforgeeks.org/design-val-structures-algorithms-memory-file-system/
 *
 * Category: Recursion, Design, Hard
 * Companies: Google, Amazon, Facebook
 * Status: Done
 *
 * Problem Statement
 * Design an in-memory file system that mimics the behavior of a file system. The system should support the following features:
 *
 * Directories: Support nested directories.
 * Files: Store files with their names and contents.
 * Operations:
 * Add files and directories.
 * Delete files and directories.
 * Calculate the total size of all files in a directory.
 * Count the total number of files and directories within a given directory.
 * Application
 * This in-memory file system design can be useful for applications that require:
 *
 * Simulated File Management: Ideal for teaching or testing file system concepts without disk I/O.
 * Data Organization: Useful in applications that need to structure data in a hierarchical manner.
 * Development of IDEs: Integrated Development Environments can benefit from such designs when managing projects and files.
 * Derived Problems
 * File System Permissions: Extend the design to include user permissions for files and directories.
 * File Search: Implement functionality to search for files by name.
 * Undo/Redo Operations: Allow users to undo or redo changes to the file system.
 */

public class PartH_2_InMemoryFileSystemDesign {

    // Entry is a superclass for both File and Directory 
    abstract class Entry {
        private Directory parent;
        private long created;
        private long lastUpdated;
        private String name;

        public Entry(String name, Directory parent) {
            this.name = name;
            this.parent = parent;
            created = System.currentTimeMillis();
            lastUpdated = System.currentTimeMillis();
        }

        public boolean delete() {
            if (parent == null) return false;
            return parent.deleteEntry(this);
        }

        public abstract int size();

        /* Getters and setters */
        public long getCreationTime() {
            return created;
        }

        public long getLastUpdatedTime() {
            return lastUpdated;
        }

        public String getName() {
            return name;
        }

        public void changeName(String name) {
            this.name = name;
        }
    }

    // A class to represent a File (Inherits from Entry) 
    public class File extends Entry {
        private String content;
        private int size;

        public File(String name, Directory parent, String content) {
            super(name, parent);
            this.content = content;
            this.size = content.length(); // Size based on content length
        }

        public int size() {
            return size;
        }

        public String getContents() {
            return content;
        }

        public void setContents(String content) {
            this.content = content;
            this.size = content.length(); // Update size based on new content
        }
    }

    // A class to represent a Directory (Inherits from Entry) 
    class Directory extends Entry {
        protected ArrayList<Entry> contents;

        public Directory(String name, Directory parent) {
            super(name, parent);
            contents = new ArrayList<>();
        }

        public int size() {
            int size = 0;
            for (Entry entry : contents) {
                size += entry.size();
            }
            return size;
        }

        public int numberOfFiles() {
            int count = 0;
            for (Entry entry : contents) {
                count++; // Count every entry (File or Directory)
                if (entry instanceof Directory) {
                    Directory directory = (Directory) entry;
                    count += directory.numberOfFiles(); // Count files in subdirectory
                }
            }
            return count;
        }

        public boolean deleteEntry(Entry entry) {
            return contents.remove(entry);
        }

        public void addEntry(Entry entry) {
            contents.add(entry);
        }

        protected ArrayList<Entry> getContents() {
            return contents;
        }
    }

    void runInMemoryFileSystemDesign() {
        // Create root directory
        Directory root = new Directory("root", null);

        // Create two directories
        Directory dir1 = new Directory("dir1", root);
        Directory dir2 = new Directory("dir2", root);

        // Create files and add them to dir1
        File file1 = new File("file1.txt", dir1, "Content of file 1");
        dir1.addEntry(file1);
        File file2 = new File("file2.txt", dir1, "Content of file 2");
        dir1.addEntry(file2);

        // Create files and add them to dir2
        File file3 = new File("file3.txt", dir2, "Content of file 3");
        dir2.addEntry(file3);

        // Add directories to root
        root.addEntry(dir1);
        root.addEntry(dir2);

        // Calculate number of files and size in root
        System.out.println("Total file and directory inside root: " + root.numberOfFiles());
        System.out.println("Total size inside root: " + root.size());
    }

    public static void main(String[] args) {
        PartH_2_InMemoryFileSystemDesign imfs = new PartH_2_InMemoryFileSystemDesign();
        imfs.runInMemoryFileSystemDesign();
    }
}