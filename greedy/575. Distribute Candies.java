/*
Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister. Return the maximum number of kinds of candies the sister could gain.
Example 1:
Input: candies = [1,1,2,2,3,3]
Output: 3
Explanation:
There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too. 
The sister has three different kinds of candies. 
Example 2:
Input: candies = [1,1,2,3]
Output: 2
Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1]. 
The sister has two different kinds of candies, the brother has only one kind of candies. 
Note:

The length of the given array is in range [2, 10,000], and will be even.
The number in given array is in range [-100,000, 100,000].
*/

/*
    if the candies are like example 2: there are 3 kinds of candies, but total is 4, so the sister can have 2 candies
    if the candies are like 
    [1,1,1,1,3,3]
    there are 2 kinds of candies, but total is 6, so the sister can have only 2 kinds of candies
    so when total kinds of candies is smaller than len(candy)/2, sister can get total kinds
    else when total kinds of candies is larger than len(candy)/2,sister can get total len(candy)/2
    
    the answer is either the len(candy)/2, or the kinds total
*/

class Solution {
  public int distributeCandies(int[] candies) {
      HashSet<Integer> set = new HashSet<>();
      for(int n: candies) set.add(n);
      return Math.min(candies.length/2, set.size());   
  }
}
