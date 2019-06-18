/*
Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

 

Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
Example 2:

Input: "aba"
Output: False
Example 3:

Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/

/*
         e.g: ababab
         sub = aba
         since sub+sub!=s  sub--=ab
         since sub+sub+sub ==s return true
         
*/

class Solution {
  public boolean repeatedSubstringPattern(String s) {
      int n = s.length();
      for(int i=n/2; i>=1; i--) {
          if(n % i ==0) {
              String sub = s.substring(0, i), cur = "";
              while(cur.length() < n) {
                  cur += sub;
              }
              if(cur.equals(s))  return true;
          }
      }
      return false;
  }
}
