package com.interview.systemdesign;

import java.util.*;
/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/design-in-memory-file-system/
 * All google question: https://www.youtube.com/watch?v=SJmYTJEVa_U
 * Reference: https://www.geeksforgeeks.org/design-val-structures-algorithms-memory-file-system/ 
 * Category: Recursion, Design, Hard
 * Company: Google, Amazon, Facebook
 * Status: Done
 */

public class InMemoryFileSystemDesign {
    
    // Entry is superclass for both File and Directory 
    abstract class Entry 
    { 
        private Directory parent; 
        private long created; 
        private long lastUpdated; 
        private long lastAccessed; 
        private String name; 
      
        public Entry(String n, Directory p) 
        { 
            name = n; 
            parent = p; 
            created= System.currentTimeMillis(); 
            lastUpdated = System.currentTimeMillis(); 
            lastAccessed = System.currentTimeMillis(); 
        } 
      
        public boolean delete() 
        { 
            if (parent == null) 
                return false; 
            return parent.deleteEntry(this); 
        } 
      
        public abstract int size(); 
      
        /* Getters and setters. */
        public long getcreationTime() 
        { 
            return created; 
        } 
        public long getLastUpdatedTime() 
        { 
            return lastUpdated; 
        } 
        public long getLastAccessedTime() 
        { 
            return lastAccessed; 
        } 
        public void changeName(String n) 
        { 
            name = n; 
        } 
        public String getName() 
        { 
            return name; 
        } 
    } 
      
    // A class to represent a File (Inherits 
    // from Entry) 
    public class File extends Entry 
    { 
        private String content; 
        private int size; 
      
        public File(String n, Directory p, int sz) 
        { 
            super(n, p); 
            size = sz; 
        } 
        public int size() 
        { 
            return size; 
        } 
        public String getContents() 
        { 
            return content; 
        } 
        public void setContents(String c) 
        { 
            content = c; 
        } 
    } 
      
    // A class to represent a Directory (Inherits 
    // from Entry) 
    class Directory extends Entry 
    { 
        protected ArrayList<Entry> contents; 
      
        public Directory(String n, Directory p) 
        { 
            super(n, p); 
            contents = new ArrayList<Entry>(); 
        } 
        public int size() 
        { 
            int size = 0; 
            for (Entry e : contents) 
                size += e.size(); 
              
            return size; 
        } 
        public int numberOfFiles() 
        { 
            int count = 0; 
            for (Entry e : contents) 
            { 
                if (e instanceof Directory) 
                { 
                    count++; // Directory counts as a file 
                    Directory d = (Directory) e; 
                    count += d.numberOfFiles (); 
                } 
                else if (e instanceof File)             
                    count++;             
            } 
            return count; 
        } 
      
        public boolean deleteEntry(Entry entry) 
        { 
            return contents.remove(entry); 
        } 
      
        public void addEntry(Entry entry) 
        { 
            contents.add(entry); 
        } 
      
        protected ArrayList<Entry> getContents() 
        { 
            return contents; 
        } 
    } 
    
    void runInMemoryFileSystemDesign() {
        //Create Directory
        Directory d1 = new Directory("d1", null);
        //Create three file and one directory
        Directory d2 = new Directory("d1", d1);
        File f1 = new File("f1", d1, 1);
        d1.addEntry(f1);
        File f2 = new File("f2", d1, 2);
        d1.addEntry(f2);
        File f3 = new File("f3", d1, 3);
        d1.addEntry(f3);
        //Add directory entry
        d1.addEntry(d2);
        //Put two file inside d2
        File f4 = new File("f4", d2, 4);
        File f5 = new File("f5", d2, 5);
        d2.addEntry(f4);
        d2.addEntry(f5);
        //Calculate number of files
        System.out.println("Total file and directory inside d1: " + d1.numberOfFiles());
        System.out.println("Total file and directory inside d2: " + d2.numberOfFiles());
        
        //Calculate size of files
        System.out.println("Total size inside d1: " + d1.size());
        System.out.println("Total size inside d2: " + d2.size());
        
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        InMemoryFileSystemDesign imfs = new InMemoryFileSystemDesign();
        imfs.runInMemoryFileSystemDesign();
        
    }
    
}
