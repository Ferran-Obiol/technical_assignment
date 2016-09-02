package main;


/*This is the class to represent the different talks
 * It has a title and the duration of the talk (which is included inside the title,
 * but is taken apart for comodity) */


public class Talk {
	
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void setDuration(String duration) {
		if (duration.equals("lightning")) this.duration = 5;
		else if (duration.contains("min")) //recollim duració
		{
			this.duration = Integer.parseInt( duration.replace("min", "" ));
		}
		else ;
	}
	
	
	String title;
	int duration;
	
}


