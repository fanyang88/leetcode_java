/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
*/

/*
  [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
   JFK:  {SFO, ATL} -> sort{ATL, SFO}
   SFO: {ATL}
   ATL:{JFK, SFO}
   
   JFK find ATL, remove ATL from JFK->{SFO}, res +'ATL'
   ATL find JFK, remove JFK from ATL->{SFO}, res+'JFK'
   JFK find SFO, remove SFO from JFK->{}, res+'SFO'
   SFO find ATL, remove ATL from SFO->{}, res+'ATL'
   ATL find SFO, remove SFO from ATL->{}, res+'SFO'
   
   sorting the dest is the key
*/

class Solution {
  public List<String> findItinerary(String[][] tickets) {
     // List<String> res = new ArrayList<String>();
      Map<String, List<String>> map = new HashMap<>();
      List<String> res = new LinkedList();

      for(String[] ticket: tickets) {
          if(!map.containsKey(ticket[0])) map.put(ticket[0], new ArrayList<String>());
          map.get(ticket[0]).add(ticket[1]);
      }
      
      for(String key: map.keySet()) {
          Collections.sort(map.get(key));
      }
      dfs("JFK", map, res);
      return res;
  }
  
  public void dfs(String cur, Map<String, List<String>> map, List<String> res) {
      while(map.containsKey(cur) && !map.get(cur).isEmpty()) { // has to be while, not if
          String dest = map.get(cur).remove(0);
          dfs(dest, map, res);
      }
      res.add(0, cur);
  }
}