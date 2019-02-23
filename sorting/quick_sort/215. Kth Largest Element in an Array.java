/**
 Find the kth largest element in an unsorted array. 
 Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
 */

 /*
    quick select, put all elements larger than pivot to left and smaller one to the right
    check if there are k-1 elements in left side, 
        if there is, means we found the target, 
        otherwise, use binary search to do patition again.
*/

class Solution {
  public int findKthLargest(int[] nums, int k) {
      return select(0, nums.length-1, nums, k);
  }
  
  public int select(int lo, int hi, int[] nums, int k) {
      int splitPos = partition(lo, hi, nums, k);
      int len = splitPos - lo+1;
      // there are k large element from lo to split, 
      //then split point to the kth largest
      if(len == k) { 
          return nums[splitPos];
      } else if(len < k) { //less than k element, need to find k-len more in right part
          return select(splitPos+1, hi, nums, k-len);
      } else {
          return select(lo, splitPos-1, nums, k);
      }
  }
  
  /*
  [3,2,1,5,6,4]
  pivot=3
  left=1 right=5 swap [3,4,1,5,6,2]
  left++=2 right--=4 swap [3,4,6,5,1,2]
  left++=3 right--=3 break
  both left and right point to 5 which is the last element larger than pivot=3 swap it with pivot
  [5,4,6,3,1,2]
         |
        right(split position)
*/
  
  public int partition(int lo, int hi, int[] nums, int k) {
      int pivot = nums[lo], left= lo+1, right=hi;
      
      while(true) {
          while(left <= hi && nums[left] >= pivot) left++;
          while(right > lo && nums[right] <= pivot) right--;
          if(left >= right) break;
          swap(left, right, nums);
      }
      swap(right, lo, nums);
      return right; // right point to the split position.
  }
  
  public void swap(int i, int j, int[] nums) {
      int temp = nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
  }
}

