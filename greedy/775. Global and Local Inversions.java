/*
We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.

The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].

The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].

Return true if and only if the number of global inversions is equal to the number of local inversions.

Example 1:

Input: A = [1,0,2]
Output: true
Explanation: There is 1 global inversion, and 1 local inversion.
Example 2:

Input: A = [1,2,0]
Output: false
Explanation: There are 2 global inversions, and 1 local inversion.
Note:

A will be a permutation of [0, 1, ..., A.length - 1].
A will have length in range [1, 5000].
The time limit for this problem has been reduced.
*/

/*
because a local inversion is a global inversion, but a global one may not be local.
since local is A[i] > A[i+1].
     global is A[i] > A[j]  i<j
     
 The original order should be [0, 1, 2, 3, 4…], the number i should be on the position i. 
 We just check the offset of each number,  A[i] - i 
 if the absolute value is larger than 1, means there is global one, but not a local one
 means the number of global inversion must be bigger than local inversion,
 
 e.g: [2, 0, 1] false  offset of 2 is 2 > 1
 
 another solution is:
 If the number of global inversions is equal to the number of local inversions,
it means that all global inversions in permutations are local inversions.
It also means that we can not find A[i] > A[j] while j>=i+2
In other words, max(A[i]) <= A[i+2]
once we find max(A[i]) > A[i+2] means the global inversion is larger

In this first solution, I traverse A and keep the current biggest number cmax.
Then I check the condition cmax < A[i+2]
 
*/

class Solution {
    
  public boolean isIdealPermutation(int[] A) {
      for(int i=0; i<A.length; i++) {
          if(Math.abs(A[i] - i) > 1) return false;
      }
      return true;
  }
  
  public boolean isIdealPermutation(int[] A) {
      int max = Integer.MIN_VALUE;
      for(int i=0; i<A.length-2; i++) {
          max = Math.max(max, A[i]);
          if(max > A[i+2])  return false;
      }
      return true;
  }
}