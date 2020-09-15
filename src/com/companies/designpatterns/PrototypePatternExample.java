package com.companies.designpatterns;

import java.util.ArrayList;
import java.util.List;

//Reference: https://www.youtube.com/watch?v=MYe5NSmFU_c&list=PLIA-9QRQ0RqG0KnmB8MHTaRBkT0zt8byZ&index=5&t=9s

class Vehicle implements Cloneable {
  private List<String> vehicleList;
  
  public Vehicle() {
    this.vehicleList = new ArrayList<String>();
  }
  
  public Vehicle(List<String> list) {
    this.vehicleList = list;
  }
  
  public void insertData() {
    vehicleList.add("Honda amaze");
    vehicleList.add("Audi A4");
    vehicleList.add("Hyundai Creta");
    vehicleList.add("Maruti Baleno");
    vehicleList.add("Renault Duster");
  }
  
  public List<String> getVehicleList() {
    return this.vehicleList;
  }
  
  @Override
  public Object clone() throws CloneNotSupportedException {
    List<String> tempList = new ArrayList<String>();
    
    for(String s : this.getVehicleList()) {
      tempList.add(s);
    }
    
    return new Vehicle(tempList);
  }
}

public class PrototypePatternExample {

  public static void main(String[] args) throws CloneNotSupportedException {
    Vehicle a = new Vehicle();
    a.insertData();
    //Verify by changing value of a object
    
    Vehicle b = (Vehicle) a.clone();
    List<String> list = b.getVehicleList();
    list.add("Honda new Amaze");
    
    System.out.println(a.getVehicleList());
    System.out.println(list);
    
    b.getVehicleList().remove("Audi A4");
    System.out.println(list);
    System.out.println(a.getVehicleList());
  }

}
