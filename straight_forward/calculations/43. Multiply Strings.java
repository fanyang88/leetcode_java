/**
 Given two non-negative integers num1 and num2 represented as strings, 
 return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

 /*
e.g:           0       1       2   - index
         /////////////////////////
                1       2       3
                        4       5
         --------------------------------
                        1       5  (3*5)
                1       0          (2*5)
                5                  (1*5)
         ------------------------------
                1       2
                8
        4
  ----------------------------------
        5       5       3       5    sum is 5535
    we use an array to store the sum in each bit first
*/

class Solution {
  public String multiply(String num1, String num2) {
      int m = num1.length(), n=num2.length(), carry = 0;
      int[] arr = new int[m+n];
      
      for(int i=m-1; i>=0; i--) {
          for(int j=n-1; j>=0; j--) {
              int val = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
              arr[i+j] += val / 10;
              arr[i+j+1] += val % 10;
          }
      }
      for(int i=m+n-1; i>=0; i--) {
          arr[i] +=carry;
          // the order matters.
          carry = arr[i] / 10;
          arr[i] = arr[i] % 10;
      }
      
      StringBuilder sb= new StringBuilder();
      // collect all numbers and append to sb, remove the trailing 0 in front
      int k=0;
      while(k<arr.length && arr[k] == 0) k++;
      
      for(;k<arr.length; k++) {
          sb.append(arr[k]);
      }
      return sb.length() != 0 ? sb.toString() : "0";
  }
}