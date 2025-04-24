package com.interview.systemdesign.lowleveldesign.designpatterns.visitor;

class Necessity implements Visitable {

    private double price;

    Necessity(double item) {
        price = item;
    }

    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public double getPrice() {
        return price;
    }

}
