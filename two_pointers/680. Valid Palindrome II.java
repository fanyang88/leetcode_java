/*
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
*/

class Solution {
  public boolean validPalindrome(String s) {
      int i=0, j=s.length()-1;
      while(i < s.length() && j>=0 && s.charAt(i) == s.charAt(j)) {
          i++;
          j--;
      }
      if(i >= j)  return true;
      if(isPalindrome(i+1, j, s) || isPalindrome(i, j-1, s)) return true;
      return false;
  }
  
  public boolean isPalindrome(int i, int j, String s) {
      while(i < j) {
          if(s.charAt(i) != s.charAt(j))  return false;
          i++;
          j--;
      }
      return true;
  }
}
