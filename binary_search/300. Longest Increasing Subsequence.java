/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
*/

/*
    [10,9,2,5,3,7,101,18]  list= {}
    i=0 list={10} pos = -1 directly add to list
    i=1 list={9} binary search pos=0 list[0] = 9
    i=2 list = {2} binary search pos=0 list[0] = 2
    i=3 list= {2,5} binary search pos=-1 list.push =5
    i=4 list= {2,3} binary search pos=1 list[1]=3
    i=5 list= {2,3,7}
    i=6 list={2,3,7,101}
    i=7 list={2,3,7,18}
*/

class Solution {
  public int lengthOfLIS(int[] nums) {
      List<Integer> list = new ArrayList<Integer>();
      for(int i=0; i<nums.length; i++) {
          int index= search(list, nums[i]);
          if(index == -1) {
              list.add(nums[i]);
          } else {
              list.set(index, nums[i]);
          }
      }
      return list.size();
  }
  
  public int search(List<Integer> list, int target) {
      if(list.size()==0) return -1;
      int lo = 0, hi = list.size()-1;
      while(lo < hi) {
          int mid = lo+(hi-lo)/2;
          if(list.get(mid) >= target) {
              hi = mid;
          } else {
              lo = mid+1;
          }
      }
      return list.get(hi) >= target ? hi : -1;
  }
}
