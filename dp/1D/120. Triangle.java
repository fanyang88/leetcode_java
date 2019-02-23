/**
 * 
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */

 /*
[2],           [11]
[3,4],      -  [9,10],   
[6,5,7],       [7,6,10],
[4,1,8,3]      [4,1,8,3] 
bottom up, answer is triangle[0][0]
*/
class Solution {
  public int minimumTotal(List<List<Integer>> triangle) {
      int n = triangle.size();
      int[] A = new int[n+1];
      for(int i=n-1; i>=0; i--) {
          for(int j=0; j<triangle.get(i).size(); j++) {
              A[j] = Math.min(A[j], A[j+1]) + triangle.get(i).get(j);
          }
      }
      return A[0];
  }
}