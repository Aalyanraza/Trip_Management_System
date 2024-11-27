package application;

public class Feedback {
    private int feedbackId;
    private int userId;
    private String message;
    private int rating; // Added field for rating
    private String comments;

    // Existing constructor
    public Feedback(int rating, String comments) {
        this.rating = rating;
        this.comments = comments;
    }

    // New constructor for database compatibility
    public Feedback(int feedbackId, int userId, String message) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.message = message;
    }

    // Getters and setters for new fields
    public int getFeedbackId() {
        return feedbackId;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public void viewFeedback() {
        System.out.println("Feedback - ID: " + feedbackId + ", User: " + userId + ", Message: " + message);
    }
}
