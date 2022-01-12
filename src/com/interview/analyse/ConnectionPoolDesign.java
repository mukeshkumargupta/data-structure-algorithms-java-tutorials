package com.interview.analyse;

import java.util.*;

public class ConnectionPoolDesign {
    
    static class ObjectsPool {
        // Type, instanceName, Instance
        Map<String, Map<String, Object>> lookup;
        static ObjectsPool instance = null; 
        ObjectsPool getInstance() {
            if (instance == null) {
                return new ObjectsPool();
            }
            
        }
        ObjectsPool() {
            lookup = new HashMap<>();
        }
        
        Map<String, Map<String, Object>> getLookup() {
            return lookup;
        }
        
    }
    class ObjectPool {
        public ObjectPool get() {
            for (String key : lookup.keySet()) {
                // match type and instance name and then remove count from looup
                Map<String, Object> objectsPool = lookup.get(key);
                for (String childkey : objectsPool.keySet()) {
                    if (type.equals(childkey)) {
                        
                    }
                    
                }
                
            }
            return null;
        }
        
        public boolean release() {
            try {
                // match type and instance name and then add count into lookup
                for (String key : lookup.keySet()) {
                    // match type and instance name and then remove count from looup
                }
                
            } catch (Exception e) {
                
            }
            return false;
            
        }
        
        ObjectPool(String type, int number) {
            ObjectsPool ObjectsPoolInstance = ObjectsPool.getInstance();
            Map<String, Map<String, Object>> lookup = ObjectsPoolInstance.getLookup();
            this.type = type;
            this.instanceName = type + number;
            //Factory class to create instance of type and total count it will return map
            Map<String, Object> objects = //create from fatcory witll return map;
            
            lookup.putIfObsent(type, new HashMap<this.instanceName , objects>());
            Map<String, ObjectPool> objectPool = lookup.get(type);

            lookup.get(type).put(type, objects);
        }
        
        String type;
        String instanceName;
        
    }
    
    class instaceFactory {
        
        createInstance(String type) {
            switch() {
            
            case:
            }
            
            
        }
    }
    
    class Student extends ObjectPool {
        
        Student(String type) {
            super(type);
        }
        
        Object get() {
            return null;
        }
        
        release () {
            
        }
        
    }
    
    List<ObjectPool> createObjectType(String type) {
        
        return null;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
