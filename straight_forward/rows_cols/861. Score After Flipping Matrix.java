/*
We have a two dimensional matrix A where each value is 0 or 1.

A move consists of choosing any row or column, and toggling each value in that row or column: changing all 0s to 1s, and all 1s to 0s.

After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.

Return the highest possible score.

 

Example 1:

Input: [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
Output: 39
Explanation:
Toggled to [[1,1,1,1],[1,0,0,1],[1,1,1,1]].
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 

Note:

1 <= A.length <= 20
1 <= A[0].length <= 20
A[i][j] is 0 or 1.
*/

/*
        
 0 0 1 1    1 1 0 0    1 1 1 1 
 1 0 1 0 -> 1 0 1 0 -> 1 0 0 1
 1 1 0 0    1 1 0 0    1 1 1 1
to get the highest score, we need to make sure the first col is all 1.
Flip all rows that start with zero;
Flip all columns where the number of zeros is larger than the number of ones;
*/

class Solution {
  public int matrixScore(int[][] A) {
      for(int i=0; i<A.length; i++) {
          if(A[i][0] == 0) { // flip this row
              flip(i, true, A);
          }
      }
      // check each col, if 1<0, flip this col
      for(int j=0; j<A[0].length; j++) {
          int count=0;
          for(int i=0; i<A.length; i++) {
              if(A[i][j] == 1)  count++;
          }
          if(count< A.length - count) {
              flip(j, false, A);
          }
      }
      int sum=0;
      for(int i=0; i<A.length; i++) {
          int n = 0;
          for(int j=0; j<A[0].length; j++) {
              n= n<<1;
              n += A[i][j];
              
          }
          sum += n;
      }
      return sum;
  }
  
  public int[][] flip(int i, boolean isRow, int[][] A) {
      if(isRow) { // we need to flip row i
          for(int j=0; j<A[0].length; j++) {
              A[i][j] = 1 - A[i][j];
          }
      } else { // flip col ith
          for(int j=0; j<A.length; j++) {
              A[j][i]= 1 - A[j][i];
          }
      }
      return A;
  }
}
