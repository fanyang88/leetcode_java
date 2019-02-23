/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
 * find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
 */

 /**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 
 
 [[0,30],[5,10],[15,20]]
 start = [ 0, 5, 15 ] end= [ 10, 20, 30 ]
 since 0<10  count++ need 1 room
 since 5<10 means there is a new meeting start, but since previous one is not finshed yet, we need another room, count++;
 since 15>10 means another meeting start, but the start time is after the end of first meeting, so we can use first room, end ++
 
 */
class Solution {
  public int minMeetingRooms(Interval[] intervals) {
      if(intervals.length==0) return 0;
      int count=0;
      int[] start = new int[intervals.length];
      int[] end = new int[intervals.length];
      for(int i=0; i<intervals.length; i++) {
          start[i] = intervals[i].start;
          end[i] = intervals[i].end;
      }
      Arrays.sort(start);
      Arrays.sort(end);
      for(int i=0, j=0; i<start.length; i++) {
          if(start[i] < end[j]) {
              count++;
          } else {
              j++;
          }
      }
      return count;
  }
}
