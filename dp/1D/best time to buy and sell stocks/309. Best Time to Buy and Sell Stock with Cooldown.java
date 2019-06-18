/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]
*/

/*
        we can choose one of three states: buy, sell, rest
        brute force would be try each 3 and get the max, would be O(3^n)
        
        we can construct the relationship of those 3 states:
                         --
                        |  |
                           V
                        rest
              buy  /            ^   rest
                  V              \
                hold  ----->    sold
                   ^   sell
                |  |
                 ---
                 rest
                 
     If we rest on ith day, we can either rest on i-1th day or buy on i-1th day
     If we hold on ith day, we can either hold on i-1th day or buy on i-1th day
     If we sold on ith day, we can only rest on i-1th day 
     
     sold[i] = hold[i-1]+ price[i]; // sell stock
     hold[i] = max(hold[i-1], rest[i-1] - price[i]), 
     rest[i] = max(rest[i-1], sold[i-1])
     
     
*/

class Solution {
  public int maxProfit(int[] prices) {
      int rest = 0, sold = 0, hold = Integer.MIN_VALUE;
      for(int price: prices) {
          int preRest = rest;
          int preHold = hold;
          int preSold = sold;
          hold = Math.max(preHold, preRest - price);
          sold = preHold + price;
          rest = Math.max(preRest, preSold);
      }
      return Math.max(sold, rest);
  }
}
