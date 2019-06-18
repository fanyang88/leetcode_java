/*
Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [3,0,6,1,5]
Output: 3 
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had 
             received 3, 0, 6, 1, 5 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
Note: If there are several possible values for h, the maximum one is taken as the h-index.
*/

/*
        bucket sort
        [3,0,6,1,5]
                          1 1   1   1 1 
        citation number:  0,1,2,3,4,5,6
        
        i=6 sum=1
        i=5 sum=2 
        i=4 sum=2 < i
        i=3 sum=3 = i return 3
*/

class Solution {
  public int hIndex(int[] citations) {
      int maxV = 0, sum=0;
      for(int cite : citations) maxV = Math.max(cite, maxV);
      
      int[] bucket = new int[maxV+1];
      for(int cite : citations)  bucket[cite] ++;
      
      for(int i=maxV; i>=0; i--) {
          sum += bucket[i];
          if(sum >= i) return i;
      }
      return -1;
  }
}
