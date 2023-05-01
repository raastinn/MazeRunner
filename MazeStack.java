public class MazeStack<T> implements Stack<T> {

	private Node<T> head;
	//private int top;

	public MazeStack() {
		head = null;
		//top = 0;
	}

	/*
	 * Purpose: Insert an item onto the top of the stack
	 * Parameters: T - the item to insert
	 * Returns: Nothing
	 */
	public void push(T v) {
		Node<T> n = new Node<T>(v);

		n.next = head;
		head = n;
		//top++;
	}

	/*
	 * Purpose: Removes and returns the top item from the stack
	 * Parameters: None
	 * Returns: T - the data value of the element removed
	 * Throws: EmptyStackException if array is empty
	 */
	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		Node<T> removed = head;

		head = head.next;
		//top--;

		return removed.getData(); // so it compiles
	}

	/*
	 * Purpose: Accesses the top item on the stack
	 * Parameters: None
	 * Returns: T - the data value of the top element
	 * Throws: EmptyStackException if array is empty
	 */
	public T top() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		}

		//Node<T> top = head;

		return head.getData();
	}

	/*
	 * Purpose: Removes all elements from the stack
	 * Parameters: None
	 * Returns: Nothing
	 */
	public void popAll() {
		if (!isEmpty()) {
			pop();
			popAll();
		}
	}

	/*
	 * Purpose: Determines whether the stack is empty
	 * Parameters: None
	 * Returns: boolean - true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return head==null; // so it compiles
	}

}
