/*
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
        
        [3,2,1,6,0,5]
        i=0 cur= TreeNode(3)  st= [(3)]
        i=1 cur= TreeNode(2) since st not empty and st.peek> cur st.peek().right = cur   3->2  st=[(3),(2)]
        i=2 cur= TreeNode(1) since st not empty and st.peek> cur st.peek().right = cur   3->2->1  st=[(3),(2), (1)]
        i=3 cur= TreeNode(6) since st not empty and st.peek<  cur 
                6->left=>(1) pop 1 6->left=>(2) pop 2, 6->left=>(3) pop (3) st= [] push (6) st= [6]
        i=4 cur= TreeNode(0) since st not empty and st.peek> cur st.peek().right = cur   6->0  st=[(6),(0)]
        i=5 cur= TreeNode(5)  since st not empty and st.peek<  cur 
                5->left=>0 pop 0 since st is not empty, and 6>5 6->right=5
        answer is 6
     
    so the key is for currrent treenode, if stack has value < current node, current node.left = value pop out
        if there is still value in stack and value > current node, stack node->right = currrent node
    then we push current node into stack
 */
class Solution {
  public TreeNode constructMaximumBinaryTree(int[] nums) {
      TreeNode res = null;
      Stack<TreeNode> st = new Stack<>();
      for(int num: nums) {
          TreeNode cur = new TreeNode(num);
          while(!st.isEmpty() && st.peek().val < cur.val) {
              TreeNode popped = st.pop();
              cur.left = popped;
          }
          if(!st.isEmpty() && st.peek().val > cur.val) {
              st.peek().right = cur;
          }
          st.push(cur);
      }
      return st.firstElement();
  }
}

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length-1);
    }
    TreeNode build(int[] nums, int s, int e) {
        if(s > e) return null;
        int index= s, max= nums[index];
        for(int i=s+1; i<=e; i++) {
            if(max < nums[i]) {
                max = nums[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = build(nums, s, index-1);
        root.right = build(nums, index+1, e);
        return root;
    }
}
