/**
 * Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */

 /*
e.g: 10
when i=2 since [2]==F count++
     loop j=2 make[4]=T [6]=T [8]=T
when i=3 since [3]==F count++
     loop j=2 make[6]=T [9]=T 
when i=4 since [4]==T 
when i=5 since [5]==F count++
     loop j=2 make[10]=T 
when i=6 since [6]==T
...
     
    
*/

class Solution {
  public int countPrimes(int n) {
      boolean[] notPrime = new boolean[n];
      int count=0;
      for(int i=2; i<n; i++) {
          if(notPrime[i] == false) {
              count++;
              for(int j=2; j*i<n; j++) {
                  notPrime[i*j] = true;
              }
          }
      }
      return count;
  }
}