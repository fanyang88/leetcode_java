/*
Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
*/

/*
          0 1 2 3
    e.g: [1,3,4,4]
    
    pickIndex() -> return 0~3 based the weight average.
    without weight, pick 0~3 is 25% chances
    with weight, since the total is 12, pick 0 is 1/12, pick 1 is 3/12=1/4...
    so we can think of [1,3,4,4] -> [0,1,1,1,2,2,2,2,3,3,3,3] since index 3=4/12, occur 4 times
    so solution1:
        Generate an array with x occurances of each index where x is the weight
        then random pick a number from 0~11, return the index
        could be slow
    solution 2:
        Generate presum array: [1,4,8,12] the final index has the total weight
        generate a a random number between [0, 12]
        binary search the value in presum array
        e.g: pickIndex = 0 since 0<1 return index of 1 is 0
             pickIndex = 5 since 5<8 return index of 8 is 2
             return the smallest larger number that > random (0~12) 
*/


class Solution {
  int[] sum;
  public Solution(int[] w) {
      sum = new int[w.length];
      for(int i=0; i<w.length; i++) {
          sum[i] = (i==0 ? w[i] : sum[i-1] + w[i]);
      }
  }
  
  public int pickIndex() {
      int rand = new Random().nextInt(sum[sum.length-1]) + 1;
      int lo = 0, hi = sum.length-1;
      while(lo < hi) {
          int mid = lo + (hi - lo)/2;
          if(sum[mid] < rand) {
              lo = mid+1;
          } else {
              hi = mid;
          }
      }
      return hi;
  }
}

/**
* Your Solution object will be instantiated and called as such:
* Solution obj = new Solution(w);
* int param_1 = obj.pickIndex();
*/