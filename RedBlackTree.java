import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
//import RedBlackTreePlaceHold.Node;
//import RedBlackTreePlaceHold.Node;
//import RedBlackTreePlaceHold.Node;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm.
 */
public class RedBlackTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {

    /**
     * This class represents a node holding a single value within a binary tree.
     */
    protected static class Node<T> {
        public T data;
        public int blackHeight;
        // The context array stores the context of the node in the tree:
        // - context[0] is the parent reference of the node,
        // - context[1] is the left child reference of the node,
        // - context[2] is the right child reference of the node.
        // The @SupressWarning("unchecked") annotation is used to supress an unchecked
        // cast warning. Java only allows us to instantiate arrays without generic
        // type parameters, so we use this cast here to avoid future casts of the
        // node type's data field.
        @SuppressWarnings("unchecked")
        public Node<T>[] context = (Node<T>[])new Node[3];
        public Node(T data) { this.data = data; this.blackHeight=0;}
        
        /**
         * @return true when this node has a parent and is the right child of
         * that parent, otherwise return false
         */
        public boolean isRightChild() {
            return context[0] != null && context[0].context[2] == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * method that rebalances tree
     * @param redData that has just been added
     */
    protected void enforceRBTreePropertiesAfterInsert(Node<T> insert) {
      //Declare important context nodes
      Node<T> parent;
      Node<T> grandfather;
      Node<T> sibling;
      boolean case1 = false;
      //Store Relevant context-checking if node is null before each
      parent= insert.context[0];
      if (parent != null){grandfather = parent.context[0];}else{grandfather = null;}
      if(grandfather != null){
          if (parent.isRightChild()) {sibling = grandfather.context[1];}
          else{sibling = grandfather.context[2];}
      }else {sibling = null;}
      //Check if parent is null - if true it is root, and we skip everything
      if (parent != null) {
          if (isRed(parent)) {
              //Case 1: Parent Node is Red w/ sibling Red; set both upstream nodes to black
              if (sibling != null) {
                  if (isRed(sibling)) {
                      sibling.blackHeight = 1;
                      parent.blackHeight = 1;
                      grandfather.blackHeight = 0;
                      case1 = true;
                      enforceRBTreePropertiesAfterInsert(grandfather);
                  }
              }
              //Since we treat black == null, we only check grandfather
              if (grandfather != null) {
                  if (!case1) {
                      //Step 1: Check if parent and insert are in line
                      boolean inLine = (parent.isRightChild() && insert.isRightChild())
                          || (!parent.isRightChild() && !insert.isRightChild());
                      //Case 2: Parent Node is Red w/ sibling black not in line; Rotate twice (internal) then C2
                      if (!inLine) {
                          rotate(insert, parent);
                          rotateSwap(insert, grandfather);
                      }
                      //Case 3: Parent Node is Red w/ sibling black in line; Rotate P node w/ Grandfather
                      //and then swap colors
                      //call method for rotation and color swap
                      else {
                          rotateSwap(parent, grandfather);
                      }
                  }
              }
          }
          //Case 4:Parent Node is black; do nothing
      }
      //Always set root to black
      root.blackHeight = 1;


      
    }
    
    private void rotateSwap(Node<T> parent,Node<T> grandfather){
      rotate(parent,grandfather);
      parent.blackHeight = 1;
      grandfather.blackHeight = 0;
  }
  private boolean isRed(Node<T> toCheck){
      //if red return true, false otherwise
      if (toCheck.blackHeight == 0) { return true;}
      return false;
  }

    

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is already contained in the tree
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if (this.root == null) {
            // add first node to an empty tree
            root = newNode; size++; 
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;
        } else {
            // insert into subtree
            Node<T> current = this.root;
            while (true) {
                int compare = newNode.data.compareTo(current.data);
                if (compare == 0) {
                    throw new IllegalArgumentException("This RedBlackTree already contains value " + data.toString());
                } else if (compare < 0) {
                    // insert in left subtree
                    if (current.context[1] == null) {
                        // empty space to insert into
                        current.context[1] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newNode);
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[1];
                    }
                } else {
                    // insert in right subtree
                    if (current.context[2] == null) {
                        // empty space to insert into
                        current.context[2] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newNode);
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[2]; 
                    }
                }
            }
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * right child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
      //Method rotates by changing the context around nodes INSTEAD of outright replacing, instead relinking each
      //node in a consistent order, only updating the context array without deleting any nodes.
      if (parent.context[2] != null && parent.context[2].equals(child)) { //Check if right child - if so left rotation
          //Store left descendant of child B
          Node<T> toSwap = child.context[1];
          //Store parent of parent(A)
          Node<T> futureP = parent.context[0];
          //Set B(Child) Parent
          child.context[0] = futureP;
          //Set A(Parent) parent as B
          parent.context[0] = child;
          //Swap A's right child
          parent.context[2] = toSwap;
          //Set A's left child parent
          if (toSwap != null) { //if the node to swap doesn't exist then don't need to give it a parent
              toSwap.context[0] = parent;
          }
          //Set B left child
          child.context[1] = parent;
          //Update parent of B
          if(futureP != null) { //if we are not at the root we need to update parent
              if (futureP.context[2] != null && futureP.context[2].equals(parent)) {
                  futureP.context[2] = child;
              } else {
                  futureP.context[1] = child;
              }
          }else { //if we are at the root we need to set the rotated value as the root.
              this.root = child;
          }
      } else if (parent.context[1] != null && parent.context[1].equals(child)) { //Right rotation if left child
          //Store Right descendant of Child(B)
          Node<T> toSwap = child.context[2];
          //Store Parent of Parent(A)
          Node<T> futureP = parent.context[0];
          //Make Parent Node's parent the current child
          parent.context[0] = child;
          //Set B(Child) Parent to A's old parent
          child.context[0] = futureP;
          //Set A(Parent) left child to B's right child
          parent.context[1] = toSwap;
          //Set B(Child) right child to A(Parent)
          child.context[2] = parent;
          //Set B's old right child descendant to A's left child descendant
          if (toSwap != null) { //if there is a node to be swapped, reassign parents
              toSwap.context[0] = parent;
          }
          //Update parent linkage if not at root node
          if(futureP != null) {
              if (futureP.context[2] != null && futureP.context[2].equals(parent)) { //if right descendant set
                  futureP.context[2] = child;
              } else { //if left descendant set
                  futureP.context[1] = child;
              }
          }else {//we rotate at a root node, so must set new root
              this.root = child;
          }
      }else { //if nodes are Unrelated throw exception
          throw new IllegalArgumentException("Nodes aren't directly related");
      }
    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Removes the value data from the tree if the tree contains the value.
     * This method will not attempt to rebalance the tree after the removal and
     * should be updated once the tree uses Red-Black Tree insertion.
     * @return true if the value was remove, false if it didn't exist
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is not stored in the tree
     */
    public boolean remove(T data) throws NullPointerException, IllegalArgumentException {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // throw exception if node with data does not exist
            if (nodeWithData == null) {
                throw new IllegalArgumentException("The following value is not in the tree and cannot be deleted: " + data.toString());
            }  
            boolean hasRightChild = (nodeWithData.context[2] != null);
            boolean hasLeftChild = (nodeWithData.context[1] != null);
            if (hasRightChild && hasLeftChild) {
                // has 2 children
                Node<T> successorNode = this.findMinOfRightSubtree(nodeWithData);
                // replace value of node with value of successor node
                nodeWithData.data = successorNode.data;
                // remove successor node
                if (successorNode.context[2] == null) {
                    // successor has no children, replace with null
                    this.replaceNode(successorNode, null);
                } else {
                    // successor has a right child, replace successor with its child
                    this.replaceNode(successorNode, successorNode.context[2]);
                }
            } else if (hasRightChild) {
                // only right child, replace with right child
                this.replaceNode(nodeWithData, nodeWithData.context[2]);
            } else if (hasLeftChild) {
                // only left child, replace with left child
                this.replaceNode(nodeWithData, nodeWithData.context[1]);
            } else {
                // no children, replace node with a null node
                this.replaceNode(nodeWithData, null);
            }
            this.size--;
            if(nodeWithData.blackHeight==1){
            	enforceBSTafterRemove(nodeWithData);
            }
            return true;
        } 
    }

    /**
     * fixer method after tree removal
     * @param node that has been removed
     */
    protected void enforceBSTafterRemove(Node<T> rem) {
    	while(rem!=root && rem.blackHeight==1) {
    		//is node is left child
    		if(rem == rem.context[0].context[1]) {
    		      Node<T> node = rem.context[0].context[2];
    		      //uncle node is red
    		      if(node.blackHeight == 0) {
    		    	  //make uncle black
    		        node.blackHeight = 1;
    		        rem.context[0].blackHeight = 0;
    		        //leftRotate(rem.context[0]);
    		        //left rotate and reassign uncle
    		        rotate(rem.context[0].context[2],rem.context[0]);
    		        node = rem.context[0].context[2];
    		      }
    		      //if uncle's left child is =black, right child is black
    		      if(node.context[1].blackHeight == 1 && node.context[2].blackHeight == 1) {
    		        node.blackHeight = 0;
    		        rem = rem.context[0];
    		      }
    		      else {
    		          if(node.context[2].blackHeight == 1) {
    		            node.context[1].blackHeight = 1;
    		            node.blackHeight = 0;
    		            //rightRotate(w);
    		            //rotate and reassign uncle
    		            rotate(node.context[1],node);
    		            node = rem.context[0].context[2];
    		          }
    		          node.blackHeight = rem.context[0].blackHeight;
    		          rem.context[0].blackHeight = 1;
    		          node.context[2].blackHeight = 1;
    		          //leftRotate(rem.context[0]);
    		          rotate(rem.context[0].context[2],rem.context[0]);
    		          rem = this.root;
    		        }
    		      }
    		//if node is right child
    		else {
    		      Node<T> node = rem.context[0].context[1];
    		      //uncle node is red
    		      if(node.blackHeight == 0) {
    		    	  //make uncle black
    		        node.blackHeight = 1;
    		        rem.context[0].blackHeight = 0;
    		        //rightRotate(rem.context[0]);
    		        //right rotate and reassign uncle
    		        rotate(rem.context[0].context[1],rem.context[0]);
    		        node = rem.context[0].context[1];
    		      }
    		      //uncle's right child is black, uncle's left child is black
    		      if(node.context[2].blackHeight == 1 && node.context[1].blackHeight == 1) {
    		        node.blackHeight = 0;
    		        rem = rem.context[0];
    		      }
    		      else {
    		        if(node.context[1].blackHeight == 1) {
    		          node.context[2].blackHeight = 1;
    		          node.blackHeight = 0;
    		          //leftRotate(w);
    		          //rotate and reassign
    		          rotate(node.context[2],node);
    		          node = rem.context[0].context[1];
    		        }
    		        node.blackHeight = rem.context[0].blackHeight;
    		        rem.context[0].blackHeight = 1;
    		        node.context[1].blackHeight = 1;
    		        //rightRotate(rem.context[0]);
    		        //rotate and make removed node the root
    		        rotate(rem.context[0].context[1],rem.context[0]);
    		        rem = this.root;
    		      }
    		    }
    		  }
    	//make black
    	rem.blackHeight = 1;
    }
    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // return false if the node is null, true otherwise
            return (nodeWithData != null);
        }
    }

    /**
     * Helper method that will replace a node with a replacement node. The replacement
     * node may be null to remove the node from the tree.
     * @param nodeToReplace the node to replace
     * @param replacementNode the replacement for the node (may be null)
     */
    protected void replaceNode(Node<T> nodeToReplace, Node<T> replacementNode) {
        if (nodeToReplace == null) {
            throw new NullPointerException("Cannot replace null node.");
        }
        if (nodeToReplace.context[0] == null) {
            // we are replacing the root
            if (replacementNode != null)
                replacementNode.context[0] = null;
            this.root = replacementNode;
        } else {
            // set the parent of the replacement node
            if (replacementNode != null)
                replacementNode.context[0] = nodeToReplace.context[0];
            // do we have to attach a new left or right child to our parent?
            if (nodeToReplace.isRightChild()) {
                nodeToReplace.context[0].context[2] = replacementNode;
            } else {
                nodeToReplace.context[0].context[1] = replacementNode;
            }
        }
    }
    
    /**
     * finds smallest value in tree
     * @return largest value in tree of generic type
     */
    public T findMin(){
    	Node<T> current = this.root;
    	T minData = root.data;
    	while(current!=null) {
    		minData = current.data;
    		current=current.context[1];
    	}
    	return minData;
    }
    
    /**
     * finds largest value in tree
     * @return largest value in tree of type
     */
    public T findMax(){
    	Node<T> current = this.root;
    	T maxData = root.data;
    	while(current!=null) {
    		maxData = current.data;
    		current=current.context[2];
    	}
    	return maxData;
    }
    
    /**
     * in order traversal for iterator
     * 
     */
    public void inOrder(Node<T> node) {
    	List<T> elements = new ArrayList<T>();
    	if(node==null) {
    		return;
    	}
    	inOrder(node.context[1]);
    	elements.add(node.data);
    	inOrder(node.context[2]);
    }
    
    /**
     * Helper method that will return the inorder successor of a node with two children.
     * @param node the node to find the successor for
     * @return the node that is the inorder successor of node
     */
    protected Node<T> findMinOfRightSubtree(Node<T> node) {
        if (node.context[1] == null && node.context[2] == null) {
            throw new IllegalArgumentException("Node must have two children");
        }
        // take a steop to the right
        Node<T> current = node.context[2];
        while (true) {
            // then go left as often as possible to find the successor
            if (current.context[1] == null) {
                // we found the successor
                return current;
            } else {
                current = current.context[1];
            }
        }
    }

    /**
     * Helper method that will return the node in the tree that contains a specific
     * value. Returns null if there is no node that contains the value.
     * @return the node that contains the data, or null of no such node exists
     */
    protected Node<T> findNodeWithData(T data) {
        Node<T> current = this.root;
        while (current != null) {
            int compare = data.compareTo(current.data);
            if (compare == 0) {
                // we found our value
                return current;
            } else if (compare < 0) {
                // keep looking in the left subtree
                current = current.context[1];
            } else {
                // keep looking in the right subtree
                current = current.context[2];
            }
        }
        // we're at a null node and did not find data, so it's not in the tree
        return null; 
    }

    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            Stack<Node<T>> nodeStack = new Stack<>();
            Node<T> current = this.root;
            while (!nodeStack.isEmpty() || current != null) {
                if (current == null) {
                    Node<T> popped = nodeStack.pop();
                    sb.append(popped.data.toString());
                    if(!nodeStack.isEmpty() || popped.context[2] != null) sb.append(", ");
                    current = popped.context[2];
                } else {
                    nodeStack.add(current);
                    current = current.context[1];
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * This method performs a level order traversal of the tree. The string
     * representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.context[1] != null) q.add(next.context[1]);
                if(next.context[2] != null) q.add(next.context[2]);
                sb.append(next.data.toString());
                if(!q.isEmpty()) sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    
    /**
     * Main method to run tests. Comment out the lines for each test
     * to run them.
     * @param args
     */
    public static void main(String[] args) {
        
    }

}
