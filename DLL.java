
public class DLL<T> 
{
	private DLLNode<T> head;
	private DLLNode<T> tail;
	
	public DLL()
	{
		head = null;
		tail = null;
	}

	public boolean isEmpty()
	{
		return (head == null); // assumes if head is null, tail is also null
	}
	
	public DLLNode<T> getHead()
	{
		return head;
	}
	
	public T removeFromEnd()
	{
		T thingToRemove = tail.getData();
		
		tail = tail.prev;
		if (tail != null)
			tail.next = null;
		else
			head = null;		
		
		return thingToRemove;
	}
	
	public T getEnd()
	{
		return tail.getData();
	}
	
	
	public void addToBeginning(T thingToAdd)
	{
		DLLNode<T> addNode = new DLLNode<T>(thingToAdd);
		if (isEmpty())
		{
			head = addNode;
			tail = addNode;
		}
		else
		{
			addNode.next = head;
			head.prev = addNode;
			head = addNode;
		}
	}
	
	public void addToEnd(T thingToAdd)
	{
		DLLNode<T> addNode = new DLLNode<T>(thingToAdd);
		if (isEmpty())
		{
			head = addNode;
			tail = addNode;
		}
		else
		{
			addNode.prev = tail;
			tail.next = addNode;
			tail = addNode;
		}
	}
	
	// delete first occurrance
	public void delete(T thingToDelete)
	{
		if (!isEmpty())
		{
			// check if the list is of size 1 AND it is the one to delete
			if (head == tail)
			{
				if (head.getData().equals(thingToDelete))
				{
					head = null;
					tail = null;
				}
				
			}
			else
			{
				// list is of size more than 1
				DLLNode<T> temp = head;
				
				while (temp != null && !temp.getData().equals(thingToDelete))
				{
					temp = temp.next;
				}
				
				if (temp != null)
				{
					// means we found it
					if (temp == head)
					{
						head = head.next;
						head.prev = null;
					}
					else
					{
						if (temp == tail)
						{
							tail = tail.prev;
							tail.next = null;
							
						}
						else
						{
							// we found it somewhere in the middle
							temp.prev.next = temp.next;
							temp.next.prev = temp.prev;
						}
					}
				}
			}
			
		}
	}
	
	public boolean search(T thingToLookFor)
	{
		DLLNode<T> temp = head;
		boolean isFound = false;
		
		if (isEmpty())
		{
			return false;
		}
		
		while (temp != null && !temp.getData().equals(thingToLookFor))
		{
			temp = temp.next;
		}

		//return (temp != null); // would work, but less readable
		
		if (temp == null) // means we went off the end of the list
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public String toString()
	{
		String out = "";
		
		DLLNode<T> temp = head;
		while (temp != null)
		{
			out = out + temp.getData().toString() + "\n";
			temp = temp.next;
		}
		
		return out;
	}
	

	public String toStringBackwards()
	{
		String out = "";
		
		DLLNode<T> temp = tail;
		while (temp != null)
		{
			out = out + temp.getData().toString() + "\n";
			temp = temp.prev;
		}
		
		return out;
	}

	
}