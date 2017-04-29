import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

class Connection extends Thread {
  public String username;
  public boolean connected;
  private Socket socket;
  private ObjectInputStream input;
  private ObjectOutputStream output;

  public Connection(Socket socket) {
    try {
      this.socket = socket;
      connected = false;
      input = new ObjectInputStream(socket.getInputStream());
      output = new ObjectOutputStream(socket.getOutputStream());
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private void chooseUsername() {
    try {
      boolean invalidName;
      Message msg;
      do {
        // get username
        msg = (Message) input.readObject();
        username = msg.buffer;

        if(username == null) {
          break;
        }

        invalidName = false;

        for (Connection con: Server.connections) {
          if(con.connected && con.username.equals(username)) {
            invalidName = true;
            break;
          }
        }

        if(username.length() == 0 || username.contains(" ")) {
          invalidName = true;
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
    } catch(Exception e) {}
  }

  public void run() {
    chooseUsername();

    if(connected) {
      System.out.println(username + " connected");
    }
    else {
      return;
    }

    try {
      while(true) {
        Message msg = (Message) input.readObject();
        Message ans = new Message();

        switch (msg.msgType) {
          case MSG_SHOW_USERS:
            String userList = "";
            for (Connection con: Server.connections)
              if(con.connected)
                userList = userList + con.username + "\n";

            ans.buffer = userList;
            output.writeObject(ans);
            break;
          case MSG_DISCONNECT:
            socket.close();
            disconnect();
            break;
        }
      }
    } catch(Exception e) {
      disconnect();
    }
  }

  private void disconnect() {
    if(connected == true) {
      connected = false;
      Server.connections.remove(this);
      System.out.println(username + " disconnected");
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
      System.out.println("Server started.");

      while(true) {
        // new connection
        Socket socket = serverSock.accept();
        Connection con = new Connection(socket);
        connections.add(con);
        con.start();
      }
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
