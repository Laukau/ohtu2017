package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));

        //listaa kaikki joukkueen PHI pelaajat, joilla on vähintään 5 maalia ja vähintään 10 syöttöä
        Matcher m1 = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );

        //listaa kaikki pelaajat, joilla on pisteitä vähemmän kuin 3
        Matcher m2 = new HasFewerThan(3, "points");

        //listaa kaikki pelaajat, joilla on vähintään 5 syöttöä tai 3 maalia
        Matcher m3 = new Or( new HasAtLeast(5, "assists"),
                            new HasAtLeast(3, "goals"));

        //listaa kaikki pelaajat, jotka pelaavat jossakin muussa joukkueessa kuin CAR tai TOR
        Matcher m4 = new Not( new Or( new PlaysIn("CAR"),
                                    new PlaysIn("TOR")));


        for (Player player : stats.matches(m4)) {
            System.out.println( player );
        }
    }
}
