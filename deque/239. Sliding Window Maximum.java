/**
 * Given an array nums, there is a sliding window of size k which is moving 
 * from the very left of the array to the very right. 
 * You can only see the k numbers in the window. 
 * Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note: 
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
 */

 // O(nlogk)

/*
    create a newque and what the deque does:
    1. push: when call push, it would push the element to the queue, also delete all smaller elements
    2. pop: would pop the max element
    3. max: would get the max element
    
    
    Window position             deque        Max
---------------                            -----
[1  3  -1] -3  5  3  6  7       [3, -1]       3
 1 [3  -1  -3] 5  3  6  7       [3,-1,-3]     3
 1  3 [-1  -3  5] 3  6  7       [5]           5
 1  3  -1 [-3  5  3] 6  7       [5, 3]        5
 1  3  -1  -3 [5  3  6] 7       [6]           6
 1  3  -1  -3  5 [3  6  7]      [7]           7
    
    
we store the index in deque, we need to remove old index, e.g: when i=3 k=3 should remove j=0 
*/

class Solution {
  public int[] maxSlidingWindow(int[] nums, int k) {
      if(nums.length==0)  return new int[0];
      int[] res = new int[nums.length-k+1];
      Deque<Integer> q = new ArrayDeque<>();
      int index=0;
      
      for(int i=0; i<nums.length; i++) {
          // remove old data
          if(!q.isEmpty() && q.peekFirst() <= i-k) q.pollFirst();
          // remove numbers are smaller or equal
          while(!q.isEmpty() && nums[q.peekLast()] <= nums[i]) q.pollLast();
          // add current data at the end
          q.offer(i);
          // add to result 
          if(i>=k-1) res[index++]= nums[q.peekFirst()]; // the first one is the max in deque 
      }
      return res;   
  }
}

