package com.interview.designpatterns;

//Reference: https://www.youtube.com/watch?v=1HL0V7vz5mA&list=PLIA-9QRQ0RqG0KnmB8MHTaRBkT0zt8byZ&index=12&t=7s

interface TV {
    public void on();
    public void off();
}

class Sony implements TV {

    public void on() {
        System.out.println("Sony TV ON");
    }

    public void off() {
        System.out.println("Sony TV OFF");
    }
}

class Philips implements TV {


    public void on() {
        System.out.println("Philips TV ON");
    }

    public void off() {
        System.out.println("Philips TV OFF");
    }
}

/* 리모트 */
abstract class Remote {
    
    protected TV tv;

    public Remote(TV tv) {
        this.tv = tv;
    }

    abstract void on();

    abstract void off();
}

class OldRemote extends Remote {

    public OldRemote(TV tv) {
        super(tv);
    }

    @Override
    public void on() {
        System.out.print("ON with Old Remote :");
        tv.on();
    }

    @Override
    public void off() {
        System.out.print("OFF with old Remote :");
        tv.off();
    }

}

class NewRemote extends Remote {

    public NewRemote(TV tvType) {
        super(tvType);
    }

    @Override
    public void on() {
        System.out.print("ON with New Remote :");
        tv.on();
    }

    @Override
    public void off() {
        System.out.print("OFF with New Remote :");
        tv.off();
    }

}

public class BridgePatternExample {
    
    public static void main(String[] args) {
        Remote oldRemoteSony = new OldRemote(new Sony());
        oldRemoteSony.on();
        oldRemoteSony.off();
        System.out.println();

        Remote newRemoteSony = new NewRemote(new Sony());
        newRemoteSony.on();
        newRemoteSony.off();
        System.out.println();

        Remote oldRemotePhilips = new OldRemote(new Philips());
        oldRemotePhilips.on();
        oldRemotePhilips.off();
        System.out.println();

        Remote newRemotePhilips = new OldRemote(new Philips());
        newRemotePhilips.on();
        newRemotePhilips.off();
        System.out.println();
        
        oldRemoteSony = new OldRemote(new Sony());
        oldRemoteSony.on();
        oldRemoteSony.off();
        System.out.println();

    }
}
