/*
Given a square array of integers A, we want the minimum sum of a falling path through A.

A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.

 

Example 1:

Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: 12
Explanation: 
The possible falling paths are:
[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
The falling path with the smallest sum is [1,4,7], so the answer is 12.

 

Note:

1 <= A.length == A[0].length <= 100
-100 <= A[i][j] <= 100

*/

/*
        [1,2,3]
        [4,5,6],
        [7,8,9]
        
        dp: [1,2,3]
            [5,6,8]
            [12,13, 16]

*/

class Solution {
  public int minFallingPathSum(int[][] A) {
      int m =A.length, n= A[0].length, res = Integer.MAX_VALUE;
      for(int i=1; i<m; i++) {
          for(int j=0; j<n; j++) {
              int min = A[i-1][j];
              if(j-1>=0) min = Math.min(min, A[i-1][j-1]);
              if(j+1<n) min = Math.min(min, A[i-1][j+1]);
              A[i][j] += min;
          }
      }
      for(int i=0; i<n; i++) {
          res = Math.min(res, A[m-1][i]);
      }
      return res;
  }
}
