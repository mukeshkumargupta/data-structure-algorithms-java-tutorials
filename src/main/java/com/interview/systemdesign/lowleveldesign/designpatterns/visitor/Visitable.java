package com.interview.systemdesign.lowleveldesign.designpatterns.visitor;

interface Visitable {

    // Allows the Visitor to pass the object so
    // the right operations occur on the right
    // type of object.

    // accept() is passed the same visitor object
    // but then the method visit() is called using
    // the visitor object. The right version of visit()
    // is called because of method overloading

    public double accept(Visitor visitor);

}
