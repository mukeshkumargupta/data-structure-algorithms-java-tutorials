package com.kids.basic;
import java.util.*;
/**
 * @author Mukesh Kumar Gupta
 *
 */
public class StackClass {
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Stack<Integer>st=new Stack<>();
             st.push(10);
             st.push(5);
             st.push(4);
             st.push(6);
           while(!st.isEmpty()) {
               System.out.println(st.pop());
           }
    }
    
}
