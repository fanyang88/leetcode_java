/*
Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
Input: N = 10
Output: 9
Example 2:
Input: N = 1234
Output: 1234
Example 3:
Input: N = 332
Output: 299
*/

/*
    e.g:  332
          i=2 2<3 mark=2  change to 322
          i=1 2<3 mark=1  change to 222
          since mark=1 change all numbers from mark to end to be 9
          answer is 299
          
          10
          i=1 0<1 mark=1 change to 00
          since mark ==1 change all numbers from mark to end to be 9
          answer is 9

*/


class Solution {
  public int monotoneIncreasingDigits(int N) {
      int index = -1;
      char[] chrs = ("" + N).toCharArray();
      for(int i=chrs.length-1; i>=1; i--) {
          if(chrs[i] < chrs[i-1]) {
              index = i;
              chrs[i-1] --;
          }
      }
      if(index ==-1)  return N;
      for(int i=index; i<chrs.length; i++) chrs[i] = '9';
      return Integer.parseInt(String.valueOf(chrs));
  }
}
