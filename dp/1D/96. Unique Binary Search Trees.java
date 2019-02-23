/**
 Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */

 /*
e.g: to build a tree contains {1,2,3,4,5}. 
First we pick 1 as root, for the left sub tree, there are none; 
                         for the right sub tree, we need count how many possible trees 
                         are there constructed from {2,3,4,5}, 
                         apparently it's the same number as {1,2,3,4}. 
                         So the number of trees under "1" picked as root is dp[0] * dp[4] 
      we pick 2 as root, left sub tree has 1 dp[1], right sub tree is {3,4,5}=dp[1]*[3]
      we pick 3 as root, left sub tree has {1,2} dp[2], right sub tree is {3,4}=dp[2]*[2]
      we pick 4 as root, left sub tree has {1,2,3} dp[3], right sub tree is {5}=dp[3]*[1]
      we pick 5 as root, left sub tree has {1,2,3,4} dp[4], right sub tree is {}=dp[4]*[0]
      the sum is dp[5]
 to build a tree contain {1,2}  pick 1 as root, left is {}, right is {2} dp[0]*dp[1]=1
                                pick 2 as root, left is {1}, right is {} dp[1]*dp[0]=1 . 
                                dp[2]=2
 to build a tree contain {1,2,3}  pick 1 as root, left is {}, right is {2,3} dp[0]*dp[2]=2
                                  pick 2 as root, left is {1}, right is {3} dp[1]*dp[1]=1 . 
                                  pick 3 as root, left is {1,2}, right is {} dp[2]*dp[0]=2
 
 
  dp[0] = 0 
  dp[1] = 1
      
*/  

class Solution {
  public int numTrees(int n) {
      int[] dp = new int[n+1];
      dp[0] =1;
      dp[1] = 1;
      for(int i=2; i<=n; i++) {
          for(int j=0; j<i; j++) {
              dp[i] += dp[j]*dp[i-j-1];
          }
      }
      return dp[n];
  }
}