/*
Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

Example 1:

Input: 5
Output: 2
Explanation: 5 = 5 = 2 + 3
Example 2:

Input: 9
Output: 3
Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4
Example 3:

Input: 15
Output: 4
Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
Note: 1 <= N <= 10 ^ 9.
*/

/*
 The thought process goes like this- Given a number N, 
 we can possibly write it as a sum of 2 numbers, 3 numbers, 4 numbers and so on. 
 Let's assume the fist number in this series be x. Hence, we should have
x + (x+1) + (x+2)+...+ (x+k) = N
kx + (0+k)*(k-1)/2 = N => kx = N - k*(k-1)/2

since N - k*(k-1)/2 > 0 which implies
k*(k-1) < 2N which can be approximated to
k*k < 2N ==> k < sqrt(2N)
Hence the overall complexity of the algorithm is O(sqrt(N))
*/

class Solution {
  public int consecutiveNumbersSum(int N) {
      int count=0;
      for(int k=1; k < Math.sqrt(2*N); k++) {
          if((N - k*(k-1)/2) % k ==0) count++;
      }
      return count;
  }
}
