/**
 * @author PaulEllis
 * @param <T>
 */
public class Node<T> 
{
	private T element;
	private Node<T> nextNode;
	private Node<T> prevNode;
	/**
	 * Initialize a node with a specific element
	 * Sets the next and previous node to null
	 * @param element the element stored in the new node
	 */
	public Node(T element)
	{
		this.element = element;
		nextNode = null;
		prevNode = null;
	}
	/**
	 * Initialize a node with a specific element and nextNode
	 * sets the previous node to null
	 * @param element the element stored in the node
	 * @param nextNode the node that follows this node
	 */
	public Node(T element, Node<T> nextNode)
	{
		this.element = element;
		this.nextNode = nextNode;
		this.prevNode = null;
	}
	/**
	 * Initialize a node with a specific element, nextNode, and previousNode
	 * @param element the element stored in the node
	 * @param nextNode the node that follows this node
	 * @param prevNode the node that is previous to this node
	 */
	public Node(T element, Node<T> nextNode, Node<T> prevNode)
	{
		this.element = element;
		this.nextNode = nextNode;
		this.prevNode = prevNode;
	}
	/**
	 * @return the element
	 */
	public T getElement() {
		return element;
	}
	/**
	 * @param element the element to set
	 */
	public void setElement(T element) {
		this.element = element;
	}
	/**
	 * @return the nextNode
	 */
	public Node<T> getNextNode() {
		return nextNode;
	}
	/**
	 * @param nextNode the nextNode to set
	 */
	public void setNextNode(Node<T> nextNode) {
		this.nextNode = nextNode;
	}
	/**
	 * 
	 * @return the previous node
	 */
	public Node<T> getPreviousNode()
	{
		return prevNode;
	}
	/**
	 * 
	 * @param prevNode set a new previous node
	 */
	public void setPreviousNode(Node<T> prevNode)
	{
		this.prevNode = prevNode;
	}
	
}
