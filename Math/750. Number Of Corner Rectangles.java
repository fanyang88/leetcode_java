/*
Given a grid where each entry is only 0 or 1, find the number of corner rectangles.

A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.

 

Example 1:

Input: grid = 
[[1, 0, 0, 1, 0],
 [0, 0, 1, 0, 1],
 [0, 0, 0, 1, 0],
 [1, 0, 1, 0, 1]]
Output: 1
Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
 

Example 2:

Input: grid = 
[[1, 1, 1],
 [1, 1, 1],
 [1, 1, 1]]
Output: 9
Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
 

Example 3:

Input: grid = 
[[1, 1, 1, 1]]
Output: 0
Explanation: Rectangles must have four distinct corners.
 

Note:

The number of rows and columns of grid will each be in the range [1, 200].
Each grid[i][j] will be either 0 or 1.
The number of 1s in the grid will be at most 6000.
*/

/*
     * first step: we can keep two horizontal line x1 and x2 unchanged, and use a moving vertical line to scan from 
 * left to right to count number of valid vertical line we can find between x1 and x2 (valid means both of 
 * grid[x1][k] and grid[x2][k] is equal to 1). Because any two valid vertical lines between x1 and x2 can form 
 * a rectangle with connecting two horizontal lines x1 and x2
 * 
 * second step: after counting number of vertical lines between two horizontal lines, we can calculate number of
 * rectangles between x1 and x2. The equation should be equivalent to select 2 from total number of vertical lines
 * i.e.: count * (count - 1) / 2
 
 e.g:  1 1 1
       1 1 1     when j=0 count++=1 j=1 count++=2 j=2 count++=3
       
 since there are 3 lines, we can pick either 2 to form a rectangle
 pick 2 from 3, is 3/1 * 2*2
       3*(3-1)/2=3


basic math: if we have 6 peopel, 4 chairs, the permuation to put 6 in 4 is:

6(6 possiblity)  (5 possiblity)   (4 possiblity)   (3 possiblity)
--                --                --              --
1                  2                 3              4

so in our question to pick 2 lines from 3, the possiblity is 3/1* (2/2) 

to pick 2 lines from count, the possiblity is count/1* (count-1)/2

*/

class Solution {
  public int countCornerRectangles(int[][] grid) {
      int res=0, m=grid.length, n=grid[0].length;
   
      for(int r1 = 0; r1<m-1; r1++) {
          for(int r2 = r1+1; r2 < m; r2++) { 
              int count=0;
              for(int j=0; j<n; j++) {
                  if(grid[r2][j] ==1 && grid[r1][j]==1) count++;
              }
              // after get number of lines we can calcualte
              res += count* (count-1)/2;
          }
      }
      return res;
  }
}
