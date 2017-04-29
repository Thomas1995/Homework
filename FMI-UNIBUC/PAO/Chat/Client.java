import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Client {
  public static String username;

  public static void main(String[] args) {
    Socket socket;
    ObjectOutputStream output = null;
    ObjectInputStream input = null;
    Scanner scanner = null;

    try {
      // connect to server
      socket = new Socket("localhost", 8000);
      output = new ObjectOutputStream(socket.getOutputStream());
      input = new ObjectInputStream(socket.getInputStream());
      scanner = new Scanner(System.in);
    } catch(Exception e) {
      System.out.println(e.getMessage());
      return;
    }

    try {
      boolean invalidName;
      Message msg;
      do {
        // choose a username
        System.out.print("Username: ");
        username = scanner.nextLine();

        msg = new Message();
        msg.msgType = Message.MessageType.MSG_USERNAME;
        msg.buffer = username;
        output.writeObject(msg);

        // wait for the username to be validated
        msg = (Message) input.readObject();
        if(msg.msgType == Message.MessageType.MSG_INVALID_USERNAME) {
          invalidName = true;
          System.out.println("Username is already taken or it contains invalid characters! Please try again.\n");
        }
        else {
          invalidName = false;
          System.out.println("Username accepted!");
        }
      } while(invalidName);

      System.out.println("Write /h or /help for help.\n");

      String text;
      while(true) {
        text = scanner.nextLine();
        msg = Message.parse(text);

        do {
          if(msg.msgType == Message.MessageType.MSG_HELP) {
            System.out.println("/showusers or /shu - show a list of all online users");
            System.out.println("/a or /announce TEXT - send TEXT to everyone");
            System.out.println("/chat USERNAME - start a chat session with USERNAME");
            System.out.println("/d or /disconnect\n");
            break;
          }

          if(msg.msgType == Message.MessageType.MSG_DISCONNECT) {
            System.out.println("Bye-bye!");
          }

          if(msg.msgType == Message.MessageType.MSG_INVALID_COMMAND) {
            System.out.println("Invalid command, try using /help\n");
            break;
          }

          output.writeObject(msg);

          if(msg.msgType != Message.MessageType.MSG_TEXT) {
            msg = (Message) input.readObject();
            System.out.println(msg.buffer);
          }
        } while(false);
      }

    } catch(Exception e) {}
  }
}
