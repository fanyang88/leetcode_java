/*
Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

Example 1:

Input: 
S = "abcdebdde", T = "bde"
Output: "bcde"
Explanation: 
"bcde" is the answer because it occurs before "bdde" which has the same length.
"deb" is not a smaller window because the elements of T in the window must occur in order.
 

Note:

All the strings in the input will only contain lowercase letters.
The length of S will be in the range [1, 20000].
The length of T will be in the range [1, 100].
 
*/

/*
    S = abcdebdde, T = bde
    i=0 a not equal to b, skip
    i=1 pt=0 since s[1]==T[0] i++=2, pt++=1 
        s[2]!=T[1] i++ s[3]==T[1] i++=4, pt++=2 since s[4]==T[2] i++=5, pt++=3 
        pt=T.length, we find a match, need to caculate the length
        end = i=5 i--, pt--  since S[4]==T[2] i--, pt--
        since S[3]==T[1] i--, pt-- 
        since S[2]!=T[0] i--,
        since S[1]==T[0] i--, pt--
        pt < 0 i=i+1=1  i point to the start, len = end-start, pt back to move to 0
        
        next time we start from i+1 to compare
        

*/

class Solution {
  public String minWindow(String S, String T) {
      String min = S+"dummy";//.length();
      for(int i=0, j=0; i<S.length(); i++) {
          if(S.charAt(i) == T.charAt(j)) {  
              j++;
              if(j == T.length()) {  // find a match
                  j = T.length() - 1;
                  int end = i;    // backward match S and T till reach to the -1 postion of T
                  while(j>=0) {
                      if(S.charAt(i) == T.charAt(j)) {
                          i--;
                          j--;
                      } else {
                          i--;
                      }
                  }
                  i++;
                  j = 0;
                   // now i point to the start of the match
                  if(S.substring(i, end+1).length() < min.length()) {
                      min = S.substring(i, end+1);
                  } 
              }
          }
      }
      return min.equals(S+"dummy") ? "" : min;
  }
}
