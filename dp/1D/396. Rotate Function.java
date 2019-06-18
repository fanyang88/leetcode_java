/*
Given an array of integers A and let n to be its length.

Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

Calculate the maximum value of F(0), F(1), ..., F(n-1).

Note:
n is guaranteed to be less than 105.

Example:

A = [4, 3, 2, 6]

F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
*/


/*
    Consider we have 5 coins A,B,C,D,E

According to the problem statement
F(0) = (0A) + (1B) + (2C) + (3D) + (4E)
F(1) = (4A) + (0B) + (1C) + (2D) + (3E)
F(2) = (3A) + (4B) + (0C) + (1D) + (2E)

This problem at a glance seem like a difficult problem. I am not very strong in mathematics, so this is how I visualize this problem

We can construct F(1) from F(0) by two step:
Step 1. taking away one count of each coin from F(0), this is done by subtracting “sum” from “iteration” in the code below
after step 1 F(0) = (-1A) + (0B) + (1C) + (2D) + (3*E)

Step 2. Add n times the element which didn’t contributed in F(0), which is A. This is done by adding "A[j-1]len" in the code below.
after step 2 F(1) = (4A) + (0B) + (1C) + (2D) + (3E)

F1=(4A) + (0B) + (1C) + (2D) + (3E)-sum= 3A+(-1)B+0C+1D+2E 
F1=3A+(-1)B+0C+1D+2E +5B= 3A+4B+0C+1D+2E

*/
class Solution {
  public int maxRotateFunction(int[] A) {
      int F0= 0, sum=0, n=A.length;
      for(int i=0; i<A.length; i++) {
          sum+= A[i];
          F0 += i*A[i];
      }
      int maxV = F0;
      // Caculate F1, F2 ... based on F0, F1, ....
      for(int i=1; i<A.length; i++) {
          int F1=F0-sum;
          F1=F1+n*A[i-1];
          maxV = Math.max(F1, maxV);
          F0 = F1;
      }
      return maxV;
  }
}
