package com.interview.designpatterns;

//Reference: https://www.youtube.com/watch?v=jcGSowIzmzM&list=PLIA-9QRQ0RqG0KnmB8MHTaRBkT0zt8byZ&index=3&t=208s
//Note: 



public class FactoryPatternExample {
    abstract class Vehicle {
        public abstract int getWheel();
        
        public String toString() {
          return "Wheel: " + this.getWheel();
        }
      }

      class Car extends Vehicle {
        int wheel;
        
        Car(int wheel) {
          this.wheel = wheel;
        }

        @Override
        public int getWheel() {
          return this.wheel;
        }
      }

      class Bike extends Vehicle {
        int wheel;
        
        Bike(int wheel) {
          this.wheel = wheel;
        }
        
        @Override
        public int getWheel() {
          return this.wheel;
        }
      }

      class VehicleFactory {
          //public static Vehicle getInstance(String type, int wheel) { Current removed static to compile code but this method should be static when we segregate code in class file
          public Vehicle getInstance(String type, int wheel) { 
          if(type == "car") {
              return new Car(wheel);
            } else if(type == "bike") {
              return new Bike(wheel);
            }
            
            return null;
          }
        }
      
      public void runFactoryPatternExample() {
          
          //Vehicle car = VehicleFactory.getInstance("car", 4); //We need to call in static fashion
         VehicleFactory factory = new VehicleFactory();
         Vehicle car = factory.getInstance("car", 4);
          
          System.out.println(car);
          Vehicle car1 = new Car(4);//how to stop this kind of creation
          System.out.println(car1);
          
          //Vehicle bike = VehicleFactory.getInstance("bike", 2);
          Vehicle bike = factory.getInstance("bike", 2);
          System.out.println(bike);
          
          
          //Without factory if we try to access and how to stop it, Need to think
          //Vehicle bike1 = new Bike(2);
          //System.out.println(bike1);
          
      }

  public static void main(String[] args) {
      FactoryPatternExample fpe = new FactoryPatternExample();
      fpe.runFactoryPatternExample();

  }

}