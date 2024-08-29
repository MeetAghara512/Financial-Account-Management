package components;

import errorhandling.NegativeBalanceException;

public abstract class Transaction {
      String description;
      double amount;

      public Transaction(String description, double amount) {
            this.description = description;
            this.amount = amount;
      }

      public abstract void execute(UserAccount userAccount) throws NegativeBalanceException;

      @Override
      public String toString() {
            return description + " - $" + (amount >= 0 ? "+" : "") + amount;
      }
}
