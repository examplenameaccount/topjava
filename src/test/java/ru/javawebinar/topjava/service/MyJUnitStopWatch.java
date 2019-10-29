package ru.javawebinar.topjava.service;

import java.util.concurrent.TimeUnit;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

public class MyJUnitStopWatch extends Stopwatch {
    private static void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        String report = String.format("%-15s%-10s%-6s%-6d",
                testName, status, "spent", TimeUnit.NANOSECONDS.toMicros(nanos));
        MealServiceTest.log.info(report);
        MealServiceTest.reports.add(report);
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        logInfo(description, "succeeded", nanos);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        logInfo(description, "failed", nanos);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        logInfo(description, "skipped", nanos);
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "finished", nanos);
    }
}