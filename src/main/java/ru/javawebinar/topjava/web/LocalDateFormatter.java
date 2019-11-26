package ru.javawebinar.topjava.web;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) {
        return text.length() != 0 ? LocalDate.parse(text) : null;
    }

    @Override
    public String print(LocalDate date, Locale locale) {
        return date != null ? date.toString() : "";
    }
}
