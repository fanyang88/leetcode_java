/**
 * 
 A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false

 */

class Solution {
  public boolean isStrobogrammatic(String num) {
      int lo=0, hi=num.length()-1;
      while(lo < hi) {
          char c1 = num.charAt(lo);
          char c2 = num.charAt(hi);
          if((c1=='0' && c2=='0') || 
             (c1=='6' && c2=='9') || 
             (c1=='9' && c2=='6') || 
             (c1=='8' && c2=='8') || 
             (c1=='1' && c2=='1')) {
              lo++;
              hi--;
          } else {
              return false;
          }
      }
      if(lo!=hi)  return true;
      char chr = num.charAt(lo);
      if(chr == '0' || chr == '1' || chr == '8')  return true;
      return false;
  }
}
