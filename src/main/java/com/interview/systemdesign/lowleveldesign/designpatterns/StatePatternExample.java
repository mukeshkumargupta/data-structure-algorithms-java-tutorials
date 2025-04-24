package com.interview.systemdesign.lowleveldesign.designpatterns;

/*
 * Reference: https://www.youtube.com/watch?v=97AmoYlWxrI  (Good example so read some design pattern from here)
 * Status: done
 */

public class StatePatternExample {
    
    interface AtmMachineState {
        
        public void insertDebitCard();
        
        public void ejectDebitCard();
        
        public void enterPinAndWithdrawMoney();
    }
    
    class TreeNodebitCardState implements AtmMachineState {
        
        @Override
        public void insertDebitCard() {
            System.out.println("DebitCard inserted ....");
            
        }
        
        @Override
        public void ejectDebitCard() {
            
            System.out.println("No Debit Card in ATM Machine slot, so you cannot eject the Debit Card...");
        }
        
        @Override
        public void enterPinAndWithdrawMoney() {
            System.out.println(
                    "No Debit Card in ATM Machine slot, so you cannot enter the pin and withdraw the money...");
            
        }
        
    }
    
    class HasDebitCardState implements AtmMachineState {
        @Override
        public void insertDebitCard() {
            System.out.println("Debit Card is already there,So you cannot insert the Debit Card ...");
            
        }
        
        @Override
        public void ejectDebitCard() {
            
            System.out.println("Debit Card is ejected...");
        }
        
        @Override
        public void enterPinAndWithdrawMoney() {
            System.out.println("Pin number has been entered correctly and money has been withdrawn...");
            
        }
    }
    
    // This is like Context
    class AtmMachine implements AtmMachineState {
        private AtmMachineState atmMachineState;
        
        public AtmMachine() {
            atmMachineState = new TreeNodebitCardState();
        }
        
        public AtmMachineState getAtmMachineState() {
            return atmMachineState;
        }
        
        public void setAtmMachineState(AtmMachineState atmMachineState) {
            this.atmMachineState = atmMachineState;
        }
        
        @Override
        public void insertDebitCard() {
            // atmMachineState.insertDebitCard();//I think it should be inside
            // TreeNodebitCardState check
            
            /*
             * Debit Card has been inserted so internal state of ATM Machine has been
             * changed to 'hasDebitCardState'
             */
            
            if (atmMachineState instanceof TreeNodebitCardState) {
                atmMachineState.insertDebitCard();
                AtmMachineState hasDebitCardState = new HasDebitCardState();
                setAtmMachineState(hasDebitCardState);
                System.out.println(
                        "ATM Machine internal state has been moved to : " + atmMachineState.getClass().getName());
            }
            
        }
        
        @Override
        public void ejectDebitCard() {
            // atmMachineState.ejectDebitCard(); //I think it should be inside
            // HasDebitCardState check
            
            /*
             * Debit Card has been ejected so internal state of ATM Machine has been changed
             * to 'TreeNodebitCardState'
             */
            if (atmMachineState instanceof HasDebitCardState) {
                atmMachineState.ejectDebitCard();
                
                AtmMachineState TreeNodebitCardState = new TreeNodebitCardState();
                setAtmMachineState(TreeNodebitCardState);
                System.out.println(
                        "ATM Machine internal state has been moved to : " + atmMachineState.getClass().getName());
            }
            
        }
        
        @Override
        public void enterPinAndWithdrawMoney() {
            atmMachineState.enterPinAndWithdrawMoney();
            
        }
        
    }
    
    public void runStatePatternExample() {
        /*
         * Initially ATM Machine in 'TreeNodebitCardState'
         */
        AtmMachine atmMachine = new AtmMachine();
        
        System.out.println("ATM Machine Current state : " + atmMachine.getAtmMachineState().getClass().getName());
        
        System.out.println();
        atmMachine.enterPinAndWithdrawMoney();
        atmMachine.ejectDebitCard();
        atmMachine.insertDebitCard();
        
        System.out.println("\n*******************************************************");
        
        /*
         * Debit Card has been inserted so internal state of ATM Machine has been
         * changed to 'hasDebitCardState'
         */
        System.out.println("\nATM Machine Current state : " + atmMachine.getAtmMachineState().getClass().getName());
        System.out.println();
        
        atmMachine.enterPinAndWithdrawMoney();
        atmMachine.insertDebitCard();
        atmMachine.ejectDebitCard();
        System.out.println("\n*******************************************************");
        
        /*
         * Debit Card has been ejected so internal state of ATM Machine has been changed
         * to 'TreeNodebitCardState'
         */
        
        System.out.println("\nATM Machine Current state : " + atmMachine.getAtmMachineState().getClass().getName());
    }
    
    public static void main(String[] args) {
        StatePatternExample spe = new StatePatternExample();
        spe.runStatePatternExample();
        
    }
    
}
