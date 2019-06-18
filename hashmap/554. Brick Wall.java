/**
 * 
 There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

 

Example:

Input: [[1,2,2,1],
        [3,1,2],
        [1,3,2],
        [2,4],
        [3,1,2],
        [1,3,1,1]]

Output: 2

Explanation: 
[1][ 2][2   ][1]
[3    ][1 ]|[2]
[1][3     ]|[2]
[2  ][4       ]
[3     ][1]|[2 ]
[1][3     ]|[1][1]
 

Note:

The width sum of bricks in different rows are the same and won't exceed INT_MAX.
The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.
 */

 /*
    for row 1, sum=1 map[1]=1 sum=3 map[3]=1 sum=5 map[5]=1 sum=6 map[6]=1
    for row 2, sum=3 map[3]++=2 map[4] =1 map[6]=2
    for row 3, sum=1 map[1]=2 map[4]++=2 map[6]=3
    for row 4: sum[2]= 1 map[2]=1 map[6]=4
    for row 5: sum[3] =1 map[3]=3 map[4]=3 map[6]=5
    for row 6: map[1]=3 map[4]=4 map[5]=2 map[6]=6
    
    so int the map:
    key  : value
    1       3    means there is 3 gaps, so cross 3 bricks
    2       1 .  means there is 1 gaps, so cross 5 bricks
    3       3    means there is 3 gaps, so cross 3 bricks
    4       4    means there is 4 gaps, so cross 2 bricks
    5       2    means there is 2 gaps, so cross 4 bricks
    6       6    means there is 6 gaps, so cross 0 bricks
    so the last one doesn't have follow bricks, don't count

*/


class Solution {
  public int leastBricks(List<List<Integer>> wall) {
      int max = Integer.MIN_VALUE;
      HashMap<Integer, Integer> map = new HashMap<>();
      for(int i=0; i<wall.size(); i++) {
          int sum=0;
          for(int j=0; j<wall.get(i).size()-1; j++) {
              sum += wall.get(i).get(j);
              map.put(sum, map.getOrDefault(sum, 0)+1);
              max = Math.max(max, map.get(sum));
              
          }
      }// The key, if max not changed, means all bricks are same length, should return wall.size()
      return max == Integer.MIN_VALUE ? wall.size() : wall.size() - max;
  }
}