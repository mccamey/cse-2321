
public class TestClock2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Clock myClock = new Clock(4, 5, 45, false);
		
		int i = 0;
        while (i < 120) {
            System.out.println("The current time is: " + myClock.toString());
            myClock.tick();
            i = i + 1;
        }
	}

}
