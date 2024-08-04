package com.interview.companies;
import java.util.*;

/*
 * appliedmaterial
 */
public class FindMaxAverage {
    public static List<String> findMaximumAverage(String[] file) {
        int l1 = file.length;
        double maxAvg = -1;
        boolean isFirstAvg = true;
        double firstAvg = -1;
        boolean isFirstAvIncluded = true;
        String name ="";
        List<String> names = new ArrayList<>();
        for (int i = 0; i < l1; ) {

            double avg = (Integer.parseInt(file[i+1]) + Integer.parseInt(file[i+2]) + Integer.parseInt(file[i+3]))/3.0;
            if (isFirstAvg) {
                maxAvg = avg;
                isFirstAvg= false;
                firstAvg = maxAvg;
                name = file[i];
            } else if (avg >= maxAvg) {
               if (avg - maxAvg < .0001 && isFirstAvIncluded) {//equal
                   names.add(name);
                   isFirstAvIncluded = false;
               }
                maxAvg = avg;
                names.add(file[i]);
            }

            i = i+4;
        }
        return names;
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String[] file = {"Ramesh", "90", "70", "40", "Adam", "50", "10", "40", "Suresh", "22", "1", "56", "Rocky", "100", "90", "10"}; 
        findMaximumAverage(file);
    }
    
}
