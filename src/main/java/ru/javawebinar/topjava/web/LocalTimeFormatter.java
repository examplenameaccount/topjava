package ru.javawebinar.topjava.web;

import org.springframework.format.Formatter;
import java.time.LocalTime;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {

    @Override
    public LocalTime parse(String text, Locale locale) {
        return text.length() != 0 ? LocalTime.parse(text) : null;
    }

    @Override
    public String print(LocalTime time, Locale locale) {
        return time != null ? time.toString() : "";
    }
}
