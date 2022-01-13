
public class Point {
 
	/* Zeynep Özarslan, 041802044, 13 November 2020 */
	/* This is a 2D point class */
	
	//data fields
	double x;
	double y;
	
	// constructor with input parameters
	public Point (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	// class method to print coordinates
	public String toString () {
	   return "(" + x + "," + y + ")";
}
}