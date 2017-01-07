/**
 * FibonacciHeap
 *
 * An implementation of fibonacci heap over non-negative integers.
 */
public class FibonacciHeap
{
	private HeapNode min; //pointer to min. node in heap
	private int numOfNodes=0; //num. of nodes in heap
	/**
	 * public boolean empty()
	 *
	 * precondition: none
	 * 
	 * The method returns true if and only if the heap
	 * is empty.
	 *   
	 */
	public boolean empty()
	{
		return this.min == null; // should be replaced by student code
	}

	/**
	 * public HeapNode insert(int key)
	 *
	 * Creates a node (of type HeapNode) which contains the given key, and inserts it into the heap. 
	 */
	public HeapNode insert(int key)
	{    
		HeapNode node = new HeapNode(key);
		numOfNodes++;
		if(this.min == null){ 
			this.min = node;
			return node;
		}
		cat(node, this.min);
		if(this.min.getKey()>node.getKey())
			this.min = node;
		return node; 
	}

	/**
	 * gets 2 nodes, x and y, and concats their lists
	 * !!doesn't handle parents, rank, whatsoever,
	 * just right and left !!
	 * x and y are allowed to be null,
	 * in that case nothing is done.
	 */
	private void cat(HeapNode x, HeapNode y){
		if(x == null || y == null)
			return;
		HeapNode lastX = x.getL();
		HeapNode lastY = y.getL();
		lastX.setR(y);
		y.setL(lastX);
		lastY.setR(x);
		x.setL(lastY);
	}

	/**
	 * public void deleteMin()
	 *
	 * Delete the node containing the minimum key.
	 *
	 */
	public void deleteMin()
	{
		return; // should be replaced by student code

	}

	/**
	 * public HeapNode findMin()
	 *
	 * Return the node of the heap whose key is minimal. 
	 *
	 */
	public HeapNode findMin()
	{
		return this.min;// should be replaced by student code
	} 

	/**
	 * public void meld (FibonacciHeap heap2)
	 *
	 * Meld the heap with heap2
	 *
	 */
	public void meld (FibonacciHeap heap2)
	{
		if(heap2 == null)
			return;
		cat (min,heap2.findMin()); 
		this.numOfNodes = this.numOfNodes+heap2.size();
		if(this.min.getKey()>heap2.findMin().getKey())
			this.min=heap2.findMin();
	}

	/**
	 * public int size()
	 *
	 * Return the number of elements in the heap
	 *   
	 */
	public int size()
	{
		return numOfNodes; 
	}

	/**
	 * public int[] countersRep()
	 *
	 * Return a counters array, where the value of the i-th entry is the number of trees of order i in the heap. 
	 * 
	 */
	public int[] countersRep()
	{
		int[] arr = new int[42];
		return arr; //	 to be replaced by student code
	}

	/**
	 * public void delete(HeapNode x)
	 *
	 * Deletes the node x from the heap. 
	 *
	 */
	public void delete(HeapNode x) 
	{    
		return; // should be replaced by student code
	}

	/**
	 * public void decreaseKey(HeapNode x, int delta)
	 *
	 * The function decreases the key of the node x by delta. The structure of the heap should be updated
	 * to reflect this chage (for example, the cascading cuts procedure should be applied if needed).
	 */
	public void decreaseKey(HeapNode x, int delta) 
	{    
		x.setKey(x.getKey()-delta);
		HeapNode p = x.getP();
		if(p != null && x.getKey() < p.getKey()){
			cut(x);
			cascadingcut(p);
		}
		if(x.getKey() < this.min.getKey())
			this.min = x;
	}

	/**
	 * cut as was studied in class
	 */
	private void cut(HeapNode x){
		removeFromList(x);
		cat(this.min,x);
		x.setMark(false);
	}

	/**
	 * private void removeFromList(HeapNode x)
	 * Takes x out of the list where it is right now
	 * makes x.right=x.left=x
	 * Fixes it's parent's degree(if there is a parent)
	 * Makes x's parent null
	 * !! May change the parent's child
	 * even if it's child is not x !!
	 * @pre: x isn't null
	 */
	private void removeFromList(HeapNode x)
	{
		HeapNode p = x.getP();
		x.setP(null);
		HeapNode r = x.getR();
		HeapNode l = x.getL();
		if(r != x){ // x isn't alone in the list
			r.setL(l);
			l.setR(r);
			x.setR(x);
			x.setL(x);
		} else { // x is alone in the list.
			r = null;
		}
		if(p != null){
			// change p's child to r.
			// if only x was in the list
			// then r is null (see the "else"
			// above)
			p.setChild(r);
			p.decDegree();
		}
	}

	private void cascadingcut(HeapNode y){
		HeapNode p = y.getP();
		if(p != null){
			if (!y.isMark()){
				y.setMark(true);
			} else {
				cut(y);
				cascadingcut(p);
			}
		}
	}

	/**
	 * public int potential() 
	 *
	 * This function returns the current potential of the heap, which is:
	 * Potential = #trees + 2*#marked
	 * The potential equals to the number of trees in the heap plus twice the number of marked nodes in the heap. 
	 */
	public int potential() 
	{    
		return 0; // should be replaced by student code
	}

	/**
	 * public static int totalLinks() 
	 *
	 * This static function returns the total number of link operations made during the run-time of the program.
	 * A link operation is the operation which gets as input two trees of the same rank, and generates a tree of 
	 * rank bigger by one, by hanging the tree which has larger value in its root on the tree which has smaller value 
	 * in its root.
	 */
	public static int totalLinks()
	{    
		return 0; // should be replaced by student code
	}

	/**
	 * public static int totalCuts() 
	 *
	 * This static function returns the total number of cut operations made during the run-time of the program.
	 * A cut operation is the operation which diconnects a subtree from its parent (during decreaseKey/delete methods). 
	 */
	public static int totalCuts()
	{    
		return 0; // should be replaced by student code
	}

	/**
	 * public class HeapNode
	 * 
	 * If you wish to implement classes other than FibonacciHeap
	 * (for example HeapNode), do it in this file, not in 
	 * another file 
	 *  
	 */
	public class HeapNode{
		private int key;
		private HeapNode L;
		private HeapNode R;
		private HeapNode P; // Parent
		private HeapNode child; 
		private int degree;
		private boolean mark;
		/**
		 * public HeapNode(int key)
		 *
		 * makes a new heap node, ready to be inserted
		 * into the root list
		 * R and L are by default pointing to itself
		 */
		public HeapNode(int key) {
			setKey(key);
			setL(this);
			setR(this);
			setDegree(0);
			setMark(false);
		}

		/**
		 * @return the key
		 */
		public int getKey() {
			return key;
		}

		/**
		 * @param key the key to set
		 */
		public void setKey(int key) {
			this.key = key;
		}

		/**
		 * @return the l
		 */
		public HeapNode getL() {
			return L;
		}

		/**
		 * @param l the l to set
		 */
		public void setL(HeapNode l) {
			L = l;
		}

		/**
		 * @return the r
		 */
		public HeapNode getR() {
			return R;
		}

		/**
		 * @param r the r to set
		 */
		public void setR(HeapNode r) {
			R = r;
		}

		/**
		 * @return the p
		 */
		public HeapNode getP() {
			return P;
		}

		/**
		 * @param p the p to set
		 */
		public void setP(HeapNode p) {
			P = p;
		}

		/**
		 * @return the child
		 */
		public HeapNode getChild() {
			return child;
		}

		/**
		 * @param child the child to set
		 */
		public void setChild(HeapNode child) {
			this.child = child;
		}

		/**
		 * @return the degree
		 */
		public int getDegree() {
			return degree;
		}

		/**
		 * @param degree the degree to set
		 */
		public void setDegree(int degree) {
			this.degree = degree;
		}

		/**
		 * @return the mark
		 */
		public boolean isMark() {
			return mark;
		}

		/**
		 * @param mark the mark to set
		 */
		public void setMark(boolean mark) {
			this.mark = mark;
		}
		
		/**
		 * decreases degree by 1;
		 * returns the new degree
		 */
		public int decDegree(){
			return decDegree(1);
		}

		/**
		 * increases degree by 1;
		 * returns the new degree
		 */
		public int incDegree(){
			return incDegree(1);
		}

		/**
		 * decreases degree by n;
		 * returns the new degree
		 */
		public int decDegree(int n){
			setDegree(getDegree()-n);
			return this.degree;
		}

		/**
		 * increases degree by n;
		 * returns the new degree
		 */
		public int incDegree(int n){
			setDegree(getDegree()+n);
			return this.degree;
		}
	}
}
