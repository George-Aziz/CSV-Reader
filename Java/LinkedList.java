/*****************************************************************************
*  Author: George Aziz 
*  Purpose: A Class responsible for Linked List
******************************************************************************/

import java.util.*;

public class LinkedList implements Iterable
{
    //Private Classfields
    private ListNode head;
    private ListNode tail;
    
    LinkedList()
    {
        head = null;
        tail = null;
    }

    public void insertFirst(Object newValue)
    {
        ListNode newNd = new ListNode(newValue);

        if (isEmpty())
        {
            tail = newNd;
        }
        else
        {
            head.setPrev(newNd);        
        }
            newNd.setNext(head); //Moves the existing head to the right one spot to allow for the new head
            head = newNd; //New Node becomes the new head
    }

    public void insertLast(Object newValue)
    {
        ListNode newNd = new ListNode(newValue);

        if (isEmpty())
        {
            head = newNd;
        }
        else
        {
            tail.setNext(newNd); //New node goes to the right of the tail
            newNd.setPrev(tail);                      
        }
            tail = newNd; //New Node that has entered becomes the new tail
    }

    public boolean isEmpty()
    {
	    return head == null;
    }

    public Object peekFirst()
    {
        Object nodeValue;
        if (isEmpty())
        {
            throw new IllegalArgumentException("List is empty");
        }
        else
        {
            nodeValue = head.getValue();
        }
        return nodeValue;
    }

    public Object peekLast()
    {
        Object nodeValue;

        if (isEmpty())
        {
            throw new IllegalArgumentException("List is empty");
        }
        else
        {
            nodeValue = tail.getValue();
            //System.out.println(nodeValue);
        }
        return nodeValue;
    }

    public Object removeFirst()
    {
        Object nodeValue;

        if (isEmpty())
        {
            throw new IllegalArgumentException("List is empty");
        }
        else if (head == tail)
        {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }
        else
        {
            nodeValue = head.getValue();
            head.getNext().setPrev(null);
            head = head.getNext();
        }
        return nodeValue;
    }

    public Object removeLast()
    {
        Object nodeValue;

        if (isEmpty())
        {
            throw new IllegalArgumentException("List is Empty");
        }
        else if (head == tail)
        {
            nodeValue = tail.getValue();
            head = null;
            tail = null;
        }
        else
        {
            nodeValue = tail.getValue();
            tail.getPrev().setNext(null);
            tail = tail.getPrev();
        }
        return nodeValue;
    }

    public Object removeNode(Object value)
    {
        Object remNode = null; //By default it is null

        if(!isEmpty())
        {
            if(head.getValue() == value)
            {
                remNode = removeFirst(); // if the value is the head then the first node is removed
            }
            else if(tail.getValue() == value)
            {
                remNode = removeLast(); // if the value is at the tail, the last node is removed
            }
            else
            { 
                ListNode node, nextNode;
                node = head;
                if (node == null)
                {
                    throw new IllegalArgumentException("Node not found!");
                }
                else 
                {
                    while(node != null) 
                    {
                        nextNode = node.getNext();

                        if (node.getValue() == value)
                        {
                            remNode = node.getValue();
                            node.getNext().setPrev(node.getPrev()); // Makes the deleted node cut off from the references
                            node.getPrev().setNext(node.getNext());
                        }
                        node = nextNode;
                    }
                }
            }  
        }
        return remNode;
    }

    public Iterator iterator() //Return a new Iterator of internal type LinkedListIterator
    {
        return new LinkedListIterator(this); //Hooks the iterator to this DSALinkedList object
    }
    
    /*********************************************************
    * PRIVATE INNER CLASS: LinkedListIterator
    * Class that helps iterate over the whole Linked List
    * *******************************************************/
    private class LinkedListIterator implements Iterator
    {
        private ListNode iterNext;
        public LinkedListIterator(LinkedList theList)
        {
            iterNext = theList.head;
        }

        public boolean hasNext() 
        {
            return (iterNext != null);
        }
        public Object next() 
        {
            Object value;
            if (iterNext == null)
            {
                value = null;
            }
            else 
            {
                value = iterNext.getValue(); //Get the value in the node
                iterNext = iterNext.getNext(); //Ready for subsequent calls to next()
            }
            return value;
        }

        public void remove(Object value)
        {
            throw new UnsupportedOperationException("Not Supported");
        }
    }
    
    /*****************************************************************************************************
    * PRIVATE INNER CLASS: ListNode
    * Class that contains all data for a list node including its value, the next and prev references
    * ***************************************************************************************************/
    private class ListNode 
    {
        //Class Fields
        public Object value;
        public ListNode next;
        public ListNode prev;

        ListNode(Object inValue)
        {
            value = inValue;
            next = null;
            prev = null;
        }

        //ACCESSOR
        public Object getValue()
        {
            return value;
        }

        public ListNode getNext()
        {
            return next;
        }

        public ListNode getPrev()
        {
            return prev;
        }
        //MUTATORS
        public void setValue(Object inValue)
        {
            value = inValue;
        }

        public void setNext (ListNode newNext)
        {
            next = newNext;
        }

        public void setPrev (ListNode newPrev)
        {
            prev = newPrev;
        }
    }
}