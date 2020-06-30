/**
 * A simple program to test the clock object. Modify this to test your changes.
 *
 * @author Ian McCamey, Hao Chen Xing
 * @version 8/26/2019
 *
 */
public class TestClock {

    public static void main(String[] args) {
        Clock myClock = new Clock();

        int i = 0;
        while (i < 120) {
            System.out.println("The current time is: " + myClock.toString());
            myClock.tick();
            i = i + 1;
        }
    }

}
