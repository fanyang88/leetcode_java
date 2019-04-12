/*
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

Example 1:

Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:

Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:

Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:

Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:

1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
*/

class Solution {
  public boolean backspaceCompare(String S, String T) {
      String s= "", t= "";
      for(char c: S.toCharArray()) {
          if(c == '#') {
              if(s.length() > 0) s= s.substring(0, s.length()-1);
          } else {
              s = s + c;
          }
      }
      for(char c: T.toCharArray()) {
          if(c == '#') {
              if(t.length() > 0) t= t.substring(0, t.length()-1);
          } else {
              t = t + c;
          }
      }
      return s.equals(t);
  }
}
