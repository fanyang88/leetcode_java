/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
/*
e.g given string abcbm how to split it using min cut that each part would be a palindrome

                a   b   c    b   m
                0   1   2    3   4   index
          0     0   1   2    1   2    answer      
          1         0   1    0   1
          2             0    1   2
          3                  0   1
          4                      0
          
        when len=1 only 1 char in the string, e.g at[0,0]=a cut=0, so dp[i][i] = 0
        when len=2 e.g: ab since a!=b need 1, bc=1 cb=1 bm=1
        when len=3 abc= split at a= dp[0][0](a) + dp[1][2](bc) +1(cut itself)=2 \ min=2
                        split at b= dp[0][1](ab) + dp[2][2](c) +1(cut itself)=2 /
                   bcb is palindrome so it is dp[1][3]=0
                   cbm = split at c= dp[2][2](c) + dp[3][4](bm) +1(cut itself)=2
                         split at b= dp[2][3](cb) + dp[4][4](m) +1(cut itself)=2
        when len=4 abcb = split at a dp[0][3]=dp[0][0](a) + dp[1][3]+1=1
                          split at b dp[0][3]=dp[0][1](ab) + dp[2][3](cd)+1=3   => min=1
                          split at c dp[0][3]=dp[0][2](abc) + dp[3][3](d)+1=3
                   bcbm=1
        when len=5 abcbm = split at a
                           split at b
                           split at c
                           split at b
                           .....
        if(string(i, j) is palindrome) dp[i][j] = 0
        else dp[i][j] = 1+ Math.min(dp[i][k] + dp[k][j])    i<=k<=j
        
        loop 1: i=0 j=1 i=1 j=2 i=2 j=3 i=3 j=4
        loop 2: i=0 j=2 i=1 j=3 i=2 j=4
        loop 3: i=0 j=3 i=1 j=4
        loop 4: i=0 j=4
*/  

class Solution {
  public int minCut(String s) {
      int n = s.length();
      int[][] dp = new int[n][n];
      
      // no need to caculate diagnal
      for(int len=1;len<n; len++) {
          for(int i=0, j=i+len; j<n;j++, i++) {
              if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] ==0) {
                  dp[i][j] =0;
              } else {
                  int minV = Integer.MAX_VALUE;
                  for(int k=i; k<j; k++) {
                      minV = Math.min(minV, dp[i][k] + dp[k+1][j]);
                  }
                  dp[i][j] = minV +1 ;  // don't forget +1 which is the current cut
              }
          }
      }
      return dp[0][n-1];
  }
}
