/*
There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Example:

Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3      
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1
*/


/*
     post 1 is same as post 2: same = k*1
     post 1 is not same as post 2: diff = k*(k-1)
     
     post 3 is same as post 2: same=k*(k-1)*1 = previous diff
     post 3 is not same as post 2: diff = prevSame*(k-1) + prevDiff*(k-1) 
                  since it can be two senerios:
                  first one is same as second one and is colored B, then third one can be anything except color B
                  first one is diff as second one and one is R, one is B, then third one can be anything except color B as well 
 
*/

class Solution {
  public int numWays(int n, int k) {
      if(n==0)  return 0;
      if(n==1)  return k;
      int same = k;
      int diff = k*(k-1);
      for(int i=2; i<n; i++) {
          int preSame= same;
          int preDiff = diff;
          same = preDiff;
          diff = (k-1) * (preDiff + preSame);
      }
      return diff+same;
  }
}
