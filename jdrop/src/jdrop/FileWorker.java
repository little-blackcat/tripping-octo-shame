package jdrop;

import java.nio.file.Path;
import java.util.concurrent.Callable;

public class FileWorker implements Callable {
    private FileDrop fileDrop;
    private String path;
    private Path filename;

    public FileWorker(String path, Path filename, FileDrop fileDrop) {
        this.path = path;
        this.filename = filename;
        this.fileDrop = fileDrop;
    }

    @Override
    public Object call() throws Exception {
        fileDrop.send(this.path, this.filename);
        return null;
    }
}
