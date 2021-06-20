/*****************************************************************************
*  Author: George Aziz
*  Purpose: A Class responsible for Queues ADT using LinkedList
******************************************************************************/


import java.util.*;
import java.io.*;

public class Queue implements Iterable
{
    //Class fields
    private LinkedList list;

    public Queue()
    {
	    list = new LinkedList();    
    }

    public boolean isEmpty()
    {
	    return (list.isEmpty());
    }
    
    public void enqueue(Object value)
    {
        list.insertLast(value);
    }

    public Object dequeue()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("Queue is Empty");
        }

        Object frontVal;
        frontVal = list.peekFirst();
        list.removeFirst();
        return frontVal;
    }

    public Iterator iterator()
    {
        return list.iterator();
    }
}
