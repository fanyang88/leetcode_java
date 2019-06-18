/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/


/*
 use Sum Table:
e.g: matrix=        sumtable:
2  5  4  1          2  5  4  1          
3  6  1  2   ->     5  16 21  24
4  0  7  1          9  20 32  36
2  3  2  6          11 25 39  49
dp[i][j]= dp[i-1][j]+dp[i][j-1]+matrix[i][j]- dp[i-1][j-1];

get sum from (row1, col1)- (row2, col2)
             |0,0         |               
             |            |    
             |   r1-1,c1-1|            r1-1,c2
             ---------------------------------
             |            | r1,c1
             |            | 
             |            | 
             |            |
             |     r2,cl-1|            r2,c2
 
sum = sumTable[row2][col2]
      - sumTable[row1-1][col2] 
      - sumTable[row2][col1-1]
      + sumTable[row1-1][col1-1](been subtracted twice)
      
*/

class NumMatrix {
  int[][] sum;
  public NumMatrix(int[][] matrix) {
      if(matrix.length==0 || matrix[0].length ==0)  return; 
      int m = matrix.length, n= matrix[0].length;
      sum = new int[m][n];
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              int up = i==0 ? 0 : sum[i-1][j];
              int left = j==0 ? 0 : sum[i][j-1];
              int diagal = (i!=0 && j!=0) ? sum[i-1][j-1] : 0;
              sum[i][j] = matrix[i][j] + up+left - diagal;
          }
      }
  }
  
  public int sumRegion(int row1, int col1, int row2, int col2) {
      int left = col1==0 ? 0 : sum[row2][col1-1];
      int up = row1 ==0 ? 0 : sum[row1-1][col2];
      int diagal = (col1!=0 && row1!=0) ? sum[row1-1][col1-1] : 0;
      return sum[row2][col2] - left - up + diagal;
  }
}

/**
* Your NumMatrix object will be instantiated and called as such:
* NumMatrix obj = new NumMatrix(matrix);
* int param_1 = obj.sumRegion(row1,col1,row2,col2);
*/
