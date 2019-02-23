/**
 * 
 Given two integers dividend and divisor, divide two integers without using multiplication, 
 division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer 
range: [−231,  231 − 1]. For the purpose of this problem, 
assume that your function returns 231 − 1 when the division result overflows.
 */

 /*
    e.g: dividend = 309   divisor = 3
    1. rest=309 . loop newDivisor*10<309 newDivisor=30 step=10 
                       newDivisor*10<309 newDivisor=300 step=100
                       newDivisor*10>309 stop count=100 rest=309-newDivisor=9
       rest=9 > 3 newDivisor*10>9 count+=1=101 rest=9-3=6
       rest=6 > 3 newDivisor*10>9 count+=1=102 rest=6-3=3
       rest=3 = 3 stop loop  count++=103 is the answer
       
       corner case: -2147483648/-1= 2147483647
                    2147483647/-1=-2147483648
*/
class Solution {
  public int divide(int dividend, int divisor) {
  int flag = (dividend>0 && divisor < 0) || (dividend<0 && divisor> 0) ? -1 : 1;
  
  // Handle special cases first
   if(dividend== -2147483648 && Math.abs(divisor) == 1)  return divisor == 1 ? -2147483648 : 2147483647;
   if(dividend== 2147483647 && Math.abs(divisor) == 1)  return divisor == 1 ? 2147483647 : -2147483648;
  
  long ldivisor = Math.abs((long)divisor);
  long ldividend = Math.abs((long)dividend);
  long count = 0;
  
  while(ldividend > ldivisor){
      long newDivisor = ldivisor;
      long step=1;
      while(newDivisor * 10 < ldividend) {
          newDivisor *= 10;
          step *= 10;
      }
      count += step;
      ldividend -= newDivisor;
  }
  if(ldividend == ldivisor) count++;
  return (int)count*flag;
  }
}
