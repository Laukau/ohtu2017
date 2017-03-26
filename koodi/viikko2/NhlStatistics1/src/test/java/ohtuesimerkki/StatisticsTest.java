package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by laura on 26.3.2017.
 */
public class StatisticsTest {
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchFindsPlayer(){
        Player player = stats.search("Kurri");
        assertNotNull(player);
    }

    @Test
    public void searchReturnsNullIfPlayerNotInList(){
        Player player = stats.search("Litmanen");
        assertNull(player);
    }

    @Test
    public void teamFindsPlayers(){
        assertFalse(stats.team("DET").isEmpty());
    }

    @Test
    public void topScorersFindsPlayer(){
        assertFalse(stats.topScorers(1).isEmpty());
    }
}
