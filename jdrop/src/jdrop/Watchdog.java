package jdrop;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.*;

public  class Watchdog implements Runnable{

    private String localPath;
    private WatchService watcher = FileSystems.getDefault().newWatchService();
    private ExecutorService pool;
    private FileDrop fileDrop;

    public Watchdog(String path, FileDrop fileDrop, int threadAmount) throws IOException {
        this.localPath = path;
        this.fileDrop = fileDrop;
        this.pool = Executors.newFixedThreadPool(threadAmount);

        try {
            Path watch = Paths.get(localPath);
            WatchKey key = watch.register(watcher, ENTRY_CREATE); // only new files
        } catch(IOException x) {
            System.err.println(x);
        }

        InitialUpload();
    }

    public void InitialUpload() throws IOException {
        Files.walk(Paths.get(this.localPath)).forEach(filePath -> {
            if(!filePath.toString().replace('\\', '/').equals(this.localPath))
                send(this.localPath, filePath.getFileName());
        });
    }

    @Override
    public void run() {
        for(;;) {
            WatchKey wk;
            try {
                wk = watcher.take();
            } catch(InterruptedException x) {
                return;
            }

            for(WatchEvent<?> event : wk.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind(); // kind of event

                if(kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path filename = ev.context();

                this.send(localPath, filename);
            }

            boolean valid = wk.reset();
            if(!valid) {
                break;
            }
        }
    }

    public void send(String path, Path filename) {
        this.pool.submit(new FileWorker(path, filename, fileDrop));
    }

}