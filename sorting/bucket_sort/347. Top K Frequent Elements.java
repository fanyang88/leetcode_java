/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/


/*
        Idea is simple. Build a array of list to be buckets with length 1 to sort.
        [1,1,1,2,2,3, 3,3]  k=2
        val   freq
        1:     3
        2:     2
        3:     3
                0 .  1 . 2 . 3 . 4 . 5  (frequency)
        bucket:[{}, {}, {2}, {1,3}, {}, {}]
        return [1,3]
        
*/
class Solution {
  public List<Integer> topKFrequent(int[] nums, int k) {
      List<Integer>  res = new ArrayList<Integer>();
      List<Integer>[] bucket = new List[nums.length+1];  
      Map<Integer, Integer> map = new HashMap<Integer, Integer>();
      for(int num: nums) map.put(num, map.getOrDefault(num, 0)+1);
      for(int key: map.keySet()) {
          int frequency = map.get(key); 
          if(bucket[frequency] == null)  bucket[frequency] = new ArrayList<>();
          bucket[frequency].add(key);
      }
      
      // go through the buckets from right to left to get number
      for(int i=nums.length; i>=0 && res.size() <k; i--) {
          if(bucket[i] != null) res.addAll(bucket[i]);
      }
      return res;
  }
}
