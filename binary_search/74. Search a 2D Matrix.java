/**
 Write an efficient algorithm that searches for a value in an m x n matrix. 
 This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
 */


/*
e.g: matrix = [  find 23 
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]

start from 7, since 7 < 23, go down, find 20 < 23 go down, 50 > 23 go left
so if the element is smaller than target, go down, otherwise go left till hit boudary
*/

class Solution {
  public boolean searchMatrix(int[][] matrix, int target) {
      if(matrix.length==0)  return false;
      int m=matrix.length, n = matrix[0].length, row=0, col=n-1;
     
      while(row < m && row >=0 && col < n && col >=0) {
          if(matrix[row][col] == target)  return true;
          if(matrix[row][col] < target) { // go down
              row++;
          } else {
              col--;
          }
      }
      return false;
  }
}

// Method2 binary search

class Solution {
  public boolean searchMatrix(int[][] matrix, int target) {
      if(matrix.length==0)  return false;
      int m=matrix.length, n = matrix[0].length, lo=0, hi=m*n-1;
     
      while(lo <= hi) {
          int mid = (lo+hi)/2;
          int midVal = matrix[mid/n][mid%n];
          if(midVal ==target)  return true;
          if(midVal < target) {
              lo = mid+1;
          } else {
              hi = mid-1;
          }
      }
      return false;
  }
}
