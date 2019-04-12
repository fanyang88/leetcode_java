/*
There are 1000 buckets, one and only one of them is poisonous, while the rest are filled with water. They all look identical. If a pig drinks the poison it will die within 15 minutes. What is the minimum amount of pigs you need to figure out which bucket is poisonous within one hour?

Answer this question, and write an algorithm for the general case.

 

General case:

If there are n buckets and a pig drinking poison will die within m minutes, how many pigs (x) you need to figure out the poisonous bucket within p minutes? There is exactly one bucket with poison.

 

Note:

A pig can be allowed to drink simultaneously on as many buckets as one would like, and the feeding takes no time.
After a pig has instantly finished drinking buckets, there has to be a cool down time of m minutes. During this time, only observation is allowed and no feedings at all.
Any given bucket can be sampled an infinite number of times (by an unlimited number of pigs).
*/


/*

    这道题博主拿到以后并木有什么头绪，可是明明标的是Easy，打击甚大，于是去论坛上大神们的解法，感觉这道题应该算是一道Brain Teaser的题，对问题的分析能力要求很高。那么我们来一步一步从最简单的情况来分析吧，
    假设只有1只猪，只有15分钟，那么我们能测几个水桶呢？很显然是两个，因为只能测一次的话，让猪去随便喝一桶，如果毒死了，就是喝的那桶，反之则是另一桶。
    好，那么如果有两只猪呢，能测几桶？怎么喝呢，两只猪一猪喝一桶，再同时喝一桶，剩下一桶谁也不喝，那么如果两只猪都毒死了，说明是共同喝的那桶有毒，如果某个猪毒死了，说明该猪喝的那桶有毒，如果都没事，说明是谁也没喝的那桶。那么我们应该看出规律了吧，
     1   2   3   4
    00  01   10  11
        p2   p1  p1p2
    没错，三猪能测8桶，其实就是2的指数倍。
    
    so if only can test once, bucket number is 2^n(n is number of pig)
    如果只能测一次的话，实际上相当一个一维数组，而如果能测两次的话，情况就不一样了，我们就可以重复利用猪了。
    比如还是两只猪，能测两次，功能测几个桶，答案可以测9桶，为啥，我们组个二维数组：
        1  2  3
        4  5  6
        7  8  9
如果我们让第一头猪第一次喝1，2，3桶，第二次喝4，5，6桶，而让第二头猪第一次喝1，4，7桶，第二次喝2，5，8桶，我们可以根据猪的死亡情况来确定是哪一桶的问题，
    e.g: if pig 1 die, pig 2 die in first round means the first round has poison, (1,2,3) & (1,4,7)= 1 is poison
         if pig 2 die in first round, pig1 die in second round, means (4，5，6) & (1,4,7)= 4 is poision
         if pig 1 die in first round, pig2 die in second round, means (1,2,3) & (2,5,8)= 2 is poision
         if pig 1 die in second round, pig2 die in second round, means (4,5,6) & (2,5,8)= 5 is poision
         if pig 1 die in second round, pig2 not die in second round, means (4,5,6) & (3,6,9)= 6 is poision
         if pig 2 die in second round, pig1 not die in second round, means (2,5,8) & (7,8,9)= 8 is poision
         if pig 2 not die in second round, pig1 not die in second round, means (3,6,9) & (7,8,9)= 9 is poision
            round1-p1 round1-p2 round2-p1 round2-p2 (0 is alive, 1 is die)
            0000 
            1100 
            1000
            0100
            0011
            0001
            0010
            1001
            0110
   9 buckets, 2 rounds, log2(9) =          
实际上就把猪被毒死的那个节点当作了二维数组的横纵坐标来定位毒桶的位置，巧妙吧～更巧妙的是，如果再增加一头猪，实际上是给数组增加一个维度，变成了一个三维数组，
那么三只猪，测两次，可以测27桶，叼不叼。这道题让我们求最少用多少猪来测，那么就是求数组的维度，我们知道了数组的总个数，所以要尽量增加数组的长宽，尽量减少维度。
这里，数组的长宽其实都是测试的次数+1，所以我们首先要确定能测的次数，通过总测试时间除以毒发时间，再加上1就是测试次数。
有了数组长宽m，那么如果有x只猪，能测的桶数为m的x次方，现在我们给定了桶数N，要求x，就log一下就行，然后用个换底公式，就可以求出x的值了，参见代码如下：

 Thinking Process
1. What if you only have one shot?
Eg. 4 buckets, 15 mins to die, and 15 mins to test.
The answer is 2. Suppose we use A and B to represent pigs, we could have
Buckets     0   1   2   3
            00  01  10  11
drink by         B  A   AB 

Conclusion: If we have x pigs, we could use them to represent (encode) 2^x buckets.


2. What if we could have more than one attempts?

Eg. 4 buckets, 15 mins to die, and 30 mins to test.

At the moment, I consider the problem as an encoding problem: With more attempts, 
how to use fewer pigs to represent all the buckets?
What does the binary form mean? It's much easier if we regard it as:
0 means the pig does not drink.
1 means the pig drinks in the first (and only) round.

We could generalise with:
0 means the pig does not drink.
1 means the pig drinks in the first round.
2 means the pig drinks in the second round.
...
t means the pig drinks in the t-th round and die.
Conclusion: If we have t attempts, we could use t+1-based number to represent (encode) the buckets. 
(That's also why the first conclusion uses the 2-based number)

Example
Eg. 8 buckets, 15 mins to die, and 40 mins to test.
We have 2 (= (40/15).floor) attempts, as a result we'll use 3-based number to encode the buckets.

Bucket          0       1    2     3    4     5     6     7
3-based         00      01  02    10   11    12    20    21
firstRound      __      _B  __    A_   AB    A_    __    _B
sec round       __      __  _B    __   __    _B    A_    A_

For example 3-based number 02 means: the pig A does not drink and die, and the pig B drinks in the second round and die.
3^x = 8  x = ceil(log(8, 3))

*/

class Solution {
  public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
      int pig=0, base = minutesToTest / minutesToDie +1;
      while(Math.pow(base, pig) < buckets) pig++;
      return pig;
  }
}
