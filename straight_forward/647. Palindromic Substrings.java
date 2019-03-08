/*
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Note:

The input string length won't exceed 1000.
 
*/

/*
  aaa
  i=0 a aa
  i=1 a aaa |  aa
  i=2 a
*/

class Solution {
  public int countSubstrings(String s) {
      int count=0;
      for(int i=0; i<s.length(); i++) {
          count+= countPalindrome(i, i, s);
          count+= countPalindrome(i, i+1, s);
      }
      return count;
  }
  
  public int countPalindrome(int pos1, int pos2, String s) {
      int i=pos1, j=pos2, count=0;
      while(pos1 >=0 && pos2 < s.length() && s.charAt(pos1) == s.charAt(pos2)) {
          pos1--;
          pos2++;
          count++;
      }
      return count;
  }
}

