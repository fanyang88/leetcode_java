/*
A self-dividing number is a number that is divisible by every digit it contains.

For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.

Also, a self-dividing number is not allowed to contain the digit zero.

Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.

Example 1:
Input: 
left = 1, right = 22
Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
Note:

The boundaries of each input argument are 1 <= left <= right <= 10000.
*/

class Solution {
  public List<Integer> selfDividingNumbers(int left, int right) {
      List<Integer> res = new ArrayList<>();
      for(int n = left; n<=right; n++) {
          int i=n;
          for(; i>0 ;i = i / 10) {
              // e.g n=22 i=22 since i%10!=0 and n % (i % 10) ==0  continue check another 2
              // 2 % 10!=0 && n % (i % 10) ==0  continue check another , now i=0 stop
              if(i%10 ==0 || n % (i % 10) !=0) break; //can not be divide, break
          }
          if(i==0)  res.add(n);
      }
      return res;
  }
}
