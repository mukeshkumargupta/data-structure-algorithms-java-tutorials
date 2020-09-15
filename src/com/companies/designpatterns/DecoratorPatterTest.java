package com.companies.designpatterns;

//Reference: https://www.youtube.com/watch?v=ZdE9x-qE8_c&list=PLIA-9QRQ0RqG0KnmB8MHTaRBkT0zt8byZ&index=9&t=10s
interface Dress {
    public void assemble();
  }

  class BasicDress implements Dress {
    @Override
    public void assemble() {
      System.out.println("Basic Dress Features");
    }
  }

  class DressDecorator implements Dress {
    protected Dress dress;
    
    public DressDecorator(Dress c) {
      this.dress = c;
    }

    @Override
    public void assemble() {
      this.dress.assemble();
    }
  }

  class CasualDress extends DressDecorator {
    public CasualDress(Dress c) {
      super(c);
    }
    
    @Override
    public void assemble() {
      super.assemble();
      System.out.println("Adding Casual Dress Features");
    }
  }


  class SportyDress extends DressDecorator {
    public SportyDress(Dress c) {
      super(c);
    }
    
    @Override
    public void assemble() {
      super.assemble();
      System.out.println("Adding Sporty Dress Features");
    }
  }

  class FancyDress extends DressDecorator {
    public FancyDress(Dress c) {
      super(c);
    }
    
    @Override
    public void assemble() {
      super.assemble();
      System.out.println("Adding Fancy Dress Features");
    }
  }

  public class DecoratorPatterTest {

    public static void main(String[] args) {

      Dress sportyDress = new SportyDress(new BasicDress());
      sportyDress.assemble();
      System.out.println();
      
      Dress fancyDress = new FancyDress(new BasicDress());
      fancyDress.assemble();
      System.out.println();
      
      Dress casualDress = new CasualDress(new BasicDress());
      casualDress.assemble();
      System.out.println();
      
      
      Dress sportyFancyDress = new SportyDress(new FancyDress(new BasicDress()));
      sportyFancyDress.assemble();
      System.out.println();
      
      Dress casualFancyDress = new CasualDress(new FancyDress(new BasicDress()));
      casualFancyDress.assemble();
      
    }
  }
