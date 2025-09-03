public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        setFrom(from);
        setTo(to);
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String marker(){
        String type = "[E]";
        return done ? type + "[X]" : type + "[ ]";
    }

    public String toString() {
        return marker() + " " + description + " (" + "from: " + from + " to: " + to + ")";
    }

}