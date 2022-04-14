package webapp.Suggestion;

public class Suggestion {
    private String message;
    private int priority;

    public Suggestion() {
        this.priority = -1;
    }

    public Suggestion(String message, int priority) {
        this.message = message;
        this.priority = priority;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
