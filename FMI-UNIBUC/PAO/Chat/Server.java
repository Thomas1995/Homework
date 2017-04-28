import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

class Connection {
  public String username;
  public boolean connected;
  private ObjectInputStream input;
  private ObjectOutputStream output;

  public Connection(Socket socket) {
    try {
      connected = false;
      input = new ObjectInputStream(socket.getInputStream());
      output = new ObjectOutputStream(socket.getOutputStream());

      boolean invalidName;
      Message msg;
      do {
        // get username
        msg = (Message) input.readObject();
        username = msg.buffer;

        invalidName = false;
        for (Connection con: Server.connections) {
          if(con.connected && con.username.equals(username)) {
            invalidName = true;
            break;
          }
        }

        msg = new Message();
        if(invalidName) {
          msg.msgType = Message.MessageType.MSG_INVALID_USERNAME;
        }
        else {
          msg.msgType = Message.MessageType.MSG_USERNAME;
        }
        output.writeObject(msg);

      } while(invalidName);

      connected = true;
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}

public class Server {
  public static ServerSocket serverSock;
  public static ArrayList<Connection> connections;

  public static void main(String[] args) {
    try {
      serverSock = new ServerSocket(8000);
      connections = new ArrayList<Connection>();

      while(true) {
        // new connection
        Socket socket = serverSock.accept();
        connections.add(new Connection(socket));
        System.out.println(connections.size());
        System.out.println(connections.get(connections.size() - 1).username);
      }
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
