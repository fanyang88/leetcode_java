/*
You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3 
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
*/


/*
  use dp
   [[5,4],[6,4],[6,7],[2,3]]
   sort first: 
   [2,3],[5,4],[6,4],[6,7]
   
   dp[0]=1
   dp[1]=2 since [5,4] > [2,3]
   dp[2]: since[6,4] > [2,3] dp[0]+1=2 
          since[6,4] = [5,4] not qualify
          => dp[2]=2
   dp[3]: since[6,7] > [2,3] dp[0]+1=2 
          since[6,7] > [5,4] dp[1]+1=3
          dp[2]=3
   
*/
class Solution {
  public int maxEnvelopes(int[][] envelopes) {
      if(envelopes.length==0)  return 0;
      int maxV=1;
      Arrays.sort(envelopes, new Comparator<int[]>(){
          public int compare(int[] a, int[] b){
              if(a[0] == b[0])  return a[1] - b[1];
              return a[0] - b[0];
          } 
      });
      int[] dp= new int[envelopes.length];
     
      for(int i=0; i<envelopes.length; i++) {
          dp[i] = 1;  // itself need an envelope first
          for(int k=0; k<i; k++) {
              if(envelopes[k][1] < envelopes[i][1] && envelopes[k][0] < envelopes[i][0]) {
                  dp[i] = Math.max(dp[i], dp[k]+1);
              }
          }
          maxV = Math.max(maxV, dp[i]);
      }
      return maxV;
  }
}
