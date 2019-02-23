/**
 Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
 return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

Example:

Input: "Hello World"
Output: 5
 */

class Solution {
  public int lengthOfLastWord(String s) {
      int n= s.length(), maxLen = 0, i=n-1;
      while(i>=0 && s.charAt(i) == ' ') i--;
      int end = i;
      while(i>=0 && s.charAt(i) != ' ') i--;
      maxLen = Math.max(maxLen, end-i);
      return maxLen;
  }
}