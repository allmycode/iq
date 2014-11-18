package sl;
// 1
// 5
// 5
// 10
import java.util.*;

public class Candles {
    public static void main(String[] args) {
        int[] candles = new int[] {2,2,2,2,2};
        System.out.println(days(candles));
    }

    public static int days(int[] candles) {
        int day = 0;
        int burned = 0;
        final int len = candles.length;
        while (len - burned >= day + 1) {
            Arrays.sort(candles);
            final int was = burned;
            for (int i = len-1; i >= len-day-1; i--) {
                if (--candles[i] == 0) {
                    burned++;
                }
            }
            System.out.print("Day: " + day + " burned: " + (burned-was) + " candles: ");
            print(candles);
            System.out.println();
            
            day++;
        }
        return day;
       
    }

    public static void print(int[] candles) {
        for (int i = 0; i < candles.length; i++) {
            System.out.print(candles[i] + ", ");
        }
    }

}
