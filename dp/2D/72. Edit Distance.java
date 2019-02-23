/**
 Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 */


/*
     ""    h    o   r   s   e
""    0    1    2   3   4   5  
r     1    1    2   2   3   4   if word1[i] == word2[j] dp[i][j] = dp[i-1][j-1] 
o     2    2    1   2   3   4   else dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
s     3    3    2   2   2   3
answer is 3
*/

class Solution {
  public int minDistance(String word1, String word2) {
      int m = word1.length(), n= word2.length();
      int[][] dp = new int[m+1][n+1];
     
      for(int i=0; i<=m; i++) {
          for(int j=0; j<=n; j++) {
              if(i-1 <0 && j-1<0) {
                  dp[i][j] =0;
              } else if(i-1 <0) {
                  dp[i][j] = dp[i][j-1]  +1;
              } else if(j-1 < 0) {
                  dp[i][j] = dp[i-1][j]  +1;
              } else {
                  if(word1.charAt(i-1) == word2.charAt(j-1)) {
                      dp[i][j] = dp[i-1][j-1];
                  } else {
                      dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
                  }
              }
          }
      }
      return dp[m][n];
  }
}
