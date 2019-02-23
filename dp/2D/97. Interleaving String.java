/**
 Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
 */

 /*
e.g:  s2 = "aabcc", s1 = "dbbca", s3 = "aadbbcbcac"

        ""    a             a            b               c               c
    ""  T   s1[0]=s3[0]T  s1[1]=s3[1]T s1[2]!=s3[2]F    F               F .    dp[0][i-1] && s1[i-1] == s3[i-1]
    d   F   s2[0]!=s3[1] 
            s1[0]=s3[1]&T=T
    b   F
    b   F
    c   F
    a   F
    
    dp[i][j] = s1[i-1]== s3[i+j-1] && dp[i-1][j]  ||    compare with s1 
               s1[j-1]== s3[i+j-1] && dp[i][j-1]        compare with s2
        
*/

class Solution {
  public boolean isInterleave(String s1, String s2, String s3) {
      int m= s1.length(), n= s2.length();
      if(m+n!=s3.length())  return false;
      boolean[][] dp = new boolean[m+1][n+1];
      dp[0][0] = true;
      
      // init first row
      for(int i=1; i<=n; i++) {
          dp[0][i] = dp[0][i-1] && s2.charAt(i-1) == s3.charAt(i-1);
      }
      
      // init first col
      for(int i=1; i<=m; i++) {
          dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
      }
      
      // cacluate the rest
      for(int i=1; i<=m; i++) {
          for(int j=1; j<=n; j++) {
              dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1) ||
                         dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
          }
      }
      return dp[m][n];    
  }
}