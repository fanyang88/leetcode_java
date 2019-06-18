/*
Given a set of distinct positive integers, 
find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:

Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)
Example 2:

Input: [1,2,4,8]
Output: [1,2,4,8]
*/


/*
    use DP, same as LIS:
 e.g:  [1,2,4,8,1]
       0   1   2   3   4
       
 dp :| 1 | 1 | 1 | 1 | 1 |
 --------------------------
pre :| 0 | 1 | 2 | 3 | 4 |

i=1, j=0  since A[i] % A[j] ===0 since dp[j]+1 > dp[i] dp[i] = dp[j]+1=2  pre[1]=0
i=2, j=0  since A[2] % A[0] ===0 since dp[0]+1 > dp[2] dp[2] = dp[0]+1=2  pre[2]=0
     j=1  since A[2] % A[1] ===0 since dp[1]+1 > dp[2] dp[2] = dp[1]+1=3  pre[2]=1
i=3, j=0  since A[3] % A[0] ===0 since dp[0]+1 > dp[3] dp[3] = dp[0]+1=2  pre[3]=0
     j=1  since A[3] % A[1] ===0 since dp[1]+1 > dp[3] dp[3] = dp[1]+1=3  pre[3]=1
     j=2  since A[3] % A[2] ===0 since dp[2]+1 > dp[3] dp[3] = dp[2]+1=4  pre[3]=2
     
     
finaly we have filled the array as such:
       0   1   2   3   4
       
 dp :| 1 | 2 | 3 | 4 | 1 |     max=4 index=3 pre[3]=2 jump to 2 pre[2]=1 jump to 1 pre[1]=0 jump to 0, so the index path is [3,2,1,0]
 --------------------------
pre :| 0 | 0 | 1 | 2 | 4 |

max from dp is 4 and index =3 pre[3]=2 pre[2]=1  pre[1]=0 so we can get the path
    
*/
class Solution {
  public List<Integer> largestDivisibleSubset(int[] nums) {
      List<Integer> res = new ArrayList<Integer>();
      int n = nums.length, maxV = Integer.MIN_VALUE, start=0;
      if(n==0) return res;
      int[] dp= new int[n], pre=new int[n];
      Arrays.fill(dp, 1);
      Arrays.sort(nums);  // This is the key, we need to sort the array first
      for(int i=0; i<n; i++) pre[i] = i;
      
      for(int i=1; i<n; i++) {
          for(int j=0; j<i; j++) {
              if(nums[i] % nums[j] != 0)  continue; // This is the key!!!
              if(dp[i] < dp[j] + 1) {
                  dp[i] = dp[j]+1;
                  pre[i] = j;
              }
          }
          if(maxV < dp[i]) {
              maxV = dp[i];
              start = i;
          }
      }
     // retrieve the path
      res.add(0, nums[start]);         
      while(start != pre[start]) {
          res.add(0, nums[pre[start]]);
          start = pre[start];
      }
      return res;
  }
}