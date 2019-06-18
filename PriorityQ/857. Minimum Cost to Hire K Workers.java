/*
There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.

 

Example 1:

Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
 

Note:

1 <= K <= N <= 10000, where N = quality.length = wage.length
1 <= quality[i] <= 10000
1 <= wage[i] <= 10000
Answers within 10^-5 of the correct answer will be considered correct.
*/

/*

这个题不是很好理解，经过我的仔细推敲后，觉得可以这么表述：每个工人都有自己期望的价性比，雇佣K个工人的时候要满足每个人的实际价性比不低于他的期望，
即需要按照K个工人中的最高期望价性比给这K个人开工资。问需要的最少的工资是多少。注意使用的是价性比，不是性价比。因为性价比是我们买东西的时候希望的，
而这些工人是出卖自己的劳动力的，因此他们希望得到更高的价性比。而作为选择工人的这一方，我们希望工人的性价比更高点，但是啊得注意，
性价比高的工人会被那些性价比低的工人抬高工资，即他也会要求更高的工资，没有工人愿意看到别人好吃懒做还拿高工资，
所以性价比高的工人会索要更高的工资，导致自己性价比和好吃懒做的工人一样。

如果理解了上面的那段话，那么我们需要按照价性比来做排序，然后依次遍历，得到K个工人的工资总和。

That is, the group of people should have the same ratio.
Every worker in the paid group must be paid at least their minimum wage expectation.
->

money[i] >= wage[i]
ratio >= wage[i] / quality[i] -- ratio2
->
ratio is at least the maximum ratio2 within the group of K people.

If we keep current candidates in a priority queue as below,

|-- < k candidates--|    
			new worker to join is with higher ratio2
If there are k + 1candidates, we pop the highest quality to reduce total cost.
If there are k candidates, we can calculate the total cost.

Time Complexity
O(NlogN) for sort.
O(NlogK) for priority queue.
 
     sort by wage/quality first
      [10,20,5]  [70, 50, 30]
     [5, 30/5]       sort    [20, 2.5]
     [20, 50/20]     ---->   [5, 6]
     [10, 70/10]             [10, 70/10]
     pick 20 first, total = 20*2.5 = 50
     pick 5 total = 6*(5) + 20*6 = 150
     poll 20, pick 10, total = 10*7 + 5*7=105
     
     [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
     [3,4/3]            [10, 2/10]
     [1,8/1]     sort   [10, 2/10]
     [10, 2/10]  ---->  [3, 4/3]
     [10, 2/10]         [1, 7/1]
     [1, 7/1]           [1, 8/1]
     
     we need to find the max ratio that cover K workers and the ratio leads to min wage
     push [10, 0.2] total = 2
     push [10, 0.2] total = 0.2*20=4
     push [3, 4/3] total = 23*1.3333 = 30.66667 = minV
     poll [10, 0.2] push [1, 7/1] in this way total = (1+3+10)*7= 98, minV = 30.66667
     ...
     
     need to use maxHeap to pop the high quality to reduce cost

*/


class Solution {
  public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b-a);
    double[][] workers = new double[quality.length][2];
    for(int i=0; i<quality.length; i++) 
        workers[i] = new double[]{(double)(wage[i])/quality[i], (double)quality[i]};
    
    Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
    
    int sum =0;
    double minV = Double.MAX_VALUE;
    for(double[] worker : workers) {
        sum += (int)worker[1];
        pq.offer((int)worker[1]);
        if(pq.size() > K)  sum -= pq.poll(); // poll get the largest out.
        if(pq.size() == K) minV = Math.min(minV, sum* worker[0]);
    }
    return minV;
  }
}


