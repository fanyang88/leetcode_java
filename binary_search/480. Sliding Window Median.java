/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note: 
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
*/

/*
    [1  3  -1] -3  5  3  6  7       1
    1 [3  -1  -3] 5  3  6  7   
    
    we maintain a window first, window = [-1, 1, 3] 
    i=>-3, insert -3 into the new window,= [-3, -1, 1, 3]
    since to be delete number = [i-k] = 1 we need to find 1 in window and delete it

    e.g: [1,3, -1,4, 5, 2, 3]  k= 5
 sort the array in the window first, 
 we want to make sure the number in the window is always sorted
 for each new incoming number, we push it to the last postion first, 
 then we need to find the insertion point for the new number in the window.
 insert the number in the right position.
 the median is always = window[k/2] + window[(k-1) /2];
 Then at last, since nums[i-k] is the number we want to delete in the array
 
 [-1, 1, 3, 4, 5]  
 new number = 2: use binary search to find the first number larger than 2, 
                 ind = 2, we insert 2 into window: [-1, 1, 2, 3, 4, 5]
                 the number to be deleted is nums[5-5] = 1, 
                 we find the position of 1 and delete it in window
*/

class Solution {
  public double[] medianSlidingWindow(int[] nums, int k) {
      int n = nums.length;
      double[] res = new double[n-k+1];
      List<Integer> window = new ArrayList<>();
      for(int i=0; i<k; i++)  window.add(nums[i]);
      Collections.sort(window);
      res[0] = getMedian(window, k);
      
      for(int i=k, j=1; i<nums.length; i++, j++) {
          int insertPos = binarySearch(window, nums[i]);
          if(insertPos == -1) {
              window.add(nums[i]);
          } else {
              window.add(insertPos, nums[i]);
          }
          int deletePos = binarySearch(window, nums[i-k]);
          window.remove(deletePos);
          res[j] = getMedian(window, k);
      }
      return res;
  }
  
  public int binarySearch(List<Integer> list, int target) {
      int lo = 0, hi = list.size()-1;
      while(lo < hi) {
          int mid = lo + (hi- lo)/2;
          if(list.get(mid) < target) {
              lo = mid+1;
          } else {
              hi = mid;
          }
      }
      return list.get(hi) >= target ? hi : -1;
  }
  
  public double getMedian(List<Integer>window, int k) {
      double m = window.get(k/2), n = window.get((k-1)/2);
      return n + (m-n)/2.0;
  }
}
