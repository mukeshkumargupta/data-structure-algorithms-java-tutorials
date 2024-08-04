package com.interview.practice;

import java.io.*;

/*
 * https://www.javatpoint.com/how-to-read-file-line-by-line-in-java
 * https://www.geeksforgeeks.org/file-handling-java-using-filewriter-filereader/  by char wise
 */
public class FileReaderWriter {
    public void writeFile(String fileName) throws IOException{
        File f = new File(fileName);
        System.out.println(f.getAbsolutePath());
        FileWriter fw = new FileWriter(f);
        BufferedWriter bf = new BufferedWriter(fw);
        bf.write("my name is khan\n");
        bf.write("my name is ajamal\n");
        bf.write("my name is kumar\n");
        bf.write("my name is singh\n");
        bf.close();
        
        
    }
    
    public void readFileByLine(String fileName) throws IOException{
        File f = new File(fileName);
        FileReader fw = new FileReader(f);
        BufferedReader bf = new BufferedReader(fw);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bf.readLine()) != null) {
            sb.append(line + "\n");
        }
        System.out.println(sb.toString());
        bf.close();
        System.out.println("Stream closed");
        
        /*StringBuilder sb1 = new StringBuilder();
        char ch;
        while ((ch = (char) bf.read()) != -1) {
            sb1.append(ch);
        }
        System.out.println(sb1.toString());*/

        
    }
    
    public void readFileByChar(String fileName) throws IOException{
        File f = new File(fileName);
        FileReader fr = new FileReader(f);
        BufferedReader bf = new BufferedReader(fr);;
       
        
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch =  bf.read()) != -1) {//It read int value
            sb.append((char)ch);
        }
        System.out.println(sb.toString());
        System.out.println("Stream closed for readFileByChar ");

        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FileReaderWriter frw = new FileReaderWriter();
        try {
            frw.writeFile("filereaderwriter.txt");
            frw.readFileByLine("filereaderwriter.txt");
            frw.readFileByChar("filereaderwriter.txt");
        } catch (IOException io) {
            System.out.println(io);
            
        }
        
        
    }
    
}
