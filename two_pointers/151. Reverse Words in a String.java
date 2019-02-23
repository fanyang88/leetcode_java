/**
 * Given an input string, reverse the string word by word.

Example:  

Input: "the sky is blue",
Output: "blue is sky the".
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
Follow up: For C programmers, try to solve it in-place in O(1) space.
 */

 /*
   two pointers
   012345678910
   _the blue__
             i  j
  i=10 j=11  since s[i] =' ' j--=10  i--=9
  i=9 j=10   since s[i] =' ' j--=9  i--=8
  i=8 j=9   since s[i]!=' ' but s[i-1]!=" " i--
  i=7 j=9   since s[i]!=' ' but s[i-1]!=" " i--
  i=6 j=9   since s[i]!=' ' but s[i-1]!=" " i--
  i=5 j=9   since s[i]!=' '  s[i-1]=" " res+= substring(i, j) j=i
  i=4 j=5   since s[i] =' ' j--=4  i--=3
  i=3 j=4   since s[i]!=' ' but s[i-1]!=" " i--
  i=2 j=4   since s[i]!=' ' but s[i-1]!=" " i--
  i=1 j=4   since s[i]!=' '  s[i-1]=" " res+= substring(i, j) j=i
  i=0 j=1   since s[i] =' ' j--=0  i--=-1 stop
 
  make j to point to the last space before a word and make i always point to the first char of that word.
*/

public class Solution {
  public String reverseWords(String s) {
      String res = "";
      for(int i=s.length()-1, j=s.length(); i>-1; i--) {
          if(s.charAt(i) == ' ') {
              j--;
          } else {
              if(i-1 < 0 || s.charAt(i-1) == ' ') {
                  res = res=="" ? s.substring(i, j) : res + " "+s.substring(i, j);
                  j=i;
              }
          }
      }
      return res;
  }
}
