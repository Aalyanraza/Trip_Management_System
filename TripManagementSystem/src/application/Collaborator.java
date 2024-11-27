package application;

public class Collaborator {
    private int collaboratorId;
    private int userId;
    private int itineraryId;
    private String role;

    public Collaborator(int collaboratorId, int userId, int itineraryId, String role) {
        this.collaboratorId = collaboratorId;
        this.userId = userId;
        this.itineraryId = itineraryId;
        this.role = role;
    }

    public int getCollaboratorId() { return collaboratorId; }
    public int getUserId() { return userId; }
    public int getItineraryId() { return itineraryId; }
    public String getRole() { return role; }
}
