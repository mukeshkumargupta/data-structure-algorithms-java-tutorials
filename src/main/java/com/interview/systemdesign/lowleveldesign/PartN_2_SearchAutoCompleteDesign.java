package com.interview.systemdesign.lowleveldesign;

import java.util.*;
/*
 * Reference: https://www.youtube.com/watch?v=oNUdxpcGAig
 */
public class PartN_2_SearchAutoCompleteDesign {
    
    class WordFreq{
        String sentence;
        int frequency;
        public WordFreq(String s, int f) {
            this.sentence = s;
            this.frequency = f;
        }
    }
    class AutocompleteSystem {
        HashMap<String, Integer>[] array; 
        String currentString = "";
        public AutocompleteSystem(String[] sentences, int[] times) {
            for (int index = 0; index < 26; index++) {
                array[index] = new HashMap<String, Integer>();
            }
            
            for (int index = 0; index < sentences.length; index++) {
                array[sentences[index].charAt(0) - 'a'].put(sentences[index], times[index]);
            }
        }
        
        public List<String> input(char c) {
            List<String> result = new ArrayList<>();
            if (c == '#') {
                array[currentString.charAt(0) -'a'].put(currentString, array[currentString.charAt(0) -'a'].getOrDefault(currentString, 0)+1);
                currentString = "";
            } else {
                currentString += c;
                List<WordFreq> list = new ArrayList<>();
                for (String key: array[currentString.charAt(0) - 'a'].keySet()) {
                    if( key.indexOf(currentString) == 0) {
                        list.add(new WordFreq(key, array[currentString.charAt(0) - 'a'].get(key)));
                    }
                }
                Collections.sort(list , (a,b) -> a.frequency == b.frequency ? a.sentence.compareTo(b.sentence) : b.frequency - a.frequency
                );
                
                for(int index = 0; index < Math.min(3,  list.size()); index++) {
                    result.add(list.get(index).sentence);
                }
            }
            return result;
        }

    }
    
    void runSearchAutoCompleteDesign () {
        
    }
    
    public static void main(String[] args) {
        PartN_2_SearchAutoCompleteDesign sac = new PartN_2_SearchAutoCompleteDesign();
        sac.runSearchAutoCompleteDesign();
        
    }
    
}
