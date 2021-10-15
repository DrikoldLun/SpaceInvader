package edu.uchicago.gerber._03objects;

import java.util.ArrayList;

public class P8_16 {
    public static void main(String[] args) {
        Message message = new Message("Lun Zhang", "David Smith");
        message.append("Hello brother!");
        Mailbox mailbox = new Mailbox();
        mailbox.addMessage(message);
        Message message1 = mailbox.getMessage(0);
        System.out.println(message1.toString());
        mailbox.removeMessage(0);
    }
}

class Mailbox {
    private ArrayList<Message> messages = new ArrayList<Message>();

    public void addMessage(Message m) {
        messages.add(m);
    }

    public Message getMessage(int i) {
        return messages.get(i);
    }

    public void removeMessage(int i) {
        messages.remove(i);
    }
}

class Message {
    private String sender;
    private String recipient;
    private String body = "";

    public Message(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public void append(String text) {
        body += text+"\n";
    }

    @Override
    public String toString() {
        return  "From: " + sender +
                "\nTo: " + recipient +
                "\n" + body;
    }
}
