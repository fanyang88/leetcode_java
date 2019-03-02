/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 BFS, put node, col into queue at the same time
Every left child access col - 1 while right child col + 1
This maps node into different col buckets
Get col boundary min and max on the fly
Retrieve result from cols


 */
class Solution {
  public List<List<Integer>> verticalOrder(TreeNode root) {
      List<List<Integer>>  res = new ArrayList<List<Integer>>();
      if(root == null) return res;
      Map<Integer, ArrayList<Integer>> map = new HashMap<>();
      Queue<TreeNode> q = new LinkedList<>();
      Queue<Integer> cols = new LinkedList<>();
      int min=0, max = 0;
      
      cols.offer(0);
      q.offer(root);
      while(!q.isEmpty()) {
          TreeNode node  = q.poll();
          int col = cols.poll();
          // if map[col] exist, add node.val to map[col], otherwise, create map
          if(map.get(col) == null) map.put(col, new ArrayList<Integer>());
          map.get(col).add(node.val);
          
          if(node.left != null) {
              q.offer(node.left);
              cols.offer(col-1);
              min = Math.min(min, col-1);
          }
          if(node.right != null) {
              q.offer(node.right);
              cols.offer(col+1);
              max = Math.max(max, col+1);
          }
      }
      for(int i=min; i<=max; i++) {
          res.add(map.get(i));
      }
      return res;
  }
}