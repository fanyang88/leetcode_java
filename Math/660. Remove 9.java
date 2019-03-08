/*
Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...

So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...

Given a positive integer n, you need to return the n-th integer after removing. Note that 1 will be the first integer.

Example 1:
Input: 9
Output: 10
Hint: n will not exceed 9 x 10^8.
*/

/*
    basically we need to change 10 digit radix to be based on 9
    template below: convert a 10 based number to br k based number
    if n=2 ans=2%9*1=2
    if n=12 ans=12%9*1=3 n=12/9=1  ans+=1%9*10=10+3 . answer=13
    ...
    base =1 
    ans += n % k * base  
    n= n/k
    base = base *10;
    
    ans is the new radix number
*/


class Solution {
  public int newInteger(int n) {
      int k = 9, base =1, ans=0;
      while(n > 0) {
          ans += (n % k) * base;
          n = n/k;
          base *=10;
      }
      return ans;
  }
}

class Solution {
  public int newInteger(int n) {
      int k = 9;
      String num = Integer.toString(n, k);
      return Integer.parseInt(num);
  }
}
