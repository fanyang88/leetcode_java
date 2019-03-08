/*
Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

Example 1:
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
Note:
The value in the given matrix is in the range of [0, 255].
The length and width of the given matrix are in the range of [1, 150].

*/

class Solution {
  int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
  public int[][] imageSmoother(int[][] M) {
      int m = M.length, n= M[0].length;
      int[][] res = new int[m][n];
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              res[i][j] = smooth(i, j, M, m, n);
          }
      }
      return res;
  }
  
  public int smooth(int i, int j, int[][] M, int m, int n) {
      int sum= M[i][j], count=1;
      for(int[] d: dirs) {
          if(i+d[0] < 0 || i+d[0] >=m || j+d[1]<0 || j+d[1]>=n) continue;
          sum += M[i+d[0]][j+d[1]];
          count++;
      }
      return sum/count;
  }
}
