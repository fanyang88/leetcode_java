/*

Implement a MyCalendarThree class to store your events. A new event can always be added.

Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end), the range of real numbers x such that start <= x < end.

A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)

For each call to the method MyCalendar.book, return an integer K representing the largest integer such that there exists a K-booking in the calendar.

Your class will be called like this: MyCalendarThree cal = new MyCalendarThree(); MyCalendarThree.book(start, end)
Example 1:

MyCalendarThree();
MyCalendarThree.book(10, 20); // returns 1
MyCalendarThree.book(50, 60); // returns 1
MyCalendarThree.book(10, 40); // returns 2
MyCalendarThree.book(5, 15); // returns 3
MyCalendarThree.book(5, 10); // returns 3
MyCalendarThree.book(25, 55); // returns 3
Explanation: 
The first two events can be booked and are disjoint, so the maximum K-booking is a 1-booking.
The third event [10, 40) intersects the first event, and the maximum K-booking is a 2-booking.
The remaining events cause the maximum K-booking to be only a 3-booking.
Note that the last event locally causes a 2-booking, but the answer is still 3 because
eg. [10, 20), [10, 40), and [5, 15) are still triple booked.
 

Note:

The number of calls to MyCalendarThree.book per test case will be at most 400.
In calls to MyCalendarThree.book(start, end), start and end are integers in the range [0, 10^9].

*/


/*
to get the maximum concurrent events number.
we need to get the max number during any time between start and end.
[10,20]     0 - 5 - 10 - 15 - 20
                    1         -1                                               max= 1
[50,60]     0 - 5 - 10 - 15 - 20 - 25 - 30 - 35 - 40 - 45 - 50 - 55 - 60
                    1         -1                             1        -1       max= 1
[10,40]     0 - 5 - 10 - 15 - 20 - 25 - 30 - 35 - 40 - 45 - 50 - 55 - 60
                   [2         -1                 -1]         1        -1       max= 2
[5,15]      0 - 5 - 10 - 15 - 20 - 25 - 30 - 35 - 40 - 45 - 50 - 55 - 60
               [1    2   -1]  -1                  -1         1        -1       max= 3
[5,10]      0 - 5 - 10 - 15 - 20 - 25 - 30 - 35 - 40 - 45 - 50 - 55 - 60
                [2  1]   -1   -1                  -1        1        -1       max= 3
[25,55]     0 - 5 - 10 - 15 - 20 - 25 - 30 - 35 - 40 - 45 - 50 - 55 - 60
                2    1   -1   -1   [1             -1        1    -1]  -1       max= 3


This is to find the maximum number of concurrent ongoing event at any time.

We can log the start & end of each event on the timeline, each start add a new ongoing event at that time, each end terminate an ongoing event. Then we can scan the timeline to figure out the maximum number of ongoing event at any time.

The most intuitive data structure for timeline would be array, but the time spot we have could be very sparse, 
so we can use sorted map to simulate the time line to save space.

*/
class MyCalendarThree {

  TreeMap<Integer, Integer> map;
  public MyCalendarThree() {
      map = new TreeMap<>();
  }
  
  public int book(int start, int end) {
      map.put(start, map.getOrDefault(start, 0) +1);
      map.put(end, map.getOrDefault(end, 0) -1);
      // since map is already sorted, we need to the get max sum of the values
      int sum=0, max = 0;
      for(int val : map.values()) {
          sum += val;
          max = Math.max(max, sum);
      }
      return max;
  }
}

/**
* Your MyCalendarThree object will be instantiated and called as such:
* MyCalendarThree obj = new MyCalendarThree();
* int param_1 = obj.book(start,end);
*/