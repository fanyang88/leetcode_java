/**
 * 
Given a string s, find the longest palindromic substring in s. 
You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */

class Solution {
  private int lo, maxLen;
  public String longestPalindrome(String s) {
      if(s.length() < 2)  return s;
      
      // start from each position and go the further left and right
      for(int i=0; i<s.length()-1; i++) {
          getPalindrome(s, i, i);
          getPalindrome(s, i, i+1);
      }
      return s.substring(lo, maxLen+lo);
  }
  
  public void getPalindrome(String s, int l, int r) {
      while(l >=0 && r<s.length() && s.charAt(l) == s.charAt(r)) {
          l--;
          r++;
      }
      l++;
      r--;
      if(maxLen < r- l +1) {
          maxLen = r - l +1;
          lo = l;
      }
  }
}

