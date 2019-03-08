/*
If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:
The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

Example 1:
Input: [113, 215, 221]
Output: 12
Explanation: 
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.
Example 2:
Input: [113, 221]
Output: 4
Explanation: 
The tree that the list represents is: 
    3
     \
      1

The path sum is (3 + 1) = 4.
*/

/*
        How do we solve problem like this if we were given a normal tree? Yes, traverse it, keep a root to leaf running sum. 
 If we see a leaf node (node.left == null && node.right == null), we add the running sum to the final result.

We can form a tree using a HashMap. The key is first two digits which marks the position of a node in the tree. The value is value of that node. Thus, we can easily find a node's left and right children using math.

Formula: For node xy?, e.g: 113
its left child is (x+1)(y*2-1)? and right child is (x+1)(y*2)?

        Input: [113, 215, 221]
        map: [11, 3] [21, 5] [22, 1]
        start from root key=11
        left = (1+1)(2*1-1)  we can get the left node value from map
        right=(1+1)(2*1)    we can get the right node value from map
        
*/

class Solution {
  int res=0;
  public int pathSum(int[] nums) {
      Map<Integer, Integer> map = new HashMap<>();
      for(int n: nums) {
          int key = n /10;
          int val = n%10;
          map.put(key, val);
      }
      getSum(nums[0]/10, 0, map);
      return res;
  }
  
  public void getSum(int root, int curSum, Map<Integer, Integer> map) {
      if(map.get(root) == null) return;
      curSum += map.get(root);
      int depth = root/10;
      int pos = root % 10;
      int left = (depth+1)*10+(pos*2-1);
      int right = (depth+1)*10+(pos*2);
      if(map.get(left)==null && map.get(right)==null) {
          res += curSum;
          return;
      }
      getSum(left, curSum, map);
      getSum(right, curSum, map);
  }
}
