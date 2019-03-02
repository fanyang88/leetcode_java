/*
Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.
*/

/*
     translate time to be in minute, 23:59-> 23*60 + 59, 00:00=0 . 24*60-(23*60 + 59-0) = 1 diff = 1 
     The required minimum difference must be a difference between two adjacent elements in the circular array 
     (so the last element is "adjacent" to the first.) We take the minimum value of all of them.
*/

class Solution {
  public int findMinDifference(List<String> timePoints) {
      int n = timePoints.size();
      int[] ts = new int[n];
      for(int i=0; i<n; i++) {
          ts[i] = translate(timePoints.get(i));
      }
      Arrays.sort(ts);
      // the last element is "adjacent" to the first
      int res = 24*60 + ts[0] - ts[ts.length-1]; // add 24 hr to the first one to subtract the last one
      for(int i=1; i<n; i++) {
          res = Math.min(res,  (ts[i] - ts[i-1]));
      }
      return res;
  }
  
  public int translate(String time) {
      int h = Integer.valueOf(time.substring(0, time.indexOf(":")));
      int min = Integer.valueOf(time.substring(time.indexOf(":")+1));
      return h*60 + min;
  }
}
