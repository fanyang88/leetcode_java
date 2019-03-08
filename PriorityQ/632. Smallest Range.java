/*
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.
*/


/*
    [4,10,15,24,26], 
    [0,9,12,20], 
    [5,18,22,30]
    have one Priority Queue, every time pop the smallest, if max - cur pop < minRange, update range
        we also continue to add the next element to the queue, if next element > max, update max
    heap = [0,4,5]  max=5 range=max_value
    poll 0, since max-0=5 range =5 . add 9: pq= [9,4,5] max = 9
    poll 4: since max-4=5 range =5 . add 10: pq= [9,10,5] max = 10
    poll 5: since max-5=5 range =5 . add 18: pq= [9,10,18] max = 18
    poll 9: since max-9=9 range =5 . add 12: pq= [12,10,18] max = 18
    poll 10: since max-10=8 range =5 add 15: pq= [12,15,18] max = 18
    poll 12: since max-12=6 range =5 add 20: pq= [20,15,18] max = 20
    poll 15: since max-15=5 range =5 add 24: pq= [20,24,18] max = 24
    poll 18: since max-18=6 range =5 add 22: pq= [20,24,22] max = 24
    poll 20: since max-20-4 range =4 reach to end
    answer = 4

*/
class Solution {
    
  public class Element {
      int val, row, col;
       Element(int val, int row, int col) {
          this.val = val;
          this.row = row;
          this.col = col;
      }
  }
  
  public int[] smallestRange(List<List<Integer>> nums) {
      Queue<Element> pq = new PriorityQueue<>((a, b) -> (a.val - b.val)); 
      int k = nums.size(), max = Integer.MIN_VALUE, minRange = Integer.MAX_VALUE, start=-1, end=-1;
      for(int i=0; i<nums.size(); i++) {
          Element e = new Element(nums.get(i).get(0), i, 0);
          pq.add(e);
          max = Math.max(max, e.val);
      }
      
      while(pq.size() == k) {
          Element cur = pq.poll();
          if(max - cur.val < minRange) { // cur must be a smallest
              minRange = max-cur.val;
              start = cur.val;
              end = max;
          }
          if(cur.col +1 <nums.get(cur.row).size()) {
              Element e = new Element(nums.get(cur.row).get(cur.col+1), cur.row, cur.col+1);
              pq.offer(e);
              if(max < e.val) max= e.val;
          }
      }
      return new int[] {start, end};
  }
}
