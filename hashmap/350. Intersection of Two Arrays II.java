/*
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:


*/

/*
    use hashmap:e.g: [1,2,2,1] 
    1: 2
    2: 2
    go through [2,2]
    since 2 in map, map(2) > 0 res.add(2) map[2]=1
    since 2 in map, map(2) > 0 res.add(2) map[2]=0
    return res;
    
    
    nums1 = [4,9,5], nums2 = [9,4,9,8,4]
    4: 1
    9: 1
    5: 1
    go through [9,4,9,8,4]
    since 9 in map, map(9) > 0 res.add(9) map[9]=0
    since 4 in map, map(4) > 0 res.add(4) map[4]=0
    since 9 in map, but map(9)=0 do nothing
    8 not in map, do nothing
    since 4 in map, but map(9)=0 do nothing
    res = [9,4]
    return res;
*/


class Solution {
  public int[] intersect(int[] nums1, int[] nums2) {
      Map<Integer, Integer> map = new HashMap<Integer, Integer>();
      List<Integer> res = new ArrayList<Integer>();
      
      for(int num : nums1) {
          map.put(num, map.getOrDefault(num, 0) +1);
      }
      for(int num : nums2) {
          if(map.containsKey(num) && map.get(num) > 0) {
              res.add(num);
              map.put(num, map.get(num)-1);
          }
      }
      int[] ans = new int[res.size()];
      for(int i=0; i<res.size(); i++) ans[i] = res.get(i);
      return ans;
  }
}
