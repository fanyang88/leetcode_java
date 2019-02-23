/*
Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
*/


/*
         for each point, we examine how many points has same distance to it
 e.g: a=[0, 0] b=[1,0]  c[-1,0] d[0, -1] b,c,d has same distance to a which is 1
 so there can be boomerangs as: [a,b,c] [a,b,d] [a,c,b] [a,c,d] [a,d,b] [a,d,c] = 6 total
 since a is fixed postions, there are 3 values for 2 positions = 3 * 2 =6
 
*/
class Solution {
  public int numberOfBoomerangs(int[][] points) {
      int res = 0, n= points.length;
      if(n==0) return res;
      Map<Integer, Integer> map = new HashMap<>();
      for(int i=0; i<n; i++) {
          for(int j=0; j<n; j++) {
              if(i==j)  continue;
              int dist = getDistance(points[i], points[j]);
              map.put(dist, map.getOrDefault(dist, 0) +1);
          }
          for(int val: map.values()) {
              res += val * (val-1);
          }
          map.clear();
      }
      return res;
  }
  
  public int getDistance(int[] p1, int[] p2) {
      return (p1[1]-p2[1])*(p1[1]-p2[1]) + (p1[0]-p2[0])*(p1[0]-p2[0]);
  }
}
