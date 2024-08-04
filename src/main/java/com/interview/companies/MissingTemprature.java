package com.interview.companies;

import java.io.*;
import java.util.*;
/*
 * Reference:
 * https://www.hackerrank.com/rest/contests/master/challenges/temperature-predictions/hackers/rojohn11/download_solution
 * Company: Trilio
 */

    public class MissingTemprature {
        enum MONTH {
            January,
            February,
            March,
            April,
            May,
            June,
            July,
            August,
            September,
            October,
            November,
            December
        };
        static double[] tmaxMonth = new double[] {
                5,
                7.3,
                6.2,
                8.6,
                15.8,
                17.7,
                18.9,
                17.5,
                16.3,
                14.6,
                9.6,
                5.8
        };
        static double[] tminMonth = new double[] {
                -1.4,
                1.9,
                0.3,
                2.1,
                7.7,
                8.7,
                11,
                9.7,
                8.4,
                8,
                3.4,
                0
                };
        static double[] diffMonth = new double[] {
                5.035108235917819, 5.217174084963009, 6.380932259139406, 7.246668074720901, 8.502539892849407, 8.64472263261963, 7.864867133164329, 7.311982146410974, 6.997802529963802, 
                6.081123983156005, 5.105180789814467, 4.776535250806581
        };

        public static void main(String[] args) {
            /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            Scanner sc= new Scanner(System.in);
            int rows = sc.nextInt();
            sc.nextLine();
            sc.nextLine();
            final double wt1 = 0.9;
            final double wt2 = 1 - wt1;
            for (int row = 0; row < rows; row++) {
                String rowChars = sc.nextLine().trim();
                String[] val = rowChars.split("\\s+");
                MONTH mon =  MONTH.valueOf(val[1]);
                int maxCheck = val[2].indexOf("Missing_");
                int minCheck = val[3].indexOf("Missing_");
                if(maxCheck==0 && minCheck == 0){
                    System.out.println(tmaxMonth[mon.ordinal()]);
                    System.out.println(tminMonth[mon.ordinal()]);
                } else if (maxCheck==0) {
                    System.out.println(Double.parseDouble(val[3])+diffMonth[mon.ordinal()]);
                } else if (minCheck==0) {
                    System.out.println(Double.parseDouble(val[2])-diffMonth[mon.ordinal()]);
                } else {
                    double max =  Double.parseDouble(val[2]);
                    double min = Double.parseDouble(val[3]);
                    double diff =max-min;
                    diffMonth[mon.ordinal()] = wt1*diffMonth[mon.ordinal()] + wt2*diff;
                }
                
            }
        }
    }


