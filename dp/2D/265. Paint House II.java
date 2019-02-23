/**
 There are a row of n houses, each house can be painted with one of the k colors. 
 The cost of painting each house with a certain color is different. 
 You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color 0; 
costs[1][2] is the cost of painting house 1 with color 2, and so on... 
Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5. 

 */

 /*
    [1,5,3], 
    [2,9,4]]  [5,1,5]]
    
    create a matrix to record the min
    [1,5,3] min1=1 min2=3
    at[0]  since 1=min1 take min2 
    at[1]  since 5!=min1 take min1 
    at[2]  since 4 =min2 position, take min1
    
    so we need to get the min1, min2 value for i-1th row
    to cacalute the ith row,
        if costs[i-1][j] == min1 take min2: costs[i][j] = costs[i][j] + min2
        if costs[i-1][j] == min2 take min1: costs[i][j] = costs[i][j] + min1
        else if costs[i][j] = costs[i][j] + min1
        when j == min1 index, take min2 otherwise, take min1
    
*/

class Solution {
  public int minCostII(int[][] costs) {
      int n = costs.length;
      if(n ==0) return 0;
      int[] mins = new int[2];
      
      for(int i=0; i<n; i++) {
          for(int j=0; j<costs[i].length; j++) {
              if(i==0) continue; 
              costs[i][j] += (j==mins[0] ? costs[i-1][mins[1]] : costs[i-1][mins[0]]);
          }
          mins = getMin(costs[i]);
      }
      return costs[n-1][mins[0]];
  }
  
  public int[] getMin(int[] cost) {
      int min1=-1, min2=-1; 
      for(int i=0; i<cost.length; i++) {
          if(min1 < 0 || cost[i] < cost[min1]) {
              min2 = min1;
              min1 = i;
          } else if(min2 < 0 || cost[i] < cost[min2]) {
              min2= i;
          }
      }
      return new int[]{min1, min2};
  }
}