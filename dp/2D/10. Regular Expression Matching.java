/**
 Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */

 /*
the * case is the most complicated, using two example to sort it out: 
 e.g: x & xa* is match, since there can be 0 or more a   
      i     j
      for such case when p[j]=== '*' since it is 0 occrence of a, 
      we need to check p(0, j-2)(x) & T(0, i)(x) = dp[i][j-2]

      xa & xa* is matching
       i     j
      we need to check p[j-1] ?= t[i] if they equal, 
      then we need to check p(0, j)xa* & T(0, i-1)x = dp[i-1][j]
      
           x a * b . c
        T  F F F F F F
      x F  T F T F F F   => x vs xa* =dp[1][3-2]=T, case 1
      a F  F T T          => since p[j-1]=a=T[i]  we need to check dp[1][3], case 2
      a F
      b F
      y F
      c F
*/

class Solution {
  public boolean isMatch(String s, String p) {
      int m = s.length(), n=p.length();
      boolean[][] dp = new boolean[m+1][n+1];
      dp[0][0] = true;
      // deal with '' match *a, etc
      for(int i=1; i<=n; i++) {
          if(p.charAt(i-1) == '*') {
              dp[0][i] = dp[0][i-2];
          }
      }
      for(int i=1; i<=m; i++) {
          for(int j=1; j<=n; j++) {
              dp[i][j] = false;
              if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                  dp[i][j] = dp[i-1][j-1];
              } else if(p.charAt(j-1) == '*') {
                  dp[i][j] = dp[i][j-2];
                  if(s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') {
                      dp[i][j] = dp[i-1][j] | dp[i][j]; // The key
                  } 
              }
          }
      }
      return dp[m][n];
  }
}