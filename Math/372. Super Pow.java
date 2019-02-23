/*
Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

Example 1:

Input: a = 2, b = [3]
Output: 8
Example 2:

Input: a = 2, b = [1,0]
Output: 1024
*/

/*
  since  (a*b)%c =(a%c)*(b%c) =  {(a%c)*(b%c)}%c。

  so (a^3)%c = (a%c) * ( (a^2)%c) %c
  
  Input: a = 2, b = [1,0, 0]
  i=0  res=1 pow(1, 10)*2=2 . res=2
  i=1  res=2 pow(2, 10)*pow(2, 0)=1024
  i=2 res=1024 res= pow(1024, 10)*pow(2*0)
  
*/

class Solution {
  int ans = 1;
  public int superPow(int a, int[] b) {
      int res=1;
      for(int i=0; i<b.length; i++) {
          res = pow(res, 10) * pow(a, b[i]) % 1337;
      }
      return res;
  }
  
  public int pow(int a, int b) {
      if(b==0)  return 1;
      if(b==1)  return a % 1337;
      return pow(a%1337, b/2)*pow(a%1337, b-b/2) % 1337;
  }
}
