package com.interview.practice;

import java.util.*;
import java.io.*;

/*
 * Reference: https://www.geeksforgeeks.org/file-handling-java-using-filewriter-filereader/
 */

public class FileOperation  implements Serializable{
    class Employee implements Serializable{
        private String id;
        private String name;
        
        Employee(String id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public String id() {
            return id;
        }
        
        public String name() {
            return name;
        }
        
    }

    
    public void writeFile(String fileName) throws IOException {
        //File f = new File(fileName);
        FileOutputStream fo = new FileOutputStream(fileName);
        ObjectOutputStream o = new ObjectOutputStream(fo);
        Employee e = new Employee("123", "Anjani");
        o.writeObject(e);
        o.close();
    }
    
    public void readFile(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream(fileName);
        ObjectInputStream i = new ObjectInputStream(fi);
        Employee e = (Employee) i.readObject();
        System.out.println("id: " + e.id());
        System.out.println("name: " + e.name());
        i.close();
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FileOperation fo = new FileOperation();
        try {
            fo.writeFile("abc1.txt");
            fo.readFile("abc1.txt");
            //Read name and id from file
            File f = new File("abc1.txt");
            

            
        } catch (Exception e){
            System.out.println(e);
        }
        
        
    }
    
}
