/*

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]] 
Explanation: The first 3 pairs are returned from the sequence: 
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence: 
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
*/

/*
    have a k size priority queue
     0 1 2
    [1,7,11]    
    Q: [1,2, 0]      
       [7,2, 0]     [cur0, cur1, cur2] cur0 is the element in nums1, cur1 is the element in nums2,
       [11,2, 0]    cur2 is the index of nums2
    now we increment the point to point nums1
    poll=[1,2,0] res.add(1,2) index=0 point to nums2[0]=2   index++=1 offer[1,4,1]  Q=[7,2, 0][1,4, 1][11,2, 0]
    poll=[1,4,1] res.add(1,4)  index=1 point to nums2[1]=4   index++=2 offer[1,6,2]  Q=[7,2, 1][1,6][11,2]
    poll=[1,6,2] res.add(1,6)  index=2 point to nums2[2]=6   index++=3 exceed limt skip 
*/

class Solution {
  public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      List<int[]> res = new ArrayList<>();
      if(nums1.length==0 || nums2.length==0 || k==0)  return res;
      
      PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)-> a[0]+a[1] - b[0]-b[1]);
      
      for(int i=0; i<k && i<nums1.length; i++) pq.offer(new int[]{nums1[i], nums2[0], 0});
      
      while(k-->0 && !pq.isEmpty()) {
          int[] cur = pq.poll();
          res.add(new int[]{cur[0], cur[1]});
          int index= cur[2];  // index is point to which index in nums2
          if(index+1 >= nums2.length)  continue;
          index++;
          pq.offer(new int[]{cur[0], nums2[index], index});
          
      }
      return res;
  }
}
