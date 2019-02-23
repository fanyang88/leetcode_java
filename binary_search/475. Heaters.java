/*
Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Note:

Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.
 

Example 1:

Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
 

Example 2:

Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
*/


/*

For each house, find its position between those heaters (thus we need the heaters array to be sorted).
Calculate the distances between this house and left heater and right heater, get a MIN value of those two values. Corner cases are there is no left or right heater.
Get MAX value among distances in step 2. It's the answer.

    [1,2,3,4],[1,4] 
    for house 1, closest heat is at same pos, so left=0 right=0   minV=0
    for house 2, closest heat is at 1, left=1 right=maxV  minV=1 
    for house 3, closest heat is at 4, left=max, right=1 minV=1
    for house 4, left=right=0
    answer = max(0, 1, 1, 0) = 1
    
*/

class Solution {
  public int findRadius(int[] houses, int[] heaters) {
      Arrays.sort(heaters);
      int left, right, res= Integer.MIN_VALUE;
      for(int house : houses) {
          int pos = binarySearch(heaters, house); 
          // since heater[pos] >= house, means the heater is at right side of the house
          if(pos >=0 && heaters[pos] >= house) {
              left = pos-1 >=0 ? house - heaters[pos-1] : Integer.MAX_VALUE;
              right = heaters[pos] - house;
          } else {  // heater can be at left side or not exist
              left = pos >=0 ? house - heaters[pos] : Integer.MAX_VALUE;
              right = pos + 1 < heaters.length ? heaters[pos+1] - house: Integer.MAX_VALUE;
          }
          res = Math.max(res, Math.min(left, right));
      }
      return res;
  }
  
  public int binarySearch(int[] arr, int key) {
      int lo = 0, hi = arr.length-1;
      while(lo < hi) {
          int mid = lo + (hi-lo)/2;
          if(arr[mid] < key) {
              lo = mid+1;
          } else {
              hi = mid;
          }
      }
      return hi;
  }
}

