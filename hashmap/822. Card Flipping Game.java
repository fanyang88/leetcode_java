/*
On a table are N cards, with a positive integer printed on the front and back of each card (possibly different).

We flip any number of cards, and after we choose one card. 

If the number X on the back of the chosen card is not on the front of any card, then this number X is good.

What is the smallest number that is good?  If no number is good, output 0.

Here, fronts[i] and backs[i] represent the number on the front and back of card i. 

A flip swaps the front and back numbers, so the value on the front is now on the back and vice versa.

Example:

Input: fronts = [1,2,4,4,7], backs = [1,3,4,1,3]
Output: 2
Explanation: If we flip the second card, the fronts are [1,3,4,4,7] and the backs are [1,2,4,1,3].
We choose the second card, which has number 2 on the back, and it isn't on the front of any card, so 2 is good.
 

Note:

1 <= fronts.length == backs.length <= 1000.
1 <= fronts[i] <= 2000.
1 <= backs[i] <= 2000.
*/

/*
  the goal is we need to find the smallest number that not in the set
  the set should contain all the numbers that are same for front and back
  since flip such number make no sense, so we should exclude such numbers
*/

class Solution {
  public int flipgame(int[] fronts, int[] backs) {
      Set<Integer> set= new HashSet<>();
      int min = Integer.MAX_VALUE;
      for(int i=0; i<fronts.length; i++) {
          if(fronts[i] == backs[i]) set.add(fronts[i]);
      }
      for(int i=0; i<fronts.length; i++) {
          if(!set.contains(fronts[i])) min = Math.min(fronts[i], min);
          if(!set.contains(backs[i])) min = Math.min(backs[i], min);
      }
      return min== Integer.MAX_VALUE ? 0 : min;
  }
}
