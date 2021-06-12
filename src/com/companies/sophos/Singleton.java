package com.companies.sophos;

public class Singleton {
    //static method that will return instance
    //Multitreading 
    //t1 and t2
    static Singleton instance = null;
    
    static Singleton getInstance() {
        if (instance == null) {//
            synchronized(Singleton.class) {
                if (instance == null) {
                    return new Singleton();
                }
            }
        }
        return instance;
    }
    
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
