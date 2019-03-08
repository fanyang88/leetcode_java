/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

Example 1:
11000
10000
00001
00011
Given the above grid map, return 1. 

Notice that:
11
1
and
 1
11
are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
Example 2:
11100
10001
01001
01110
Given the above grid map, return 2.

Here are the two distinct islands:
111
1
and
1
1

Notice that:
111
1
and
1
111
are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
Note: The length of each dimension in the given grid does not exceed 50.
*/


/*
    using the first example:
 first island is:  [ 0, 0 ], [ 1, 0 ], [ 0, 1 ] 
 and it's reflection + rotation are below 8 forms: 
   s[0] = [ [ 0, 0 ], [ 0, 1 ], [ 1, 0 ] ],
   s[1] = [ [ 0, 0 ], [ 1, 0 ], [ 1, 1 ] ],
   s[2] = [ [ 0, 0 ], [ 0, 1 ], [ 1, 1 ] ],
   s[3] = [ [ 0, 0 ], [ 1, -1 ], [ 1, 0 ] ],
   s[4] =  [ [ 0, 0 ], [ 1, 0 ], [ 1, 1 ] ],
   s[5] =  [ [ 0, 0 ], [ 0, 1 ], [ 1, 1 ] ],
   s[6] = [ [ 0, 0 ], [ 1, -1 ], [ 1, 0 ] ],
   s[7] = [ [ 0, 0 ], [ 0, 1 ], [ 1, 0 ] ] 
   the norm process is for each s[i]:
   we need to sort each s[i] to be s[i][0] in smallest order
   extract s[i][k][0] -s[i][0][0]  s[i][k][1] - s[i][0][1]   k is from 1~2 in above case
   then set s[i][0][0]=0 s[i][0][1]=0
   sort s again to use s[0] to represent the island
   
*/

class Solution {
    
  class Point implements Comparable<Point> {
      int x, y;
      Point(int x, int y) {
          this.x = x;
          this.y = y;
      }
       @Override
      public int compareTo(Point o) {
          return this.x ==o.x ? this.y - o.y : this.x - o.x;
      }
  }
     
  public int numDistinctIslands2(int[][] grid) {
      Set<String> set = new HashSet<>();
      int m = grid.length, n = grid[0].length;
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(grid[i][j]==1) {
                  List<Point> island = new ArrayList<>();
                  dfs(i, j, grid, m, n, island);
                  set.add(norm(island));
              }
          }
      }
      return set.size();
  }

  public void dfs(int i, int j, int[][] grid, int m, int n, List<Point> island) {
      if(i>=m || i<0 || j>=n || j<0 || grid[i][j]==0)  return;
      grid[i][j] = 0;
      island.add(new Point(i, j));
      dfs(i+1, j, grid, m, n, island);
      dfs(i-1, j, grid, m, n, island);
      dfs(i, j-1, grid, m, n, island);
      dfs(i, j+1, grid, m, n, island);
  }
  
  public String norm(List<Point> island) {
      int[][] dirs = {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
      // construct the other 7 form
      List<Point>[] islands = new List[8];
      for(int i=0; i<4; i++) {
          islands[i] = new ArrayList<Point>();
          islands[i+4] = new ArrayList<Point>();
          for(Point p: island) {
              islands[i].add(new Point(p.x * dirs[i][0], p.y*dirs[i][1]));
              islands[i+4].add(new Point(p.y * dirs[i][0], p.x*dirs[i][1]));
          }
      }
      
      for(int i=0; i<8; i++) Collections.sort(islands[i]);
      
      String[] s = new String[8];
      for(int i=0; i<8; i++) {
          // now we need to substract the first one for each 
          StringBuilder sb = new StringBuilder();
          int x0 = islands[i].get(0).x, y0 = islands[i].get(0).y;
          for(int j=0; j<islands[i].size(); j++) {
              sb.append((islands[i].get(j).x - x0) + "," + (islands[i].get(j).y - y0) + ":");
          }
          s[i] = sb.toString();
      }
      Arrays.sort(s);
      return s[0];
  }
}
