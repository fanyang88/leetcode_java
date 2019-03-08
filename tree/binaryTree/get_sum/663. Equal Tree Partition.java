/*
Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:     
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation: 
    5
   / 
  10
      
Sum: 15

   10
  /  \
 2    3

Sum: 15
Example 2:
Input:     
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
Note:
The range of tree node value is in the range of [-100000, 100000].
1 <= n <= 10000
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 get the sum of each nodes and store in hashmap, get the total sum of the whole tree, 
 check if there is a sum/2 in hashmap, if there is, return true, else return false; 
 
 */
class Solution {
  public boolean checkEqualTree(TreeNode root) {
      Map<Integer, Integer> map = new HashMap<>();
      int sum = getSum(root, map);
      if(sum==0) return map.get(0) >1;
      return sum%2==0 && map.get(sum/2) !=null && map.get(sum/2)>0;
  }
  
  public int getSum(TreeNode root, Map<Integer, Integer> map) {
      if(root ==null) return 0;
      int sum = root.val + getSum(root.left, map) + getSum(root.right, map);
      map.put(sum, map.getOrDefault(sum,0)+1);
      return sum;
  }
}
