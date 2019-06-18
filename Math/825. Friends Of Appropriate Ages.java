/*
Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person. 

Person A will NOT friend request person B (B != A) if any of the following conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:

Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: [20,30,100,110,120]
Output: 
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 

Notes:

1 <= ages.length <= 20000.
1 <= ages[i] <= 120.
*/

// the key is A would request B if age[B] is: 1/2* age[A] +7 < age[B] < age[A]
/*
    use example 2: 
    map[16]=1 map[17]=1 map[18]=1
    map[16]=1 map[17]=2 map[18]=3
    for age 16: since map[16/2+7]=map[15]=0 1-0-1=0
    for age 17: since map[17] - map[17/2+7]=2 means 2 ppl in the range, but since itself can not count
    
*/
 
class Solution {
  public int numFriendRequests(int[] ages) {
      int[] map = new int[121];
      for(int age: ages)  map[age]++;
      
      for(int i=1; i<121; i++)  map[i] +=map[i-1];
      
      int res=0;
      for(int age: ages) {
          res += Math.max(0, map[age] - map[age/2+7] -1); // exclude itself
      }
      return res;
  }
}
