package com.example.demo.Services;

import java.io.*;
import java.util.concurrent.atomic.AtomicLong;

public class UniqueId {
    private static final String COUNTER_FILE = "counter.txt";
    private static final AtomicLong counter = new AtomicLong(loadCounter());

    public static synchronized Long generateUniqueId() {
        long id = counter.incrementAndGet();
        saveCounter(id);
        return id;
    }

    private static long loadCounter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(COUNTER_FILE))) {
            return Long.parseLong(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0L; 
        }
    }

    private static void saveCounter(long value) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COUNTER_FILE))) {

            writer.write(Long.toString(value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
