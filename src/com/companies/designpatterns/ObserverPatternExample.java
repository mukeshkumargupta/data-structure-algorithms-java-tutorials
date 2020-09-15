package com.companies.designpatterns;

import java.util.ArrayList;
import java.util.List;

//Reference: https://www.youtube.com/watch?v=w79jycVKi_Q&list=PLIA-9QRQ0RqG0KnmB8MHTaRBkT0zt8byZ&index=13&t=120s

interface Subject {
  void register(Observer obj);
  void unregister(Observer obj);
  void notifyObservers();
}

//Notification is based on location changed
class LocationData implements Subject {

  private List<Observer> observers;
  private String location;
  
  public LocationData() {
    this.observers = new ArrayList<>();
  }
  
  @Override
  public void register(Observer obj) {
    observers.add(obj);
  }

  @Override
  public void unregister(Observer obj) {
      observers.remove(obj);
  }

  @Override
  public void notifyObservers() {
    for(Observer obj : observers) {
      obj.update(location);
    }
  }

  public void locationChangedTo(String location) {
    if (location != null && !location.equals(getLocation())) {//Change any
        this.location = location;
        notifyObservers();
    }
  }
  
  public String getLocation() {
    return this.location;
  }
}

//Notification is based on price changed
//Similar way you can add topic or subject as per requirement
class PriceData implements Subject {

private List<Observer> observers;
private String price;

public PriceData() {
  this.observers = new ArrayList<>();
}

@Override
public void register(Observer obj) {
  observers.add(obj);
}

@Override
public void unregister(Observer obj) {
    observers.remove(obj);
}

@Override
public void notifyObservers() {
  for(Observer obj : observers) {
    obj.update(price);
  }
}

public void priceChangedTo(String price) {
  if (price != null && !price.equals(getPrice())) {//Change any
      this.price = price;
      notifyObservers();
  }
}

public String getPrice() {
  return this.price;
}
}


interface Observer {
  public void update(String location);
}


class Seller implements Observer {
  private String location;
  private String price;
  
  //Need to correct
  @Override
  public void update(String location) {
    this.location = location;
    showLocation();
  }

  public void showLocation() {
    System.out.println("Notification at Seller - Current Location: " + location);
  }
  
  public void showPrice() {
      System.out.println("Notification at Seller - Current price: " + price);
    }
}

class User implements Observer {
  private String location;
  private String price;
  
  @Override
  public void update(String location) {
    this.location = location;
    showLocation();
  }

  public void showLocation() {
    System.out.println("Notification at User - Current Location: " + location);
  }
  public void showPrice() {
      System.out.println("Notification at User - Current Price: " + price);
  }
}

class DeliveryWarehouseCenter implements Observer {
  private String location;
  
  @Override
  public void update(String location) {
    this.location = location;
    showLocation();
  }

  public void showLocation() {
    System.out.println("Notification at Warehouse - Current Location: " + location);
  }
}


public class ObserverPatternExample {
    
    public static void main(String[] args) {
        LocationData locationTopic = new LocationData();
        
        Observer obj1 = new Seller();
        Observer obj2 = new User();
        Observer obj3 = new DeliveryWarehouseCenter();
        
        locationTopic.register(obj1);
        locationTopic.register(obj2);
        locationTopic.register(obj3);
        
        locationTopic.locationChangedTo("ABC");
        
        locationTopic.unregister(obj3);
        
        System.out.println();
        locationTopic.locationChangedTo("DEF");
        
        //New topic
        PriceData priceTopic = new PriceData();
        
        
        priceTopic.register(obj1); //Only two interested in price
        priceTopic.register(obj2);
        
        priceTopic.priceChangedTo("12");
        
        priceTopic.unregister(obj1);
        
        System.out.println();
        priceTopic.priceChangedTo("15");
        
    }
    
}
