/*
Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
Notes:

The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.
*/

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class Solution {
  public void cleanRoom(Robot robot) {
      dfs(0, 0, 0, robot, new HashSet<>());
  }
  
  public void dfs(int x, int y, int curDirection, Robot robot, Set<String> set) {
      robot.clean();
      set.add(x + ":" + y);
      for(int i=0; i<4; i++) {
          int nx = x, ny = y;
          switch(curDirection) {
              case 0: // go up
                  nx = x-1; break;
              case 90: // go up
                  ny = y+1; break;
              case 180: // go up
                  nx = x+1; break;
              case 270: // go up
                  ny = y-1; break;
          }
          if(!set.contains(nx + ":" + ny) && robot.move()) {
               dfs(nx, ny, curDirection, robot, set);
               // mvoe back to original pos
               robot.turnLeft();
               robot.turnLeft();
               robot.move();
               robot.turnLeft();
               robot.turnLeft();
          }
         curDirection = (curDirection + 90) % 360;
          robot.turnRight();
      }
  }
}
