package entity;

/**
 *
 * @author Quoc_anh
 */
public class Feedback{
    private String title;
    private String connent;
    private String username;

    public Feedback() {
    }

    public Feedback(String title, String connent, String username) {
        this.title = title;
        this.connent = connent;
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConnent() {
        return connent;
    }

    public void setConnent(String connent) {
        this.connent = connent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    
}

