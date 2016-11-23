package socketchat.dto;

import java.time.LocalDateTime;


public class Message {

    private String owner;
    private String message;
    private LocalDateTime localDateTime;

    public Message(String owner, String message) {
        this.owner = owner;
        this.message = message;
        localDateTime = LocalDateTime.now();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
//        return "Message{" +
//                "owner='" + owner + '\'' +
//                ", message='" + message + '\'' +
//                ", localDateTime=" + localDateTime +
//                '}';

        return message + '\'' +
                ", localDateTime=" + localDateTime;
    }
}
