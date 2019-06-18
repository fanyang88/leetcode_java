/*
Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

Example 1:
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
Example 2:
Input: s1 = "delete", s2 = "leet"
Output: 403
Explanation: Deleting "dee" from "delete" to turn the string into "let",
adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
Note:

0 < s1.length, s2.length <= 1000.
All elements of each string will have an ASCII value in [97, 122].

*/

/*
if s1[i-1] = s2[j-1]   // no deletion
    dp[i][j] = dp[i-1][j-1];
else   // if delete s1[i-1] equals dp[i-1][j]+s1[i-1] or delete s2[j-1]
    dp[i][j] = min(dp[i-1][j]+s1[i-1], dp[i][j-1]+s2[j-1]);
    
    e.g: s2= sea    s1=eat
     ''  s     e                  a
  ''  0  21  10+21              31+1
  e  10  31  21                 21+1 (either we can delete e: 32+e or delete a: 21+1)
  a  11  42  22(delete s,a)     21
  t  33  ...

the key is at dp[i][j] if we want to delete s1[i-1] we should use dp[i-1][j] +s1[i-1]
                       if we want to delete s2[i-1] we should use dp[i][j-1] +s2[j-1]  
dp[i][j] means the min sum we need to delete to make s1 equal s2
*/

class Solution {
  public int minimumDeleteSum(String s1, String s2) {
      int m = s1.length()+1, n = s2.length()+1;
      int[][] dp= new int[m][n];
      for(int i=1; i<m; i++) {
          dp[i][0] = dp[i-1][0] + (int)(s1.charAt(i-1)); 
      }
      for(int i=1; i<n; i++) {
          dp[0][i] = dp[0][i-1] + (int)(s2.charAt(i-1)); 
      }
      
      for(int i=1; i<m; i++) {
          for(int j=1; j<n; j++) {
              if(s1.charAt(i-1) == s2.charAt(j-1)) {
                  dp[i][j] = dp[i-1][j-1]; 
              } else {
                  dp[i][j] = Math.min(dp[i-1][j] + (int)s1.charAt(i-1), dp[i][j-1] + (int)s2.charAt(j-1));
              }
          }
      }
      return dp[m-1][n-1];
  }
}
