/*
Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

Example:

Input:  [1,2,1,3,2,5]
Output: [3,5]
Note:

The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
*/

/*
       Assume the two numbers are a, b
        The Key is to divide the array into two parts, 
            one subarray related to a, all elements XOR a = a
            one subarray related to b, all elements XOR b = b
            
        1. XOR all numbers = x = a XOR b
        2. since there must be one place that a and b are not same, say a =1 in y digit b=0 in y digit
           means we need to find the first digit from right to left that =1, that digit is the place where a & b are different
           x&(~(x-1)) = x 
           e.g:    a=10100, b=10000 => x = 00100  x & a = 100>0   x&b=0 
           based on x, we can sepeate the numbers into two groups, and each group xor within, then we can get the answer
            
        [1,2,1,3,2,5]
        a=3 b=5 a XOR b = 110 
        6 & (~5)=  110 & 010= 10 
        01 & 10 = 0   group(a): 1
        10 & 10 > 0 . group(b): 2
        01 & 10 = 0   group(a): 1, 1
        11 & 10 > 0 . group(b): 2, 3
        10 & 10 > 0   group(a): 2,3,2
        101 & 10= 0 . group(b): 1,1,5
        
*/

class Solution {
  public int[] singleNumber(int[] nums) {
      int[] res ={0,0};
      int x= 0;
       for(int num : nums) {
           x = x ^ num;
       }
      x = x &(~(x-1));
      for(int num : nums) {
          if((num & x) > 0) {
              res[0] = res[0] ^ num;
          } else {
              res[1] = res[1] ^ num;
          }
      }
      return res;
  }
}