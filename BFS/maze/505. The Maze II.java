/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

 

Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12

Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
*/

/*
    similar with maze I, just add the distance to the node
    don't return even if you find destination, could be a smaller dist in future.
  
*/

class Solution {
  public int shortestDistance(int[][] maze, int[] start, int[] destination) {
      int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      int m = maze.length, n = maze[0].length;
      int[][] visited = new int[m][n];
      for(int i=0; i<m; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
      visited[start[0]][start[1]]=0;
      Queue<int[]> q =new LinkedList<>();
      q.offer(start);
      while(!q.isEmpty()) {
          int[] cur = q.poll();
          for(int[] d : dirs) {
              int step= visited[cur[0]][cur[1]];
              int x = cur[0], y=cur[1];
              while(x + d[0] <m && x + d[0] >=0 && y + d[1] <n && y + d[1] >=0 && maze[x + d[0]][y+ d[1]] ==0) {
                  step++;
                  x +=d[0];
                  y +=d[1];
              }
              if(visited[x][y] > step) {
                  visited[x][y] = step;
                  q.offer(new int[]{x, y});
              }
          }
      }
      return visited[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : visited[destination[0]][destination[1]];
  }
}
