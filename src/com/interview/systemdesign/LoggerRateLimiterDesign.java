package com.interview.systemdesign;

import java.util.*;

/*
 * Reference: https://www.youtube.com/watch?v=a5vcIOaKuuY
 * Status: Done
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
        Logger logger = new Logger();
        System.out.println("Message should be printed of timstamp 1" + logger.shouldPrintMessage(1, "Logger1"));
        System.out.println("Message should be printed of timstamp 2" + logger.shouldPrintMessage(2, "Logger2"));
        System.out.println("Message should be printed of timstamp 3" + logger.shouldPrintMessage(3, "Logger3"));
        System.out.println("Message should be printed of timstamp 10" + logger.shouldPrintMessage(10, "Logger10"));
        System.out.println("Message should be printed of timstamp 11" + logger.shouldPrintMessage(11, "Logger11"));
        System.out.println("Message should be printed of timstamp 20" + logger.shouldPrintMessage(20, "Logger11"));
        System.out.println("Message should be printed of timstamp 21" + logger.shouldPrintMessage(21, "Logger11"));
        System.out.println("Message should be printed of timstamp 22" + logger.shouldPrintMessage(22, "Logger11"));
        System.out.println("Message should be printed of timstamp 23" + logger.shouldPrintMessage(23, "Logger11"));
        System.out.println("Message should be printed of timstamp 24" + logger.shouldPrintMessage(24, "Logger11"));
        System.out.println("Message should be printed of timstamp 25" + logger.shouldPrintMessage(25, "Logger11"));
        System.out.println("Message should be printed of timstamp 26" + logger.shouldPrintMessage(26, "Logger11"));
        System.out.println("Message should be printed of timstamp 27" + logger.shouldPrintMessage(27, "Logger11"));
        System.out.println("Message should be printed of timstamp 28" + logger.shouldPrintMessage(28, "Logger11"));
        System.out.println("Message should be printed of timstamp 29" + logger.shouldPrintMessage(29, "Logger11"));
        System.out.println("Message should be printed of timstamp 30" + logger.shouldPrintMessage(30, "Logger11"));
        System.out.println("Message should be printed of timstamp 31" + logger.shouldPrintMessage(31, "Logger11"));
        System.out.println("Message should be printed of timstamp 32" + logger.shouldPrintMessage(32, "Logger11"));
    }
    
    public static void main(String[] args) {
        LoggerRateLimiterDesign lrt = new LoggerRateLimiterDesign();
        lrt.runLoggerRateLimiterDesign();
        
    }
    
}
