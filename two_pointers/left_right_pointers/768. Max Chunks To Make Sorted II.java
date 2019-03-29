/*
This question is the same as "Max Chunks to Make Sorted" except the integers of the given array are not necessarily distinct, the input array could be up to length 2000, and the elements could be up to 10**8.

Given an array arr of integers (not necessarily distinct), we split the array into some number of "chunks" (partitions), and individually sort each chunk.  After concatenating them, the result equals the sorted array.

What is the most number of chunks we could have made?

Example 1:

Input: arr = [5,4,3,2,1]
Output: 1
Explanation:
Splitting into two or more chunks will not return the required result.
For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.
Example 2:

Input: arr = [2,1,3,4,4]
Output: 4
Explanation:
We can split into two chunks, such as [2, 1], [3, 4, 4].
However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.
Note:

arr will have length in range [1, 2000].
arr[i] will be an integer in range [0, 10**8].
*/

/*we can see two parts first,  i....k  k+1 ... j
 if the maxValue from i...k = maxV <= the minValue from k+1 ...j then we can divide into two 
 so we need to pass, the first pass from right to left to record the minV up to current index
 next pass from left to right, once max[i] <= min[i+1] count++.
 
 */

class Solution {
  public int maxChunksToSorted(int[] arr) {
      int n = arr.length, minV = Integer.MAX_VALUE, count=0, max = Integer.MIN_VALUE;
      int[] min = new int[n];
      
      // first round get minV up to each index from right to left
      for(int i=n-1; i>=0; i--) {
          minV = Math.min(arr[i], minV);
          min[i] = minV;
      }
      // next pass from left to right, once we get max[i] <= min[i+1] count should ++
      for(int i=0; i<n-1; i++) {
          max = Math.max(arr[i], max);
          if(max <= min[i+1]) count++;
      }
      return count+1;
  }
}
