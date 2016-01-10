import static org.junit.Assert.*;

public class PeselTest {

    Pesel pesel = new Pesel("85122354789");

    @org.junit.Test
    public void testGetYear() throws Exception {
        assertEquals(85, pesel.getYear());
    }

    @org.junit.Test
    public void testPeselIsCorrect() throws Exception {
        assertEquals(true, pesel.peselIsCorrect());
    }

    @org.junit.Test
    public void testAgeCaculator() {
        assertEquals(31, pesel.ageCalculator());
    }

    @org.junit.Test
    public void testPeselLength() {
        assertEquals(11, pesel.wholePesel.length());
    }
}