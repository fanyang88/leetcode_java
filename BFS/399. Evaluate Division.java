/*
Equations are given in the format A / B = k, where A and B are variables represented as strings, 
and k is a real number (floating point number). 
Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/

/*
    use a map to store the equation as: key : character value : [another char with value]
 e.g:   
 equations = [ ["a", "b"], ["b", "c"] ],
 values = [2.0, 3.0],
 map: a: [[b, 2.0]]
      b: [[a, 1/2.0], [c, 3.0]]
      c: [[b, 1/3.0]]
      
use BFS to search for each query, if the dest or src not in map, return -1, if dest = src, return 1.0, otherwise, there is a path, find the multiple of the values

e.g: a/c = a/b=2.0 * b/c=3.0=6.0
     a/a = 1.0  src ==dest
      

*/

class Solution {
    
  class Node {
      double val;
      String key;
      public Node(String key, double val) {
          this.key = key;
          this.val = val;
      }
  }
  
  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
      Map<String, List<Node>> map = new HashMap<>();
      double[] res = new double[queries.length];
      
      for(int i=0; i<equations.length; i++) {
          String[] eqn= equations[i];
          if(map.get(eqn[0]) == null) map.put(eqn[0], new ArrayList<>());
          if(map.get(eqn[1]) == null) map.put(eqn[1], new ArrayList<>());
          map.get(eqn[0]).add(new Node(eqn[1], values[i]));
          map.get(eqn[1]).add(new Node(eqn[0], 1/values[i]));
      }
      
      // start BFS
      for(int i=0; i<queries.length; i++) {
          res[i] = bfs(queries[i][0], queries[i][1], map);
      }
      return res;
  }
  
  public double bfs(String src, String dest, Map<String, List<Node>> map) {
      if(map.get(src)==null || map.get(dest) ==null) return -1.0;
      if(src == dest) return 1.0;
      
       // find if there is a path from src to dest
      Set<String> set = new HashSet<>();
      set.add(src);
      Queue<Node> q = new LinkedList<>();
      q.offer(new Node(src, 1.0));
      while(!q.isEmpty()) {
          Node cur = q.poll();
          if(cur.key.equals(dest))  return cur.val;
          if(map.get(cur.key) ==null)  continue;
          for(Node next: map.get(cur.key)) {
              if(set.contains(next.key))  continue;
              q.offer(new Node(next.key, next.val*cur.val));
              set.add(next.key);
          }
      }
      return -1.0;
  }
  
}
