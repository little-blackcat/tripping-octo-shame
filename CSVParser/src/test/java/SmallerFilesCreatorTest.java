import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Paula on 11.01.2016.
 */
public class SmallerFilesCreatorTest {

    ArrayList<Person> al = new ArrayList<Person>();
    SmallerFilesCreator sfc = new SmallerFilesCreator(100, al, "aaa");

    @org.junit.Test
    public void filenameIsCorrect() throws Exception {
        assertEquals("aaa", sfc.nameOfFiles);
    }


}