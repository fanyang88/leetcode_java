/*
You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
 

You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

Example 1:

Input: 
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6
 

Example 2:

Input: 
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1
 

Example 3:

Input: 
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
*/

/*
    to find all trees from start (0, 0)
 1. sort all trees in height order and store in the array
 2. in the array, we need to find the shortest path(0, 0) => arr[0](tree 1) => arr[1](tree 1) => arr[2](tree 2) => arr[n](tree n) 
 3. use a bfs to find the minimum path between two points: (sx, sy) => (tx, ty)
 O(mn * mn)
 
*/

class Solution {
  class Node {
      int x, y, h;
      Node(int x, int y, int h) {
          this.x = x;
          this.y = y;
          this.h = h;
      }
  }
  
  int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  public int cutOffTree(List<List<Integer>> forest) {
      int sum=0, m = forest.size(), n=forest.get(0).size();
      Queue<Node> pq = new PriorityQueue<>((a,b) -> (a.h - b.h)); 
      for(int i=0; i<m; i++) {
          for(int j = 0; j<n; j++) {
              if(forest.get(i).get(j) > 0) 
                  pq.add(new Node(i, j, forest.get(i).get(j)));
          }
      }
      // get path using bfs
      int sx=0, sy=0;
      while(!pq.isEmpty()) {
          Node cur = pq.poll();
          int path = bfs(sx, sy, cur.x, cur.y, forest, m, n);
          if(path == -1)  return -1;
          sum += path;
          sx = cur.x;
          sy = cur.y;
      }
      return sum;
  }
  
  public int bfs(int sx, int sy, int ex, int ey, List<List<Integer>> forest, int m, int n) {
      boolean[][] visited = new boolean[m][n];
      Queue<int[]> queue = new LinkedList<>();
      int step = 0;
      queue.add(new int[]{sx, sy});
      while(!queue.isEmpty()) {
          int size = queue.size();
          for(int i=0; i<size; i++) {
              int[] cur = queue.poll();
              if(cur[0] ==ex && cur[1]==ey) return step;
              for(int[] d: dirs) {
                  int nx = cur[0] + d[0], ny = cur[1] + d[1];
                  if(nx < 0 | nx >=m || ny<0 || ny>=n || visited[nx][ny] || forest.get(nx).get(ny) ==0) continue;
                  queue.offer(new int[]{nx, ny});
                  visited[nx][ny]=true;
              }
          }
          step ++;
      }
      return -1;
  }
}

