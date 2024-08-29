package components;

import errorhandling.InvalidAccountNumberException;
import errorhandling.InvalidPhoneNumberException;

public class UserInfo {
      private String username;
      private String email;
      private String phoneNumber;
      private String accountNumber;
      private String bankBranch;

      public UserInfo(String username, String email, String phoneNumber, String accountNumber, String bankBranch)
                  throws InvalidPhoneNumberException, InvalidAccountNumberException {
            this.username = username;
            this.email = email;
            setPhoneNumber(phoneNumber);
            setAccountNumber(accountNumber);
            this.bankBranch = bankBranch;
      }

      public String getUsername() {
            return username;
      }

      public String getEmail() {
            return email;
      }

      public String getPhoneNumber() {
            return phoneNumber;
      }

      public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
            if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
                  throw new InvalidPhoneNumberException("Phone number cannot be null or empty.");
            }
            this.phoneNumber = phoneNumber;
      }

      public String getAccountNumber() {
            return accountNumber;
      }

      public void setAccountNumber(String accountNumber) throws InvalidAccountNumberException {
            if (accountNumber == null || accountNumber.trim().isEmpty()) {
                  throw new InvalidAccountNumberException("Account number cannot be null or empty.");
            }
            this.accountNumber = accountNumber;
      }

      public String getBankBranch() {
            return bankBranch;
      }
}
