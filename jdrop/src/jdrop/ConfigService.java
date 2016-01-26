package jdrop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {
    private Properties prop = new Properties();

    public ConfigService(String path) throws IOException {
        try (FileInputStream in = new FileInputStream(path)) {
            prop.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProp(String p) {
        return prop.getProperty(p, "");
    }
}