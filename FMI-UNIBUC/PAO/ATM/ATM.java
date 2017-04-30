import java.util.HashMap;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.lang.Process;
import java.lang.Runtime;

public class ATM extends UnicastRemoteObject implements IATM {
  static HashMap<Integer, Integer> sums;
  static HashMap<Integer, Integer> pins;
  static int lastId;

  public ATM() throws RemoteException {
    super();
    sums = new HashMap<Integer, Integer>();
    pins = new HashMap<Integer, Integer>();
    lastId = 0;
  }

  public int createAccount(int password) {
    ++lastId;
    sums.put(lastId, 0);
    pins.put(lastId, password);
    return lastId;
  }

  public boolean isAccountValid(int accountId) {
    if(sums.get(accountId) == null)
      return false;
    return true;
  }

  public boolean login(int accountId, int password) {
    if(pins.get(accountId) == password)
      return true;
    return false;
  }

  public void deposit(int accountId, int amount) {
    sums.put(accountId, inquiry(accountId) + amount);
  }

  public int widthdraw(int accountId, int amount) {
    int balance = inquiry(accountId);
    if(balance < amount)
      amount = 0;
    sums.put(accountId, balance - amount);
    return amount;
  }

  public int inquiry(int accountId) {
    Integer i = sums.get(accountId);
    return i == null ? 0 : i;
  }

  public static void main(String[] args) {
    try {
      Runtime run = Runtime.getRuntime();
      Process proc = run.exec(new String[]{"/bin/sh", "rmiregistry &"});
      proc.waitFor();

      ATM atm = new ATM();
      Naming.rebind("ATM", atm);
      System.out.println("The ATM is open!");

    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
