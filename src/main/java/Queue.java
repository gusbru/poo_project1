import java.lang.reflect.Method;

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
        this.size = 0;
        this.begin = 0;
        this.end = -1;
        this.queue = new Object[maxLength];
    }

    // copy-constructor
    public Queue(Queue<T> model) throws Exception
    {
        if (model == null)
            throw new Exception("null object");

        this.maxLength = model.getMaxLength();
        this.size = model.getSize();
        this.begin = model.getBegin();
        this.end = model.getEnd();
        this.queue = new Object[model.queue.length];

        for (int i = 0; i < model.queue.length; i++)
            this.queue[i] = model.queue[i];
    }

    public void addItem(T item) throws Exception
    {
        if (item == null)
            throw new Exception("Must provide one element");

        if (this.size == this.maxLength)
            throw new Exception("Queue is full");

        this.size++;
        this.end = (this.end + 1) % this.maxLength;
        this.queue[this.end] = item;
    }

    public T getItem() throws Exception
    {
        if (this.size == 0)
            throw new Exception("Queue is empty");

        return (T) this.queue[this.begin];
    }

    public void removeItem() throws Exception
    {
        if (this.size == 0)
            throw new Exception("Queue is empty, cannot remove an item");

        this.queue[this.begin] = null;
        this.begin = (this.begin + 1) % this.maxLength;
        this.size--;
    }

    public boolean isFull()
    {
        return this.size == this.maxLength;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }

    public int getSize()
    {
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

    public boolean equals(Object obj)
    {

        // point to the same memory address
        if (this == obj)
            return true;

        // one object is null
        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;


        Queue<T> q = (Queue<T>) obj;

        if (this.size != q.size)
            return false;

        int positionThis = this.begin;
        int positionQ = q.begin;
        int quantity = this.size;

        while (quantity > 0)
        {
            if (!this.queue[positionThis].equals(q.queue[positionQ]))
                return false;

            // next element;
            positionThis = (positionThis + 1) % this.maxLength;
            positionQ = (positionQ + 1) % q.maxLength;

            quantity--;
        }

        return true;

    }

    public int hashCode()
    {
        int ret = 304;

        // for each attribute
        ret = 3*ret + Integer.valueOf(this.begin).hashCode();
        ret = 3*ret + Integer.valueOf(this.end).hashCode();
        ret = 3*ret + Integer.valueOf(this.maxLength).hashCode();
        ret = 3*ret + Integer.valueOf(this.size).hashCode();

        int atual = this.begin;
        int qtd   = this.size;

        while (qtd > 0)
        {
            ret = 3*ret + this.queue[atual].hashCode();
            qtd--;
            atual++;
            if (atual == this.queue.length)
                atual = 0;
        }

        return ret;
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder();
        int position;

        for (int i = 0; i < this.size; i++)
        {
            position = (this.begin + i) % this.maxLength;
            str.append(this.queue[position]).append(" ");
        }

        return str.toString();
    }

//    public Object clone()
//    {
//        Queue<T> ret = null;
//
//        try
//        {
//            ret = new Queue<T>(this);
//        } catch (Exception error){
//            System.err.println("Error " + error);
//        }
//
//        return ret;
//    }
    public Queue<T> clone() throws CloneNotSupportedException
    {
        Queue<T> cloneQueue = (Queue<T>) super.clone();
        cloneQueue.queue = this.queue.clone();
        return cloneQueue;
    }

    /**
     * Helper method to clone an object of a generic class X
     * <p>
     *
     * @param model is an object of a generic class X
     * @return an object identical to the one given as parameter
     */
    private T myClone(T model)
    {
        T ret = null;
        try
        {
            Class<?> xClass = model.getClass();
            Class<?>[] parameterTypes = null;
            Method method = xClass.getMethod("clone", parameterTypes);
            Object[] realParameter = null;
            ret = (T) method.invoke(model, realParameter);
        } catch (Exception error)
        {}

        return ret;
    }


}
