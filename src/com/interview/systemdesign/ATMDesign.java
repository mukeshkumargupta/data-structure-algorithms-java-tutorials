package com.interview.systemdesign;

/*
 * Category: Must Do, Amazon
 */
public class ATMDesign {
    /*
     * Enums and Constants: Here are the required enums, data types, and constants:
     */
    public enum TransactionType {
        BALANCE_INQUIRY, DEPOSIT_CASH, DEPOSIT_CHECK, WITHDRAW, TRANSFER
    }
    
    public enum TransactionStatus {
        SUCCESS, FAILURE, BLOCKED, FULL, PARTIAL, NONE
    }
    
    public enum CustomerStatus {
        ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, CLOSED, UNKNOWN
    }
    
    public class Address {
        private String streetAddress;
        private String city;
        private String state;
        private String zipCode;
        private String country;
    }
    
    /*
     * Customer, Card, and Account: �Customer� encapsulates the ATM user, �Card� the
     * ATM card, and �Account� can be of two types: checking and savings: // For
     * simplicity, we are not defining getter and setter functions. The reader can
     * // assume that all class attributes are private and accessed through their
     * respective // public getter method and modified only through their public
     * setter function.
     * 
     */
    
    public class Customer {
        private String name;
        private String email;
        private String phone;
        private Address address;
        private CustomerStatus status;
        
        private Card card;
        private Account account;
        
        public boolean makeTransaction(Transaction transaction);
        
        public Address getBillingAddress();
    }
    
    public class Card {
        private String cardNumber;
        private String customerName;
        private Date cardExpiry;
        private int pin;
        
        public Address getBillingAddress();
    }
    
    public class Account {
        private int accountNumber;
        private double totalBalance;
        private double availableBalance;
        
        public double getAvailableBalance();
    }
    
    public class SavingAccount extends Account {
        private double withdrawLimit;
    }
    
    public class CheckingAccount extends Account {
        private String debitCardNumber;
    }
    
    /*
     * Bank, ATM, CashDispenser, Keypad, Screen, Printer and DepositSlot: The ATM
     * will have different components like keypad, screen, etc.
     */
    
    public class Bank {
        private String name;
        private String bankCode;
        
        public String getBankCode();
        
        public boolean addATM();
    }
    
    public class ATM {
        private int atmID;
        private Address location;
        
        private CashDispenser cashDispenser;
        private Keypad keypad;
        private Screen screen;
        private Printer printer;
        private CheckDeposit checkDeposit;
        private CashDeposit cashDeposit;
        
        public boolean authenticateUser();
        
        public boolean makeTransaction(Customer customer, Transaction transaction);
    }
    
    public class CashDispenser {
        private int totalFiveDollarBills;
        private int totalTwentyDollarBills;
        
        public boolean dispenseCash(double amount);
        
        public boolean canDispenseCash();
    }
    
    public class Keypad {
        public String getInput();
    }
    
    public class Screen {
        public boolean showMessage(String message);
        
        public TransactionType getInput();
    }
    
    public class Printer {
        public boolean printReceipt(Transaction transaction);
    }
    
    public abstract class DepositSlot {
        private double totalAmount;
        
        public double getTotalAmount();
    }
    
    public class CheckDepositSlot extends DepositSlot {
        public double getCheckAmount();
    }
    
    public class CashDepositSlot extends DepositSlot {
        public double receiveDollarBill();
    }
    
    /*
     * Transaction and its subclasses: Customers can perform different transactions
     * on the ATM, these classes encapsulate them:
     */
    public abstract class Transaction {
        private int transactionId;
        private Date creationTime;
        private TransactionStatus status;
        
        public boolean makeTransation();
    }
    
    public class BalanceInquiry extends Transaction {
        private int accountId;
        
        public double getAccountId();
    }
    
    public abstract class Deposit extends Transaction {
        private double amount;
        
        public double getAmount();
    }
    
    public class CheckDeposit extends Deposit {
        private String checkNumber;
        private String bankCode;
        
        public String getCheckNumber();
    }
    
    public class CashDeposit extends Deposit {
        private double cashDepositLimit;
    }
    
    public class Withdraw extends Transaction {
        private double amount;
        
        public double getAmount();
    }
    
    public class Transfer extends Transaction {
        private int destinationAccountNumber;
        
        public int getDestinationAccount();
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
