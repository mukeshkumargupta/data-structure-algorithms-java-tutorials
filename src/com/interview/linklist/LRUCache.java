package com.interview.linklist;

import java.util.Deque; 
import java.util.HashSet; 
import java.util.LinkedList; 
import java.util.Iterator; 

/**
 * http://www.geeksforgeeks.org/implement-lru-cache/
 * Test cases:
 * MAX_SIZE greater than 1
 * Delete when empty
 * Delete when full
 * Enter data more than max
 * Delete till cache is empty
 */
/* We can use Java inbuilt Deque as a double  
ended queue to store the cache keys, with  
the descending time of reference from front  
to back and a set container to check presence  
of a key. But remove a key from the Deque using 
remove() , it takes O(N) time. This can be  
optimized by storing a reference (iterator) to  
each key in a hash map. 
Category: Must Know
*/

public class LRUCache { 
 // store keys of cache 
 static Deque<Integer> dq;   
 // store references of key in cache  
 static HashSet<Integer> map; 
 //maximum capacity of cache  
 static int csize; 
   
 LRUCache(int n) 
 { 
     dq=new LinkedList<>(); 
     map=new HashSet<>(); 
     csize=n; 
 } 
   
 /* Refers key x with in the LRU cache */
 public void refer(int x) 
 { 
	 //Note: When you add element, then it can be found or not found, if not found then check
	 //whether list is full if full then remove from last so you can add new element from front.
	 //if element found then find on which index that element is there then remove from that place 
	 // because found element should be on top. 
     if(!map.contains(x)) 
     { 
         if(dq.size()==csize) 
         { 
             int last=dq.removeLast(); 
             map.remove(last); 
         } 
     } 
     else
     { 
         /* The found page may not be always the last element, even if it's an  
            intermediate element that needs to be removed and added to the start  
            of the Queue */
         int index =0 , i=0; 
         Iterator<Integer> itr = dq.iterator();  
         while(itr.hasNext())  
         {  
             if(itr.next()==x) 
             { 
                 index = i; 
                 break; 
             } 
             i++; 
         }  
         dq.remove(index); 
     } 
     dq.push(x); 
     map.add(x); 
 } 
   
 // display contents of cache  
 public void display() 
 { 
     Iterator<Integer> itr = dq.iterator(); 
     while(itr.hasNext()) 
     { 
         System.out.print(itr.next()+" "); 
     } 
 } 
   
   
 public static void main(String[] args) { 
     LRUCache ca=new LRUCache(4); 
     ca.refer(1);  
     ca.refer(2);  
     ca.refer(3);  
     ca.refer(1);  
     ca.refer(4);  
     ca.refer(5);  
     ca.display();      
 } 
} 