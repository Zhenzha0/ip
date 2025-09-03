public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        setDeadline(deadline);
    }

    public String getDate() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String marker(){
        String type = "[D]";
        return done ? type + "[X]" : type + "[ ]";
    }

    public String toString() {
        return marker() + " " + description + " (" + "by: " + deadline + ")" ;
    }

}
