/*
Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".
Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
*/

/*
b b b a b
b a b b b 
    use reverse string compare with orginal string
 for example:   b b b a b
             b  4 3 3 2 1 0
             a  3 3 2 2 1 0
             b  3 3 2 1 1 0
             b  2 2 2 1 1 0
             b  1 1 1 1 1 0
                0 0 0 0 0 0
if s[i] === T[j]  dp[i][j] = dp[i+1][j+1]+1;
else dp[i][j] = Max(dp[i+1][j], dp[i][j+1]);
    
*/

class Solution {
  public int longestPalindromeSubseq(String s) {
      int n = s.length();
      StringBuilder sb = new StringBuilder(s);
      String t = sb.reverse().toString(); // This is the key, we need to compare the original with the reversed.
      int[][] dp = new int[n+1][n+1];
      for(int i=n-1; i>=0; i--) {
          for(int j=n-1; j>=0; j--) {
              if(s.charAt(i) == t.charAt(j)) {
                  dp[i][j] = dp[i+1][j+1]+1;
              } else {
                  dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
              }
          }
      }
      return dp[0][0];
  }
}
