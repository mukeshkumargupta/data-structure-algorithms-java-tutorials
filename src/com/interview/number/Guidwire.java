package com.interview.number;

import java.util.*;
public class Guidwire {
    public Guidwire(Queue<Profile> pq) {
        super();
        this.pq = pq;
    }
    //Neha , Female, 21 , Singing, dancing
    class Profile {
        String name;
        String sex;
        int age;
        List<String> hobies;

        public Profile(String name, String sex, int age, List<String> hobies) {
            super();
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.hobies = hobies;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getSex() {
            return sex;
        }
        public void setSex(String sex) {
            this.sex = sex;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public List<String> getHobies() {
            return hobies;
        }
        public void setHobies(List<String> hobies) {
            this.hobies = hobies;
        }
    }
    
    void buildRanking(List<Profile> profiles, Profile input, Queue<Profile> pq) {
        
 
        for (Profile p : profiles) {
            if (!input.sex.equals(p.sex)) {//Opposite sex //partition it negive slot for oposite sex and same sex for positive abs value
                //find age difference
                int agediff = p.getAge() > input.getAge() ? p.getAge() - input.getAge() : input.getAge() - p.getAge(); //1 st criteria 
                //Find intersection of hobies

            }
            
            
        }
        
        
    }
    
    void findBestMatch(List<Profile> profiles, Profile input) {
        
        
        
        Collections.sort(profiles, (p1, p2)-> {
            if (!p1.getSex().equals(p2.getSex()) && p1.getAge() != p2.getAge() ) {
                return Math.abs(input.getAge() - p1.getAge()) - Math.abs(input.getAge() - p2.getAge());
            } else if (!p1.getSex().equals(p2.getSex()) && p1.getAge() == p2.getAge()) {
                //sort by common hobies
                int i = 0; 
                int j = 0;
                //Find intersection with p1 user and p2 user
                int count
                while(i < p1.getHobies() && j < input.getHobies()) {
                    if () 
                }
                input.getHobies()
            } else {
                return  Math.abs(input.getAge() - p2.getAge()) - Math.abs(input.getAge() - p1.getAge());
            }
            return 0;
            
        });
    }
    
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
