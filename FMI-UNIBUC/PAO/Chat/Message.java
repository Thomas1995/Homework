import java.io.Serializable;

public class Message implements Serializable {
  public enum MessageType {
    MSG_USERNAME,
    MSG_INVALID_USERNAME,
    MSG_TEXT,
    MSG_INVALID_COMMAND,
    MSG_HELP,
    MSG_SHOW_USERS,
    MSG_ANNOUNCE,
    MSG_CHAT,
    MSG_DISCONNECT
  };
  public MessageType msgType;
  public String buffer;

  Message() {}

  public static Message parse(String text) {
    Message msg = new Message();
    if(text.charAt(0) == '/') {
      do {
        if (text.equals("/h") || text.equals("/help")) {
          msg.msgType = MessageType.MSG_HELP;
          break;
        }

        if (text.equals("/shu") || text.equals("/showusers")) {
          msg.msgType = MessageType.MSG_SHOW_USERS;
          break;
        }

        if (text.equals("/d") || text.equals("/disconnect")) {
          msg.msgType = MessageType.MSG_DISCONNECT;
          break;
        }

        if (text.length() >= 12 && text.substring(0, 9).equals("/announce")) {
          msg.msgType = MessageType.MSG_ANNOUNCE;
          msg.buffer = text.substring(11);
          break;
        }

        if (text.length() >= 4 && text.substring(0, 2).equals("/a")) {
          msg.msgType = MessageType.MSG_ANNOUNCE;
          msg.buffer = text.substring(3);
          break;
        }

        if (text.length() >= 7 && text.substring(0, 5).equals("/chat")) {
          msg.msgType = MessageType.MSG_CHAT;
          msg.buffer = text.substring(6);
          break;
        }

        msg.msgType = MessageType.MSG_INVALID_COMMAND;
      } while(false);
    }
    else {
      msg.msgType = MessageType.MSG_TEXT;
      msg.buffer = text;
    }
    return msg;
  }
}
