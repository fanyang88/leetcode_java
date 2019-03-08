/*
There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.

Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.

If there isn't such day, output -1.

Example 1:
Input: 
flowers: [1,3,2]
k: 1
Output: 2
Explanation: In the second day, the first and the third flower have become blooming.
Example 2:
Input: 
flowers: [1,2,3]
k: 1
Output: -1
Note:
The given array will be in the range [1, 20000].
*/

/*
use bucket sort
 partition the pots into buckets, each size is k+1, total number =n/k+1
 b[0]: 1~k+1  b[1]: k+2~2k+2 ....
 
 Track the min and max of each bucket
 for each flower at pos x, bucket number bx= x/k+1
 if min in bucket bx equals x and max value in bucket bx-1 = x-k-1, we found a match, return current flower index+1
 if max in bucket bx equals x and min value in bucket bx+1 = x+k+1, we found a match, return current flower index+1
 
the reason for each flower, we need to check the max from previous bucket and the min from next bucket, is because there are the most closest flowers to my current flower.

e.g: since k=1 [1,3,2]
we can have 3 minbuckets and 3 maxBuckets
i=0 since minBucket is empty put 1/(k+1)=0 put 1 into minBucket and maxBuckets
i=1 since 3/(1+1)=1 id=1 put 3 into 1st minBucket, but since the max from 0th bucket is 1, # of flower between them is 1 which is flower 2, so we get the answer, return i+1

e.g: since k=1 [1,2,3]
i=0 since minBucket is empty put 1/(k+1)=0 put 1 into minBucket and maxBuckets
i=1 since 2/(1+1)=1 id=1 put 2 into 1st minBucket, but since the max from 0th bucket is 1, 
    there is no flower between 1 and 2, continue
i=2 since 3/(1+1)=1 id=1 put 3 into 1st maxBucket, compare with 2th minbucket, no exist, contine

the key is if val should go be bucket i, 
    check if val < minBucket[i] if it is, update minBucket[i], and minBucket[i] should compare with maxBucket[i-1]
    check if val > maxBucket[i] if it is, update maxBucket[i], and maxBucket[i] should compare with minBucket[i+1]
    

*/

class Solution {
  public int kEmptySlots(int[] flowers, int k) {
      int n = flowers.length;
      int[] minBucket = new int[n+1], maxBucket= new int[n+1];
      Arrays.fill(minBucket, Integer.MAX_VALUE);
      Arrays.fill(maxBucket, Integer.MIN_VALUE);
      
      for(int i=0; i<flowers.length; i++) {
          int flower = flowers[i];
          int id = flower/(k+1);
          if(flower < minBucket[id]) {
              minBucket[id] = flower;
              // compare with maxBucket[id-1];
              if(id-1>=0 && maxBucket[id-1]== flower-k-1) return i+1;
          }
          if(flower > maxBucket[id]) {
              maxBucket[id] = flower;
              // compare with minBucket[id+1];
              if(id+1<=n && minBucket[id+1]== flower+k+1) return i+1;
          }
      }
      return -1;
  }
}
