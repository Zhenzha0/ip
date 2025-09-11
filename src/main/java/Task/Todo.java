package Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String marker(){
        String type = "[T]";
        return done ? type + "[X]" : type + "[ ]";
    }

}
