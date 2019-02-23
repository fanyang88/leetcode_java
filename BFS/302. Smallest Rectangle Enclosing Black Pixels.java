/*
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
The black pixels are connected, i.e., 
there is only one black region. Pixels are connected horizontally and vertically. 
Given the location (x, y) of one of the black pixels, 
return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

Example:

Input:
[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2

Output: 6
*/


/*
  get Xmin, Xmax, Ymin, Ymax from all 1s.
    max area = (Xmax - Xmin + 1 )* (Ymax - Ymin + 1 );
    use BFS from x, y
*/
class Solution {
  public int minArea(char[][] image, int x, int y) {
      if(image.length ==0)  return 0;
      int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      int m = image.length, n = image[0].length, minX=m, minY=n, maxX=0, maxY=0;
      
      boolean[][] visited = new boolean[m][n];
      Queue<Integer> q = new LinkedList<>();
      q.offer(x*n+y);
      visited[x][y]=true;
      while(!q.isEmpty()) {
          int cur = q.poll();
          int curX = cur / n;
          int curY = cur % n;
          minX = Math.min(minX, curX);
          minY = Math.min(minY, curY);
          maxX = Math.max(maxX, curX);
          maxY = Math.max(maxY, curY);
          
          for(int[] d: dirs) {
              int newX = curX + d[0];
              int newY = curY + d[1];
              if(newX < m && newX >=0 && newY < n && newY >=0 && !visited[newX][newY] && image[newX][newY]=='1') {
                  q.offer(newX*n+newY);
                  visited[newX][newY]=true;
              }
          }
      }
      return (maxX - minX+1)*(maxY - minY+1);
  }
}