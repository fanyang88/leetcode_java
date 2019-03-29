/*
In a group of N people (labelled 0, 1, 2, ..., N-1), each person has different amounts of money, and different levels of quietness.

For convenience, we'll call the person with label x, simply "person x".

We'll say that richer[i] = [x, y] if person x definitely has more money than person y.  Note that richer may only be a subset of valid observations.

Also, we'll say quiet[x] = q if person x has quietness q.

Now, return answer, where answer[x] = y if y is the least quiet person (that is, the person y with the smallest value of quiet[y]), among all people who definitely have equal to or more money than person x.

 

Example 1:

Input: richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
Output: [5,5,2,5,4,5,6,7]
Explanation: 
answer[0] = 5.
Person 5 has more money than 3, which has more money than 1, which has more money than 0.
The only person who is quieter (has lower quiet[x]) is person 7, but
it isn't clear if they have more money than person 0.

answer[7] = 7.
Among all people that definitely have equal to or more money than person 7
(which could be persons 3, 4, 5, 6, or 7), the person who is the quietest (has lower quiet[x])
is person 7.

The other answers can be filled out with similar reasoning.
Note:

1 <= quiet.length = N <= 500
0 <= quiet[i] < N, all quiet[i] are different.
0 <= richer.length <= N * (N-1) / 2
0 <= richer[i][j] < N
richer[i][0] != richer[i][1]
richer[i]'s are all different.
The observations in richer are all logically consistent.
*/

/*
 using above example, we can use dfs, first construct a map
 map 0:[1]  1:[2,3]   7:[3]  3:[4,5,6]
 0 has 1,2,3,4,5,6 more richer than it, so we get the least quiet one is quiet[5] so person 5 is the least quiet person has money more than 0
 2 has no richer ppl than it, return itself.
*/

class Solution {
  public int[] loudAndRich(int[][] richer, int[] quiet) {
      int[] res = new int[quiet.length];
      Arrays.fill(res, -1);
      Map<Integer, Set<Integer>> map = new HashMap<>();
      for(int rich[]: richer) {
          if(!map.containsKey(rich[1])) map.put(rich[1], new HashSet<>());
          map.get(rich[1]).add(rich[0]);
      }
      for(int i=0; i<quiet.length; i++) {
          dfs(map, i, quiet, res);
      }
      return res;
  }
  
  public int dfs(Map<Integer, Set<Integer>> map, int i, int[] quiet, int[] res) {
      if(res[i] >= 0) return res[i];
      res[i] = i;
      if(map.get(i) == null) return res[i];
      for(int j: map.get(i)) {
          if(quiet[res[i]] > quiet[dfs(map, j, quiet, res)]) res[i] =res[j];
      }
      return res[i];
  }
}
