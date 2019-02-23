/*
Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.

Example:

Input: n = 12, primes = [2,7,13,19]
Output: 32 
Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 
             super ugly numbers given primes = [2,7,13,19] of size 4.
Note:

1 is a super ugly number for any given primes.
The given numbers in primes are in ascending order.
0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
*/

/*
[2,3,5]
       dp[0] = 1
 i=1: i2=0, i3=0, i5=0  min = dp[i2]*2=2=dp[1] i2++=1
 i=2: i2=1, i3=0, i5=0 min = dp[i3]*3=3=dp[2] i3++=1
 i=3: i2=1, i3=1, i5=0 min = dp[i2]*2=4=dp[3] i2++=2
 i=4: i2=2, i3=1, i5=0 min = dp[i5]*5=5=dp[4] i5++=1
 i=4: i2=2, i3=1, i5=1 min = dp[i2]*2=6=dp[5] i2++=3  min also = dp[i3]*3 i3++=2
 
 k[1] = min( k[0]x2, k[0]x3, k[0]x5). The answer is k[0]x2. So we move 2's pointer to 1. Then we test:
 k[2] = min( k[1]x2, k[0]x3, k[0]x5). And so on. 
 Be careful about the cases such as 6, in which we need to forward both pointers of 2 and 3.
 This is the key!!!!

*/

class Solution {
  public int nthSuperUglyNumber(int n, int[] primes) {
      int[] dp = new int[n];
      int[] p = new int[primes.length];
      dp[0] = 1;
      
      for(int i=1; i<n; i++) {
          dp[i] = Integer.MAX_VALUE;
          for(int j=0; j<primes.length; j++) {
              dp[i] = Math.min(dp[i], dp[p[j]]*primes[j]);
          }
          for(int j=0; j<primes.length; j++) {
              if(dp[i] == dp[p[j]]*primes[j])  p[j]++;
          }
      }
      return dp[n-1];
  }
}
