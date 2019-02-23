/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false

 */

 /*
    e.g: s= "hatmeof"  ['hat', 'me', 'of'] 
    when it is an empty string, it is in the list, so dp[0] = T
    when len=1 dp[1] = dp[0] & substr(0, 1) not in list = F
    
    when len=2 dp[2] = dp[0] & substr(0, 2) not in list = F
                       dp[1] & substr(1, 2) not in list = F
                       dp[2] = F
                       
    when len=3 dp[3] = dp[0] & substr(0, 3) in list = T 
                       dp[3] = T
    when len=4 dp[4] = dp[0] & substr(0, 4) not in list = F
                       dp[1] & substr(1, 4) not in list = F
                       dp[2] & substr(2, 4) not in list = F
                       dp[3] & substr(3, 4) not in list = F
                       dp[4] = F
                       ....

when i=5 (check hatme)
k=0: p[5]= p[0](true) && s.sub(0+1, 5-0)[hatme](false) =false
k=1: p[5]= p[1](false) && s.sub(1+1, 5-1)[atme](false) =false
k=2: p[5]= p[2](false) && s.sub(2+1, 5-2)[tme](false) =false
k=3: p[5]= p[3](true) && s.sub(3+1, 5-3)[me](true) =true
    p[5]= true, break
    
when i=6 (check hatmeo)
k=0: p[6]= p[0](true) && s.sub(0+1, 6-0)[hatmeo](false) =false
k=1: p[6]= p[1](false) && s.sub(1+1, 6-1)[atmeo](false) =false
k=2: p[6]= p[2](false) && s.sub(2+1, 6-2)[tmeo](false) =false
k=3: p[6]= p[3](true) && s.sub(3+1, 6-3)[meo](false) =false
k=4: p[6]= p[4](false) && s.sub(4+1, 6-4)[eo](false) =false
k=5: p[6]= p[5](true) && s.sub(5+1, 6-5)[o](false) =false
    p[6]= false, end loop
    
when i=7 (check hatmeof)
k=0: p[7]= p[0](true) && s.sub(0+1, 7-0)[hatmeof](false) =false
k=1: p[7]= p[1](false) && s.sub(1+1, 7-1)[atmeof](false) =false
k=2: p[7]= p[2](false) && s.sub(2+1, 7-2)[tmeof](false) =false
k=3: p[7]= p[3](true) && s.sub(3+1, 7-3)[meof](false) =false
k=4: p[7]= p[4](false) && s.sub(4+1, 7-4)[eof](false) =false
k=5: p[7]= p[5](true) && s.sub(5+1, 7-5)[of](true) =true
    p[6]= true, break
    
since p[n-1]= true, can be segmented
*/

class Solution {
  public boolean wordBreak(String s, List<String> wordDict) {
      int n = s.length();
      boolean[] dp = new boolean[n+1];
      dp[0] = true;
      for(int len=1; len<=n; len++) {
          for(int i=0; i<len; i++) {
              if(dp[i] && wordDict.contains(s.substring(i, len))) {
                  dp[len] = true;
                  break;
              }
          }
      }
      return dp[n];
  }
}
