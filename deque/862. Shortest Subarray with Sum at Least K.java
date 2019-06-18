/*
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.

If there is no non-empty subarray with sum at least K, return -1.

 

Example 1:

Input: A = [1], K = 1
Output: 1
Example 2:

Input: A = [1,2], K = 4
Output: -1
Example 3:

Input: A = [2,-1,2], K = 3
Output: 3
 

Note:

1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9
*/

/*
    用一个简单的示例来分析，设 A = [4,-1,2,3]，K = 5，那么dp = [0,4,3,5,8]，
    我们从dp数组的第2个数开始分析，（假设来了个-1，那么因为-1比0小，后面任意一个数val如若满足val-0>K,那么val+1也一定大于K，且-1所在的位置i显然能获得更优解，所以0这个位置就失去了意义），
    
    现在考虑示例，来了个4，我们发现4-0小于5，我们怎么对4进行处理呢，因为考虑到之后或许会出现一个足够大的数，比如9，那么4相对于0是更优的，但也有可能只来一个8，那么4就没作用了，所以先暂且保留观察。
    等到来了一个5以上的数，我们依次对保留的数（目前是0，4）进行判断得最优解。
接下来来了个3，那么根据上面提到的论点，4将会被舍弃，但3比0要大，故此时0，3保留。
然后来了个5，5-0>=5，故找到一组i,j，记录下来，然后判断 5-3>=5 ?如若确实大于，即再次找到一组i,j，若小于，则5保留（考虑到之后或许来了个10），依次类推

    i=0 dp=[0]
    i=1 val=4 since 4>0 no need to pop the last, dp=[0,1]
    i=2 val=3 since 3<4 means if there is a new val-3 better than val-4, we need to remove 4
        sp=[0, 2]
    i=3 val=5 since 5>3 no need to pop last, but since 5-0=5 means we find a potential match
        len = 3-0=3 we should pop 0, since even if there is 6 incoming, the best is still len
        dp=[2,3]
    i=4 val=8 since 8>5 no need to pop the last
        since 8-dp[2]=5 len=4-2=0 len=2
        answer is 2
        
        Deque: pollFirst, pollLast peekFirst, peekLast offer
*/


class Solution {
  public int shortestSubarray(int[] A, int K) {
      int len = A.length+1;
      Deque<Integer> q = new ArrayDeque<>();
      int[] dp = new int[A.length+1];
      dp[0] = 0;
      for(int i=0; i<A.length; i++)  dp[i+1] = dp[i] + A[i];
      
      for(int i=0; i<dp.length; i++) {
          // if the last one is larger than new val, we should use new val which give shorter distace
          while(!q.isEmpty() && dp[q.peekLast()] >= dp[i]) q.pollLast();
          while(!q.isEmpty() && dp[i] - dp[q.peekFirst()] >= K) {
              // find a potential answer
              len = Math.min(len, i-q.peekFirst());
              q.pollFirst();
          }
          q.offer(i);
      }
      return len==A.length+1 ? -1 : len;
  }
}
