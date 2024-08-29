package components;

import errorhandling.NegativeBalanceException;
import java.util.ArrayList;

public class UserAccount {
      private double balance;
      private ArrayList<Transaction> transactions;
      private UserInfo userInfo;

      public UserAccount(UserInfo userInfo) {
            this.userInfo = userInfo;
            balance = 0.0;
            transactions = new ArrayList<>();
      }

      public UserInfo getUserInfo() {
            return userInfo;
      }

      public double getBalance() {
            return balance;
      }

      public void addToBalance(double amount) {
            balance += amount;
      }

      public void subtractFromBalance(double amount) throws NegativeBalanceException {
            if (balance + amount < 0) {
                  throw new NegativeBalanceException("Insufficient balance.");
            }
            balance += amount;
      }

      public ArrayList<Transaction> getTransactions() {
            return transactions;
      }

      public void addTransaction(Transaction transaction) throws NegativeBalanceException {
            transaction.execute(this);
            transactions.add(transaction);
      }
}
