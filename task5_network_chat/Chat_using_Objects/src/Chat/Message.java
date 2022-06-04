package Chat;

import Names.CommandName;
import Names.UserName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Message implements Serializable {
    private String messageData;
    private Calendar date;
    private int senderID;
    private UserName senderName;
    private MessageType messageType;

    public Message(String messageData, MessageType messageType) {
        this.messageData = messageData;
        date = new GregorianCalendar();
        this.messageType = messageType;
        senderName = null;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessageData() {
        return messageData;
    }

    public String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date.getTime());
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public UserName getSenderName() {
        return senderName;
    }

    public void setSenderName(UserName senderName) {
        this.senderName = senderName;
    }


}
