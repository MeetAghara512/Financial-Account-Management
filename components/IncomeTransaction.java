package components;

public class IncomeTransaction extends Transaction {
      public IncomeTransaction(String description, double amount) {
            super(description, amount);
      }

      @Override
      public void execute(UserAccount userAccount) {
            userAccount.addToBalance(amount);
      }
}
