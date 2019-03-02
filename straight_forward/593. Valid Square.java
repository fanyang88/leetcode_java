/*
Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:
Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
Note:

All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.
*/

/*
    Calculate distances between all pairs and count distinct values.
There should be only 2 distinct distances (side and diagonal), if not return false.
Fetch lower and bigger value - lower value should occur 4 times (sides), higher 2 times (diagonals) and a^2 + b^2 = c^2 condition should be met.
1. there should be only two type of distance and the longer one has freq=2 shorter one has freq=4
2. shorter^2 * 2 = longer^2
*/

class Solution {
  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
      Set<Integer> set = new HashSet<>(Arrays.asList(
          dist(p1, p2), dist(p1, p3), dist(p1, p4), dist(p2, p3), dist(p2, p4), dist(p3, p4)));
          return !set.contains(0) && set.size()==2;
  }
  
  public int dist(int[] p1, int[] p2) {
      return (p1[0] - p2[0])*(p1[0] - p2[0]) + (p1[1] - p2[1])*(p1[1] - p2[1]);
  }
}
