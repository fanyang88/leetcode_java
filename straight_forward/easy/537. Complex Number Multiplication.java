/*
Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:

The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.

*/

/*
        1+1i", "1+1i"
        e.g: a=1 b=1 c=1 d=1
        integer part: a*c + b*d*(-1) 
         i . part: a*d + b*c
         
         answer = a*c + b*d*(-1)  + (a*d + b*c)i
*/

class Solution {
  public String complexNumberMultiply(String a, String b) {
      int A = Integer.valueOf(a.substring(0, a.indexOf("+")));
      int B = Integer.valueOf(a.substring(a.indexOf("+")+1, a.length()-1));
      int C = Integer.valueOf(b.substring(0, b.indexOf("+")));
      int D = Integer.valueOf(b.substring(b.indexOf("+")+1, b.length()-1));
      int first = A*C + B*D*(-1);
      int sec = A*D + B*C;
      return  first + "+" + sec + "i";
      
  }
}
