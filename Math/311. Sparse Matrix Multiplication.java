/*
Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

Input:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]

Output:

     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/

/*
    A =   [ 1, 0, 0, 0],
          [-1, 0, 3, 0]

B = [ 7, 0, 0],
    [ 0, 0, 0],
    [ 0, 0, 1]
    [ 0, 1, 1]

C is [2][4] = [A.m][B.n]

C[0][0] = A[0][0]*B[0][0] + A[0][1]*B[1][0] + A[0][2] *B[2][0]
...
C[i][j] = A[i][0]*B[0][j] + A[i][1]*B[1][j] + A[i][2]*B[2][j] + A[i][3]*B[3][j] (0<k<=3)

[1, -5] * [12
          -1]

*/

class Solution {
  public int[][] multiply(int[][] A, int[][] B) {
      int m = A.length, n = B[0].length;
      int[][] C = new int[m][n];
      
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              for(int k=0; k<B.length; k++) {
                  if(A[i][k]==0 || B[k][j]==0) continue;
                  C[i][j] += A[i][k] * B[k][j];
              }
          }
      }
      return C;
  }
}
