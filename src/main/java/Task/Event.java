package Task;

import Task.Parser.EventParser;
import Task.Parser.DateTimeParser;

public class Event extends Task {

    private DateTimeParser from;
    private DateTimeParser to;

    public Event(String description, DateTimeParser from, DateTimeParser to) {
        super(description);
        setFrom(from);
        setTo(to);
    }

    public Event(EventParser parsedEvent) {
        super(parsedEvent.getDescription());
        setFrom(parsedEvent.getFrom());
        setTo(parsedEvent.getTo());
    }

    public void setFrom(DateTimeParser from) {
        this.from = from;
    }

    public void setTo(DateTimeParser to) {
        this.to = to;
    }

    public DateTimeParser getFrom() {
        return from;
    }

    public DateTimeParser getTo() {
        return to;
    }

    public String marker(){
        String type = "[E]";
        return done ? type + "[X]" : type + "[ ]";
    }

    public String toString() {
        return marker() + " " + description + " (" + "from: " + from + " to: " + to + ")";
    }

    public String toMemoryFrom() {
        return from.toMemoryDateTime();
    }

    public String toMemoryTo() {
        return to.toMemoryDateTime();
    }

}