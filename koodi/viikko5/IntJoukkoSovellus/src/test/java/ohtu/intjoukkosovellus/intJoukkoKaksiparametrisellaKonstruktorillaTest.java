
package ohtu.intjoukkosovellus;

import org.junit.Before;

public class intJoukkoKaksiparametrisellaKonstruktorillaTest extends intJoukkoTest {
    
    @Before
    public void setUp() {
        joukko = new intJoukko(4, 2);
        joukko.lisaa(10);
        joukko.lisaa(3);
    }
}
