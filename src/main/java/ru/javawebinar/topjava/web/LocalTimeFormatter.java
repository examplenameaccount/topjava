package ru.javawebinar.topjava.web;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalTime;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {

    @Override
    public LocalTime parse(String text, Locale locale) {
        return DateTimeUtil.parseLocalTime(text);
    }

    @Override
    public String print(LocalTime time, Locale locale) {
        return time != null ? time.toString() : "";
    }
}
