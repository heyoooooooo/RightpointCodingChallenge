import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Robot {
	// List to hold the routes to the destinations
	static List<Route> routes = new ArrayList<Route>(); 
	
	public static void main(String[] args) {
		// Ask the user to enter input to be computed 
		System.out.println("Enter the date, destination, and directions seperated by commas. Follow the example format exactly.");
		System.out.println("ex. 'YYYY-MM-DD; Destination name; Dx, Dx'");
		System.out.println("x = number of blocks, D = direction. you can have any amount of directions, which are seperated by commas.");
		// Create a Scanner object
		Scanner myScan = new Scanner(System.in);
		// input the list of directions 
	    String input = myScan.nextLine();	    
		// call the store method which will call the compute method
		// to compute the most direct route
		store(input);
		//System.out.println("Would you like to do another? 0=yes 1=no");
		//if (myScan.nextLine() == "0")
	    	// close the input scanner
			myScan.close();
			// Close the program
			System.exit(0);

	}
	// Create a new route from the input and run it through the compute method to give it the most efficient route
	public static void store(String s) {
		// create a route object using the Compute method
		Route route = new Route();
		// Split the input strings by semicolons to set the route date/destination/directions
		String[] test = s.split(";");
		// Set Date
		route.setDate(test[0]);
		// Set the destination
		route.setDestination(test[1]);
		// Set the directions
		route.setDirections(test[2]);
		// Use compute method to find efficient route
		compute(route);
		// Once the route is updated by the compute method, store route in the routes list
		routes.add(route);
		// Print out the updated route
		System.out.println(route);
		System.out.println("This route was succesfully stored in the list of known routes");
	}
	// take in the original directions and create the most efficient
	public static void compute(Route r) {
		String finalRoute="";
		// These ints will be used to calculate the final route
		int n =0, e=0, s=0, w =0;
		// This string will be used to hold the current direction that the robot is facing
		String currentDir = "w";
		// Create a string to hold the directions
		String directions = r.directions;
		// Split the directions into each individual direction
		String[] dirs = directions.split(",");
		// Add to the appropriate int (n,e,s,w) the amount of blocks that the robot travels per each direction
		for (String curr : dirs) {
			//System.out.println("Init Direction: " + currentDir);
			// pull out the direction and number of blocks for each direction
			char dir = curr.charAt(1);
			String numb = "" + curr.charAt(2);
			//System.out.println(dir);
			int num = Integer.parseInt(numb);
			//System.out.println(num);
			// if curDir is west treat the direction this way
			if (currentDir == "w") {
				if (dir == 'L') {
					s+=num;
					currentDir = "s";
				}
				else {
					n+=num;
					currentDir = "n";
				}
			}
			// if curDir is North treat the direction this way
			else if (currentDir == "n") {
				if (dir == 'L') {
					w+=num;
					currentDir = "w";
				}
				else {
					e+=num;
					currentDir = "e";
				}
			} 
			// if curDir is East treat the direction this way
			else if (currentDir == "e") {
				if (dir == 'L') {
					n+=num;
					currentDir = "n";
				}
				else {
					s+=num;
					currentDir = "s";
				}
			}
			// if curDir is South treat the direction this way
			else if (currentDir == "s") {
				if (dir == 'L') {
					e+=num;
					currentDir = "e";
				}
				else {
					w+=num;
					currentDir = "w";
				}
			} 
			/*		Print lines for testing to make sure that it is computing correctly
			System.out.println("New Direction: " + currentDir);
			System.out.println("North: " + n);
			System.out.println("South: " + s);
			System.out.println("East: " + e);
			System.out.println("West: " + w);
			*/
		}
		// Now we are going to do some math to get the minimum two directions necessary to get to any destination
		// dir1 and dir2 are used to hold the final two directions
		String dir1;
		String dir2;
		// figure out the distance and direction in y
		if ((n-s)>=0)
			dir1="R"+Math.abs(n-s);
		else 
			dir1="L"+Math.abs(n-s);
		// figure out the distance and direction in x
		if ((e-w)>=0)
			dir2="R"+Math.abs(w-e);
		else 
			dir2="L"+Math.abs(w-e);
		
		finalRoute = dir1 + "," + dir2;
		// now set the final directions for the route 
		r.setDirections(finalRoute);
	}
}







