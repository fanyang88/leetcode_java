/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.
*/

class Solution {
  public int numDistinctIslands(int[][] grid) {
      Set<String> set = new HashSet<>();
      int m = grid.length, n = grid[0].length;
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(grid[i][j]==1) {
                  String island = dfs(i, j, "", grid, m, n);
                  set.add(island);
              }
          }
      }
      return set.size();
  }
  
  public String dfs(int i, int j, String str, int[][] grid, int m, int n) {
      if(i>=m || i<0 || j>=n || j<0 || grid[i][j]==0)  return str;
      grid[i][j] = 0;
      str = dfs(i+1, j, str+"D", grid, m, n);
      str = dfs(i-1, j, str+"U", grid, m, n);
      str = dfs(i, j-1, str+"L", grid, m, n);
      str = dfs(i, j+1, str+"R", grid, m, n);
      return str;
  }
}
