package com.interview.systemdesign.lowleveldesign;

import java.util.HashMap;
import java.util.Map;
/*
    For an ATM system, we can apply a similar design pattern as the URL Shortener and Airline Management systems.
    The ATM system will focus on managing customer accounts, processing transactions, and ensuring security.
    Here’s how we can break down the key components and structure the design:

    1. Core Requirements:
       - **Account Management**: Add, update, and manage customer accounts.
       - **Transaction Processing**: Handle withdrawals, deposits, and balance inquiries.
       - **Security**: Ensure PIN-based authentication for account access.
       - **Uniqueness**: Ensure that each customer account has a unique account number.
       - **Optional**: Support multiple accounts per customer (e.g., savings, checking).

    2. Approach:
       - Use unique account numbers and PINs to authenticate customers.
       - Store account information and transaction history in a database or key-value store.
       - Implement services to handle withdrawals, deposits, and balance checks.
       - Use a secure hashing algorithm to store PINs.

    3. Components of the ATM System:
       - **Account Table**: A database or key-value store to store customer account details (account number, balance, PIN).
       - **Transaction Table**: A log of all transactions (withdrawals, deposits) for auditing and tracking.
       - **Services**:
         - `authenticate()`: Validate the account number and PIN.
         - `withdraw()`: Deduct money from the account if sufficient balance exists.
         - `deposit()`: Add money to the account.
         - `checkBalance()`: Retrieve the current balance of an account.
       - **Security**: Use hashed PINs for secure authentication.

    4. Services:
       - `authenticate()`: Takes account number and PIN, validates against stored information.
       - `withdraw()`: Subtracts the requested amount from the account balance (if sufficient funds exist).
       - `deposit()`: Adds the requested amount to the account balance.
       - `checkBalance()`: Returns the current balance for a given account.

    5. Scalability Considerations:
       - Use a NoSQL database to store account information for scalability.
       - Implement logging for each transaction to track withdrawals and deposits efficiently.
       - Ensure that concurrent transactions (like simultaneous withdrawals) are handled properly using database
         locking or similar mechanisms to prevent race conditions.

    6. Security Considerations:
       - Store PINs securely using hashing (e.g., bcrypt) to ensure they cannot be easily compromised.
       - Implement session timeouts and multi-factor authentication (optional) for added security.
       - Rate-limit PIN entry attempts to prevent brute-force attacks.

    7. Explanation:
       - **Account Management**: Each customer has a unique account number and a hashed PIN. This is stored in the Account Table.
       - **Transaction Management**: Every transaction (deposit or withdrawal) is logged for auditing purposes.
       - **Authentication**: Before any transaction, the customer must authenticate using their account number and PIN.
         The PIN is compared against the stored hash.
       - **Withdrawals/Deposits**: Once authenticated, the customer can perform transactions like deposits or withdrawals,
         with checks in place to ensure enough balance for withdrawals.

    8. Additional Features:
       - Implement rate limiting to prevent brute-force attacks on account authentication.
       - **Caching**: Cache account balances for faster access, reducing database load.
       - Implement alerts for transactions above a certain threshold for security monitoring.

    Key Points:
    - **Authentication**: This verifies the account number and PIN using hashed PIN storage to ensure security.
    - **Transaction Management**: Services like withdrawal, deposit, and checking the balance are provided. Deposits add funds,
      and withdrawals ensure that there is enough balance before deducting.
    - **Security Considerations**: The PINs are hashed to prevent unauthorized access, and basic rate-limiting strategies can
      be applied to avoid brute-force attacks.

    You can expand this design by adding logging, error handling, and using a persistent database instead of the
    in-memory HashMap for scalability.
*/

class Account {
    private String accountNumber;
    private double balance;
    private String hashedPIN; // Store the hashed version of the PIN for security

    public Account(String accountNumber, double balance, String hashedPIN) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.hashedPIN = hashedPIN;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getHashedPIN() {
        return hashedPIN;
    }
}

class ATMService {
    private Map<String, Account> accountMap = new HashMap<>(); // In-memory storage for simplicity

    // Authentication service
    public boolean authenticate(String accountNumber, String enteredPIN) {
        Account account = accountMap.get(accountNumber);
        if (account == null) {
            return false;
        }

        // In a real system, you'd compare the entered PIN's hash to the stored hashed PIN
        return account.getHashedPIN().equals(hashPIN(enteredPIN));
    }

    // Hashing function (simplified for this example)
    private String hashPIN(String pin) {
        // Implement a real hash function like bcrypt or SHA-256
        return String.valueOf(pin.hashCode());
    }

    // Add a new account
    public void addAccount(String accountNumber, double initialBalance, String pin) {
        String hashedPIN = hashPIN(pin);
        Account account = new Account(accountNumber, initialBalance, hashedPIN);
        accountMap.put(accountNumber, account);
    }

    // Withdraw service
    public boolean withdraw(String accountNumber, double amount) {
        Account account = accountMap.get(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }

    // Deposit service
    public void deposit(String accountNumber, double amount) {
        Account account = accountMap.get(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
        }
    }

    // Check balance service
    public double checkBalance(String accountNumber) {
        Account account = accountMap.get(accountNumber);
        return (account != null) ? account.getBalance() : -1;
    }
}

public class PartRATMDesign {
    public static void main(String[] args) {
        ATMService atmService = new ATMService();

        // Add an account
        atmService.addAccount("123456", 1000.0, "1234");

        // Authenticate user
        boolean isAuthenticated = atmService.authenticate("123456", "1234");
        if (isAuthenticated) {
            System.out.println("Authentication successful!");
            atmService.deposit("123456", 500.0);
            boolean withdrawalSuccess = atmService.withdraw("123456", 300.0);
            if (withdrawalSuccess) {
                System.out.println("Withdrawal successful. New balance: " + atmService.checkBalance("123456"));
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Authentication failed!");
        }
    }
}
