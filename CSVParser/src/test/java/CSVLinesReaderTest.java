import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by Paula on 11.01.2016.
 */
public class CSVLinesReaderTest {

    @Test(expected = java.io.FileNotFoundException.class)
    public void badFilename() throws FileNotFoundException {
        new CSVLinesReader("aaa", "aaa");
    }
}