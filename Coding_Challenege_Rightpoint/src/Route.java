
public class Route {
	String destination;
	String date;
	String directions;
	
	// Constructor
	Route(){
		this.date ="";
		this.destination="";
		this.directions="";
	}
	public void setDate(String s) {
		// set the date 
		this.date =s;
	}
	public void setDestination(String s) {
		// set the destination 
		this.destination =s;
	}
	public void setDirections(String s) {
		// set the directions 
		this.directions =s;
	}
	public String toString() {
		return this.date + ";" + this.destination + "; " + this.directions;
	}
}
