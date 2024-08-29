package components;

import errorhandling.NegativeBalanceException;

public class ExpenseTransaction extends Transaction {
      public ExpenseTransaction(String description, double amount) {
            super(description, -amount); // Store as negative amount
      }

      @Override
      public void execute(UserAccount userAccount) throws NegativeBalanceException {
            userAccount.subtractFromBalance(amount);
      }
}
