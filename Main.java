import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		
		/* Zeynep Özarslan, 041802044, 13 November 2020 */
		/* This program draws the smallest convex polygon that encloses random points */

		Random input = new Random();
		// an arraylist to add random points
		ArrayList<Point> S = new ArrayList<>();
		int n = 500; //n number of points
		int i = 0;
		while (i < n) {
			// creates points with random coordinates
			Point randomPoint = new Point(input.nextDouble(), input.nextDouble());
			// sets the color and size of points
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.setPenRadius(0.015);
			StdDraw.setScale(0,1);
			StdDraw.point(randomPoint.x, randomPoint.y);
			S.add(randomPoint);
			i++;
		}

		for (int j = 0; j < S.size(); j++) {
			System.out.println("Point Coordinate: " + S.get(j).toString());
		}

		// prints lowest point coordinates to test method
		System.out.println("Lowest Point Coordinates: " + lowestPoint(S));
		// prints S's first point's angle according to the x-axis with lowest point as center
		System.out.println("Angle: " + angleBetweenPoints(S.get(0), S));

		sortPoints(S);

		// prints angularly sorted points
		System.out.println("Sorted points coordinates: ");
		for (Point counter : S) {
			System.out.println(counter);
		}

		// creates a stack that will contain all the points in the convex hull
		Stack<Point> H = new Stack<>();
		H.push(S.get(0));
		H.push(S.get(1));
		H.push(S.get(2));

		// checks if the point to be inserted is to the left of the line
		// formed by the top two points in H
		int a = 3;
		while (a < S.size()) {
			double b = (H.peek().x - peekSecond(H).x) * (S.get(a).y - (peekSecond(H).y))
					- (S.get(a).x - (peekSecond(H).x)) * (H.peek().y - (peekSecond(H).y));
			if (b > 0) {
				H.push(S.get(a));
				a++;
			} else {
				H.pop();
			}
		}

		// points' x and y coordinates in H are assigned to arrays
		// in order to draw polygon
		double[] xArray = new double[H.size()];
		double[] yArray = new double[H.size()];
		Stack<Point> copiedH = (Stack<Point>) H.clone();
		for (int k = H.size() - 1; k >= 0; k--) {
			xArray[k] = H.pop().x;
			yArray[k] = copiedH.pop().y;
		}

		// draws polygon with the points in H
		StdDraw.setPenRadius(0.003);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.polygon(xArray, yArray);

	}

	// a method to find lowest point by traversing array list
	public static Point lowestPoint(ArrayList<Point> S) {
		Point p0 = S.get(0);
		for (int i = 0; i < S.size(); i++) {
			if (p0.y > S.get(i).y)
				p0 = S.get(i);
			if (p0.y == S.get(i).y && p0.x < S.get(i).x)
				p0 = S.get(i);
		}
		return p0;
	}

	// a method to calculate angle according to the x-axis with lowest point as center
	public static double angleBetweenPoints(Point point2, ArrayList<Point> A) {
		Point point1 = lowestPoint(A);
		double angle = Math.atan2((point2.y - point1.y), (point2.x - point1.x)) * 180 / Math.PI;
		if (angle < 0) {
			return (angle + 360);
		} else {
			return (angle);
		}
	}
 
	// a method to sort points angularly along the x-axis with lowest point as center
	public static void sortPoints(ArrayList<Point> B) {
		Point tempPoint;
		for (int i = 0; i < B.size() - 1; i++) {
			double currentM = angleBetweenPoints(B.get(i), B);
			int currentMIndex = i;
			for (int j = i + 1; j < B.size(); j++) {
				if (currentM > angleBetweenPoints(B.get(j), B)) {
					currentM = angleBetweenPoints(B.get(j), B);
					currentMIndex = j;
				}
			}
			// swap points if necessary
			if (currentMIndex != i) {
				tempPoint = B.get(currentMIndex);
				B.set(i, B.set(currentMIndex, B.get(i)));
				B.set(i, tempPoint);
			}
		}
	}

	// a method to use the top second element in the stack
	public static Point peekSecond(Stack<Point> A) {
		// created a copied stack to not be delete top element from the original stack.
		Stack<Point> copiedStack = (Stack<Point>) A.clone();
		if (copiedStack.size() != 0) {
			copiedStack.pop();
			return copiedStack.peek();
		}
		return copiedStack.peek();
	}
}
