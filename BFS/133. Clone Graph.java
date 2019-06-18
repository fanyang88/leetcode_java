/**
 * 
 * Given the head of a graph, return a deep copy (clone) of the graph. Each node in the graph contains a label (int) and a list (List[UndirectedGraphNode]) of its neighbors. There is an edge between the given node and each of the nodes in its neighbors.


OJ's undirected graph serialization (so you can understand error output):
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 

As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 

Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
Note: The information about the tree serialization is only meant so that you can understand error output if you get a wrong answer. You don't need to understand the serialization to solve the problem.
 */

 /**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
    BFS
    use a hashmap to store the label as key and value is the created cloned node
    everytime, we check each neighor, if map doesn't have this neighbor, 
                                    we need to create a cloned neighbor node first, and add to queue
                                    then add the neighbor node into the cloned parent node
                                    else direct add neighbor node into the cloned parent node
 */
class Solution {
    public Node cloneGraph(Node node) {
        Node copy = new Node(node.val, new ArrayList<Node>());
        Map<Integer, Node> map = new HashMap<>();
        map.put(node.val, copy);
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while(!q.isEmpty()) {
            Node cur = q.poll();
            for(Node next: cur.neighbors) {
                if(!map.containsKey(next.val)) {
                    map.put(next.val, new Node(next.val, new ArrayList<Node>()));
                    q.add(next);
                } 
                map.get(cur.val).neighbors.add(map.get(next.val));
            }
        }
        return copy;
    }    
}