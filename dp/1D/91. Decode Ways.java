/**
 A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */


/*  i:   0 1 2 3
   e.g:  1 2 3 1
        
  dp[0] = 1 pre-processing
  dp[1] = first char !=0 ? 1 : 0;
  i=2
  one word = 2 = substring(i-1, i) , if it is within 1~9 dp[2] += dp[1]
  two word = 12 = substring(i-2, i), if it is within 10~26 dp[2] += dp[0] 
  dp[2]=2
  i=3
  one word = 3 = substring(i-1, i) , if it is within 1~9 dp[3] += dp[2]
  two word = 23 = substring(i-2, i), if it is within 10~26 dp[3] += dp[1] 
  dp[3]=3
  i=4
  one word = 1 = substring(i-1, i) , if it is within 1~9 dp[4] += dp[3]
  two word = 31 = substring(i-2, i), not within 10~26 
  dp[4] = 3
*/
class Solution {
  public int numDecodings(String s) {
      int n = s.length();
      if(n ==0)  return 0;
      
      int[] dp= new int[n+1];
      dp[0] = 1;
      dp[1] = s.charAt(0) == '0' ? 0 : 1;
      for(int i=2; i<=n; i++) {
          int one = Integer.valueOf(s.substring(i-1, i));
          int two = Integer.valueOf(s.substring(i-2, i));
          if(one <=9 && one >=1) {
              dp[i] +=dp[i-1];
          } 
          if(two <=26 && two >=10) {
              dp[i] +=dp[i-2];
          }  
      }
      return dp[n];
  }
}

// O(1)
class Solution {
  public int numDecodings(String s) {
      int n = s.length();
      if(n ==0)  return 0;
      
     // int[] dp= new int[n+1];
      int dp0 = 1;
      int dp1 = s.charAt(0) == '0' ? 0 : 1;
      for(int i=2; i<=n; i++) {
          int one = Integer.valueOf(s.substring(i-1, i));
          int two = Integer.valueOf(s.substring(i-2, i));
          int cur = 0;
          if(one <=9 && one >=1) {
              cur +=dp1;
          } 
          if(two <=26 && two >=10) {
              cur +=dp0;
          }  
          dp0 = dp1;
          dp1 = cur;
      }
      return dp1;
  }
}
