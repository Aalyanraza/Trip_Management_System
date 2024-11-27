package application;

public class TripChat {
    private int messageId;
    private int userId;
    private int itineraryId;
    private String message;

    public TripChat(int messageId, int userId, int itineraryId, String message) {
        this.messageId = messageId;
        this.userId = userId;
        this.itineraryId = itineraryId;
        this.message = message;
    }

    public int getMessageId() { return messageId; }
    public int getUserId() { return userId; }
    public int getItineraryId() { return itineraryId; }
    public String getMessage() { return message; }
}
