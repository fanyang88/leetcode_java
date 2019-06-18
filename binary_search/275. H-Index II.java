/*
Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [0,1,3,5,6]
Output: 3 
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
             received 0, 1, 3, 5, 6 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
Note:

If there are several possible values for h, the maximum one is taken as the h-index.

Follow up:

This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
Could you solve it in logarithmic time complexity?
*/ 

/*
there are h papers have at least h citations each

[0,1,3,5,6]  ans=5
i=0 0<5 means there are 5 paper each with citation >=0 we narrow to 4
i=1 1<4 means there are 4 paper each with citation >=1 we narrow to 3
i=2 3=3 means there are 3 paper each with citation >=3 

[0,1,2,5,6]  ans=5
i=0 0<5 means there are 5 paper each with citation >=0(0<5=h) we narrow to 4
i=1 1<4 means there are 4 paper each with citation >=1(1<4=h) we narrow to 3
i=2 2=3 means there are 3 paper each with citation >=2(2<3=h) we narrow to 2
i=3 5>2 means there are 2 paper each with citation >=5(5>2=h) break

find the first citation that has value >= number of papers
*/

class Solution {
  public int hIndex(int[] citations) {
      int res = citations.length;
      for(int citation: citations) {
          if(citation >= res) break;
          res--;
      }
      return res;
  }
}


// Binary search based on above code
class Solution {
  public int hIndex(int[] citations) {        
      int left=0, len = citations.length, right= len-1,  mid;
      while(left<=right) {
          mid=(left+right)>>1;
          if(citations[mid]== (len-mid)) return citations[mid];
          if(citations[mid] >= (len-mid)) right = mid - 1;
          else left = mid + 1;
      }
      return len - (right+1);
  }
}

