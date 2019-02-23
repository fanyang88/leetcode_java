/**
 Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
 */

 
/*
first scan through the whole matrix, and if one row i has zero, label matrix[i][0] = 0, 
if column j has zero, then label matrix[0][j] = 0.
if we find the first row has zero, then mark a boolean row = true, 
if the first column has zeros, mark a boolean col = true;

By the markers on the first row and first col, set the other columns and rows to zeros. 
(first row and first column already contain zeros)

According to booleans row and col, decide whether to set first row and column to zeros.
*/

class Solution {
  public void setZeroes(int[][] matrix) {
      int m = matrix.length, n=matrix[0].length, row=0, col=0;
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(matrix[i][j] == 0) {
                  matrix[i][0] = 0;
                  matrix[0][j] = 0;
                  if(i==0)  row=1;
                  if(j==0) col=1;
              }  
          }
      }
      for(int i=1; i<m; i++) {
          // scan row by row, if the first element is 0, the whole row should be 0
          if(matrix[i][0] ==0) {
              for(int j=1; j<n; j++) {
                  matrix[i][j] = 0;
              }
          }
      }
      
      for(int j=1; j<n; j++) {
          // scan col by col, if the first element is 0, the whole col should be 0
          if(matrix[0][j] ==0) {
              for(int i=1; i<m; i++) {
                  matrix[i][j] = 0;
              }
          }
      }
      if(row ==1) {
          for(int i=0; i<n; i++) {
              matrix[0][i] = 0;
          }
      }
      if(col ==1) {
          for(int i=0; i<m; i++) {
              matrix[i][0] = 0;
          }
      }
  }
}