/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:

The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.

*/

/*
    we can use segment tree, it is a balanced binary tree. O(logn) height for n nodes
    each leaf represent a element in the array, each non-leaf node covers the union of its children range.
    e.g: nums= [2,1, 5, 3, 4]
                   15
                 /    \
                8       7
             / .  \ . / . \
            3     5 . 3    4
          / . \
         2     1
         
    Operations:
    1. build(start, end, nums) -> O(n)
    2. update(index, val) -> O(logn)
    3. rangeQuery(start, end) -> O(logn+ k) k is # of nodes visited

*/

class NumArray {
  class Node {
      int start, end, sum;
      Node left, right;
      Node(int start, int end, int sum, Node left, Node right){
          this.start = start;
          this.end = end;
          this.sum = sum;
          this.left= left;
          this.right= right;
      }
  }
  Node root = null;
  public NumArray(int[] nums) {
      root = build(0, nums.length-1, nums);
  }
  
  public Node build(int start, int end, int[] nums) {
      if(start>end) return null;
      if(start == end) {
          return new Node(start, end, nums[start], null, null);
      }
      int mid = (start + end)/2;
      Node left = build(start, mid, nums);
      Node right = build(mid+1, end, nums);
      return new Node(start, end, left.sum + right.sum, left, right);
  }
  
  public void update(int i, int val) {
      _update(root, i, val);
  }
  
  public void _update(Node node, int i, int val) {
      if(node.start == node.end && node.end == i) {
          node.sum = val;
          return;
      }
      int mid = (node.start + node.end) /2;
      if(i <= mid) { // node is on left subtree
          _update(node.left, i, val);
      } else {
          _update(node.right, i, val);
      }
      node.sum = node.left.sum + node.right.sum;
  }
  
  public int sumRange(int i, int j) {
      return query(root, i, j);
  }
  
  public int query(Node node, int i , int j) {
      if(node.start == i && node.end == j) return node.sum;
      int mid = (node.start + node.end) /2;
        if(j <= mid) { //e.g: j=4 mid=5 should query left subtree
          return query(node.left, i, j);
      } else if(i>=mid+1) { // e.g: i=5 mid=4 on right subtree
         return query(node.right, i, j);
      }
      else {  // part on left, part on right
          return query(node.left, i, mid) + query(node.right, mid+1, j);
      }
  }
}

/**
* Your NumArray object will be instantiated and called as such:
* NumArray obj = new NumArray(nums);
* obj.update(i,val);
* int param_2 = obj.sumRange(i,j);
*/
