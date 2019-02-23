/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
*/

/*
    nums = [-2, 0, 3, -5, 2, -1]
    get the presum: [-2, -2, 1, -4, -2, -3]
    
    so to get sum from 1~3: sum[3] - sum[0] = -2
    sum[i~j] = sum[j] - sum[i-1];
*/

class NumArray {
  int[] sum;
  public NumArray(int[] nums) {
      sum = new int[nums.length];
      int s = 0;
      for(int i=0; i<nums.length; i++) {
          s += nums[i];
          sum[i] = s;
      }
  }
  
  public int sumRange(int i, int j) {
      if(i==0) return sum[j];
      return sum[j] - sum[i-1];
  }
}

/**
* Your NumArray object will be instantiated and called as such:
* NumArray obj = new NumArray(nums);
* int param_1 = obj.sumRange(i,j);
*/
