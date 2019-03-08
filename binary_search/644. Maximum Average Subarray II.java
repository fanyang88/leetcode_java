/*
Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.
Note:
1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.
*/


/*
我们首先找出数组中的最小值left和最大值right，因为这将构成最大平均值的区间。然后每次找到left和right的平均值mid，对于mid，在数组内找是否存在一个长度大于等于k的连续区间，其平均值大于mid，
    如果存在，那么说明mid还不是最大平均值，所以修改左边界left；
    否则说明mid已经大于等于左右长度大于等于k的所有连续区间的最大平均值了，所以此时修改右边界right。
这样当left和right逐渐收敛到一点的时候，该收敛点就是最大平均值。

    first we find out the max and min in the array
    the avg is in the range of [min, max]
    now we search an array, see if we can find a subarray with length >=k and avg > mid
        if we can find such subarray, means the mid is too small, we need to make left = mid
        otherwise, means mid is already the largest we can find in the subarray, make right = mid
    
    calculation error less than 10-5
    so left + 0.00001 < right is the condition
    
    space complexity: O(1)，time complexity: O(logm*n)
    
    how to check if there is an array has length>=k and avg >mid
    since (nums[i]+nums[i+1]+...+nums[j])/(j-i+1)>x 
            =>nums[i]+nums[i+1]+...+nums[j]>x*(j-i+1)  
            =>(nums[i]-x)+(nums[i+1]-x)+...+(nums[j]-x)>0
    
    we get the 0~k sum called cur first with each element - mid, if cur>0 return true
    
    0 1 2 3 4.. k-1  k k+1 ..
    |-----k------|
        cur .       
    
    when i=k . cur+=num[k] prev+= num[i-k]  
        if prev<0 means there is a possbility that if current range include previous range 
        may make the sum becomes bigger, i.e., is more possible to >= 0.
    e.g: cur=[0-k]=0 pre=[0,0]=-2 if we make cur to be[1,k] then =cur-prev=2  we found a subarray with length=k and avg > 0, then we need to make prev=0 

*/
class Solution {
  public double findMaxAverage(int[] nums, int k) {
      double left = Double.MAX_VALUE, right = Double.MIN_VALUE;
      for(int n: nums) {
          left = Math.min(left, n);
          right = Math.max(right, n);
      }
      while(left + 0.00001 < right) {
          double mid = left + (right - left) /2;
          if(isLargerArray(nums, mid, k)) {
              left = mid;
          } else {
              right = mid;
          }
      }
      return left;
  }
  
  public boolean isLargerArray(int[] nums, double mid, int k) {
      double[] temp = new double[nums.length];
      double cur = 0, prev=0;
      for(int i=0; i<nums.length; i++) {
           temp[i] = nums[i]-mid;
          if(i<k) cur+= temp[i];
      }
      if(cur >=0.0) return true;
      for(int i=k; i<temp.length; i++) {
          cur+= temp[i];
          prev += temp[i-k];
          if(prev < 0.0) {
              cur = cur - prev; // cut off the previous part
              prev= 0.0;
          }
          if(cur >=0.0) return true;
      }
      return false;
  }
}
