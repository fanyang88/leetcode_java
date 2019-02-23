/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
 */

 /**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 
    1
**----*
 \    | \
  4\ 2|  \3
      *   *
      
for line 1 & 4, there is 3 points, line 2, and 3 both have 2 points, so answer is 3
for each point, we need to get the lines and number of points in that line
use a "clean" hashmap to record for each point

 
 */  
class Solution {
  public int maxPoints(Point[] points) {
      if(points.length <=1)  return points.length;
      int maxV = Integer.MIN_VALUE;
      for(int i=0; i<points.length; i++) {
          int dup=1; // itself need to be included
          Map<String, Integer> map = new HashMap<>();
          for(int j=0; j<points.length; j++) {
              if(i==j)  continue;
              if(points[j].x==points[i].x && points[j].y==points[i].y) {
                  dup++;
                  continue;
              }
              String slope = getSlope(points[i], points[j]);
              map.put(slope, map.getOrDefault(slope, 0)+1);
          }
          if(map.values().size() ==0)  {
              maxV = Math.max(maxV, dup);
          } else {
              maxV = Math.max(maxV, Collections.max(map.values()) + dup);
          }
      }
      return maxV;
  }
  
  public String getSlope(Point p1, Point p2) {
      if(p1.x == p2.x) return "1";
      if(p1.y == p2.y)  return "0";
      int x = p1.x-p2.x;
      int y= p1.y-p2.y;
      int gcd = GCD(x, y);
      return  ""+(y/gcd)+(x/gcd);
  }
  
  public int GCD(int a, int b) {
      if(b==0)  return a;
      return GCD(b, a%b);
  }
}
