import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Paula on 11.01.2016.
 */
public class DuplicatesRemoverTest {

    @Test
    public void testRemoveDuplicates() throws Exception {
        ArrayList<Person> al = new ArrayList<Person>();
        al.add(new Person(new Pesel("85122354789"), "Ania", "Kowalska"));
        al.add(new Person(new Pesel("85122354789"), "Ania", "Kowalska"));
        al.add(new Person(new Pesel("85122354789"), "Ania", "Kowalska"));

        DuplicatesRemover dr = new DuplicatesRemover();
        ArrayList<Person> al2 = dr.removeDuplicates(al);

        assertEquals(1, al2.size());
    }
}