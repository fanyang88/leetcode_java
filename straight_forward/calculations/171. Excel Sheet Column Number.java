/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
Example 1:

Input: "A"
Output: 1
Example 2:

Input: "AB"
Output: 28
Example 3:

Input: "ZY"
Output: 701
 */


/*
Input: "AB"
Output: 28

A
*/
class Solution {
  public int titleToNumber(String s) {
      int sum=0;
      char[] chrs = s.toCharArray();
      for(int i=0; i<chrs.length; i++) {
          sum*=26;
          sum+=chrs[i] - 'A'+1;
      }
      return sum;
  }
}
