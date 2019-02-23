/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
*/

/*

    Two Queue and add all the Pacific border to one queue; 
    Atlantic border to another queue.
    Keep a visited matrix for each queue. In the end, add the cell visited by two queue to the result.
    DFS: initially we have height to be min_value, 
    Since water can only flow from high/equal cell to low cell, 
    add the neighboor cell with height larger or equal to current cell to the queue and mark as visited.
   
*/

class Solution {
  int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  public List<int[]> pacificAtlantic(int[][] matrix) {
      List<int[]> res = new ArrayList<int[]>();
      if(matrix.length==0)  return res;
      int m = matrix.length, n = matrix[0].length, minV = Integer.MIN_VALUE;
      boolean[][] pacific = new boolean[m][n],  atlantic = new boolean[m][n];
      
      for(int i=0; i<m; i++) {
          dfs(i, 0, pacific, minV, matrix, m, n); // pacfic left
          dfs(i, n-1, atlantic, minV, matrix, m, n); // atlantci right
      }
      for(int j=0; j<n; j++) {
          dfs(0, j, pacific, minV, matrix, m, n); // pacfic left
          dfs(m-1, j, atlantic, minV, matrix, m, n); // atlantci right
      }
      
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(atlantic[i][j] && pacific[i][j]) {
                  res.add(new int[]{i, j});
              }
          }
      }
      return res;
  }
  
  public void dfs(int x, int y, boolean[][] visited, int curV, int[][] matrix, int m, int n) {
      if(x < 0 || x>=m ||y < 0 || y>=n || visited[x][y] || matrix[x][y] < curV)  return;
      visited[x][y] = true;
      for(int[] d: dirs) {
          int x1 = x + d[0], y1= y+d[1];
          dfs(x1, y1, visited, matrix[x][y], matrix, m, n);
      }
  }
}
