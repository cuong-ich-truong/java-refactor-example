package com.example.refactor.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PaymentLogger {
    public static void logMessage(String logStream, String statusMessage) {
        try {
            File historyFile = new File(logStream);
            if (!historyFile.exists()) {
                historyFile.createNewFile();
            }

            System.out.print(statusMessage);
            Files.write(Paths.get(logStream), statusMessage.getBytes(), StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
