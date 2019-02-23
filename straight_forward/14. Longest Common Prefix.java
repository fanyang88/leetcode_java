/**
 Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 */

class Solution {
  public String longestCommonPrefix(String[] strs) {
      if(strs.length==0)  return "";
      String res = strs[0];
      for(int i=1; i<strs.length; i++) {
          res = getCommon(res, strs[i]);
      }
      return res;
  }
  
  public String getCommon(String s1, String s2) {
      int end = s1.length() < s2.length() ? s1.length() : s2.length();
      for(int i=0; i<s1.length() && i<s2.length(); i++) {
          if(s1.charAt(i) != s2.charAt(i)) {
              end = i;
              break;
          }
      }
      return s1.substring(0, end);
  }
}
