/*
instances of that value in-place and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example 1:

Given nums = [3,2,2,3], val = 3,

Your function should return length = 2, with the first two elements of nums being 2.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,1,2,2,3,0,4,2], val = 2,

Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.

Note that the order of those five elements can be arbitrary.

It doesn't matter what values are set beyond the returned length.
*/

/*
        3 1 2 4 3
        i
          j   when nums[j] !=3 nums[i] = nums[j]=1 i++
        1 1 2 4 3
          i
            j  when nums[j] !=3 nums[i] = nums[j]=2 i++
        1 2 2 4 3
            i
              j  when nums[j] !=3 nums[i] = nums[j]=4 i++
        1 2 4 4 3
              i
                j  since nums[j]==3 continue
        return i;
*/ 

class Solution {
  public int removeElement(int[] nums, int val) {
      int i=0, n= nums.length;
      for(int j=0; j<n; j++) {
          if(nums[j] != val) {
              nums[i] = nums[j];
              i++;
          }
      }
      return i;
  }
}