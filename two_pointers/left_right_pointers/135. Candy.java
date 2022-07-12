/**
 * There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1:

Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.
 */


/*
e.g: rating                 1,3,0,2,1,0
candies init:               1,1,1,1,1,1
from left to right          1 2 1 2 1 1
from right to left          1 2 1 3 2 1  sum of this array is the answer

e.g:   1 0 2
       1 1 1
->:    1 1 2
<-:    2 1 2

*/
class Solution {
  public int candy(int[] ratings) {
      int n = ratings.length, sum=0;
      int[] candy = new int[n];
      Arrays.fill(candy, 1);
      for(int i=1; i<n; i++) {
          if(ratings[i] > ratings[i-1]) {
              candy[i] = candy[i-1]+1;
          }
      }
      for(int i=n-2; i>=0; i--) {
          if(ratings[i] > ratings[i+1]) {
              candy[i] = Math.max(candy[i], candy[i+1]+1);
          }
      }
      for(int num: candy) sum += num;
      return sum;
  }
}



PYTHON *********************************************

class Solution:
    def candy(self, ratings: List[int]) -> int:
        candy = deque()
      
        
        #init all to 1
        for i in range(len(ratings)):
            candy.append(1)
        
        #from left to right
        for i in range(1, len(ratings)):
            if ratings[i] > ratings[i-1]:
                candy[i] = candy[i-1]+1
                
        #from right to left
        for i in range(len(ratings)-2, -1, -1):
            if ratings[i] > ratings[i+1]:
                # this is the KEY
                candy[i] = max(candy[i], candy[i+1]+1)
        print(candy)
        return sum(candy)
        
        
        
        
# Thoughts:
    
# e.g: rating                 1,3,0,2,1,0
# candies init:               1,1,1,1,1,1
    
#     if nums[i] > nums[i-1] 
#         candy[i] = candy[i-1]+1
# from left to right          1 2 1 2 1 1

# continue work on same arr:
#     if nums[i] > nums[i+1] 
#         candy[i] = candy[i+1]+1
        
# from right to left          1 2 1 3 2 1  

# sum candy is the answer

#         [1,0, 2]
# left:   [1, 1, 2]
# right:  [2, 1, 2] - > sum is answer
    
    
# [1,3,4,5,2]

# [1,1,1,1,1]

# [1,2,3,4,1]

# [1,2,3,2,1]
# for kid 4 this is not correct, since kid 4 has rating larger than kid 3 it should be 4
# so candy[i] = max(candy[i], candy[i+1]+1)
    
    
  
