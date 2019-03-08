/*
Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

Example 1:
Input:
n = 2
logs = 
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
Output:[3, 4]
Explanation:
Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. 
Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. 
So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
Note:
Input logs will be sorted by timestamp, NOT log id.
Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
Two functions won't start or end at the same time.
Functions could be called recursively, and will always end.
1 <= n <= 100
*/

/*

e.g: 
        "0:start:0",
        "1:start:2",
         2:start:3
         2: end: 4
        "1:end:  5",
        "0:end:  6"
 
 
 process input using stack
 push[0,0]
 push[1,2]
 push[2,3]
 encounter [2,4] pop[2,3] map[2] += 4-3+1=2 current stack:{[0,0], [1,2]}  map[1] -= 2=-2
 encounter [1,5] pop[1,2] map[1] += 5-2+1=2  current stack:{[0,0]} map[0] -= 4=-4
 encounter [0,6] pop[0,0] map[0] += 6-0+1= 7-4=3
 
*/
class Solution {
  public int[] exclusiveTime(int n, List<String> logs) {
      int[] res = new int[n];
      Stack<int[]> st = new Stack<>();
      for(String log: logs) {
          int id = Integer.valueOf(log.substring(0, log.indexOf(":")));
          String status = log.substring(log.indexOf(":")+1, log.lastIndexOf(":"));
          int curTime = Integer.valueOf(log.substring(log.lastIndexOf(":")+1));
          if(status.equals("start")) {
              st.push(new int[]{id, curTime});
          } else {
              int[] popped= st.pop();
              int functionId = popped[0];
              int startTime = popped[1];
              res[functionId] += curTime - startTime +1; // update current functionId
              if(!st.isEmpty())
                  res[st.peek()[0]] -= curTime - startTime +1; // update the parent functionId as well, The key!!!
          }
      }
      return res;
  }
}
