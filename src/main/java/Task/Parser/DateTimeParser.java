package Task.Parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import Exception.ZukeException;

public class DateTimeParser {
    private LocalDateTime parsedDateTime;
    private String MemoryDateTime;

    //for date searcher
    public LocalDate getParsedDate() {
        return parsedDateTime.toLocalDate();
    }

    public LocalDateTime getParsedDateTime() {
        return parsedDateTime;
    }

    public DateTimeParser(String dateTime) throws ZukeException.MissingTimeException {
        parsedDateTime = ParseDateTime(dateTime);
        MemoryDateTime = dateTime;
    }



    public LocalDateTime ParseDateTime(String dateTime) throws IllegalArgumentException, ZukeException.MissingTimeException {
        String s = dateTime.trim();

        if(s.isEmpty()) {
            throw new ZukeException.MissingTimeException();
        }

        DateTimeFormatter YMD = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter DMY = DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT);
        DateTimeFormatter HM = DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);

        String[] parts = s.split("\\s+");  // [date] [time?]
        if (parts.length > 2) {
            throw new IllegalArgumentException("Too many parts after /by. Use exactly: <date> or <date> <time>");
        }

        LocalDate date;
        LocalTime time = LocalTime.of(23, 59);;
        try {

            if (parts[0].contains("/")) {
                date = LocalDate.parse(parts[0], DMY);
            } else {
                date = LocalDate.parse(parts[0], YMD); // stricter than default
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

    public String toString() {
        return parsedDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma"));
    }

    public String toMemoryDateTime() {
        return MemoryDateTime;
    }
}
