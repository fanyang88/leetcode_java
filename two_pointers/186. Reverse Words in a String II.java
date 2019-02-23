/**
 * Given an input string , reverse the string word by word. 

Example:

Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
Note: 

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?
 */

 /*
    ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
    reverse whole string first
    then reverse each word within
*/

class Solution {
  public void reverseWords(char[] str) {
      reverse(0, str.length-1, str);
      int j=0;
      for(int i=0; i<str.length; i++) {
          if(str[i] == ' ') {
              reverse(j, i-1, str);
              j=i+1;
          }
      }
      reverse(j, str.length-1, str);
      return;
  }
  
  public void reverse(int lo, int hi, char[] str) {
      while(lo < hi) {
          char temp = str[lo];
          str[lo] = str[hi];
          str[hi] = temp;
          lo++;
          hi--;
      }
  } 
}