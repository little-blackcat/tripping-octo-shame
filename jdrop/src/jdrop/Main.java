package jdrop;

import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        ConfigService config = new ConfigService("config.ini");
        String pathToSync = config.getProp("pathToSync");
        int threadAmount = Integer.parseInt(config.getProp("threads"));
        String statsFile = config.getProp("statsFile");

        File f = new File(pathToSync);
        if(!f.isDirectory()) {
            System.out.println(pathToSync + " - nie jest katalogiem.\nWprowadz poprawna sciezke katalogu do synchronizacji.");
            return;
        }

        FileDrop fileDrop = new FileDrop();

        Runnable watchdogRunnable = new Watchdog(pathToSync, fileDrop, threadAmount);
        Thread watchdogThread = new Thread(watchdogRunnable);
        watchdogThread.start();

        Runnable statsRunnable = new StatsService(fileDrop, statsFile);
        Thread statsThread = new Thread(statsRunnable);
        statsThread.start();

    }

}
