/*
We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation: 
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.
Note:

A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].

*/

/*
    if A[i-1] < B[i] & B[i-1] < A[i] 
       i-1   i
    A  5     4
    B  3     7
    in such case, we can swap A[i, B[i]
    
    for case:A[i-1] < B[i] & B[i-1] < A[i] && A[i-1] < A[i] & B[i-1] < B[i]
       i-1   i
    A  3     4
    B  2     7
    in such case, we can choose either swap or not swap

we can use DFS which cause TLE

solution should use DP:
use swap[i] and keep[i] to denote min swap to keep A[0-i], B[0-i] increasing with or without swaping A[i] with B[i]

             A          2 3
             B          1 4
             /        /      \         \
            2  3     1 3      2 4     1  4
            1  4     2 4      1 3     2  3
          no swap  swap i-1  swap i   swap i, i-1
            1            2      3          4
          
     for A[i-1] < A[i] & B[i-1] < B[i], can be swap in 4 cases above
     for A[i-1] < B[i] & B[i-1] < A[i] only, we should go to case 3,4
       A   5  4   or   4  9
       B   2  7        7  6
       
       so the formula is:
       if(A[i-1] < A[i] & B[i-1] < B[i]) {
          keep[i] = keep[i-1];
          swap[i] = swap[i-1]+1; 
       }
       if(A[i-1] < B[i] & B[i-1] < A[i]) {
          swap[i] = min(keep[i-1] + 1, swap[i])
          keep[i] = min(swap[i-1], keep[i])
       }
*/


// class Solution {
//     int ans = Integer.MAX_VALUE;
//     public int minSwap(int[] A, int[] B) {
//         dfs(1, A, B, 0);
//         return ans;
//     }
    
//     public void dfs(int i, int[] A, int[] B, int count) {
//         if(count >= ans) return; // pruning, if current swap number > ans, no need to go down
//         if(i == A.length) {
//             ans = Math.min(ans, count);
//             return;
//         }
//         if(A[i-1] < A[i] && B[i-1] < B[i]) { // already increase, no need to swap
//             dfs(i+1, A, B, count);
//         }
//         if(A[i-1] < B[i] && B[i-1] < A[i]) { // we can swap it
//             swap(i, A, B);
//             dfs(i+1, A, B, count+1);
//             swap(i, A, B); // sawp back
//         }
//     }
    
//     public void swap(int i, int[] A, int[] B) {
//         int t = A[i];
//         A[i] = B[i];
//         B[i] = t;
//     }
// }

class Solution {
  int ans = Integer.MAX_VALUE;
  public int minSwap(int[] A, int[] B) {
      int[] swap = new int[A.length], keep = new int[A.length];
      Arrays.fill(swap, Integer.MAX_VALUE);
      Arrays.fill(keep, Integer.MAX_VALUE);
      swap[0]=1;
      keep[0]=0;
      
      for(int i=1; i<A.length; i++) {
          if(A[i-1] < A[i] && B[i-1] < B[i]) {
              keep[i] = keep[i-1];
              swap[i] = swap[i-1]+1;
          }
          if(A[i-1] < B[i] && B[i-1] < A[i]) {
              keep[i] = Math.min(swap[i-1], keep[i]);
              swap[i] = Math.min(swap[i], keep[i-1]+1);
          }
      }
      return Math.min(swap[A.length-1], keep[A.length-1]);
  }
}
