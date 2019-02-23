/*
Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

Note:
The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.
Example 1:
Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
Example 2:
Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
*/

/*
   101
   since i=0 < 5 i=1 j=1
   since i=2 < 5 i=2+1=3 j=2
   since i=3 < 5 i=3+2^2=7 j=3
   i=7=111 > 5 
   111 -101 =010

*/

class Solution {
  public int findComplement(int num) {
      int i=0, j=0;
      while( i < num) {
          i += Math.pow(2, j++);
      }
      return i-num;
  }
}
