import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IATM extends Remote {
  int createAccount(int password) throws RemoteException;
  boolean isAccountValid(int accountId) throws RemoteException;
  boolean login(int accountId, int password) throws RemoteException;
  void deposit(int accountId, int amount) throws RemoteException;
  int widthdraw(int accountId, int amount) throws RemoteException;
  int inquiry(int accountId) throws RemoteException;
}
