/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. 
 * This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
 */

class Solution {
  public boolean searchMatrix(int[][] matrix, int target) {
      if(matrix.length==0)  return false;
      int m = matrix.length, n = matrix[0].length;
      int col=n-1, row=0;
      while(col >=0 && row < m) {
          if(matrix[row][col] == target) return true;
          else if(matrix[row][col] < target) {
              row++;
          } else {
              col--;
          }
      }
      return false;
  }
}