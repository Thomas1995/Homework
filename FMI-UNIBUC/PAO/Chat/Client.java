import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Client {
  public static String username;

  public static void main(String[] args) {
    try {
      // connect to server
      Socket socket = new Socket("localhost", 8000);
      ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
      Scanner scanner = new Scanner(System.in);

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

        msg = (Message) input.readObject();
        if(msg.msgType == Message.MessageType.MSG_INVALID_USERNAME) {
          invalidName = true;
          System.out.println("Username is already taken! Please try again.");
        }
        else {
          invalidName = false;
          System.out.println("Username accepted!");
        }
      } while(invalidName);

      // wait for username to be validated
      //msg = (Message) input.readObject();

      /*System.out.println("Hai ca trimit.");
      msg = new Message();
      msg.msgType = Message.MessageType.MSG_TEXT;
      msg.buffer = "Ce pula mea vrei?";

      System.out.println("Hai ca trimit.");
      output.writeObject(msg);
      System.out.println("Am trimis.");*/
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
