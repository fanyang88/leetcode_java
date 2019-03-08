/*
Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.

 

Example 1:

Input: "Hello"
Output: "hello"
Example 2:

Input: "here"
Output: "here"
Example 3:

Input: "LOVELY"
Output: "lovely"
*/

class Solution {
  public String toLowerCase(String str) {
      String res = "";
      for(char c: str.toCharArray()) {
          if(c <= 'Z' && c >= 'A') {
              c = (char)(c - 'A' +'a');
          }
          res = res+ c;
      }
      return res;
  }
}
