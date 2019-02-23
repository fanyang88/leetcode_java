/*
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example 1:

Input: a = 1, b = 2
Output: 3
Example 2:

Input: a = -2, b = 3
Output: 1
*/

/*
e.g:  a = 1, b= 3
       a = 0001 b =0011  carry = a & b= 0001, a = a ^b = 0010   b= carry >> 1 = 0010
       a = 0010 b =0010  carry = a & b = 0010  a = a ^ b = 0000 b = carry >> 1 = 0100
       a = 0000 b =0100  carry = a & b = 0000  a = a ^ b = 0100 b = carry >> 1 = 0000
       a is the answer
       termination condition is when b==0
*/

class Solution {
  public int getSum(int a, int b) {
      return b==0 ? a : getSum(a^b, (a&b)<<1);
  }
}
