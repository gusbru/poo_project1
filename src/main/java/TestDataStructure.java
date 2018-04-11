public class TestDataStructure {
  
  public void testAll() throws Exception {
    System.out.println("Starting Test...");
      
      try {
        int n = 5;
        Queue<String> queue = new Queue<String>(n);
        Queue<String> queue2 = new Queue<String>(n);
        Stack<String> stack = new Stack<String>(n);
        Stack<String> stack2 = new Stack<String>(n);
  
        // Queue
        System.out.println("************************************************");
        System.out.println("***                  QUEUE                   ***");
        System.out.println("************************************************");
        System.out.println("The queue is full? " + queue.isFull());
        System.out.println("The queue is empty? " + queue.isEmpty());
        System.out.println("Elements: " + queue.toString());
  
        System.out.println("Filling the queue...");
        for (int i = 0; i < n; i++) {
          queue.addItem("Test" + i);
          queue2.addItem("Test" + i);
        }
  
        System.out.println("Is the queue and queue2 equals? " + queue.equals(queue2));
  
        System.out.println("The queue is full? " + queue.isFull());
        System.out.println("Elements: " + queue.toString());
  
        System.out.println("Execute the first element: " + queue.getItem());
        queue.removeItem();
  
        System.out.println("Is the queue and queue2 equals? " + queue.equals(queue2));
  
        System.out.println("The queue is full? " + queue.isFull());
        System.out.println("Elements: " + queue.toString());
  
        System.out.println("Add one element");
        queue.addItem("Test" + n);
  
        System.out.println("The queue is full? " + queue.isFull());
        System.out.println("Elements: " + queue.toString());
  
        
        // Stack
        System.out.println("************************************************");
        System.out.println("***                  STACK                   ***");
        System.out.println("************************************************");
        System.out.println("The stack is full? " + stack.isFull());
        System.out.println("Elements: " + stack.toString());
  
        System.out.println("Filling the stack...");
        for (int i = 0; i < n; i++) {
          stack.addItem("testStack" + i);
          stack2.addItem("testStack" + i);
        }
  
        System.out.println("The stack is full? " + stack.isFull());
        System.out.println("The stack is empty? " + stack.isEmpty());
        System.out.println("Is the stack and stack2 equals? " + stack.equals(stack2));
        System.out.println("Elements: " + stack.toString());
  
        System.out.println("HashCode: " + stack.hashCode());
  
        System.out.println("Execute the first element: " + stack.getItem());
        stack.removeItem();
  
        System.out.println("The stack is full? " + stack.isFull());
        System.out.println("Is the stack and stack2 equals? " + stack.equals(stack2));
        System.out.println("Elements: " + stack.toString());
  
        System.out.println("HashCode: " + stack.hashCode());
  
  
  
      } catch (Exception error) {
        System.err.println(error);
      }
  }
}