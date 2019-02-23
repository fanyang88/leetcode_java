/*
A group of two or more people wants to meet and minimize the total travel distance. 
You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6 

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance 
             of 2+2+2=6 is minimal. So return 6.
*/

/*
     since the min distance of n points = (xN - x1 + xN-1 - x2 + ... ) + (yN - y1 + yN-1 - y2 + ... )
     x1, x2, ...xN is sorted, so as y1, y2, ....yN

*/

class Solution {
  public int minTotalDistance(int[][] grid) {
      List<Integer> xs = new ArrayList<Integer>();
      List<Integer> ys = new ArrayList<Integer>();
      for(int i=0; i<grid.length; i++) {
          for(int j=0; j<grid[0].length; j++) {
              if(grid[i][j] == 1) {
                  xs.add(i);
                  ys.add(j);
              }
          }
      }
      return getSum(xs) + getSum(ys);
  }
  
  public int getSum(List<Integer> list) {
      Collections.sort(list);
      int i=0, j=list.size()-1, sum=0;
      while(i<j) {
          sum += list.get(j--) - list.get(i++);
      }
      return sum;
  }
}
