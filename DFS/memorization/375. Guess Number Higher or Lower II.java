/*
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
*/


/*
         first introducebest strategyto guess:
for one number, like 1, best strategy is 0$
for two number, like 3,4, best strategy is 3$, which can be understood in this way: you have two way to guess: a) start by guess 4 is the target, (the worst case is) if wrong, you get charged $4, then immediately you know 3 is the target number, get get charged $0 by guessing that, and finally you get charged $4. b) similarly, if you start by 3, (the worst case is) if wrong, you get charged $3, then you immediately know that 4 is the target number, and get charged $0 for guessing this, and finally you get charged $3. In summary:
range ---------> best strategy cost
3, 4 ---------> $3   for 3: 3 + (0,0(4 is target, no cost))  for 4: 4 + (0,0(3 is target, no cost)) the min is $3 
5, 6 ---------> $5
...
for three number, the best strategy is guess the middle number first, and (worst case is) if wrong, you get charged that middle number money, and then you immediately know what target number is by using "lower" or "higher" response, so in summary:
range ---------> best strategy cost
3, 4, 5 ---------> $4 if we split as 3 | 4 | 5=> 4 + max(0, 0)=4   
                            split as 3,4|5=> 5+ dp(3,4) = 5+3=8 
                            split as 3|4,5=> 3+4=7
7, 8, 9 ---------> $8
...
for more numbers, it can simply be reduced them into smaller ranges, and here is why DP solution make more sense in solving this.
suppose the range is [start, end]
the strategy here is to iterate through all number possible and select it as the starting point, say for any k between start and end, the worst cost for this is: k+max(DP( start, k-1 ), DP(k+1, end )), and the goal is minimize the cost, so you need the minimum one among all those k between start and end
*/

class Solution {
  public int getMoneyAmount(int n) {
      int[][] map = new int[n+1][n+1];
      return dfs(1, n, map);
  }
  
  public int dfs(int lo, int hi, int[][] map) {
      if(lo >= hi) return 0; // only one element left, no cost needed
      if(map[lo][hi] > 0)  return map[lo][hi];
      int res = Integer.MAX_VALUE;
      for(int k=lo; k<=hi; k++) {
          int val = k + Math.max(dfs(lo, k-1, map), dfs(k+1, hi, map)); // max cost = cost from left, or cost from right
          res = Math.min(res, val);
      }
      map[lo][hi]  =res;
      return res;
  }
}
