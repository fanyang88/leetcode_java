/*
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

Example 1:

Input: "112358"
Output: true 
Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
Example 2:

Input: "199100199"
Output: true 
Explanation: The additive sequence is: 1, 99, 100, 199. 
             1 + 99 = 100, 99 + 100 = 199
Follow up:
How would you handle overflow for very large input integers?
*/


class Solution {
  public boolean isAdditiveNumber(String num) {
      int n = num.length();
      if(n <=2)  return false;
      
      for(int len1 =1; len1 <= n/2; len1++) {
           if( num.charAt(0) =='0' && len1>1) return false;
           long v1 = Long.valueOf(num.substring(0, len1));
          
          for(int len2=1; n-len1-len2 >= Math.max(len1, len2); len2++) {
              if(num.charAt(len1) =='0' && len2>1) break;  // this is break, not return false;
              
              long v2 = Long.valueOf(num.substring(len1, len1+len2));
              if(isValid(v1, v2, len1+len2, num))  return true;
          }
      }
      return false;
  }
  
  public boolean isValid(long v1, long v2, int index, String s) {
      if(index == s.length())  return true;
      long sum = v1 + v2;
      String str = String.valueOf(sum);
      
      if(!s.substring(index).startsWith(str))  return false;
      return isValid(v2, sum, str.length()+index, s);
  }
}


