package com.interview.systemdesign;

import java.util.*;

/*
 * Reference: https://www.youtube.com/watch?v=a5vcIOaKuuY
 * Status: Done
 * 
 * 
 */
public class LoggerRateLimiterDesign {
    class Logger {
        Map<String, Integer> hashmap;
        
        Logger() {
            hashmap = new HashMap<String, Integer>();
        }
        
        public boolean shouldPrintMessage(int timeStamp, String messsage) {
            if (!hashmap.containsKey(messsage)) {
                hashmap.put(messsage, timeStamp);
                return true;
            }
            
            int previousTimeStamp = hashmap.get(messsage);
            if (timeStamp - previousTimeStamp >= 10) {
                hashmap.put(messsage, timeStamp);
                return true;
            }
            
            return false;
        }
    }
    
    void runLoggerRateLimiterDesign() {
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LoggerRateLimiterDesign lrt = new LoggerRateLimiterDesign();
        lrt.runLoggerRateLimiterDesign();
        
    }
    
}
