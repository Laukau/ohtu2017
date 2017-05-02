
package ohtu.intjoukkosovellus;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

public class JoukkoOperaatiotTest {
    
    
    @Test
    public void testSomething() {
        intJoukko eka = teeJoukko(1,2);
        intJoukko toka = teeJoukko(3,4);
        
        intJoukko tulos = intJoukko.yhdiste(eka, toka);
        int[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);
        
        int[] odotettu = {1,2,3,4};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    } 

    private intJoukko teeJoukko(int... luvut) {
        intJoukko joukko = new intJoukko();
        
        for (int luku : luvut) {
            joukko.lisaa(luku);
        }
        
        return joukko;
    }
}
