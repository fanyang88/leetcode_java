/*
You are given K eggs, and you have access to a building with N floors from 1 to N. 

Each egg is identical in function, and if an egg breaks, you cannot drop it again.

You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.

Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N). 

Your goal is to know with certainty what the value of F is.

What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?

 

Example 1:

Input: K = 1, N = 2
Output: 2
Explanation: 
Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
If it didn't break, then we know with certainty F = 2.
Hence, we needed 2 moves in the worst case to know what F is with certainty.
Example 2:

Input: K = 2, N = 6
Output: 3
Example 3:

Input: K = 3, N = 14
Output: 4
 

Note:

1 <= K <= 100
1 <= N <= 10000

*/

/*
    e.g: if there are 10 floors, 2 eggs
    if we drop 1 egg from 4th floor, 
        if it breaks, we have 1 egg for 3 floors to work with
        if it won't break, we have 2 eggs for 6 floor to work with
    given n floors and K eggs, find out in worse case, the minimum tempts needed to find out from which floor the eggs would break
    e.g: 6 floors and 2 eggs
    
floor/# of eggs     1   2   3   4   5   6
        1           1   2   3   4   5   6
        2           1   2   2

dp[0][0] for 1 floor of 1 egg, temp=1
dp[0][1] for 2 floor of 1 egg, temp=2
...

if we have 2 eggs:
    for 1 floor, temp=1 = dp[0][0]
    for 2 floor, drop from 1th, 1 + max(0,  dp[0][0]) (not break answer is 2, if break 1 eggs for 1 floor)
                 frop from 2th, 1 + max(0, dp[0][0]) (egg breaks we have 1 floor 1 egg, if not break, we know the answer)
                 dp[1][1]=2
    
    for 3 floors: drop from 1th, 1 + max(0, dp[1][1])=3 (break we have answer, not break, we have 2 eggs for 2 floors)
                  drop from 2th, 1 + max(dp[0][0], dp[1][0])=2 (break we have 1 egg for 1 floors, not break, we have 2 eggs for 1 floors)
                  drop from 3th, 1 + max(dp[0][1], 0)=3 (break we have 1 egg for 2 floors, not break, found the aswer)
                  dp[1][2] = 2
    for 4 floors: drop from 1th, 1 + max(0, dp[1][2])=3 (break we have answer, not break, we have 2 eggs for 3 floors)
                  drop from 2th, 1 + max(dp[0][0], dp[1][1])=3 (break we have 1 egg for 1 floors, not break, we have 2 eggs for 2 floors)
                  drop from 3th, 1 + max(dp[0][1], dp[1][0])=3 (break we have 1 egg for 2 floors, not break, we have 1 egg for 1 floor)
                  drop from 4th, 1 + max(dp[0][2], 0)=4 (break we have 1 egg for 3 floors, not break, found the answer)
                  dp[1][3] = 3
    ....
    
    if(i>j) eggs number > floor, we just get the value from the top
        dp[i][j] = dp[i-1][j]
    else dp[i][j] = 1+ min(max(dp[i-1][k-1], dp[i][j-k]))  k: i~j
                                break at k, not break at k
    
*/

class Solution {
  
  // This solution result to TLE, use binary search to optimize 
   
   public int superEggDrop(int K, int N) {
       int[][] dp = new int[K+1][N+1];
       for(int i=0; i<=N; i++) dp[1][i] = i;
       
       for(int e=2; e<=K; e++) {
           for(int f=1; f<=N; f++) {
               if(e > f) {
                   dp[e][f] = dp[e-1][f];
               } else {
                   dp[e][f] = Integer.MAX_VALUE;
                   for(int k=1; k<=f; k++) {
                       int v = 1+ Math.max(dp[e-1][k-1], dp[e][f-k]);
                       dp[e][f] = Math.min(dp[e][f], v);
                   }
               }
           }
       }
       return dp[K][N];
   }
   
   
/*   Notice that for the same K when N goes up, dp[K][N] goes up.
Then for int left = helper(K - 1, i - 1, memo); int right = helper(K, N - i, memo); when i goes up, left goes up and right goes down.
We can use Binary Search here to get the minimum Math.max(left, right) + 1, when left and right are as close as possible.
We come to this O(KNlogN) solution:
*/
   
   public int superEggDrop(int K, int N) {
       int[][] dp = new int[K+1][N+1];
       return dfs(K, N, dp);
   }
   
   public int dfs(int K, int N, int[][] dp) {
       if(N<=1)  return N;
       if(K==1) return N;
       if(dp[K][N] > 0) return dp[K][N];
       int lo = 1, hi = N, res= N;
       while(lo < hi) {
           int mid = lo + (hi- lo)/2;
           // drop from mid-th floor
           int left = dfs(K-1, mid-1, dp);
           int right = dfs(K, N-mid, dp);
           res = Math.min(res, Math.max(right, left)+1);
           if(left == right) break;
           if(left < right) { // mid is too small
               lo = mid+1;
           } else {
               hi = mid;
           }
       }
       dp[K][N] = res;
       return res;
   }
   
}