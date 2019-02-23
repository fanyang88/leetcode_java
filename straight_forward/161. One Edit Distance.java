/**
 * Given two strings s and t, determine if they are both one edit distance apart.

Note: 

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
Example 3:

Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.
 */
/*
 * There're 3 possibilities to satisfy one edit distance apart: 
 * 
 * 1) Replace 1 char:
 	  s: a B c
 	  t: a D c
      i!=j check if i+1 to end == j+1 to end (t.length == s.length)
 * 2) Delete 1 char from s: 
	  s: a D  b c
	  t: a    b c
      i!=j check if i+1 to end == j to end  (t.length < s.length)
 * 3) Delete 1 char from t
	  s: a   b c
	  t: a D b c
      i!=j check if i to end == j+1 to end  (t.length > s.length)
*/

class Solution {
  public boolean isOneEditDistance(String s, String t) {
      for(int i=0; i<Math.min(s.length(), t.length()); i++) {
          if(s.charAt(i) != t.charAt(i)) {  
              if(s.length() == t.length()) {
                  return s.substring(i+1).equals(t.substring(i+1));
              } else if(s.length() > t.length()) {
                  return s.substring(i+1).equals(t.substring(i));
              } else {
                  return s.substring(i).equals(t.substring(i+1));
              }
          }
      }
      // if all chars are equal
      return Math.abs(s.length()- t.length()) == 1;
  }
}

