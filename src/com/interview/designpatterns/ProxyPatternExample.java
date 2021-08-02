package com.interview.designpatterns;

//Reference: https://www.youtube.com/watch?v=AB0gaAg9jwI&list=PLIA-9QRQ0RqG0KnmB8MHTaRBkT0zt8byZ&index=6&t=8s
interface valbaseExecuter {
    public void executevalbase(String query) throws Exception;
}

class valbaseExecuterImpl implements valbaseExecuter {
    
    @Override
    public void executevalbase(String query) throws Exception {
        System.out.println("Going to execute Query: " + query);
    }
    
}

class valbaseExecuterProxy implements valbaseExecuter {
    boolean ifAdmin;
    valbaseExecuterImpl dbExecuter;
    
    public valbaseExecuterProxy(String name, String passwd) {
        if (name == "Admin" && passwd == "Admin@123") {
            ifAdmin = true;
        }
        dbExecuter = new valbaseExecuterImpl();
    }
    
    @Override
    public void executevalbase(String query) throws Exception {
        if (ifAdmin) {
            dbExecuter.executevalbase(query);
        } else {
            if (query.equals("DELETE")) {
                throw new Exception("DELETE not allowed for non-admin user");
            } else {
                dbExecuter.executevalbase(query);
            }
        }
    }
}

public class ProxyPatternExample {
    
    public static void main(String[] args) throws Exception {
        valbaseExecuter nonAdminExecuter = new valbaseExecuterProxy("NonAdmin", "Admin@123");
        nonAdminExecuter.executevalbase("DELEE");
        
        valbaseExecuter nonAdminExecuterDELETE = new valbaseExecuterProxy("NonAdmin", "Admin@123");
        nonAdminExecuterDELETE.executevalbase("DELETE");
        
        valbaseExecuter adminExecuter = new valbaseExecuterProxy("Admin", "Admin@123");
        adminExecuter.executevalbase("DELETE");
        
    }
    
}
