/*
Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

               4
            /     \
            2      5
          /  \
          1  3

    We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.
 <- <- <- <-
1->2->3->4-> 5
^            |
|-------------

Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};

iteratively

st=[4,2,1]
    since 1.left=null, pop 1 first = 1 first.right = last last=1  
    since 1 has no right, pop 2, cur=2 last =1 last.right=2 2.left=last last = 2 1->2
    
    in the end, we need to connect first and last
    
    in-order traverse
*/
class Solution {
  public Node treeToDoublyList(Node root) {
      if(root ==null)  return null;
      Stack<Node> st = new Stack<>();
      Node cur = root, first = null, last = null;
      while(cur != null || st.size() > 0) {
          while(cur != null) {
              st.push(cur);
              cur = cur.left;
          }
          cur = st.pop(); 
          if(first == null)  first = cur;
          if(last != null) {
              last.right = cur;
              cur.left = last;
          }
          last = cur;
          cur =cur.right;
      }
      first.left = last;
      last.right = first;
      return first;
  }
}


// recursive
class Solution {
    Node pre;
    public Node treeToDoublyList(Node root) {
        if(root == null) return null;
        Node dummy = new Node(-1, null, null); // dummy head
        pre  = dummy;
        dfs(root);
        // pre point to the tail, dummy.right is head, connect them
        pre.right = dummy.right;
        dummy.right.left = pre;
        return dummy.right;
    }
    
    void dfs(Node root) {
        if(root == null)  return;
        dfs(root.left);
        pre.right = root;
        root.left = pre;
        pre = root;
        dfs(root.right);
    }
}
