/*
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].
*/

/*
    The key task here is to connect those emails, and this is a perfect use case for union find.
to group these emails, each group need to have a representative, or parent.
At the beginning, set each email as its own representative.
Emails in each account naturally belong to a same group, and should be joined by assigning to the same parent (let's use the parent of first email in that list);
Simple Example:

a b c // now b, c have parent a
d e f // now e, f have parent d
g a d // now abc, def all merged to group g

parents populated after parsing 1st account: a b c
a->a
b->a
c->a

parents populated after parsing 2nd account: d e f
d->d
e->d
f->d

parents populated after parsing 3rd account: g a d
g->g
a->g
d->g

1. map first email with name
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
map1: johnsmith@mail.com : John
     johnnybravo@mail.com: John
     john_newyork@mail.com: John
     mary@mail.com: Mary
     
another map to store     roots:
johnsmith@mail.com:     johnsmith@mail.com
john00@mail.com         johnsmith@mail.com
johnnybravo@mail.com    johnnybravo@mail.com
john_newyork@mail.com   john00@mail.com -> johnsmith@mail.com
mary@mail.com           mary@mail.com

now we define another map to get all values from above map
johnsmith@mail.com:  johnsmith@mail.com, john00@mail.com, john_newyork@mail.com
johnnybravo@mail.com: johnnybravo@mail.com
mary@mail.com: mary@mail.com

then we get the owner from map1 to get the result
*/


class Solution {
  public List<List<String>> accountsMerge(List<List<String>> accounts) {
      List<List<String>> res = new LinkedList<>();
      Map<String, String> roots = new HashMap<>(), owner = new HashMap<>();
      Map<String, TreeSet<String>> unions = new HashMap<>();
      for(List<String> act: accounts) {
          for(int i=1; i<act.size(); i++) {
              roots.put(act.get(i), act.get(1));
              owner.put(act.get(i), act.get(0));
          }
      }
      
      // now we need to merge the roots
      for(List<String> act: accounts) {
          String parent = find(roots, act.get(1));
          for(int i=2; i<act.size(); i++) {
              roots.put(find(roots, act.get(i)), parent);  // roots to roots
          }
      }
      
       for(List<String> act: accounts) {
          String parent = find(roots, act.get(1));
          if(!unions.containsKey(parent))  unions.put(parent, new TreeSet<>());
          for(int i=1; i<act.size(); i++) {
              unions.get(parent).add(act.get(i));  // roots to roots
          }
      }
      
      for(String key: unions.keySet()) {
          List<String> account = new ArrayList<>(unions.get(key));
          account.add(0, owner.get(key));
          res.add(account);
      }
      
      return res;
  }
  
  String find(Map<String, String> map, String email) {
      return email.equals(map.get(email)) ? email : find(map, map.get(email));
  }
}
