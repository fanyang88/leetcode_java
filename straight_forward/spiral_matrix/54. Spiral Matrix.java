/**
 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */



class Solution {
  public List<Integer> spiralOrder(int[][] matrix) {
      List<Integer> res = new ArrayList<Integer>();
      if(matrix.length==0)  return res;
      int rs=0, re=matrix.length-1, cs=0, ce= matrix[0].length-1;
      while(rs <= re && cs <= ce) {
          // to right
          for(int i=cs; i<=ce; i++) {
              res.add(matrix[rs][i]);
          }
          rs++;
          if(rs > re)  break;
          // to down
          for(int i=rs; i<=re; i++) {
              res.add(matrix[i][ce]);
          }
          ce--;
          if(ce < cs)  break;
          // to left
          for(int i=ce; i>=cs; i--) {
              res.add(matrix[re][i]);
          }
          re--;
          // to Up
           for(int i=re; i>=rs; i--) {
              res.add(matrix[i][cs]);
          }
          cs ++;
      }
      return res;
  }
}
