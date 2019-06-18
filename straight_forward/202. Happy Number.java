/**
 * Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, 
replace the number by the sum of the squares of its digits, 
and repeat the process until the number equals 1 (where it will stay),
 or it loops endlessly in a cycle which does not include 1. 
 Those numbers for which this process ends in 1 are happy numbers.

Example: 

Input: 19
Output: true
Explanation: 
1*1+9*9=82
8*8 + 2*2=68
6*6 + 8*8=100
1*1+0*0+0*0=1

 */

class Solution {
  public boolean isHappy(int n) {
      HashSet<Integer> set = new HashSet<Integer>();
     
      while(true) {
          int sum = getSum(n);
          if(sum==1) return true;
          if(!set.add(sum))  break;
          n = sum;
      }
      return false;
  }
  
  public int getSum(int num) {
      int sum=0;
      while(num > 0) {
          int val = num % 10;
          sum+= val*val;
          num = num / 10;
      }
      return sum;
  }
}
