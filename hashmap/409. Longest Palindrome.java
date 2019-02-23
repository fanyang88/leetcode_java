/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.

*/

class Solution {
  public int longestPalindrome(String s) {
      Set<Character> set = new HashSet<>();
      int pairs=0;
      for(char c: s.toCharArray()) {
          if(set.contains(c)) {
              set.remove(c);
              pairs++;
          } else {
              set.add(c);
          }
      }
      return set.isEmpty() ? pairs*2 : pairs*2+1;
  }
}
