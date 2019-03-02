/*
Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.

Your implementation should support following operations:

MyCircularQueue(k): Constructor, set the size of the queue to be k.
Front: Get the front item from the queue. If the queue is empty, return -1.
Rear: Get the last item from the queue. If the queue is empty, return -1.
enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
isEmpty(): Checks whether the circular queue is empty or not.
isFull(): Checks whether the circular queue is full or not.
 

Example:

MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
circularQueue.enQueue(1);  // return true
circularQueue.enQueue(2);  // return true
circularQueue.enQueue(3);  // return true
circularQueue.enQueue(4);  // return false, the queue is full
circularQueue.Rear();  // return 3
circularQueue.isFull();  // return true
circularQueue.deQueue();  // return true
circularQueue.enQueue(4);  // return true
circularQueue.Rear();  // return 4
 
Note:

All values will be in the range of [0, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in Queue library.
*/

/*
    create a queue, we use front to denote the head of the queue and tail to denote the tail fo the queue
    to enqueue, if the queue is not full
                we add the new number at the rear of the queue 
                rear ++, nums[rear] = val len++
    to dequeue, if the queue is not empty
                we remove the number at the front of the queue 
                front ++, len--;
*/

class MyCircularQueue {
  int[] queue;
  int len=0, front=0, rear=-1;  // This is the key!!!
  /** Initialize your data structure here. Set the size of the queue to be k. */
  public MyCircularQueue(int k) {
      queue = new int[k];
  }
  
  /** Insert an element into the circular queue. Return true if the operation is successful. */
  public boolean enQueue(int value) {
      if(!isFull()) {
          rear = (rear + 1) % queue.length;
          queue[rear] = value;
          len++;
          return true;
      }
      return false;
  }
  
  /** Delete an element from the circular queue. Return true if the operation is successful. */
  public boolean deQueue() {
      if(!isEmpty()) {
          front = (front + 1) % queue.length;
          len--;
          return true;
      }
      return false;
  }
  
  /** Get the front item from the queue. */
  public int Front() {
      return isEmpty() ? -1 : queue[front];
  }
  
  /** Get the last item from the queue. */
  public int Rear() {
      return isEmpty() ? -1 : queue[rear];
  }
  
  /** Checks whether the circular queue is empty or not. */
  public boolean isEmpty() {
      return len == 0;
  }
  
  /** Checks whether the circular queue is full or not. */
  public boolean isFull() {
      return len == queue.length;
  }
}

/**
* Your MyCircularQueue object will be instantiated and called as such:
* MyCircularQueue obj = new MyCircularQueue(k);
* boolean param_1 = obj.enQueue(value);
* boolean param_2 = obj.deQueue();
* int param_3 = obj.Front();
* int param_4 = obj.Rear();
* boolean param_5 = obj.isEmpty();
* boolean param_6 = obj.isFull();
*/
