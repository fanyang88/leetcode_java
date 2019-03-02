/*
Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

 

Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]

Explanation:

 

Note:

The total number of elements of the given matrix will not exceed 10,000.
*/


/*
     Walk patterns:

If out of bottom border (row >= m) then row = m - 1; col += 2; change walk direction.
if out of right border (col >= n) then col = n - 1; row += 2; change walk direction.
if out of top border (row < 0) then row = 0; change walk direction.
if out of left border (col < 0) then col = 0; change walk direction.
Otherwise, just go along with the current direction.


check order: 
          up
       [  ....  ]
 left  [  ...   ]  right
       [ ...    ]
         bottom
        bottom -> right -> up -> left

there is to direction: up : [-1, 1] and down: [1, -1]

when hit the boundary, we need to check bottom -> right -> up -> left
for example:
when at (0, 0) go up => (-1, 1) since row=-1<0  row = 0 col stay the same, change dir
when at (m-2, 0) go down => (m-1, -1) since col=-1<0  col = 0 change dir
when at (0, n-1) go up=> (-1, n) since col>=n, col=n-1, row+=2=1 change dir
when at (m-1, n-2) go down=> (m, n-3)  since row>=m, row=m-1, col+=2=n-1 change dir

*/

class Solution {
  public int[] findDiagonalOrder(int[][] matrix) {
      if( matrix.length==0)  return new int[0];
      int[][] dir = {{-1, 1}, {1, -1}};
      int index= 0, m= matrix.length, n = matrix[0].length, row=0, col=0, d=0;
      int[] res = new int[m*n];
      
      while(index < m*n) {
          res[index++] = matrix[row][col];
          row += dir[d][0];
          col += dir[d][1];
          if(row==m) {  // go down, col = -1, next should be [m-1, 1]check bottom, hit bottom boundray, e,g: 8 go down
              row = m-1;
              col = col + 2;
              d = 1-d;
          } else if(col==n) {  // hit right boundary, e.g: 3 go up
              col = n-1;
              row += 2;
              d = 1-d;
          } else if(row==-1) {  // hit up, e.g: 3 go up, row=-1, col=1 next should be row=0 col=1
              row=0;
              d = 1-d;
          } else if(col==-1) { // hit left, e.g: 4 go down
              col=0;
              d = 1-d;
          }
      }
      return res;
  }
}
