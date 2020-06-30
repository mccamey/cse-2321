
public abstract class TestClock5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Clock myClock = new Clock(11, 7, 1997);
		int i = 0;
        while (i < 120) {
            System.out.println("The current time is: " + myClock.toString());
            myClock.tick();
            i = i + 1;
        }
	}

}
