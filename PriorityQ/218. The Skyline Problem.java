/**
 * 
 A city's skyline is the outer contour of the silhouette formed by all the buildings 
 in that city when viewed from a distance. Now suppose you are given the locations and height 
 of all the buildings as shown on a cityscape photo (Figure A), 
 write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], 
where Li and Ri are the x coordinates of the left and right edge of the ith building, 
respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, 
and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded 
on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: 
[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of 
[ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
A key point is the left endpoint of a horizontal line segment. 
Note that the last key point, where the rightmost building ends, 
is merely used to mark the termination of the skyline, and always has zero height. 
Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:
[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */



/*
[2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8]
sort by first value as below, q= [0]
[2, -10] push 10, q =[0, 10]    record[2,10]
[3,-15]  push 15, q =[0, 10, 15]  record [3,15]
[5, -12] since 15>12, 12 is not the highest, not record, q= [0, 10, 12,15]
[7, 15] 15= pop 15, record second highest [7,12] q=[0, 10, 12]
[9, 10] 10 is not the highest, pop 10, q=[0, 12]
[12, 12]  pop 12, record second highest [12, 0] q=[0]
[15, -10]   q =[0, 10]    record[15,10]
[19, -8]    q =[0, 8, 10]
[20, 10]  top=10 pop 10, record [20, 8]  q=[0,8]
[24, 8]   top=8 pop 8, record [24, 0]  q=[0]

sort the array based on the first element
for the first time, the prevMAx = 0, we can direct push the height, and put it on result
for entering event, if the new incoming height smaller than queue peek, that is prevMax still the same, push to stack only,                          otherwise, we also update the prevMax and add it to result
                     otherwise, we need to record the height

for leaving event, if the height after removed from queue won't change the prevMax, means it get shadowed, then that's it,                    otherwise, we also update the prevMax and add the curMax to result
                    otherwise, we need to record the second highest height which is the peek after pop.

when sorting, if two building enter same time, the taller one is before the shorter one
              if two building leave same time, the taller one is after the shorter one
to avoid corner cases
   
  e.g: [0,2,3] [2,5,3]
  [0, -3]  q= [0,3]  pre=-1!=3 record[0,3] pre=3
  [2, -3]  q = [0,3,3] pre=3 =cur=3 continue
  [2, 3]   q=[0,3] pre=3  = cur=3 continue
  [5, 3]  q=[0]  pre=3!=cur=0 record[5, 0] pre=0
*/

class Solution {
  public List<int[]> getSkyline(int[][] buildings) {
      List<int[]> res = new ArrayList<int[]>();
      List<int[]> heights = new ArrayList<int[]>();
      for(int[] b: buildings) {
          heights.add(new int[]{b[0], -b[2]});
          heights.add(new int[]{b[1], b[2]});
      }
      Collections.sort(heights, (a, b)-> {
          if(a[0] == b[0])  return a[1]- b[1];
          return a[0] - b[0];
      });
      int pre = -1;
      Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a)); // sort from smaller to large, poll get the largest
      pq.offer(0);
      for(int[] h: heights) {
          if(h[1] < 0) {      // entering point
              pq.offer(-h[1]);
          } else {                //leaving point
              pq.remove(h[1]);
          }
          int cur = pq.peek();
          if(pre != cur) {  // previous tallest get changed
              res.add(new int[]{h[0], cur});
              pre = cur;
          }
      }
      return res;
  }
}