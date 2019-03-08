/*
There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.

Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.

Example:

Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
Output: 3
Explanation: 
There're totally 4 courses, but you can take 3 courses at most:
First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day. 
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day. 
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 

Note:

The integer 1 <= d, t, n <= 10,000.
You can't take two courses simultaneously.
*/

/*
     [[100, 200], 
     [1000, 1250],  
     [200, 1300], 
     [2000, 3200]]
     
     after sort, is above:
     i=0 time=100   pq=[100]
     i=1 time=100+1000 since time<1250, it is ok to be added pq=[100, 1000]
     i=2 time=1100+200=1300 time<=1300, it is ok to be added pq=[100, 1000, 200]
     i=3 time=1300+2000=3300>3200 pq=[100, 1000, 200, 2000] 
        since time exceeds, drop the previous course which costs the most time. 
            (That must be the best choice!)we need to remove the most costing time course which is 2000
    
         
    answer is pq.size()
    [5, 5] 
    [4, 6] 
    [2, 6]
    
    i=0 time=5 pq=[5]
    i=1 time=5+4=9 pq=[5,4]  since time=9>6 we need to drop the most costing time course which is [5]
        pq=[4] time=4
    i=2 time=2+4=6 pq=[2,4] time=6=6 we can take this course
*/

class Solution {
  public int scheduleCourse(int[][] courses) {
      int time=0;
      Arrays.sort(courses, (a,b)-> (a[1] - b[1]));
      Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b-a)); // poll get the largest
      for(int[] course: courses) {
          time += course[0];
          pq.offer(course[0]);
          if(time > course[1]) {  // we need to remove the most costing time one in order to take this one
              time -= pq.poll();
          }
      }
      return pq.size();
  }
}
