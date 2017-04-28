import java.io.Serializable;

public class Message implements Serializable {
  public enum MessageType {
    MSG_INVALID,
    MSG_USERNAME,
    MSG_INVALID_USERNAME,
    MSG_TEXT
  };
  public MessageType msgType;
  public String buffer;

  Message() {
    msgType = MessageType.MSG_INVALID;
    buffer = new String();
  }
}
