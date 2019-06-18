/*
You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.

Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3
3, 4, 5
Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences : 
1, 2, 3, 4, 5
3, 4, 5
Example 3:
Input: [1,2,3,4,4,5]
Output: False
Note:
The length of the input is in range of [1, 10000]
*/

/*
        I used a greedy algorithm.
left is a hashmap, left[i] counts the number of i that I haven't placed yet.
end is a hashmap, end[i] counts the number of consecutive subsequences that ends at number i
Then I tried to split the nums one by one.
    If I could neither add a number to the end of a existing consecutive subsequence 
    nor find two following number in the left, I returned False

    e.g: [1,2,3,3,4,4,5,5]
    left: 1:1 2:1 3:2 4:2 5:2  end= {}
    i=0 n=1 since left[1]>0 end[0] not exist, but left[2], left[3] exist, 
            so we make left[1]--=0 left[2]--=0 left[3]--=1, end[3]=1
    i=1 n=2 since left[2]==0 continue
    i=2 n=3 since left[3]>0 end[2] not exist, so we can not find a sequence to append
            but left[4]>0 left[5]>0 so: left[3]--=0 left[4]--=1 left[5]--=1, end[5]=1
    i=3 n=3 continue
    i=4 n=4 since left[4]>0 but since end[3] exist, so we can append to previous sequence now end at 4 
            left[4]--=0 end[3]=0 end[4]=1
    i=5 n=4 continue
    i=6 n=5 since left[5]>0 but since end[4] exist, so we can append to previous sequence now end at 5
            left[5]--=0 end[4]=0 end[5]=1
           
*/
class Solution {
  public boolean isPossible(int[] nums) {
      Map<Integer, Integer> left = new HashMap<>();
      Map<Integer, Integer> end = new HashMap<>();
      for(int n : nums) left.put(n, left.getOrDefault(n, 0)+1);
      
      for(int n: nums) {
          if(left.get(n)==0)  continue;
          Integer endV = end.get(n-1);
          if(endV != null && endV > 0) { // if there is a sequence end with n-1 that we can append n onto
              left.put(n, left.get(n)-1);
              end.put(n-1, end.get(n-1)-1);
              end.put(n, end.getOrDefault(n, 0)+1);
          }  // we can start a new sequence with n and ends at n+2
          else if(left.get(n+1) != null && left.get(n+2) != null && left.get(n+1)>0 && left.get(n+2)> 0) { 
              left.put(n, left.get(n)-1);
              left.put(n+1, left.get(n+1)-1);
              left.put(n+2, left.get(n+2)-1);
              end.put(n+2, end.getOrDefault(n+2, 0)+1);
          } else {
              return false;
          }
      }
      return true;
  }
}
