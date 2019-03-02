/*
Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.

You are given several projects. For each project i, it has a pure profit Pi and a minimum capital of Ci is needed to start the corresponding project. Initially, you have W capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.

To sum up, pick a list of at most k distinct projects from given projects to maximize your final capital, and output your final maximized capital.

Example 1:
Input: k=2, W=0, Profits=[1,2,3], Capital=[0,1,1].

Output: 4

Explanation: Since your initial capital is 0, you can only start the project indexed 0.
             After finishing it you will obtain profit 1 and your capital becomes 1.
             With capital 1, you can either start the project indexed 1 or the project indexed 2.
             Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
             Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
Note:
You may assume all numbers in the input are non-negative integers.
The length of Profits array and Capital array will not exceed 50,000.
The answer is guaranteed to fit in a 32-bit signed integer.

*/

/*
Profits=[1,2,3], Capital=[0,1,1].
[1, 0] [2, 1][3, 1]   maxmize capital
W=0 choose [0, 1]
captial=1 choose the one with max captial 

use priority queue
 just use a priority queue, [capital, profit]
 in above case it would be: [[0, 1], [1, 2], [1, 3]]
 k=0, W=0, push 1 to pq: [1] since only the first one qualified, W=1
 k=1, W=1, push 2, 3 to pq, W=1+3
 each time we add the top of pq to W

*/

class Solution {
  public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
      int n = Profits.length;
      int[][] pairs = new int[n][2];
      for(int i=0; i<n; i++) {
          pairs[i][0] = Capital[i];
          pairs[i][1] = Profits[i];
      }
      Arrays.sort(pairs, (a, b) -> {
          if(a[0]== b[0])  return a[1]-b[1];
          return a[0] -b[0];
      });
      Queue<Integer> pq = new PriorityQueue<Integer>((a, b) -> (b - a)); //sort from smaller to large, poll get the largest

      int j=0;
      for(int i=0; i<k; i++) {
          while(j<n && pairs[j][0]<=W) {
              pq.offer(pairs[j++][1]);
          }
          if(pq.size() ==0) return W; // no qualified project
          W+= pq.poll();
      }
      return W;
  }
}