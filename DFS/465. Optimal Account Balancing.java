/*
A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
Example 1:

Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
Example 2:

Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.
*/

/*
debt[i] > 0 means a person needs to pay $ debt[i] to other person(s);
debt[i] < 0 means a person needs to collect $ debt[i] back from other person(s).

        [0,1,10] [2,0,5]
        map[0] = -10
        map[1] = 10
        map[2] = -5
        map[0] = -10+5=-5
        
        debts: [-5, 10, -5]
        transaction debt[i] += debt[0] to clear the person with debt debt[0]. From now on, the person with debt debt[0] is dropped out of the problem and we recursively drop persons one by one until everyone's debt is cleared meanwhile updating the minimum number of transactions during DFS.


        start from index =0
            i=1 since 10*-5<0 clear debts[0] by debts[1] [0, 5, -5], 
                start from debts[1] since-5*5<0 clear debts[1] by debts[2] debts[2]=0
                the branch returns 2
            i=2 since -5*-5>0 debts[2] can not help debts[0] skip
            
                -5
            2   /
            10
         1  /
        -5
    
*/

class Solution {
  public int minTransfers(int[][] transactions) {
      Map<Integer, Integer> map = new HashMap<>();
      for(int[] t: transactions) {
      // Negative debt means the person borrowed money
      // Positive debt means the person lent money
          map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
          map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
      }
      List<Integer> list = new ArrayList<Integer>();
      for(int v: map.values()) {
          if(v==0) continue;
          list.add(v);
      }
      return dfs(0, list);
  }
  
  public int dfs(int cur, List<Integer> list) {
      while(cur<list.size() && list.get(cur)==0) cur++;
      if(cur==list.size()) return 0; // reach to the end
      
      int res = Integer.MAX_VALUE;
      for(int i=cur+1; i<list.size(); i++) {
          if(list.get(i) * list.get(cur) < 0) {
              list.set(i, list.get(i) + list.get(cur));
              res = Math.min(res, 1+dfs(cur+1, list)); // cur debt is clear, move on to cur+1
              list.set(i, list.get(i) - list.get(cur));
          } 
      }
      return res;
  }
}
