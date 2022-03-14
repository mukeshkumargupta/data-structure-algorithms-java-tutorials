package com.interview.systemdesign;

/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 *
 * Reference: 1. https://www.devglan.com/java8/hashmap-custom-implementation-java
 * Reference: 2. https://www.youtube.com/watch?v=c3RVW3KGIIE
 * Reference 3: https://dzone.com/articles/custom-hashmap-implementation-in-java
 * Difficulty: Hard, Must Do
 * Company: 
 * Status: Done
 */

public class CustomHashMapDesign {
    
    class Entry<K, V> {

        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }

    
    class CustomHashMap<K, V> {

        private int capacity = 16; //Initial default capacity

        private Entry<K, V>[] table; //Array of Entry object

        public CustomHashMap(){
            table = new Entry[capacity];
        }

        public CustomHashMap(int capacity){
            this.capacity = capacity;
            table = new Entry[capacity];
        }

        public void put(K key, V value){
            int index = index(key);
            Entry newEntry = new Entry(key, value, null);
            if(table[index] == null){
                table[index] = newEntry;
            } else {
                Entry<K, V> previousTreeNode = null;
                Entry<K, V> currentTreeNode = table[index];
                while(currentTreeNode != null){
                    if(currentTreeNode.getKey().equals(key)){
                        currentTreeNode.setValue(value);
                        break;
                    }
                    previousTreeNode = currentTreeNode;
                    currentTreeNode = currentTreeNode.getNext();
                }
                if(previousTreeNode != null)
                    previousTreeNode.setNext(newEntry);
                
            }
        }

        public V get(K key){
            V value = null;
            int index = index(key);
            Entry<K, V> entry = table[index];
            while (entry != null){
                if(entry.getKey().equals(key)) {
                    value = entry.getValue();
                    break;
                }
                entry = entry.getNext();
            }
            return value;
        }

        public void remove(K key){
            int index = index(key);
            Entry previous = null;
            Entry entry = table[index];
            while (entry != null){
                if(entry.getKey().equals(key)){
                    if(previous == null){//Whenever, the previous TreeNode is null means it is the first element of the list.
                        entry = entry.getNext();
                        table[index] = entry;
                        return;
                    } else {
                        previous.setNext(entry.getNext());//assign the next TreeNode of the current TreeNode to the next TreeNode of previous TreeNode and hence the current matched TreeNode will be removed.
                        return;
                    }
                }
                previous = entry;
                entry = entry.getNext();
            }
        }

        public void display(){
            for(int i = 0; i < capacity; i++){
                if(table[i] != null){
                    Entry<K, V> currentTreeNode = table[i];
                    while (currentTreeNode != null){
                        System.out.println(String.format("Key is %s and value is %s", currentTreeNode.getKey(), currentTreeNode.getValue()));
                        currentTreeNode = currentTreeNode.getNext();
                    }
                }
            }
        }

        private int index(K key){
            if(key == null){
                return 0;
            }
            return Math.abs(key.hashCode() % capacity);
        }
    }
    
    public void runCustomHashMapDesign() {
        CustomHashMap<Integer, String> map = new CustomHashMap<Integer, String>();
        System.out.println("Going to add entries in map");
        map.put(null, "Nothing");
        map.put(1, "ETC");
        map.put(2, "John");
        System.out.println("Displaying all the entry in map");
        map.display();
        System.out.println("Removing the entry with key 2");
        map.remove(2);
        map.display();
        System.out.println("Adding duplicate key 1 in map");
        map.put(1, "CSE");
        map.put(2, "John again");
        System.out.println("Displaying all the entry in map again");
        map.display();
        System.out.println("Adding entry with key 17 in map");
        map.put(17, "CS");
        map.display();
    }
    
    public static void main(String[] args) {
        CustomHashMapDesign chm = new CustomHashMapDesign();
        chm.runCustomHashMapDesign();
    }
    
}
