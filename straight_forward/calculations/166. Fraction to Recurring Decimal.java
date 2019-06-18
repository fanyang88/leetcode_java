/**
 Given two integers representing the numerator and denominator of a fraction, 
 return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"
 */

 /*
    special cases: numerator ==0 
                   numerator = MIN_VALUE, denominator=-1
                   
    2, 3
    first caculate the integer part: res += 2/3=0
    since 2%3 !=0 res=res+'.'
    r= 2%3=2  map[2]=res.length=2 r=20 res+=20/3=6 r=r%d=2 
              since map[2] exist, we need to add () from 2 to the end, return res
    e.g: 1.4123123...
           34567
    we need to substring 4~7 which is 123 and put in bracket
*/

class Solution {
  public String fractionToDecimal(int numerator, int denominator) {
      // Handle corner cases
      if(numerator == 0)  return "0";
    
      // get integer part
      String res = "";
      res += ((numerator > 0) ^ (denominator > 0) ? "-" : "");
      long n = Math.abs((long)numerator);
      long d = Math.abs((long)denominator);
      res = res+ (n/d);
      long r = n %d;
      if(r==0)  return res;
      res += '.';
      
      // handle loop part
      HashMap<Long, Integer> map = new HashMap<Long, Integer>();
      while(r!=0) {
          if(map.containsKey(r)) {  // loop exist
              return res.substring(0, map.get(r)) + "(" + res.substring(map.get(r))+ ")";
          } else {
              map.put(r, res.length());
              r= r*10;
              res += (r/d);
              r = r %d;
          }
      }
      return res;
  }
}