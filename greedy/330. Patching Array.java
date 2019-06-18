/*
Given a sorted positive integer array nums and an integer n, 
add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

Example 1:

Input: nums = [1,3], n = 6
Output: 1 
Explanation:
Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.
Example 2:

Input: nums = [1,5,10], n = 20
Output: 2
Explanation: The two patches can be [2, 4].
Example 3:

Input: nums = [1,2,2], n = 5
Output: 0
*/



/*
    e.g:  [1,2,3,9]  n=9
 sum=0 i=0
 i=0 since num[0] = sum + 1 it is expected  sum+=1=1 covered (1, 1)
 i=1 since num[1] = sum+1 it is expected sum=3 covered (1, 3)
 i=2 since num[2] < sum+1 under covered range sum=6 cover (1, 6)
 i=3 since num[3] > sum+1=7 missing number here, the missing one is 7, so we need to add 7
     sum= 6+7=13 cover(1,13)
 since sum > 9 return, need 1 patch which is 7
 

e.g:  [2,3]  n=4
 sum=0 i=0
 i=0 since num[0] > sum + 1, miss patch 1, sum=0+1=1 cover(1,1)
 i=0 since num[0] = sum + 1, sum=sum+num[0]=3 cover(1,3)
 i=1 since num[1] < sum + 1  expected sum=sum+nums[1]=6 covered (1, 6)
 since 6>4 stop miss one patch which is 1
*/

class Solution {
  public int minPatches(int[] nums, int n) {
      int i=0, count=0;
      long sum = 0; // has to be long type, KEY!!!
      while(sum < n) {
          if(i<nums.length && sum+1 >= nums[i]) { // nums[i] is in the range
              sum += nums[i++];
          } else { //we miss a patch which is sum+1
              sum += sum+1;
              count++;
          }
      }
      return count;
  }
}
