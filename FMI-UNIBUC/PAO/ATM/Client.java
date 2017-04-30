import java.util.Scanner;
import java.rmi.Naming;

public class Client {
  public static void main(String[] args) {
    IATM atm = null;
    try {
      atm = (IATM) Naming.lookup("/ATM");

      Scanner scanner = new Scanner(System.in);

      int accountId = 0;
      while(true) {
        System.out.print("Enter your account ID to login or 0 to be given a new one: ");
        accountId = scanner.nextInt();

        if(accountId == 0) {
          System.out.print("Choose a 4 digit PIN: ");
          int pin = scanner.nextInt();

          if(pin < 0 || pin > 9999) {
            System.out.println("Invalid PIN!\n");
            continue;
          }

          accountId = atm.createAccount(pin);
          System.out.println("Your account ID is " + accountId);
          break;
        }
        else {
          if(atm.isAccountValid(accountId) == false)
            System.out.print("Sorry, the account ID you entered is invalid. Try again? [Y\\n]: ");
          else
            break;
        }
        char r = (char) System.in.read();

        if(r == 'n') {
          System.out.println("Bye!");
          return;
        }
        else {
          System.out.println();
        }
      }

      while(true) {
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        if(!atm.login(accountId, pin))
          System.out.println("PIN is invalid. Try again.\n");
        else
          break;
      }

      System.out.println("\nCommands:");
      System.out.println("deposit X");
      System.out.println("widthdraw X");
      System.out.println("inquiry");
      System.out.println("quit");

      while(true) {
        System.out.println();
        String line = scanner.nextLine();

        if(line.equals("quit")) {
          System.out.println("Have a great day!");
          break;
        }

        if(line.equals("inquiry")) {
          System.out.println("Balance: " + atm.inquiry(accountId));
        }

        if (line.length() >= 9 && line.substring(0, 7).equals("deposit")) {
          int amount = Integer.parseInt(line.substring(8));

          if(amount <= 0) {
            System.out.println("The amount can not be 0 or negative.");
            continue;
          }

          atm.deposit(accountId, amount);
          System.out.println("Transaction succeeded!");
        }

        if (line.length() >= 11 && line.substring(0, 9).equals("widthdraw")) {
          int amount = Integer.parseInt(line.substring(10));

          if(amount <= 0) {
            System.out.println("The amount can not be 0 or negative.");
            continue;
          }

          if(amount % 10 != 0) {
            System.out.println("The amount must be a multiple of 10.");
            continue;
          }

          int res = atm.widthdraw(accountId, amount);

          if(res == 0) {
            System.out.println("You do not have enough money in your account!");
          }
          else {
            System.out.println("Transaction succeeded!");
          }
        }
      }
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
