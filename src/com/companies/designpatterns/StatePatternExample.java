package com.companies.designpatterns;

/*
 * Reference: https://www.youtube.com/watch?v=97AmoYlWxrI  (Good example so read some design pattern from here)
 * 
 */

public class StatePatternExample {
    
    interface AtmMachineState
    {

            public void insertDebitCard();
            
            public void ejectDebitCard();
            
            public void enterPinAndWithdrawMoney();
    }
    
    class NoDebitCardState implements AtmMachineState
    {

            @Override
            public void insertDebitCard()
            {
                    System.out.println("DebitCard inserted ....");

            }

            @Override
            public void ejectDebitCard()
            {

                    System.out.println("No Debit Card in ATM Machine slot, so you cannot eject the Debit Card...");
            }

            @Override
            public void enterPinAndWithdrawMoney()
            {
                    System.out.println("No Debit Card in ATM Machine slot, so you cannot enter the pin and withdraw the money...");

            }

    }
    
    class HasDebitCardState implements AtmMachineState
    {
            @Override
            public void insertDebitCard()
            {
                    System.out.println("Debit Card is already there,So you cannot insert the Debit Card ...");

            }

            @Override
            public void ejectDebitCard()
            {

                    System.out.println("Debit Card is ejected...");
            }

            @Override
            public void enterPinAndWithdrawMoney()
            {
                    System.out.println("Pin number has been entered correctly and money has been withdrawn...");

            }
    }
    
 // This is like Context
    class AtmMachine implements AtmMachineState
    {
            private AtmMachineState atmMachineState;
            public AtmMachine()
            {
                    atmMachineState = new NoDebitCardState();
            }

            public AtmMachineState getAtmMachineState()
            {
                    return atmMachineState;
            }

            public void setAtmMachineState( AtmMachineState atmMachineState )
            {
                    this.atmMachineState = atmMachineState;
            }

            @Override
            public void insertDebitCard()
            {
                    atmMachineState.insertDebitCard();

                    /*
                     * Debit Card has been inserted so internal state of ATM Machine
                     * has been changed to 'hasDebitCardState'
                     */

                    if( atmMachineState instanceof NoDebitCardState )
                    {

                            AtmMachineState hasDebitCardState = new HasDebitCardState();
                            setAtmMachineState(hasDebitCardState);
                            System.out.println("ATM Machine internal state has been moved to : "
                                            + atmMachineState.getClass().getName());
                    }

            }

            @Override
            public void ejectDebitCard()
            {
                    atmMachineState.ejectDebitCard();

                    /*
                     * Debit Card has been ejected so internal state of ATM Machine
                     * has been changed to 'noDebitCardState'
                     */
                    if( atmMachineState instanceof HasDebitCardState )
                    {

                            AtmMachineState noDebitCardState = new NoDebitCardState();
                            setAtmMachineState(noDebitCardState);
                            System.out.println("ATM Machine internal state has been moved to : "
                                            + atmMachineState.getClass().getName());
                    }

            }

            @Override
            public void enterPinAndWithdrawMoney()
            {
                    atmMachineState.enterPinAndWithdrawMoney();

            }

    }
    
    public void runStatePatternExample() {
        /*
         * Initially ATM Machine in 'noDebitCardState'
         */
        AtmMachine atmMachine = new AtmMachine();

        System.out.println("ATM Machine Current state : "
                        + atmMachine.getAtmMachineState().getClass().getName());

        System.out.println();
        atmMachine.enterPinAndWithdrawMoney();
        atmMachine.ejectDebitCard();
        atmMachine.insertDebitCard();
        
        System.out.println("\n*******************************************************");

        /*
         * Debit Card has been inserted so internal state of ATM Machine
         * has been changed to 'hasDebitCardState'
         */
        System.out.println("\nATM Machine Current state : "
                        + atmMachine.getAtmMachineState().getClass().getName());
        System.out.println();

        atmMachine.enterPinAndWithdrawMoney();
        atmMachine.insertDebitCard();
        atmMachine.ejectDebitCard();
        System.out.println("\n*******************************************************");

        /*
         * Debit Card has been ejected so internal state of ATM Machine
         * has been changed to 'noDebitCardState'
         */

        System.out.println("\nATM Machine Current state : "
                        + atmMachine.getAtmMachineState().getClass().getName());
    }
    
    public static void main(String[] args) {
        StatePatternExample spe = new StatePatternExample();
        spe.runStatePatternExample();
        
    }
    
}
