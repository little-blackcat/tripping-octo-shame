package jdrop;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class FileDrop {
    static final String ACCESS_TOKEN = "TBH3QlG7gP0AAAAAAAAACiGuSoQBr-6vKUVIhVUr3C_bgZelnEmNpy5VoX9ATTZ1";
    private DbxClientV2 client;

    private AtomicInteger stats = new AtomicInteger(0);

    public FileDrop() {
        DbxRequestConfig config = new DbxRequestConfig("jdrop_pt", Locale.getDefault().toString());
        this.client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    public void send(String path, Path filename) throws IOException, DbxException {
        // stats
        this.stats.incrementAndGet();

        System.out.println("[+] " + filename.toString());
        // send file
        DbxFiles.Metadata metadata;
        try {
            InputStream in = new FileInputStream(path+'/'+filename.toString());
            try {
                metadata = this.client.files.uploadBuilder("/"+filename).run(in);
            } finally {
                in.close();
            }
        } catch (DbxException ex) {
            System.out.println("Error uploading to Dropbox: " + ex.getMessage());
        }
        catch (IOException ex) {
            System.out.println("Error reading from file \"" + path + "\": " + ex.getMessage());
        }
    }

    public int getStats() {
        return this.stats.getAndSet(0);
    }

}
