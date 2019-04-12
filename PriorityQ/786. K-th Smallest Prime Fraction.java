/*
A sorted list A contains 1, plus some number of primes.  Then, for every p < q in the list, we consider the fraction p/q.

What is the K-th smallest fraction considered?  Return your answer as an array of ints, where answer[0] = p and answer[1] = q.

Examples:
Input: A = [1, 2, 3, 5], K = 3
Output: [2, 5]
Explanation:
The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
The third fraction is 2/5.

Input: A = [1, 7], K = 1
Output: [1, 7]
Note:

A will have length between 2 and 2000.
Each A[i] will be between 1 and 30000.
K will be between 1 and A.length * (A.length - 1) / 2.

*/


/*
        Says if the list is [1, 2, 3, 5], we can easily have this table of relationships, K=2
        answer is 1/3

1/5  < 1/3   < 1/2
2/5  < 2/3    
3/5 
So now the problem becomes "find the kth smallest element of (n-1) sorted list"
 using PriorityQueue, running time is O(nlogn) O(max(n,k) * logn), space is O(n):
we push ({1,5}, {1, 3}, {1, 2}) into q
K=3 pop{1,5} since there is number after 1/5 push {2, 5}, pq={2,5}, {1, 3}, {1,2}}
K=2 pop{1,3} push {2,3}, pq={2,5}, {2, 3}, {1,2}}
K=1 pop {2,5} push {3,5} pq={3,5} {2,3}{1,2} K--=0 stop

O(max(n,k) * logn)
*/
class Solution {
  public int[] kthSmallestPrimeFraction(int[] A, int K) {
      int n = A.length;
      int[] res = new int[2];
      Arrays.sort(A);
      PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
          @Override
          public int compare(int[] o1, int[] o2) {
              return A[o1[0]] * A[o2[1]] - A[o2[0]] * A[o1[1]];
          }
      });
      for(int i=1; i<n; i++) 
          pq.offer(new int[]{0, i});
      
      // now we iterative
      while(K-- > 0) {
          int[] cur = pq.poll();
          res = new int[]{A[cur[0]], A[cur[1]]};
          if(cur[0]+1< n) {
              pq.offer(new int[]{cur[0]+1, cur[1]});
          }
      }
      return res;
  }
}