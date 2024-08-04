package com.interview.systemdesign;

import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 

/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 *
 * Reference 1: https://www.youtube.com/watch?v=fMZMm_0ZhK4
 * Reference: https://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/?ref=lbp
 * Reference for md5 : https://www.geeksforgeeks.org/md5-hash-in-java/
 * Difficulty: Hard
 * Company: Google, Amazon, Facebook
 * Status: Not Done
 */



public class TinyURLDesign {
    
    public static String getMd5(String input) 
    { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
      
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16);
            //Convert message digest into binary value value 
            String binarytext = no.toString(2);

           return binarytext;
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
    
    //Since n is big number so u cant take  modulo
    //https://www.geeksforgeeks.org/how-to-compute-mod-of-a-big-number/
    static int mod(String stringNumber, int modulo) {
        int result = 0;
        for(int i = 0; i < stringNumber.length(); i++) {
            result = (result*10 + stringNumber.charAt(i) - '0') % modulo;
        }
        return result;
        
    }
    
    
 // Function to generate a short url from integer ID  
    static String idToShortURL(String longURL)  
    {  
        String binarytext = getMd5(longURL);
        //Take only 43 bit
        //Reference to extract string from last: 
       // https://www.journaldev.com/807/java-string-substring
       String binarytext43 = binarytext.substring(binarytext.length()- 43);
       //Convert to decimal
       //Integer n = Integer.parseInt(binarytext43, 2);
       //will not work because integer is 32 bit
       //Integer n = Integer.parseInt(binarytext43, 2);
       //Alternate way
       long longnumber = 0;
       for(int i = 0; i < binarytext43.length(); i++) {
           longnumber = longnumber*2 + binarytext43.charAt(i) - '0';
       }

        // Map to store 62 possible characters  
        char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
      
        StringBuffer shorturl = new StringBuffer();  
      
        // Convert given integer id to a base 62 number  
        while (longnumber > 0)  
        {  
            // use above map to store actual character  
            // in short url  
            String stringNumber = "" + longnumber;
            shorturl.append(map[mod(stringNumber, 62)]); 
            longnumber = longnumber / 62;  
        }  
      
        // Reverse shortURL to complete base conversion  
        return shorturl.reverse().toString();  
    }  
      
    //Like how we to for base 10 for example 23, 2*10 +3, so here 62 is multiplcation factor
    // Function to get integer ID back from a short url  
    static int shortURLtoID(String shortURL)  
    {  
        int id = 0; // initialize result  
      
        // A simple base conversion logic  
        for (int i = 0; i < shortURL.length(); i++)  
        {  
            if ('a' <= shortURL.charAt(i) &&  
                       shortURL.charAt(i) <= 'z')  
            id = id * 62 + shortURL.charAt(i) - 'a';  
            if ('A' <= shortURL.charAt(i) &&  
                       shortURL.charAt(i) <= 'Z')  
            id = id * 62 + shortURL.charAt(i) - 'A' + 26;  
            if ('0' <= shortURL.charAt(i) &&  
                       shortURL.charAt(i) <= '9')  
            id = id * 62 + shortURL.charAt(i) - '0' + 52;  
        }  
        return id;  
    } 
    
    public static void main(String[] args) {
        
        String tinyUrl = TinyURLDesign.idToShortURL("https://www.geeksforgeeks.org/md5-hash-in-java/");
        System.out.println(tinyUrl);
        long number = TinyURLDesign.shortURLtoID(tinyUrl);
        System.out.println(number);
    }
    
}
