/**
 You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
 */

 /*
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],
switch [0][0] ~ [0][n-1] ~ [m-1][n-1] ~ [m-1][0]
switch [0][1] ~ [1][n-1] ~ [m-1][1] ~ [m-1-1][0]
       [0][i] ~ [i][n-1] ~ [m-1][n-1-i] ~ [m-1-i][0]
       [start][i] ~ [i][end] ~ [end][n-1-i] ~ [m-1-i][start]  m=n
*/

class Solution {
  public void rotate(int[][] matrix) {
      int start = 0, n= matrix.length, end = n-1;
      while(start < end) {
          for(int i=start; i<end; i++) { // < end not <= end
              int a = matrix[start][i];
              int b = matrix[i][end];
              int c = matrix[end][n-1-i];
              int d = matrix[n-1-i][start];
              matrix[start][i] = d;
              matrix[i][end] = a;
              matrix[end][n-1-i] = b;
              matrix[n-1-i][start] =c;
          }
          start++;
          end--;
      }
  }
}

