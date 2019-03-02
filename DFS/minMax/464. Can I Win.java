/*
In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
*/

/*
    using min -max algorithm, we already assume p1 would choose the best solution, 
    so as p2, in order p1 to win, p2 have to fail in all braches, 
    otherwise, p2 would choose the branch it can win, then p1 fail
 e.g: maxChoosableInteger = 3, desiredTotal = 4
      p1(1):  means p1 choose 1, V(1) stands for visited has 1
      
            p1(1): V(1)                p1(2): V(2)                         p1(3): V(3)
          /             \             /          \                     /               \
 p2(3): V(1,3)W     p2(2):V(1,2)  p2(1): V(1,2)   p2(3): V(2,3)W    p2(1): V(1,3)W   p2(2,3): V(2,3)W 
                        |            |
                p1(3): V(1,2,3)W  p1(3): V(1,2,3)W
p2 would always choose the best, so p1 can never win under above situation
win: 1  lose: -1     unknown:  0 
we can use a int to denote which int has visited and use a map to record it leads a win or lose

*/

class Solution {
  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
      if((1+maxChoosableInteger)*maxChoosableInteger/2 < desiredTotal) return false; // can never reach to
      if(desiredTotal ==0)  return true;
      // since maxChoosableInteger is no larger than 20, 
      // we use a 32 bit number to represent which integer has been visited
      Map<Integer, Boolean> map = new HashMap<>();  
      return dfs(map, 0, maxChoosableInteger, desiredTotal);
  }
  
  public boolean dfs(Map<Integer, Boolean> map, int state, int maxChoosableInteger, int curTotal) {
      if(curTotal <=0) return false; // the last play is from player 2
      if(map.containsKey(state)) return map.get(state);
      for(int i=1; i<=maxChoosableInteger; i++) {
          if((state & (1 << i)) > 0) continue; // i is visited
          if(!dfs(map, (state | (1 << i)), maxChoosableInteger, curTotal - i)) { // if player is lose
              map.put(state, true);
              return true;
          }
      }
      map.put(state, false);
      return false;
  }
}
