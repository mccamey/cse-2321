import java.util.ArrayList;

/**
 * A simple class used to demonstrate the implemenation of Java classes.
 *
 * @author Ian McCamey, Hao Chen Xing
 * @version 8/26/2019
 */

public class Clock {
    private int hour;
    private int minute;
    private int second;
    private boolean am;
    private int day;
    private int month;
    private int year;

    /**
     * Simple no-argument constructor
     */
    public Clock() {
        this.hour = 1;
        this.minute = 0;
        this.second = 0;
        this.am = true;
        this.day = 1;
        this.month = 1;
        this.year = 2000;
    }
    /**
     * Constructor with time arguments
     * @param hour
     * @param minute
     * @param second
     * @param am
     */
    public Clock (int hour, int minute, int second, boolean am) {
    	if(setTime(hour, minute, second, am)) {
    		this.hour = hour;
    		this.minute = minute;
    		this.second = second;
    		this.am = am;
    		this.day = 1;
            this.month = 1;
            this.year = 2000;
    	}
    }
     /**
      * Constructor with date arguments
      * @param day
      * @param month
      * @param year
      * @param hour
      */
    public Clock (int day, int month, int year) {
    	this.hour = 1;
    	this.minute = 0;
    	this.second = 0;
    	this.am = true;
    	this.day = day;
    	this.month = month;
    	this.year = year;
    }

    /**
     * Returns a true/false about whether the set time is valid
     */
    public boolean setTime(int hour, int minute, int second, boolean am) {
    	if(hour > 12 || minute > 59 || second > 59) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    /**
     * Returns a true/false about whether the set date is valid
     * @param year
     * @param month
     * @param day
     * @return
     */
    public boolean setDate(int year, int month, int day) {
    	return true;
    }

    /**
     * @return the current hour
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * @return the current minute value
     */
    public int getMinute() {
        return this.minute;
    }

    /**
     * @return the current second value
     */
    public int getSecond() {
        return this.second;
    }

    /**
     * @return true if the clock is showing an AM time, false otherwise
     */
    public boolean getAM() {
        return this.am;
    }

    /**
     * Advances the time stored in the clock by 1 second
     */
    public void tick() {
    	this.second += 1;
    	if(this.second > 59) {
    		this.second = 0;
    		this.minute += 1;
    	}
    	if(this.minute > 59) {
    		this.minute = 0;
    		this.hour += 1;
    	}
    	if(this.hour > 12) {
    		this.hour = 1;
    	}
    	if(this.hour == 12 && this.minute == 0 && this.second == 0) {
    		this.am = !this.am;
    		if(this.am) {
    			this.day += 1;
    		}
    	}
    	if(this.day > monthDays(this.month)) {
    		if(this.month == 12 ) {
    			this.day = 1;
    			this.month = 1;
    			this.year += 1;
    		}else {
    			this.day = 1;
    			this.month += 1;
    		}
    	}
    }
    
    public int monthDays(int month) {
    	switch(month) {
    		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
    			return 31;
    		case 4: case 6: case 9: case 11:
    			return 30;
    		default:
    			return 28;
    	}
    }
    
    
    /**
     *
     * @return the current time and date as a String in YYYY-MM-DD hh:mm:ssAM format
     */
    @Override
    public String toString() {
    	String result = "";
        result += this.year + "-";
        if(this.month < 10) {
        	result += "0" + this.month + "-";
    	}else {
    		result += this.month + "-";
    	}
    	if(this.day < 10) {
    		result += "0" + this.day + " ";
    	}else {
    		result += this.day + " ";
    	}
    	if(this.hour < 10) {
        	result += "0" + this.hour + ":";
        }else {
        	result += this.hour + ":";
        }
        if(this.minute < 10) {
        	result += "0" + this.minute + ":";
        }else {
        	result += this.minute + ":";
        }
        if(this.second < 10) {
        	result += "0" + this.second;
       	}else {
        	result += this.second;
       	}
        if(this.am) {
        	result += "AM";
        }else {
        	result += "PM";
        }
        return result;
    }
     
    /**
      * @return military time in string
      */
    public String militaryString() {
    	String result = "";
    	if(this.am) {
    		if(this.hour < 10) {
    			result = "0" + this.hour;
    		}
    		else {
    			result += this.hour;
    		}
    	}else {
    		result += (this.hour + 12);
    	}
    	if(this.minute < 10) {
    		result += "0" + this.minute;
    	}
    	else {
    		result += this.minute;
    	}
    	return result;
    }
    
    /**
     * @return military time in integer
     */
    public int militaryInt() {
    	String militaryString = "";
    	militaryString += this.hour;
    	if(this.minute < 10){
    		militaryString += 0;
    	}
    	militaryString += this.minute;
    	int result = Integer.parseInt(militaryString);
    	return result;
    }
}
