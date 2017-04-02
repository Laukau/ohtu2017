import java.util.*;
import ohtu.Multiplier;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Multiplier kolme = new Multiplier(2);
        System.out.println("anna luku ");
        int luku = scanner.nextInt();
        /* rikkoo checkstyle-säännön
        for( int i=0; i<1; i++ ) {
            for( int j=0; i<j; j++ ) {
              System.out.println("virhe");
            }
          }
        */
        System.out.println("luku kertaa kolme on "+kolme.multipliedBy(luku) );
    }
}
