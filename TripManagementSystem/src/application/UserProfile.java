package application;

public class UserProfile {
    private String name;
    private String email;
    private String preferences;

    public UserProfile(String name, String email, String preferences) {
        this.name = name;
        this.email = email;
        this.preferences = preferences;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
}
