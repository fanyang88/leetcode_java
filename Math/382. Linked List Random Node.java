/*
Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
 我们读到了第一个数据，这次我们不能直接返回该数据，因为数据流没有结束。我们继续读取第二个数据，发现数据流结束了。
 因此我们只要保证以相同的概率返回第一个或者第二个数据就可以满足题目要求。因此我们生成一个0到1的随机数R,
 如果R小于0.5我们就返回第一个数据，如果R大于0.5，返回第二个数据。

接着我们继续分析有三个数据的数据流的情况。为了方便，我们按顺序给流中的数据命名为1、2、3。
我们陆续收到了数据1、2和前面的例子一样，我们只能保存一个数据，所以必须淘汰1和2中的一个。应该如何淘汰呢？
不妨和上面例子一样，我们按照二分之一的概率淘汰一个，例如我们淘汰了2。继续读取流中的数据3，发现数据流结束了，
我们知道在长度为3的数据流中，如果返回数据3的概率为1/3,那么才有可能保证选择的正确性。
也就是说，目前我们手里有1,3两个数据，我们通过一次随机选择，
以1/3的概率留下数据3，以2/3的概率留下数据1.那么数据1被最终留下的概率是多少呢？

数据1被留下：（1/2）*(2/3) = 1/3
数据2被留下概率：（1/2）*(2/3) = 1/3
数据3被留下概率：1/3

so the algo is if we read to ith number, we generate a rand between 0~i, if it is ==i replace the res, otherwise, keep res as it is
 */
class Solution {
  ListNode head;
  /** @param head The linked list's head.
      Note that the head is guaranteed to be not null, so it contains at least one node. */
  public Solution(ListNode head) {
      this.head = head;      
  }
  
  /** Returns a random node's value. */
  public int getRandom() {
      int res = head.val;
      ListNode cur = head;
      int size=1;
      while(cur.next != null) {  // This is the key!!!
          cur =cur.next;
          if((int)(Math.random()*(size + 1)) == size)  res = cur.val; 
          size++;
      }
      return res;
  }
}

/**
* Your Solution object will be instantiated and called as such:
* Solution obj = new Solution(head);
* int param_1 = obj.getRandom();
*/
