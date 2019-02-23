/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/


/*
  use union find
  [0,0], [0,1], [1,2], [2,1]  m = 3 n=3
  root[{0,0}] = {0,0}
  root[{0,1}] since [0,1] connect to {0,0} root[{0,1}] ={0,0}
  root{[1,2]} = [1,2]
  root[{2,1}] not connect to any nodes count++
   
   
  use n*x + y to convert to 1D
 for example: positions = [[1,0], [0,1], [1,2], [2,1], [1,1], ]
 when add [1,0]: root[3] = 3 count++=1
 when add [0,1]: root[1] = 1  count++=2
 when add [1,2]: root[5] = 5   count++=3
 when add [2,1]: root[7] = 7   count++=4
 when add [1,1]: root[4] = 4, check left, since 4!=root[3]  curPos = 3, root[4] = 3
                              check right, since 3!=root[5]  curPos = 5, root[3] = 5
                              check top, since 5!=root[1]  curPos = 1, root[5] = 1
                              check bottom, since 1!=root[7]  curPos = 7, root[1] = 7
                              roots= [-1, 7, -1, 5, 3, 1, 7, -1]
 when add [0,0]: root[0] = 0, check right, since root[1] = 7 !=0, root[0] = 7, curPos = 7
                              check bottom,since root[3] = 5,root[5] = 1,root[1] = 7,superroot[3]=7 curPos=7=superroot[3],skip 
 
  when current node is connect to a neighbor node, we count-- and root[currentNode] = root[neighbor] jump to root[neighbor]
  check current pos with another neighbor, if connected, count--,  root[curPos] = root[neighbor] jump to root[neighbor]
  .... until current Pos is equal to neighbor node, means they alreay connected.
  This is the key
*/
class Solution {
  public List<Integer> numIslands2(int m, int n, int[][] positions) {
      // convert to 1D: nx+y
      List<Integer> res= new ArrayList<Integer>();
      int[] roots = new int[m*n];
      int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      Arrays.fill(roots, -1);
      int count=0;
      
      for(int[] pos: positions) {
          int curPos = n*pos[0]+pos[1];
          roots[curPos] = curPos;
          count++;
          for(int[] d: dirs) {
              int neighbor= n*(pos[0] +d[0]) + (pos[1] +d[1]);
              if(pos[0] +d[0] < 0 || pos[0] +d[0] >=m || 
                 pos[1] +d[1] < 0 || pos[1] +d[1] >=n || roots[neighbor] ==-1) continue; 
              int neighborRoot = find(neighbor, roots);
              if(curPos != neighborRoot) { //this is not the destination
                  roots[curPos] = neighborRoot;
                  curPos = neighborRoot;
                  count--;
              }
          }
          res.add(count);
      }
      return res;
  }
  
  public int find(int node, int[] roots) {
      if(node == roots[node]) return node;
      return find(roots[node], roots);
  }
}

