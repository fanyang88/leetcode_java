/**
 * 
 * Given an array of integers, find out whether there are two distinct indices i and j 
 * in the array such that the absolute difference between nums[i] and nums[j] 
 * is at most t and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
 */



/*
    use bucket sort
    e.g: [11,20,5,30,8] t=5 k=3
    we can use a hashmap to simulate the buckets
    min=5, gap is t+1 to avoid t=0
    i=0 (11-5)/(t+1) =1 so we put 11 in bucket 1, no value in adject bucket
    i=1 (20-5)/(t+1) =2 so we put 20 in bucket 3, no value in adject bucket
    i=2 (5-5)/(t+1) =0 so we put 5 in bucket 0, since 11-5> t safe
    i=3  (30-5)/(t+1) =4 so we put 30 in bucket 4, bucket 3=20 30-20>t safe, oldindex=0 clean bucket[0]
    i= (8-5)/(t+1) =0 so we put 8 in bucket 0, since bucket 1 =11 -8< t return true;
    we need to not only compare with current bucket, but also the adjacent bucket, 
    e.g: bucket 1 has 4, bucket 2 has 6, bucket 3 has 11-15 but 4 and 6 astisify the condition
     
*/
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums.length==0 || k<=0 || t<0)  return false;
        
        long diff = Long.valueOf(t)+1;
        long minV = nums[0];
        for(int num : nums) {
            minV = Math.min(minV, (long)num);
        }
        Map<Long, Integer> bucket = new HashMap<Long, Integer>();
        for(int i=0; i<nums.length; i++) {
            long cur = Long.valueOf(nums[i]);
            long index= (Long.valueOf(nums[i]) - minV) / diff;
            // check left bucket
            Integer left = bucket.get(index-1);
            if(left != null && Math.abs(Long.valueOf(left) - cur) <= t) return true;
            
            // check right bucket
            Integer right = bucket.get(index+1);
            if(right != null && Math.abs(Long.valueOf(right) - cur) <= t) return true;
            
            // check itself bucket
            Integer itself = bucket.get(index);
            if(itself != null && Math.abs(Long.valueOf(itself) - cur) <= t) return true;
            bucket.put(index, nums[i]);
            
            // remove old data
            if(i>=k) {
                long oldIndex = (Long.valueOf(nums[i-k]) - minV)/diff; // The key, not i-k
                bucket.remove(oldIndex);
            }
        }
     return false;
    }
}
