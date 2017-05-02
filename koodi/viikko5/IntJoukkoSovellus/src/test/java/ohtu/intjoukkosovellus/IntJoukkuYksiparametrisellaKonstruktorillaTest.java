
package ohtu.intjoukkosovellus;

import org.junit.Before;


public class IntJoukkuYksiparametrisellaKonstruktorillaTest extends intJoukkoTest {
    
    @Before
    @Override
    public void setUp() {
        joukko = new intJoukko(3);
        joukko.lisaa(10);
        joukko.lisaa(3);
    }
    
    // perii kaikki testit luokasta intJoukkoTest
}
