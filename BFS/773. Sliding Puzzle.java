/*
On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
*/

/*
  we can convert to 1D:  0 1 2 3 4 5
  everytime, we get index of 0 in the board and move 4 direction, -1(left), 1(right), 3(down), -3(up)
  we need to care about the boundry:
  if index of 0 < 0 || > 5, out of board, don't move
  if index of 0 =2 while we need to move it to 3, that's impossbile, don't move
  if index of 0 =3 while we need to move it to 2, that's impossbile, don't move
  use BFS
*/

class Solution {
  public int slidingPuzzle(int[][] board) {
      String state = "", ideal = "123450";
      int[] dirs = {-1, 1, 3, -3};
      Set<String> visited = new HashSet<>();
      Queue<String> q = new LinkedList<>();
      for(int[] row: board) {
          for(int number: row) state += ""+number;
      }
      int distance = 0;
      q.offer(state);
      while(!q.isEmpty()) {
          int size = q.size();
          for(int i=0; i<size; i++) {
              String cur = q.poll();
              if(cur.equals(ideal)) return distance;
              int index = cur.indexOf("0");
              for(int d: dirs) {
                  int next = index +d;
                  if(next < 0 || next > 5 || (next==2 && index==3) || (next==3 && index==2)) continue;
                  String nextState = swap(next, index, cur);
                  if(visited.contains(nextState)) continue;
                  visited.add(nextState);
                  q.offer(nextState);
              }
          }
          distance++;
      }
      return -1;
  }
  
  public String swap(int i, int j, String s) {
      char[] chr = s.toCharArray();
      char t = chr[i];
      chr[i] = chr[j];
      chr[j] = t;
      return new String(chr);
  }
}
