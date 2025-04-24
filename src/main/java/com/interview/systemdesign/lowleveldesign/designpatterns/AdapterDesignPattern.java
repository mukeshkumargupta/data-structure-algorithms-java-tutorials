package com.interview.systemdesign.lowleveldesign.designpatterns;

//Reference:https://www.youtube.com/watch?v=FsS-jWCPtQI&list=PLIA-9QRQ0RqG0KnmB8MHTaRBkT0zt8byZ&index=11&t=11s

interface WebDriver {
    public void getElement();
    
    public void selectElement();
}

class ChromeDriver implements WebDriver {
    
    @Override
    public void getElement() {
        System.out.println("Get element from ChromeDriver");
    }
    
    @Override
    public void selectElement() {
        System.out.println("Select element from ChromeDriver");
        
    }
}

abstract class DriverBase {
    public abstract String getName();
}

class IEDriver extends DriverBase {
    String name;
    
    IEDriver(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void findElement() {
        System.out.println("Find element from IEDriver");
    }
    
    public void clickElement() {
        System.out.println("Click element from IEDriver");
    }
}

class FirefoxDriver extends DriverBase {
    String name;
    
    FirefoxDriver(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void findElement1() {
        System.out.println("Find element1 from FirefoxDriver");
    }
    
    public void clickElement1() {
        System.out.println("Click element1 from FirefoxDriver");
    }
    
}

class WebDriverAdapter implements WebDriver {
    
    DriverBase driver;
    
    public WebDriverAdapter(DriverBase driver) {
        this.driver = driver;
    }
    
    @Override
    public void getElement() {
        if (driver.getName().equals("IEDriver")) {
            ((IEDriver) driver).findElement();
            
        } else if (driver.getName().equals("FirefoxDriver")) {
            ((FirefoxDriver) driver).findElement1();
        }
    }
    
    @Override
    public void selectElement() {
        if (driver.getName().equals("IEDriver")) {
            ((IEDriver) driver).clickElement();
            
        } else if (driver.getName().equals("FirefoxDriver")) {
            ((FirefoxDriver) driver).clickElement1();
        }
    }
    
}

public class AdapterDesignPattern {
    
    public static void main(String[] args) {
        ChromeDriver a = new ChromeDriver();
        a.getElement();
        a.selectElement();
        
        IEDriver e = new IEDriver("IEDriver");
        e.findElement();
        e.clickElement();
        
        FirefoxDriver fd = new FirefoxDriver("FirefoxDriver");
        
        WebDriver wID = new WebDriverAdapter(fd);
        wID.getElement();
        wID.selectElement();
        
    }
    
}