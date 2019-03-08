/*
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

 

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 

Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
*/


/*
    Using round robin:
 "A","A","A","B","B","B"  
 we can arrange it to :
 A  B  #    A  B  #  A  B
 |-n+1-|    |-n+1-|  p
 largest freq is A = 3
 so we have (3-1) * (n+1) slots plus the number of tasks that have same freq with A
 the answer is (largest freq-1) * (n-1) + number of tasks that have largest freq
 
 e.g: say freq(A)=k > freq(B) > ...   and there are p tasks has the same freq as A
 so we can arange it to:
 
 A B ..  | A B .. |   A + the other p tasks
 --n+1--   --n+1--

total = (k-1)(n+1) + p
 
 special case is without adding any intervals the tasks can be filled. when tasks number > total
 
e.g: n=2   "A","A","A","A", "B","B","B", "C", "C", "D", "D", "E", "E", "F"  tasks = 14  
using above formula: (4-1)+(2+1) + 1 = 10
A B C | A B C | A B D | A E D | E F 

anwer should be the number of tasks, means there is no idle need to be inserted

why:
since assume A=4 B=3 C=2 D=2 n=2 we can insert:
A B C | A B C | A B D | A 
using formula: 
(4-1)*(2+1) + 1 = 10
but there is one more D missing, so the tasks.length is the answer


*/


class Solution {
  public int leastInterval(char[] tasks, int n) {
      int[] map = new int[26];
      int count=0, max =-1;
      for(char task : tasks) {
          map[task - 'A'] ++;
          max = Math.max(map[task-'A'], max);
      }
      for(int i=0; i<26; i++) {
          if(map[i] == max) count++;
      }
      return Math.max(tasks.length, (max-1)*(n+1) + count);
  }
}
