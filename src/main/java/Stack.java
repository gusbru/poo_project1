import java.lang.reflect.Method;

public class Stack<T> implements Cloneable
{
    private Object[] stack;
    private int size, maxHeight, end;

    // constructor
    public Stack(int maxHeight) throws Exception
    {
        if (maxHeight <= 0)
            throw new Exception("Size must be positive");

        this.maxHeight = maxHeight;
        this.stack = new Object[maxHeight];
        this.end = -1;
        this.size = 0;

    }

    // copy constructor
    public Stack(Stack<T> model) throws Exception
    {
        if (model == null)
            throw new Exception("null object");

        // not possible.... trying to access a private attribute
        this.maxHeight = model.maxHeight;
        this.end = model.end;
        this.size = model.size;
        this.stack = new Object[maxHeight];

        for (int i = 0; i < model.getSize(); i++)
            this.stack[i] = model.getItem(i);

    }

    /**
     * Add an item to the stack
     * <p>
     * If there is space in the stack, this method
     * add an object to the stack.
     *
     * @param item an object to be added to the stack
     */
    public void addItem(T item) throws Exception
    {
        if (item == null)
            throw new Exception("Item cannot be null");

        if (this.size == this.maxHeight)
            throw new Exception("Stack Overflow!");

        this.end++;
        if (item instanceof Cloneable)
        {
            this.stack[end] = myClone(item);
        } else
        {
            this.stack[end] = item;
        }

        this.size++;
    }

    /**
     * Retrieves the first item (on top) from a stack.
     * <p>
     * If the stack is not empty,
     *
     * @param item an object to be added to the stack
     */
    public T getItem() throws Exception
    {
        if (this.size == 0)
            throw new Exception("Stack is empty. Cannot retrieve any item");

        if (this.stack[this.end] instanceof Cloneable)
            return myClone((T) this.stack[this.end]);
        else
            return (T) this.stack[this.end];

    }

    public T getItem(int index) throws Exception
    {
        if (this.size == 0)
            throw new Exception("Stack is empty. Cannot retrieve any item");

        if (index < 0 || index > this.size)
            throw new Exception("Index outside range");

        if (this.stack[index] instanceof Cloneable)
            return myClone((T) this.stack[index]);
        else
            return (T) this.stack[index];
    }

    public void removeItem() throws Exception
    {
        if (this.size == 0)
            throw new Exception("Stack is empty. Cannot remove any element.");

        this.stack[this.end] = null;
        this.end--;
        this.size--;
    }

    public int getMaxHight()
    {
        return this.maxHeight;
    }

    public int getSize()
    {
        return this.size;
    }

    public int getEnd()
    {
        return this.end;
    }

    public boolean isFull()
    {
        return this.size == this.maxHeight;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }


    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass() != obj.getClass())
            return false;


        Stack<T> s = (Stack<T>) obj;

        if (this.size != s.size)
            return false;

        int position = this.end;
        int quantity = this.size;

        while (quantity > 0)
        {
            if (!this.stack[position].equals(s.stack[position]))
                return false;

            position--;
            quantity--;
        }

        return true;
    }

    public String toString()
    {
        String str = "";

        for (int i = 0; i < this.size; i++)
        {
            str += this.stack[i] + " ";
        }

        return str;
    }

    public int hashCode()
    {
        int ret = 494;

        // for each attribute (size, maxHeight, end)
        ret = ret * 31 + Integer.valueOf(this.size).hashCode();
        ret = ret * 31 + Integer.valueOf(this.maxHeight).hashCode();
        ret = ret * 31 + Integer.valueOf(this.end).hashCode();


        // loop over all elements in the stack
        for (int i = 0; i < this.end; i++)
            ret = ret * 31 + this.stack[i].hashCode();

        return ret;
    }

    public Object clone()
    {
        Stack<T> ret = null;

        try
        {
            ret = new Stack<T>(this);
        } catch (Exception error)
        {
        }

        return ret;
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
        {
        }

        return ret;
    }

}
