/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

 

Example 1:

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"

Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Example 2:

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)

Output: "impossible"

Explanation: The ball cannot reach the hole.

 

Note:

There is only one ball and one hole in the maze.
Both the ball and hole exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

*/

/*
        Similar with maze II, we need to record the path in a list as well
        if cur == hole we also need to check if it is the shortest path, 
        
        if str1.compareTo(str2) < 0, means str1 is bigger lexically
*/

class Solution {
  public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
      int[][] dirs = {{0, 1, (int)('r'-'a')}, {0, -1, (int)('l'-'a')}, {1, 0, (int)('d'-'a')}, {-1, 0, (int)('u'-'a')}};
      int m = maze.length, n = maze[0].length;
      int[][] visited = new int[m][n];
      String[][] path = new String[m][n];
      for(int i=0; i<m; i++) {
           Arrays.fill(visited[i], Integer.MAX_VALUE);
           Arrays.fill(path[i], "");
      }
      visited[ball[0]][ball[1]]=0;
      Queue<int[]> q =new LinkedList<>();
      q.offer(ball);
      while(!q.isEmpty()) {
          int[] cur = q.poll();
          for(int[] d : dirs) {
              int step= visited[cur[0]][cur[1]];
              String curPath = path[cur[0]][cur[1]] + (char)(d[2] + 'a');
              int x = cur[0], y=cur[1];
              while(x + d[0] <m && x + d[0] >=0 && y + d[1] <n && y + d[1] >=0 && maze[x + d[0]][y+ d[1]] ==0 && 
                    !(x == hole[0] && y==hole[1])) {  // This is the key!!!
                  step++;
                  x +=d[0];
                  y +=d[1];
              }
              if(visited[cur[0]][cur[1]] == step)  continue; // If it stay where it is, break
              
              if(visited[x][y] > step) {
                  visited[x][y] = step;
                  path[x][y] = curPath;
                  q.offer(new int[]{x, y});
              } else if(visited[x][y] == step) {
                  path[x][y] = path[x][y].length()==0 ? curPath : (curPath.compareTo(path[x][y]) >0 ? path[x][y] : curPath);
                  q.offer(new int[]{x, y}); // Even if it is the same shortest distance, we still need to check again
              } 
          }
      }
      return path[hole[0]][hole[1]].length()==0 ? "impossible" : path[hole[0]][hole[1]];
  }
}