/*
Given several boxes with different colors represented by different positive numbers. 
You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
Find the maximum points you can get.

Example 1:
Input:

[1, 3, 2, 2, 2, 3, 4, 3, 1]
Output:
23
Explanation:
[1, 3, 2, 2, 2, 3, 4, 3, 1] 
----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
----> [1, 3, 3, 3, 1] (1*1=1 points) 
----> [1, 1] (3*3=9 points) 
----> [] (2*2=4 points)
Note: The number of boxes n would not exceed 100.
*/

/*
    base case:
    to remove b[i]~b[j] the max score we can get is dp[i][j]
    if j==i or j<i dp[i][j]=0
    if b[i]~b[j] has same color, dp[i][j] = (j-i+1)*(j-i+1)  e.g: j=2 i=0 
    
    
    dp[i][j] = max(dp[i][k] + dp[k+1][j])  k=[i, j-1]
    answer is dp[0][n-1]
    
    but using the above solution is worong
    e.g: dfs(ABA) = dfs(A) + dfs(BA) 
                  or dfs(AB) + dfs(A)
                  
    but the best solution is dfs(ABA) = dfs(B) + dfs(AA)
    
    we need 3D dp:
    assume dp[i][j][k] means from box i~ box j there are k boxes following box j has same color as j
     A . ...    B .             B ....  B
     i .        p  p+1 . j-1 j j+1 ... j+k
    box        box .             kboxes
                
    
    dp[i][j][k] = dp[i][j-1][0] + (k+1)(k+1)  case 1: remove all k+1 boxes(include jth)
                  dp[i][p][k+1] + dp[p+1][j-1][0]    or we split at p if p has same color as k boxes, 
                                                    remove box from p+1 to j-1 
                                                    then we have i~p boxes following by k+1 same color boxes(include jth)
                                                    
    answer: dp[0][n-1][0]
    
    
    e.g: ABACA .                       dfs(ABACA)
                         /              |                \ 
                    dfs(ABAC) +1 .  dfs(ABA|A)+dfs(C)   dfs(A|A) + dfs(BAC) 
                                     /       \
                                dfs(AB)+4  dfs(AAA)+1
                                ...          
                                
    O(n^4)
*/ 

class Solution {
  public int removeBoxes(int[] boxes) {
      int n = boxes.length;
      int[][][] map = new int[n][n][n];
      return dfs(0, n-1, 0, map, boxes);
  }
  
  public int dfs(int i, int j, int k, int[][][] map, int[] boxes) {
      if(j<i)  return 0;
      if(map[i][j][k] >0) return map[i][j][k];
      
      // Optimization
      while(i<j && boxes[j] == boxes[j-1]) {  j--;  k++;}
      
      map[i][j][k] = dfs(i, j-1, 0, map, boxes) + (k+1)*(k+1);
      for(int p=i; p<j; p++) {
          if(boxes[p] == boxes[j]) {
              map[i][j][k] =Math.max(map[i][j][k], dfs(i, p, k+1, map, boxes) + dfs(p+1, j-1, 0, map, boxes));
          }
      }
      return map[i][j][k];
  }
}
