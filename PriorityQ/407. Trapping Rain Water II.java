/*
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

 

Note:

Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

 

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.


The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.

 



After the rain, water is trapped between the blocks. The total volume of water trapped is 4.
*/

/*

use BFS:


能装水的底面肯定不能在边界上，因为边界上的点无法封闭，那么所有边界上的点都可以加入queue，当作BFS的启动点，同时我们需要一个二维数组来标记访问过的点，访问过的点我们用红色来表示

   0 1 2 3 4 5       
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
  add all surround points in a priority queue first based on height
  Q: [1(0,0), 1(0,3), 1(2,5), 2(0,5), 2(2,0), 2(2,3), 3(0,2), 3(0,4), 3(1,0), 3(2,1), 3(2,2), 3(2,4), 4(0,1), 4(1,5)]
  
  pop 1(0,0) height=1 since surround has visited, continue
  pop 1(0,3) height=1 since surround 3(1,3) not visited add to Q
  pop 1(2,5) height=1 since surround has visited, continue
  pop 2(0,5) height=2 since surround has visited, continue
  pop 2(2,0) height=2 since surround has visited, continue
  pop 2(2,3) height=2 since surround has visited, continue
     current Q = [3(0,2), 3(0,4), 3(1,0), 3(2,1), 3(1,3), 3(2,2), 3(2,4), 4(0,1), 4(1,5)] 
  pop 3(0,2) height=3 since surround 1(1,2) not visited add to Q  res +=2
     current Q = [1(1,2), 3(0,4), 3(1,0), 3(2,1), 3(1,3), 3(2,2), 3(2,4), 4(0,1), 4(1,5)] 
  pop 1(1,2), height still=3 since surround 2(1,1) and 3(1,3) not visited add to Q
     current Q = [2(1,1), 3(1,3), 3(0,4), 3(1,0), 3(2,1), 3(2,2), 3(2,4), 4(0,1), 4(1,5)] 
  pop 2(1,1), height still=3 since surround visited, res += 1=3
  pop 3(1,3) height=3 since surround 2(1,4) not has visited add to Q, res += 3-2=4
  pop 3(0,4) since surround has visited, continue
  pop 3(1,0) since surround has visited, continue
  pop 3(2,1) since surround has visited, continue
  pop 3(2,2) since surround has visited, continue
  ...
  
  res = 4

*/

class Solution {
  public int trapRainWater(int[][] heightMap) {
      if(heightMap.length==0) return 0;
      
      int m = heightMap.length, n = heightMap[0].length, h=0, res=0;
      int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      boolean[][] visited = new boolean[m][n];
      // sort from smaller to large, poll get the smallest
      Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); 
      // add all boundry to pq first
      for(int j=0; j<n; j++) {
          pq.offer(new int[]{heightMap[0][j], 0*n+j});
          visited[0][j] = true;
          pq.offer(new int[]{heightMap[m-1][j], (m-1)*n+j});
          visited[m-1][j] = true;
      }
      for(int i=1 ;i<m-1 ;i++) {
          pq.offer(new int[]{heightMap[i][0], i*n});
          visited[i][0] = true;
          pq.offer(new int[]{heightMap[i][n-1], i*n+n-1});
          visited[i][n-1] = true;
      }
      // now BFS
      while(!pq.isEmpty()) {
          int[] cur = pq.poll();
          h  = Math.max(cur[0], h);
          for(int[] d: dirs) {
              int x = (cur[1]/n) + d[0];
              int y = (cur[1]%n) + d[1];
              if(x < 0 || y<0 || x >= m ||y >= n || visited[x][y])  continue;
              if(heightMap[x][y] < h)  res += h - heightMap[x][y];
              pq.offer(new int[] {heightMap[x][y], x*n+y});
              visited[x][y] =true;
          }
      }
      return res;
  }
}