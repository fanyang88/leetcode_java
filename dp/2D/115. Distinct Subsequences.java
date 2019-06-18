/**
 * 
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example 1:

Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:

As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
Example 2:

Input: S = "babgbag", T = "bag"
Output: 5
Explanation:

As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
 */

 /*
e.g         ""     b    a   b   g   b   a   g 
     ""     1      1    1   1   1   1   1   1
     b      0      1    1   2   2   3   3   3        e.g: ba&b = b&b=1, bab&b = ba&b + ba&""=2
     a      0      0    1   1   1   1   4   4             b&ba=0(T>S)   ba&ba=b&b + b&ba=1
     g      0
     
     if(s[i] = t[j]) dp[i][j] = dp[i][j-1](skip t[j]) + dp[i-1][j-1] (skip s[i] and t[j])
     
            ""     r    a   b   b   b   i   t
     ""     1      1    1   1   1   1   1   1
     r      0      1    1   1   1   1   1   1
     a      0      0    1   1   1   1   1   1
     b      0      0    0   1   2   3   3   3             rabb&rab = rab&ra(=1) + rab&rab(=1)
     b      0
     i      0
     t      0
*/

class Solution {
  public int numDistinct(String s, String t) {
      int n = s.length(), m= t.length();
      int[][] dp = new int[m+1][n+1];
      // init first row 
      for(int i=0; i<=n; i++) dp[0][i] = 1;
      
      for(int i=1; i<=m; i++) {
          for(int j=1; j<=n; j++) {
              dp[i][j] = dp[i][j-1];
              if(s.charAt(j-1) == t.charAt(i-1)) {
                  dp[i][j] += dp[i-1][j-1];
              }
          }
      }
      return dp[m][n];
  }
}