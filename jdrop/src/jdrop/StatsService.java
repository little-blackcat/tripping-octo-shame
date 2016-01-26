package jdrop;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatsService implements Runnable {

    private FileDrop fileDrop;
    private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    private Date dNow;
    private PrintWriter pw;

    public StatsService(FileDrop fileDrop, String statsFile) throws FileNotFoundException {
        this.fileDrop = fileDrop;
        pw = new PrintWriter(statsFile);
    }

    public int getStats(){
        return fileDrop.getStats()/10;
    }

    @Override
    public void run() {
        for(;;) {
            dNow = new Date();
            pw.println("Godzina: " + df.format(dNow) + " | " + this.fileDrop.getStats() + " plikow/s.");
            pw.flush();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}