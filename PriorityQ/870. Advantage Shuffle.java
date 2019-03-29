/*
Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].

Return any permutation of A that maximizes its advantage with respect to B.

 

Example 1:

Input: A = [2,7,11,15], B = [1,10,4,11]
Output: [2,11,7,15]
Example 2:

Input: A = [12,24,8,32], B = [13,25,32,11]
Output: [24,32,8,12]
 

Note:

1 <= A.length = B.length <= 10000
0 <= A[i] <= 10^9
0 <= B[i] <= 10^9
*/

/*
    we want to make A[i] > B[i] as many i as possible
    we should always satisfy the biggest value of B, if the biggest value of A can't satisfy, nothing can
    
    so we sort A first
    using priorityQueue
    A = [12,24,8,32], B = [13,25,32,11]
    A= [8, 12, 24, 32]
    we push B into pq: {32, 2}, {25, 1} {13, 0} {11, 3}
    
    pop B: {32, 2} since A[3]=32 = B[0] can not satisfy, C[2]=A[0]=8
    pop B: {25, 1} since A[3]=32>25 can satisfy, C[1]=A[3]=32 3--=2
    pop B: {13, 0} since A[2]=24>13 can satisfy, C[0]=A[2]=24 2--=1
    pop B: {11, 3} since A[1]=12>11 can satisfy, C[3]=A[1]=12 1--=0
    
    so we need to push B in pq in desc order
    lo=0 hi=n-1
    when A[hi] > pq.poll() means this value from A can statisfy, C[pq.poll(index)] = A[hi] hi--
    otherwise, we put A[lo] at C[pq.poll(index)] since no value can satisfy it anyway

*/

class Solution {
  public int[] advantageCount(int[] A, int[] B) {
      int hi= A.length-1, lo=0;
      Arrays.sort(A);
      PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
      int[] res = new int[A.length];
      for(int i=0, j=0; i<B.length; i++) {
          pq.offer(new int[]{B[i], i});
      }
      while(!pq.isEmpty()) {
          int[] cur = pq.poll();
          if(A[hi] > cur[0]) {
              res[cur[1]] = A[hi--];
          } else {
              res[cur[1]] = A[lo++];
          }
      }
      return res;
  }
}
