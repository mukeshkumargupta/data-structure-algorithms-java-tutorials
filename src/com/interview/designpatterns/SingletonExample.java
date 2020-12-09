package com.interview.designpatterns;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//Reference: https://www.youtube.com/watch?v=VGLjQuEQgkI&list=PLIA-9QRQ0RqG0KnmB8MHTaRBkT0zt8byZ&index=2&t=242s
//It is also good example: https://www.geeksforgeeks.org/prevent-singleton-pattern-reflection-serialization-cloning/?ref=lbp
//Note: All cases are not handled, need to take care


class SingletonEagar {
    private static SingletonEagar instance = new SingletonEagar();
    
    private SingletonEagar() {
    }
    
    public static SingletonEagar getInstance() {
        return instance;
    }
}

class Singleton {
    private static Singleton instance;
    
    private Singleton() {
    }
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        
        return instance;
    }
}

class SingletonSynchronizedMethod {
    private static SingletonSynchronizedMethod instance;
    
    private SingletonSynchronizedMethod() {
    }
    
    public static synchronized SingletonSynchronizedMethod getInstance() {
        if (instance == null) {
            instance = new SingletonSynchronizedMethod();
        }
        return instance;
    }
}

class SingletonSynchronized {
    private static SingletonSynchronized instance;
    
    private SingletonSynchronized() {
    }
    
    public static SingletonSynchronized getInstance() {
        if (instance == null) {
            synchronized (SingletonSynchronized.class) {
                if (instance == null) {
                    instance = new SingletonSynchronized();
                }
            }
        }
        return instance;
    }
}

public class SingletonExample {
    
    public static void main(String[] args) {
        SingletonSynchronized instance = SingletonSynchronized.getInstance();
        
        System.out.println(instance);
        
        SingletonSynchronized instance1 = SingletonSynchronized.getInstance();
        
        System.out.println(instance1);
        
        //Reflection use case break scenario
        Constructor[] constructors =  SingletonSynchronized.class.getDeclaredConstructors(); 
        SingletonSynchronized instance2 = null; 
        for (Constructor constructor  : constructors)  
        { 
            // Below code will destroy the singleton pattern 
            constructor.setAccessible(true); 
            try {
                instance2 = (SingletonSynchronized) constructor.newInstance();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
            break; 
        } 
        System.out.println("instance1.hashCode():- " 
                + instance1.hashCode()); 
        System.out.println("instance2.hashCode():- " 
                + instance2.hashCode()); 
    }
}
