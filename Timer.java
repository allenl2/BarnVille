
public class Timer {

	private int currentTime;
	private int maxTime;
	private String[] listMonths= {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
			"Jul", "Aug", "Sept", "Oct", "Nov", "Dec"}; 

	public Timer()
	{
		currentTime = 0;
		maxTime = 24;
	} 

	//this method checks to see if the timer has reached the max number of months
	public boolean isOver() {
		if (currentTime >= maxTime)
		{
			return true;
		}
		return false;
	}

	//this method returns the current time, in the format (month, year) as a string
	public String getCurrentTimeString() {
		String result = listMonths[currentTime%12] + "\nYear ";
		result += (int)Math.floor(currentTime/12) + 1 ;

		return result;
	}

	//this method returns the current time as an int
	public int getCurrentTime() {
		return currentTime;
	}

	//increases the current month by one
	public void increaseCurrentTime() {
		this.currentTime++;
	}

	public int getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}



}
