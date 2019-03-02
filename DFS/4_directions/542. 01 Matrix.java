/*
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1: 
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2: 
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.

*/

/*
        DFS
        change all element which equal 1 but no 0 surrounded to be max_value
 0 0 0 
 0 1 0
 1 max 1
 then we traverse from each 1, if current val > 1, assign the value to matrix
 but if the current value is already assigned, let's say 2, then we need to exclude it from checking again
 
 the key is if the value has been assigned at (x, y) and next time before we check this value again, if it has a value already < cur assign value
 return, not check again
*/

class Solution {
  public int[][] updateMatrix(int[][] matrix) {
      int m = matrix.length, n = matrix[0].length;
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(matrix[i][j] == 1 && !hasZero(i, j, matrix, m, n)) matrix[i][j] = Integer.MAX_VALUE;
          }
      }
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(matrix[i][j] == 1) {
                  dfs(i, j, 1, matrix, m, n);
              }
          }
      }
      return matrix;
  }
  
  public void dfs(int x, int y, int dist, int[][] matrix, int m, int n) {
      if(x <0 || y < 0 || x>=m || y>=n || matrix[x][y] ==0 || matrix[x][y] < dist)  return;
      matrix[x][y]= dist;
      dfs(x+1, y, dist+1, matrix, m, n);
      dfs(x-1, y, dist+1, matrix, m, n);
      dfs(x, y-1, dist+1, matrix, m, n);
      dfs(x, y+1, dist+1, matrix, m, n);
  }
  
  public boolean hasZero(int x, int y, int[][] matrix, int m, int n) {
      if(x+1 <m && matrix[x+1][y] ==0) return true;
      if(x-1>=0 && matrix[x-1][y] ==0) return true;
      if(y+1 <n && matrix[x][y+1] ==0) return true;
      if(y-1>=0 && matrix[x][y-1] ==0) return true;
      return false;
      
  }
}
