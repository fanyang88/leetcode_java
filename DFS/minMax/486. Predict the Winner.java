/*
Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.
Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
Note:
1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner.
*/

/*
    [1, 5, 2]
    if player 1 choose 1, then palyer 2 can choose [5,2]
       if player 2 choose 5, player 1 can choose 2, player 1 lose 
       if player 2 choose 2, player 1 can choose 5, player 1 win 
    if player 1 choose 2, then palyer 2 can choose [1,5]
       if player 2 choose 1, player 1 can choose 5, player 1 win 
       if player 2 choose 5, player 1 can choose 1, player 1 lose
    we can use minMAx, means each player always choose the best
    
                        [1, 5, 2]
               /                              \
      p1:1  p2:[5,2]                      p1:2  p2:[5,1]
          / .    \                           /      \
    p2:5 p1:[2]  p2:2 p1:[5]         p2:5 p1:[1]  p2:1 p1:[5]
      |            |                       |          |
    p1:2 []       p1:5 []              p1: 1 []      p1:5 []
    lose          win                    lose         win
    
    palyer1: palyer2
                           [1, 5, 2]
                              3:5
               /                              \
            [5,2]                             [5,1]     player 2 always choose the best which is 3:5
             3:5                               3:5
          / .    \                           /      \
     [2]          [5]                   [1]          [5]
      |            |                      |           |
     []            []                     []          []
     3:5          5:3                    3:5         5:3
     
     The prototype of minmax is:
     public boolean PredictTheWinner(int[] nums) {
        return getScore(0, nums.length-1, nums) >=0;
    }
    
    public int getScore(int l, int r, int[] nums) {
        if(l ==r)  return nums[r];
        return Math.max(nums[l] - getScore(l+1, r, nums), nums[r] - getScore(l, r-1, nums));
    }
     
     
                        [1, 5, 233, 7]
              /                              \
            [1, 5, 233]                    [5, 233, 7]     
    
          / .    \                           /      \
     [1, 5]      [5,233]                 [5, 233]    [233, 7]
      |    \      |   |                      |    \       |     \
     [1]   [5]   [5]   [233]              [5]    [233]    [233]  [7]
     
     
    there are duplicates,so we can memorize it.
    we can use dp(i, j) to store the best the score the current player can acheive
    dp[l, r] means when the suarray is frmo l to r the best score is what
*/


class Solution {
  public boolean PredictTheWinner(int[] nums) {
      int[] map = new int[nums.length * nums.length];
      return getScore(0, nums.length-1, nums, map) >=0;
  }
  
  public int getScore(int l, int r, int[] nums, int[] map) {
      if(l ==r)  return nums[r];
      int curKey = l * nums.length + r;
      if(map[curKey] != 0)  return map[curKey];
      int val = Math.max(nums[l] - getScore(l+1, r, nums, map), nums[r] - getScore(l, r-1, nums, map));
      map[curKey] = val;
      return val;
  }
}
