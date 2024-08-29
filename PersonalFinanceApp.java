import components.*;
import errorhandling.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonalFinanceApp {
      private static HashMap<String, String> userCredentials = new HashMap<>();
      private static HashMap<String, UserAccount> userAccounts = new HashMap<>();
      private static HashMap<String, UserInfo> userInformation = new HashMap<>();

      public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                  System.out.println("\nPersonal Finance Management System");
                  System.out.println("1. Create User Account");
                  System.out.println("2. Log In");
                  System.out.println("3. Exit");
                  System.out.print("Enter your choice: ");

                  int choice = 0;
                  try {
                        choice = scanner.nextInt();
                        scanner.nextLine(); 
                  } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid choice.");
                        scanner.nextLine(); 
                        continue;
                  }

                  switch (choice) {
                        case 1:
                              createUserAccount(scanner);
                              break;
                        case 2:
                              logIn(scanner);
                              break;
                        case 3:
                              System.out.println("Exiting the application.");
                              scanner.close();
                              System.exit(0);
                        default:
                              System.out.println("Invalid choice. Please try again.");
                  }
            }
      }

      private static void createUserAccount(Scanner scanner) {
            System.out.print("Enter a username: ");
            String username = scanner.nextLine();
            if (username == null || username.trim().isEmpty()) {
                  System.out.println("Username cannot be null or empty.");
                  return;
            }

            System.out.print("Enter a password: ");
            String password = scanner.nextLine();
            if (password == null || password.trim().isEmpty()) {
                  System.out.println("Password cannot be null or empty.");
                  return;
            }

            System.out.print("Enter an email address: ");
            String email = scanner.nextLine();
            System.out.print("Enter a phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter an account number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter a bank branch: ");
            String bankBranch = scanner.nextLine();

            try {
                  if (userCredentials.containsKey(username)) {
                        System.out.println("Username already exists. Please choose another.");
                  } else {
                        UserInfo userInfo = new UserInfo(username, email, phoneNumber, accountNumber, bankBranch);
                        userCredentials.put(username, password);
                        userAccounts.put(username, new UserAccount(userInfo));
                        userInformation.put(username, userInfo);
                        System.out.println("Account created successfully.");
                  }
            } catch (InvalidPhoneNumberException | InvalidAccountNumberException | IllegalArgumentException e) {
                  System.out.println(e.getMessage());
            }
      }

      private static void logIn(Scanner scanner) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            if (username == null || username.trim().isEmpty()) {
                  System.out.println("Username cannot be null or empty.");
                  return;
            }

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            if (password == null || password.trim().isEmpty()) {
                  System.out.println("Password cannot be null or empty.");
                  return;
            }

            if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                  manageUserAccount(scanner, username);
            } else {
                  System.out.println("Invalid username or password.");
            }
      }

      private static void manageUserAccount(Scanner scanner, String username) {
            UserAccount userAccount = userAccounts.get(username);

            while (true) {
                  System.out.println("\nWelcome, " + username);
                  System.out.println("1. Add Income");
                  System.out.println("2. Add Expense");
                  System.out.println("3. Show Balance");
                  System.out.println("4. List Transactions");
                  System.out.println("5. Information");
                  System.out.println("6. Log Out");
                  System.out.print("Enter your choice: ");

                  int choice = 0;
                  try {
                        choice = scanner.nextInt();
                        scanner.nextLine();
                  } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid choice.");
                        scanner.nextLine();
                        continue;
                  }

                  switch (choice) {
                        case 1:
                              try {
                                    System.out.print("Description: ");
                                    String incomeDescription = scanner.nextLine();
                                    System.out.print("Amount: $");
                                    double incomeAmount = scanner.nextDouble();
                                    scanner.nextLine(); 
                                    userAccount.addTransaction(new IncomeTransaction(incomeDescription, incomeAmount));
                              } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid amount.");
                                    scanner.nextLine();
                              } catch (NegativeBalanceException e) {
                                    System.out.println(e.getMessage());
                              }
                              break;
                        case 2:
                              try {
                                    System.out.print("Description: ");
                                    String expenseDescription = scanner.nextLine();
                                    System.out.print("Amount: $");
                                    double expenseAmount = scanner.nextDouble();
                                    scanner.nextLine(); 
                                    userAccount.addTransaction(
                                                new ExpenseTransaction(expenseDescription, expenseAmount));
                              } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid amount.");
                                    scanner.nextLine();
                              } catch (NegativeBalanceException e) {
                                    System.out.println(e.getMessage());
                              }
                              break;
                        case 3:
                              System.out.printf("Current balance: $%.2f\n", userAccount.getBalance());
                              break;
                        case 4:
                              System.out.println("Transaction History:");
                              for (Transaction transaction : userAccount.getTransactions()) {
                                    System.out.println(transaction);
                              }
                              break;
                        case 5:
                              UserInfo userInfo = userAccount.getUserInfo();
                              System.out.println("User Information:");
                              System.out.println("Username: " + userInfo.getUsername());
                              System.out.println("Email: " + userInfo.getEmail());
                              System.out.println("Phone Number: " + userInfo.getPhoneNumber());
                              System.out.println("Account Number: " + userInfo.getAccountNumber());
                              System.out.println("Bank Branch: " + userInfo.getBankBranch());
                              break;
                        case 6:
                              System.out.println("Logging out.");
                              return; 
                        default:
                              System.out.println("Invalid choice. Please try again.");
                  }
            }
      }
}
