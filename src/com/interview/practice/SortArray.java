package com.interview.practice;

import java.util.*;
import java.util.Arrays;

public class SortArray {
    public void sort(Integer[] input) {
        Comparator<Integer> c = (a, b) ->  {
            return b -a;
        };
        
        Arrays.sort(input, (a, b) ->  b -a);
        List<Integer> l = new ArrayList<>();
        l.add(5);
        l.add(-3);
        l.add(8);
        Collections.sort(l, c);
        for (int v : l) {
            System.out.println(v);
        }
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Integer[] input = {1,5,-8,6,9,5,2};

        SortArray s = new SortArray();
        s.sort(input);
        for (int val : input) {
            System.out.print(val);
        }
        
    }
    
}

