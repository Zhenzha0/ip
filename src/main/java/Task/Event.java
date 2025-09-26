package Task;

import Task.Parser.EventParser;
import Task.Parser.DateTimeParser;

/**
 * Represents an event task with a start and end time.
 * An event has a description and occurs during a specific time period.
 */
public class Event extends Task {

    private DateTimeParser from;
    private DateTimeParser to;

    /**
     * Creates an Event task with description and time period.
     *
     * @param description The description of the event.
     * @param from The start date/time of the event.
     * @param to The end date/time of the event.
     */
    public Event(String description, DateTimeParser from, DateTimeParser to) {
        super(description);
        setFrom(from);
        setTo(to);
    }

    /**
     * Creates an Event task from a parsed event object.
     *
     * @param parsedEvent The EventParser containing parsed event information.
     */
    public Event(EventParser parsedEvent) {
        super(parsedEvent.getDescription());
        setFrom(parsedEvent.getFrom());
        setTo(parsedEvent.getTo());
    }

    /**
     * Sets the start time of the event.
     *
     * @param from The start date/time to set.
     */
    public void setFrom(DateTimeParser from) {
        this.from = from;
    }

    /**
     * Sets the end time of the event.
     *
     * @param to The end date/time to set.
     */
    public void setTo(DateTimeParser to) {
        this.to = to;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The DateTimeParser containing the start time.
     */
    public DateTimeParser getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The DateTimeParser containing the end time.
     */
    public DateTimeParser getTo() {
        return to;
    }

    /**
     * Returns the marker string indicating task type and completion status.
     *
     * @return A string marker like "[E][X]" or "[E][ ]".
     */
    public String marker(){
        String type = "[E]";
        return done ? type + "[X]" : type + "[ ]";
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A formatted string showing the task marker, description, and time period.
     */
    public String toString() {
        return marker() + " " + description + " (" + "from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the start time in memory storage format.
     *
     * @return The start time string formatted for file storage.
     */
    public String toMemoryFrom() {
        return from.toMemoryDateTime();
    }

    /**
     * Returns the end time in memory storage format.
     *
     * @return The end time string formatted for file storage.
     */
    public String toMemoryTo() {
        return to.toMemoryDateTime();
    }

}