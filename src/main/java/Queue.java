
public class Queue<T> implements Cloneable 
{
  private Object[] queue;
  private int size, maxLength, begin, end;

  // constructor
  public Queue(int maxLength) throws Exception 
  {
    if (maxLength <= 0)
      throw new Exception("Invalid queue size");
    
    this.maxLength = maxLength;
    this.size      =  0;
    this.begin     =  0;
    this.end       = -1;
    this.queue     = new Object[maxLength];
  }

  // copy-constructor
  public Queue(Queue<T> model) throws Exception 
  {
	  if (model == null)
		  throw new Exception("null object");

	  this.maxLength = model.getMaxLength();
	  this.size      = model.getSize();
	  this.begin     = model.getBegin();
	  this.end       = model.getEnd();
	  
    
		  
  }

  public void addItem(T item) throws Exception {
    if (item == null)
      throw new Exception("Must provide one element");
    
    if (this.size == this.maxLength)
      throw new Exception("Queue is full");

    this.size++;
    this.end = (this.end + 1) % this.maxLength ;
    this.queue[this.end] = item;
  }

  public T getItem() throws Exception {
    if (this.size == 0)
      throw new Exception("Queue is empty");

    return (T)this.queue[this.begin];
  }

  public void removeItem() throws Exception {
    if (this.size == 0)
      throw new Exception("Queue is empty, cannot remove an item");
    
    this.queue[this.begin] = null;
    this.begin = (this.begin + 1) % this.maxLength;
    this.size--;
  }

  public boolean isFull() {
    return this.size == this.maxLength;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public int getSize() {
    return this.size;
  }

  public int getBegin()
  {
    return this.begin;
  }

  public int getEnd()
  {
    return this.end;
  }

  public int getMaxLength()
  {
    return this.maxLength;
  }

  public boolean equals(Object obj) {

    // point to the same memory address
    if (this == obj)
      return true;

    // one object is null
    if (obj == null)
      return false;

    if (this.getClass() != obj.getClass())
      return false;

    
    Queue<T> q = (Queue<T>)obj;

    if (this.size != q.size)
      return false;

    int positionThis = this.begin;
    int positionQ    = q.begin;
    int quantity     = this.size;

    while (quantity > 0) {
      if (!this.queue[positionThis].equals(q.queue[positionQ]))
        return false;

      // next element;
      positionThis = (positionThis + 1) % this.maxLength;
      positionQ = (positionQ + 1) % q.maxLength;
      
      quantity--;
    }

    return true;

  }

  public String toString() {
    String str = "";
    int position;

    for (int i = 0; i < this.size; i++) {
      position = (this.begin + i) % this.maxLength;
      str += this.queue[position] + " ";
    }

    return str;
  }



}
