/**
 The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
 */

 /*
  e.g: n=4 k=9
  if n=0 there is only 1 combination {}, dp[0]=1
  if n=1 there is only 1 combination {1}  dp[1] = 1
  if n=2 there is only 2 combination {1, 2}  {2, 1} dp[2]=2
  if n=3 there is 6 combination = 3*2  dp[3] = n*dp[n-1]=3*dp[2]
    "123"
    "132"
    "213"
    "231"
    "312"
    "321"
  so for n=4 1,{2,3,4}  the permutation can start from 1|2|3|4
             2,{1,3,4}
             3,{1,2,4}
             4,{1,2,3}
  since k=9-1=8
  i=4   res= {1,2,3,4} 
  index = k/6 =8/dp[3]=1   k=k%6= 2   so the first number is res[1]=2
  res= {1,3,4} 
  i=3  res= {1,3,4} 
  index = 2/dp[2] =1   k=k%2= 0   so the second number is res[1]=3
  res = {1,4}
  i=2  res= {1,4} 
  index = 0/dp[1] =0   k=k%1= 0   so the second number is res[0]=1 
  res={4}
  index = 0/dp[0] =0   k=k%1= 0   so the second number is res[0]=4
  
  so the answer is 2314
  
  so we need to get the dp[] from 0~n-1
*/

class Solution {
  public String getPermutation(int n, int k) {
      int[] dp= new int[n];
      String res = "";
      List<Integer> list = new ArrayList<Integer>();
      for(int i=1; i<=n; i++) {
          list.add(i);
      }
      dp[0]=1;
      for(int i=1; i<n; i++) {
          dp[i] = i*dp[i-1];
      }
      
      k--; // This is the key!!!, since we start from 0, count to k-1
      for(int i=n-1; i>=0 ;i--) {  //i=3,2,1,0
          int index = k/dp[i];
          k = k % dp[i];
          res += list.get(index);
          list.remove(index);
      }
      return res;
  }
}