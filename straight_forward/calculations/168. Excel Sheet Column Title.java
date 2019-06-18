/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
Example 1:

Input: 1
Output: "A"
Example 2:

Input: 28
Output: "AB"
Example 3:

Input: 701
Output: "ZY"
 */


/*
   28:
   n--=27 27%26=1 res+=B n=27/26=1
   n=--=0 0%26=0 res=A+B=AB
*/
class Solution {
  public String convertToTitle(int n) {
      String res = "";
      while(n > 0) {
          n--;
          res = (char)('A' + n%26) + res;
          n= n/26;
      }
      return res;
  }
}
