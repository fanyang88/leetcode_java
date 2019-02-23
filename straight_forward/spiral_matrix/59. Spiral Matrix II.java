/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
 */

class Solution {
  public int[][] generateMatrix(int n) {
      int[][] res = new int[n][n];
      if(n==0) return res;
      int rs=0, re=n-1, cs=0, ce=n-1, num=1;
      while(rs <= re && cs <= ce) {
          // to right
          for(int i=cs; i<=ce; i++) {
              res[rs][i] = num++;
          }
          rs++;
          if(rs > re)  break;
          // to down
          for(int i=rs; i<=re; i++) {
              res[i][ce] = num++;
          }
          ce--;
          if(ce < cs)  break;
          // to left
          for(int i=ce; i>=cs; i--) {
              res[re][i] = num++;
          }
          re--;
          // to up
          for(int i=re; i>=rs; i--) {
              res[i][cs] = num++;
          }
          cs++;
          
      }
      return res;
  }
}