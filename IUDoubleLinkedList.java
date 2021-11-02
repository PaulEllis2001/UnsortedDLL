import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * A simple Indexed Unsorted Double Linked List implementing the IndexedUnsortedList Interface
 * @author Paul Ellis
 *
 * @param <T> Type of Object that the Double Linked List will contain
 */
public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {

	private Node<T> head,tail;
	private int size,modCount;
	
	public IUDoubleLinkedList()
	{
		head = tail = null;
		size = modCount = 0;
	}
	
	@Override
	public void addToFront(T element) {
		Node<T> newNode = new Node<T>(element);
		newNode.setNextNode(head);
		newNode.setPreviousNode(null);
		head = newNode;
		if(tail == null)
		{
			tail = head;
		}
		else
		{
			head.getNextNode().setPreviousNode(head);
		}
		size++;
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		Node<T> newNode = new Node<T>(element);
		if(tail == null)
		{
			head = tail = newNode;
		}
		else 
		{
			
			tail.setNextNode(newNode);
			newNode.setPreviousNode(tail);
			newNode.setNextNode(null);
			tail = newNode;
		}
		size++;
		modCount++;
	}

	@Override
	public void add(T element) {
		addToRear(element);
	}

	@Override
	public void addAfter(T element, T target) {
		Node<T> newNode = new Node<T>(element);
		Node<T> current = head;
		if(isEmpty()) 
		{
			throw new NoSuchElementException();
		}
		while((current!=null)&&(!current.getElement().equals(target)))
		{
			current = current.getNextNode();
		}
		if(current == null)
		{
			throw new NoSuchElementException();
		}
		if(size == 1)
		{
			head.setNextNode(newNode);
			newNode.setPreviousNode(head);
			newNode.setNextNode(null);
			tail = newNode;
		}
		else if(current == tail)
		{
			tail.setNextNode(newNode);
			newNode.setPreviousNode(tail);
			newNode.setNextNode(null);
			tail = newNode;
		}
		else
		{
			newNode.setNextNode(current.getNextNode());
			newNode.setPreviousNode(current);
			current.setNextNode(newNode);
		}
		
		size++;
		modCount++;
	}

	@Override
	public void add(int index, T element) {
		if(index <0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		Node<T> newNode = new Node<T>(element);
		Node<T> current = head;
		if(size == index && index == 0)
		{
			head = tail = newNode;
		}
		else if(index == size)
		{
			tail.setNextNode(newNode);
			newNode.setPreviousNode(tail);
			newNode.setNextNode(null);
			tail = newNode;
		}
		else if(index == 0)
		{
			newNode.setPreviousNode(null);
			newNode.setNextNode(head);
			head.setPreviousNode(newNode);
			head = newNode;
		}
		else
		{
			for(int i = 0; i<index-1;i++)
			{
				current = current.getNextNode();
			}
			newNode.setPreviousNode(current);
			newNode.setNextNode(current.getNextNode());
			current.setNextNode(newNode);
			newNode.getNextNode().setPreviousNode(newNode);
		}
		size++;
		modCount++;
	}

	@Override
	public T removeFirst() {
		T retVal = null;
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		if(size == 1)
		{
			retVal = head.getElement();
			head = tail = null;
		}
		else
		{
			retVal = head.getElement();
			head = head.getNextNode();
			head.setPreviousNode(null);
		}
		size--;
		modCount++;
		
		return retVal;
	}

	@Override
	public T removeLast() {
		T retVal = null;
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		if(size == 1)
		{
			retVal = tail.getElement();
			head = tail = null;
		}
		else
		{
			retVal = tail.getElement();
			tail = tail.getPreviousNode();
			tail.setNextNode(null);
		}
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public T remove(T element) {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		T retVal = null;
		Node<T> current = head;
		while(current != null && (!current.getElement().equals(element)))
		{
			current = current.getNextNode();
		}
		if(current == null)
		{
			throw new NoSuchElementException();
		}
		retVal = current.getElement();
		if(current == head)
		{
			retVal = removeFirst();
		}
		else if(current == tail)
		{
			retVal = removeLast();
		}
		else {
			retVal = current.getElement();
			current.getPreviousNode().setNextNode(current.getNextNode());
			current.getNextNode().setPreviousNode(current.getPreviousNode());
			size--;
			modCount++;
		}
		
		return retVal;
	}

	@Override
	public T remove(int index) {
		T retVal = null;
		Node<T> current = head;
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		if(index == 0)
		{
			retVal = head.getElement();
			if(head == tail) {
				head = tail = null;
			} 
			else {
				head = head.getNextNode();
				head.setPreviousNode(null);
			}
			
		}
		else if(index == size-1)
		{
			retVal = tail.getElement();
			tail = tail.getPreviousNode();
			tail.setNextNode(null);
		}
		else
		{
			for(int i = 0; i <= index-1;i++)
			{
				current = current.getNextNode();
			}
			retVal = current.getElement();
			current.getNextNode().setPreviousNode(current.getPreviousNode());
			current.getPreviousNode().setNextNode(current.getNextNode());
		}
		size--;
		modCount++;
		return retVal;
	}

	@Override
	public void set(int index, T element) {
		Node<T> current = head;
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		if(index == 0)
		{
			head.setElement(element);
		}
		else 
		{
			for(int i = 0; i <= index-1; i++)
			{
				current = current.getNextNode();
			}
			current.setElement(element);
		}
		modCount++;
	}

	@Override
	public T get(int index) {
		if(index<0||index>=size||isEmpty())
		{
			throw new IndexOutOfBoundsException();
		}
		Node<T> current=head;
		for(int i =0;i<index;i++)
		{
			current=current.getNextNode();
		}
		
		return current.getElement();
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		int currentIndex = 0;
		Node<T> current = head;
		while((current!=null)&& (!current.getElement().equals(element)))
		{
			current=current.getNextNode();
			currentIndex++;
		}
		
		if(current!=null)
		{
			index=currentIndex;
		}
		
		return index;
	}

	@Override
	public T first() {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		return head.getElement();
	}

	@Override
	public T last() {
		if(isEmpty())
		{
			throw new NoSuchElementException();
		}
		return tail.getElement();
	}

	@Override
	public boolean contains(T target) {
		Node<T> current=head;
		boolean containsElement = false;
		while(current!= null && !containsElement)
		{
			if(current.getElement().equals(target))
			{
				containsElement=true;
			}
			current=current.getNextNode();
		}
		return containsElement;
	}

	@Override
	public boolean isEmpty() {
		return (head == null);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("[");
		Node<T> current = head;
		while(current!=null)
		{
			str.append(current.getElement());
			str.append(", ");
			current = current.getNextNode();
		}
		if(!isEmpty())
		{
			str.delete(str.length()-2, str.length());
		}
		str.append("]");
		return str.toString();
	}
	
	@Override
	public Iterator<T> iterator() {
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator() {
		// TODO Auto-generated method stub
		return new DLLIterator();
	}

	@Override
	public ListIterator<T> listIterator(int startingIndex) {
		// TODO Auto-generated method stub
		return new DLLIterator(startingIndex);
	}
	
	
	
	/**
	 * ListIterator for IUDoubleLinkedList
	 * Can be used as an Iterator
	 */
	private class DLLIterator implements ListIterator<T>
	{
		private int iModCount, nextIndex;
		private Node<T> nextNode, lastReturned;
		/**
		 * Initialize the iterator in front of the first node
		 */
		public DLLIterator()
		{
			this(0);
		}
		/**
		 * Initialize Iterator in front of startingIndex
		 * @param startingIndex index of the element that would be next
		 */
		public DLLIterator(int startingIndex)
		{
			if((startingIndex<0)||(startingIndex>size))
			{
				throw new IndexOutOfBoundsException();
			}
			nextNode = head;
			iModCount = modCount;
			nextIndex = startingIndex;
			for(int i = 0; i<startingIndex; i++)
			{
				nextNode = nextNode.getNextNode();
			}
			lastReturned = null;
		}
		
		@Override
		public boolean hasNext() {
			if(this.iModCount != modCount)
			{
				throw new ConcurrentModificationException();
			}
			return (nextNode!=null);
		}

		@Override
		public T next() {
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			T retVal = this.nextNode.getElement();
			lastReturned = nextNode;
			this.nextNode = this.nextNode.getNextNode();
			nextIndex++;
			return retVal;
		}
		
		@Override
		public void remove()
		{
			if(iModCount!=modCount)
			{
				throw new ConcurrentModificationException();
			}
			if(lastReturned == null)
			{
				throw new IllegalStateException();
			}
			if(nextNode!=lastReturned)//Last move Next
			{
				if(nextNode != null)
				{
					nextNode.setPreviousNode(lastReturned.getPreviousNode());
				}
				else {
					tail = tail.getPreviousNode();
				}
				if(lastReturned == head)
				{
					head = head.getNextNode();
				}
				else {
					lastReturned.getPreviousNode().setNextNode(nextNode);
				}
			}
			else//last move previous
			{
				if(lastReturned == tail)
				{
					tail = tail.getPreviousNode();
				}
				else
				{
					lastReturned.getNextNode().setPreviousNode(lastReturned.getPreviousNode());
				}
				if(lastReturned == head)
				{
					head = head.getNextNode();
				}
				else
				{
					lastReturned.getPreviousNode().setNextNode(lastReturned.getNextNode());
				}
				nextNode = nextNode.getNextNode();
			}
			size--;
			iModCount++;
			modCount++;
			lastReturned = null;
		}

		@Override
		public boolean hasPrevious() {
			if(iModCount!=modCount)
			{
				throw new ConcurrentModificationException();
			}
			return (nextNode!=head);
		}

		@Override
		public T previous() {
			if(!hasPrevious())
			{
				throw new NoSuchElementException();
			}
			if(nextNode == null)
			{
				nextNode = tail;
			} else {
			nextNode = nextNode.getPreviousNode();
			}
			lastReturned = nextNode;
			nextIndex--;
			return nextNode.getElement();
		}

		@Override
		public int nextIndex() {
			if(iModCount!=modCount)
			{
				throw new ConcurrentModificationException();
			}
			return nextIndex;
		}

		@Override
		public int previousIndex() {
			if(iModCount!=modCount)
			{
				throw new ConcurrentModificationException();
			}
			return (nextIndex-1);
		}

		@Override
		public void set(T e) {			
			if(iModCount!=modCount)
			{
				throw new ConcurrentModificationException();
			}
			if(lastReturned == null)
			{
				throw new IllegalStateException();
			}
			lastReturned.setElement(e);
			
			
			
			iModCount++;
			modCount++;
		}

		@Override
		public void add(T e) {
			if(iModCount != modCount)
			{
				throw new ConcurrentModificationException();
			}
			Node<T> newNode = new Node<T>(e);
			if(head == tail && head == null)
			{
				head = tail = newNode;
			}
			else if(nextNode == null)
			{
				tail.setNextNode(newNode);
				newNode.setPreviousNode(tail);
				tail = newNode;
			}
			else if(nextNode == head) {
				newNode.setNextNode(head);
				head.setPreviousNode(newNode);
				head = newNode;
			}
			else
			{
				newNode.setNextNode(nextNode);
				newNode.setPreviousNode(nextNode.getPreviousNode());
				nextNode.getPreviousNode().setNextNode(newNode);
				nextNode.setPreviousNode(newNode);
			}
			
			
			
			iModCount++;
			modCount++;
			size++;
			nextIndex++;
		}
		
	}

}
