/*
You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.

For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time .

Given an integer array representing the number of dresses in each washing machine from left to right on the line, you should find the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.

Example1

Input: [1,0,5]

Output: 3

Explanation: 
1st move:    1     0 <-- 5    =>    1     1     4
2nd move:    1 <-- 1 <-- 4    =>    2     1     3    
3rd move:    2     1 <-- 3    =>    2     2     2   
Example2

Input: [0,3,0]

Output: 2

Explanation: 
1st move:    0 <-- 3     0    =>    1     2     0    
2nd move:    1     2 --> 0    =>    1     1     1     
Example3

Input: [0,2,0]

Output: -1

Explanation: 
It's impossible to make all the three washing machines have the same number of dresses. 
Note:
The range of n is [1, 10000].
The range of dresses number in a super washing machine is [0, 1e5].
*/

/*

The logic here is to find the max/peak 'throughput' going from the leftmost washer to the rightmost washer, and the max of the 'GIVE-OUT' washer. The max of these two is the answer. (1) We want the max/peak of the 'throughput' because for washer(s) from one side, giving/receiving its load to/from washer(s) from the other side, their to-give/to-receive loads accumulate during the transportation, like for [-2 -2 0 1, 3]. (its original nums could be [1, 1, 3, 4, 6]), the leftmost -2 cannot be balanced directly without going through the 2nd -2. So it is the same as [0, -4, 0, 1, 3] or [0, 0, -4, 1, 3]. Only adjacent machines can transfer loads, and potentially balance each other or accumulate to-balance values. Here, 4 is the max absolute to-balance value we found going from left to right, so it is 4.

(2) Why not Math.abs(load-avg)? Because [-1, 2 ,-1] and [1, -2, 1] are different!! The former can be balanced with 2 steps, but the latter can be balanced with only 1 step! That is to say, giving out loads and receiving loads are different. One machines can at most give 1 load each step, but can receive at most 2 loads each step. Therefore, finding the max positive to-balance load is what we want. Like [0, -7, 8, -1], no matter what you do or how you do it, the machines with 8 loads need no less than 8 to balance itself and become 0.
You can actually add an extra max = Math.max(Math.abs( (load-avg)/2 ), max) and it won't hurt, since for a machines with -8, it needs no less than 4 steps to become 0.

For example, your machines[] is [0,0,11,5]. So your total is 16 and the target value for each machine is 4. Convert the machines array to a kind of gain/lose array, we get: [-4,-4,7,1]. Now what we want to do is go from the first one and try to make all of them 0.
To make the 1st machines 0, you need to give all its "load" to the 2nd machines.
So we get: [0,-8,7,1]
then: [0,0,-1,1]
lastly: [0,0,0,0], done.
You don't have to worry about the details about how these machines give load to each other. In this process, the least steps we need to eventually finish this process is determined by the peak of abs(cnt) and the max of "gain/lose" array. In this case, the peak of abs(cnt) is 8 and the max of gain/lose array is 7. So the result is 8.

Some other example:
machines: [0,3,0]; gain/lose array: [-1,2,-1]; max = 2, cnt = 0, -1, 1, 0, its abs peak is 1. So result is 2.
machines: [1,0,5]; gain/lose array: [-1,-2,3]; max = 3, cnt = 0, -1, -3, 0, its abs peak is 3. So result is 3.

        [1,0, 5]  avg=2 sum=6
        i=0 ideal=2 sum-ideal = 2-1=1 self=1-2=-1 maxV=1  to make the i=0 to be 2
        i=1 ideal=4 sum-ideal=1-4=3   self = 0-2=-2 maxV=3
        i=2 ideal=6 sum-ideal=6 self=5-2=3 maxV=3
*/

class Solution {
  public int findMinMoves(int[] machines) {
      int sum = 0, max=0, cur=0;
      for(int machine: machines) sum += machine;

      if(sum % machines.length !=0) return -1;
      int avg = sum / machines.length;
      
      for(int i=0; i<machines.length; i++) {
          cur += machines[i];
          int ideal = (i+1)*avg;
          int self = machines[i] -avg;  // This is not the abs, KEY!!!!
          int diff = Math.abs(ideal - cur);
          max = Math.max(self, Math.max(diff, max));
      }
      return max;
  }
}
