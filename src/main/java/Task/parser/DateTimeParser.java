package task.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import exception.ZukeException;

/**
 * Parses date and time strings into LocalDateTime objects.
 * Supports multiple date formats: yyyy-MM-dd and d/M/yyyy.
 * Supports time formats: HHmm and HH:mm.
 */
public class DateTimeParser {
    private LocalDateTime parsedDateTime;
    private String memoryDateTime;

    /**
     * Gets the date portion of the parsed date/time.
     *
     * @return The LocalDate extracted from the parsed date/time.
     */
    public LocalDate getParsedDate() {
        return parsedDateTime.toLocalDate();
    }

    /**
     * Gets the complete parsed date and time.
     *
     * @return The parsed LocalDateTime object.
     */
    public LocalDateTime getParsedDateTime() {
        return parsedDateTime;
    }

    /**
     * Creates a DateTimeParser and parses the given date/time string.
     *
     * @param dateTime The date/time string to parse.
     * @throws ZukeException.MissingTimeException If the date/time string is empty.
     */
    public DateTimeParser(String dateTime) throws ZukeException.MissingTimeException {
        parsedDateTime = parseDateTime(dateTime);
        memoryDateTime = dateTime;
    }

    /**
     * Parses a date/time string into a LocalDateTime object.
     * Accepts formats: yyyy-MM-dd [HHmm|HH:mm] or d/M/yyyy [HHmm|HH:mm].
     * If no time is provided, defaults to 23:59.
     *
     * @param dateTime The date/time string to parse.
     * @return The parsed LocalDateTime.
     * @throws IllegalArgumentException If the date/time format is invalid.
     * @throws ZukeException.MissingTimeException If the date/time string is empty.
     */
    public LocalDateTime parseDateTime(String dateTime) throws IllegalArgumentException, ZukeException.MissingTimeException {
        String s = dateTime.trim();

        if(s.isEmpty()) {
            throw new ZukeException.MissingTimeException();
        }

        DateTimeFormatter yearMonthDayFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter dayMonthYearFormatter = DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter HourMinute = DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);

        String[] parts = s.split("\\s+");  // [date] [time?]
        if (parts.length > 2) {
            throw new IllegalArgumentException("Too many parts after /by. Use exactly: <date> or <date> <time>");
        }

        LocalDate date;
        LocalTime time = LocalTime.of(23, 59);;
        try {

            if (parts[0].contains("/")) {
                date = LocalDate.parse(parts[0], dayMonthYearFormatter);
            } else {
                date = LocalDate.parse(parts[0], yearMonthDayFormatter); // stricter than default
            }

            if (parts.length > 1) {
                String t = parts[1];
                if (t.matches("\\d{4}")) {            // e.g., 1800
                    int hh = Integer.parseInt(t.substring(0, 2));
                    int mm = Integer.parseInt(t.substring(2, 4));
                    time = LocalTime.of(hh, mm);
                } else {                               // e.g., 18:00
                    time = LocalTime.parse(t);         // ISO HH:mm works
                }
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date/time. Use yyyy-MM-dd [HHmm|HH:mm] or d/M/yyyy [HHmm|HH:mm].", e);

        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Invalid time values. Hours 00–23, minutes 00–59.", e);
        }


        return LocalDateTime.of(date, time);
    }

    /**
     * Returns a formatted string representation of the date/time.
     *
     * @return The date/time formatted as "MMM dd yyyy, h:mma".
     */
    public String toString() {
        return parsedDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }

    /**
     * Returns the original date/time string for storage purposes.
     *
     * @return The original date/time string.
     */
    public String toMemoryDateTime() {
        return memoryDateTime;
    }
}
