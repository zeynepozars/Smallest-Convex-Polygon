# SmallestConvexPolygon
This Java program draws the smallest convex polygon that encloses randomly generated 2-dimensional points. 
 
N number of random points were stored in an arraylist and the lowest point in the arraylist was determined. Then, the points were sorted angularly along the x-axis by
taking the lowest point as the center. The lowest three of the sorted points were inserted on the stack and other points in the arraylist were added according to their position relative to the line formed by the top 2 points in the stack. Finally, the x and y coordinates of the points in the stack have been inserted to the arrays in order to draw the polygon.

Visual output for 10 random points:

![cnvx](https://user-images.githubusercontent.com/82055290/149249794-96235ae1-b0ec-42df-99f3-95f3459fa18c.png)

Visual output for 50 random points:

![502](https://user-images.githubusercontent.com/82055290/149250196-3546807f-a6eb-40fe-9093-1d061d76e6bc.png)

Visual output for 100 random points:

![100](https://user-images.githubusercontent.com/82055290/149250229-ea8748ee-30c5-46ec-a5ed-ca21071f0679.png)
