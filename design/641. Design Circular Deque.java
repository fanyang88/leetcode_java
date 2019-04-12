/*
Design your implementation of the circular double-ended queue (deque).

Your implementation should support following operations:

MyCircularDeque(k): Constructor, set the size of the deque to be k.
insertFront(): Adds an item at the front of Deque. Return true if the operation is successful.
insertLast(): Adds an item at the rear of Deque. Return true if the operation is successful.
deleteFront(): Deletes an item from the front of Deque. Return true if the operation is successful.
deleteLast(): Deletes an item from the rear of Deque. Return true if the operation is successful.
getFront(): Gets the front item from the Deque. If the deque is empty, return -1.
getRear(): Gets the last item from Deque. If the deque is empty, return -1.
isEmpty(): Checks whether Deque is empty or not. 
isFull(): Checks whether Deque is full or not.
 

Example:

MyCircularDeque circularDeque = new MycircularDeque(3); // set the size to be 3
circularDeque.insertLast(1);			// return true
circularDeque.insertLast(2);			// return true
circularDeque.insertFront(3);			// return true
circularDeque.insertFront(4);			// return false, the queue is full
circularDeque.getRear();  			// return 2
circularDeque.isFull();				// return true
circularDeque.deleteLast();			// return true
circularDeque.insertFront(4);			// return true
circularDeque.getFront();			// return 4
 

Note:

All values will be in the range of [0, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in Deque library.
*/

/*
MycircularDeque(3); // set the size to be 3
circularDeque.insertLast(1);			// [,,1]
circularDeque.insertLast(2);			// [,1,2]
circularDeque.insertFront(3);			// [3,1,2]
circularDeque.insertFront(4);			// return false, the queue is full
circularDeque.getRear();  			// return 2
circularDeque.isFull();				// return true
circularDeque.deleteLast();			// [3,1,]
circularDeque.insertFront(4);			// [4,3,1]
circularDeque.getFront();			// return 4


The key is to assume the size = 4 
if index = 1, to get the pre index of head = (1-1+4) %4 = 0 
             to get the next index of head = (1+1) % 4 = 2

if index = 3, to get the pre index of head = (3-1+4) %4 = 2 
             to get the next index of head = (3+1) % 4 = 0

e.g: head= 3 tail=0
insertToFront: [,,,3] head should be 2, since next time it should insert to 2, head = (head-1+4)%4=2
insertToLast: [1,,3] tail next should be 1, tail=1
insertToLast:[1,2,,3] tail next should be 2, tail=2
insertToFront:[1,2,4,3] front next should be 1, head = (2-1+4)%4=1
deleteFront: since front is 4, move head back to 3, head= (head+1)%4=2
deleteLast: since tail =2, move tail back to 1, tail = (2-1+4)%4=1

in summary:
head, tail always point to the next available position for insert, 
for delete at tail we need to move the tail move backford, (tail-1+size)%size
for delete at front we need to move the front move forward, (head+1) $ size
for insert at tail we need to move the tail move forward, (tail+1)%size
for insert at front we need to move the front move backford, (head-1+size) % size

initial, head = k-1, tail=0
                
*/

class MyCircularDeque {
  int head, tail, count, size;
  int[] q;
  /** Initialize your data structure here. Set the size of the deque to be k. */
  public MyCircularDeque(int k) {
      this.q = new int[k];
      this.head = k-1;
      this.tail = 0;
      this.count=0;
      this.size = k;
  }
  
  /** Adds an item at the front of Deque. Return true if the operation is successful. */
  public boolean insertFront(int value) {
      if(isFull())  return false;
      q[head] = value;
      head = (head-1 + size) % size;
      count++;
      return true;
  }
  
  /** Adds an item at the rear of Deque. Return true if the operation is successful. */
  public boolean insertLast(int value) {
      if(isFull())  return false;
      q[tail] = value;
      tail = (tail+1) % size;
      count++;
      return true;
  }
  
  /** Deletes an item from the front of Deque. Return true if the operation is successful. */
  public boolean deleteFront() {
      if(isEmpty()) return false;
      head = (head+1) % size;
      count--;
      return true;
  }
  
  /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
  public boolean deleteLast() {
      if(isEmpty()) return false;
      tail = (tail-1 +size) % size;
      count--;
      return true;
  }
  
  /** Get the front item from the deque. */
  public int getFront() {
      return isEmpty() ? -1 : q[(head+1) % size];
  }
  
  /** Get the last item from the deque. */
  public int getRear() {
      return isEmpty() ? -1 : q[(tail-1+size)%size];
  }
  
  /** Checks whether the circular deque is empty or not. */
  public boolean isEmpty() {
      return count==0? true : false;
  }
  
  /** Checks whether the circular deque is full or not. */
  public boolean isFull() {
      return count==size? true : false;
  }
}

/**
* Your MyCircularDeque object will be instantiated and called as such:
* MyCircularDeque obj = new MyCircularDeque(k);
* boolean param_1 = obj.insertFront(value);
* boolean param_2 = obj.insertLast(value);
* boolean param_3 = obj.deleteFront();
* boolean param_4 = obj.deleteLast();
* int param_5 = obj.getFront();
* int param_6 = obj.getRear();
* boolean param_7 = obj.isEmpty();
* boolean param_8 = obj.isFull();
*/