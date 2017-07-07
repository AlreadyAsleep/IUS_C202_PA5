
import java.io.*;
import java.util.*;

public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

  protected TreeNode<E> root;
  protected int size = 0;

  /** Create a default binary tree */
  public BinarySearchTree() { }

  /** Create a binary tree from an array of objects */
  public BinarySearchTree(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      insert(objects[i]);
  }

  /** Returns true if the element is in the tree */
  public boolean search(E e) {
    TreeNode<E> current = root; // Start from the root
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else // element matches current.element
        return true; // Element is found
    }
    return false;
  }
  
  public boolean search(E e, int[] i) {
    TreeNode<E> current = root; // Start from the root
    int i_temp =0;
    while (current != null) 
    {
      i_temp++;
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else // element matches current.element
      {
        i[0] = i_temp;
        return true; // Element is found
      }
    }
    i[0] = i_temp;
    return false;
  }
  
  
  public TreeNode<E> find(E e)    
  {
      TreeNode<E> current = root; // Start from the root
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else // element matches current.element
        return current; // Element is found
    }
    return new TreeNode<>(e);
  }

  /** Insert element o into the binary tree
   * Return true if the element is inserted successfully. 
   *  Uses an iterative algorithm
   */
  public boolean insert(E e) {
    if (root == null)
      root = createNewNode(e); // Create a new root
    else {
      // Locate the parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null)
        if (e.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        }
        else if (e.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        }
        else
          return false; // Duplicate node not inserted
          
      // Create the new node and attach it to the parent node
      if (e.compareTo(parent.element) < 0)
        parent.left = createNewNode(e);
      else
        parent.right = createNewNode(e);
    }
    size++;
    return true; // Element inserted
  }

  protected TreeNode<E> createNewNode(E e) {
    return new TreeNode<E>(e);
  }

  /** Inorder traversal from the root*/
  public void inorder() {
    inorder(root);
  }

  /** Inorder traversal from a subtree */
  protected void inorder(TreeNode<E> root) {
    if (root == null) return;
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

   
  /** Postorder traversal from the root */
  public void postorder() {
    postorder(root);
  }

  /** Postorder traversal from a subtree */
  protected void postorder(TreeNode<E> root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element + " ");
  }

  /** Preorder traversal from the root */
  public void preorder() {
    preorder(root);
  }

  /** Preorder traversal from a subtree */
  protected void preorder(TreeNode<E> root) {
    if (root == null) return;
    System.out.print(root.element + " ");
    preorder(root.left);
    preorder(root.right);
  }

  /** Inner class tree node */
  public static class TreeNode<E extends Comparable<E>> {
    E element;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E e) {
      element = e;
    }
  }

  /** Get the number of nodes in the tree */
  public int getSize() {
    return size;
  }

  /** Returns the root of the tree */
  public TreeNode<E> getRoot() {
    return root;
  }
   
    /** Returns an ArrayList containing elements in the path from the root leading to the specified element, returns an empty ArrayList if no  such element exists. */
    public ArrayList<E> path(E e)
    {
        if(search(e))
        {
            java.util.ArrayList<E> list = new java.util.ArrayList<>();
            TreeNode<E> current = root; // Start from the root
            list = path(list, root, e);
            return list; // Return an array of elements
        }
        return  new ArrayList<>();
     }
    
  protected ArrayList<E> path(ArrayList<E> list, TreeNode<E> node, E e)
  {
      if(node.element.compareTo(e) == 0)
          return list;
     list.add(node.element);
      if(node.element.compareTo(e) < 0)
      {
         return path(list, node.right, e);
      }
     else
     {
         return path(list, node.left, e);
     }
  }
  
    
    /* Returns the number of leaf nodes in this tree, returns 0 if tree is empty*/
    public int  getNumberOfLeaves()
    {
        return  getNumberOfLeaves(root);
    }
    
    protected int getNumberOfLeaves(TreeNode<E> node)
    {
        int num = 0;
        if(node.left != null)
        {
            num+= 1 + getNumberOfLeaves(node.left);
        }
        if(node.right != null)
        {
            num+= 1 + getNumberOfLeaves(node.right);
        }
        return num;
        
    }
    /* Returns an ArrayList containing all elements in preorder of the specified element’s left sub-tree, returns an empty ArrayList if no  such element exists. */
    public ArrayList<E> leftSubTree(E e)
    {
        ArrayList<E> list = new ArrayList<>();
        return subTree(list, find(e).left);       
    }
    
    protected ArrayList<E> subTree(ArrayList<E> list, TreeNode<E> node)
    {
        if(node != null)
        {
             list.add(node.element);
             list = subTree(list, node.left);
             list = subTree(list, node.right);
        }
         return list;
    }
    
    /* Returns an ArrayList containing all elements in preorder of the specified element’s right sub-tree, returns an empty ArrayList if no  such element exists. */
    public ArrayList<E> rightSubTree(E e){
       ArrayList<E> list = new ArrayList<>();
        return subTree(list, find(e).right);
    }
    
    /* Returns the inorder predecessor of the specified element, returns null if tree is empty or element 'e' is not in the tree. */
    public E inorderPredecessor(E e)
    {
        if(find(e) == root)
            return root.element;
        TreeNode<E> current = root;
        TreeNode<E> pre = null;
        while(current.element.compareTo(e) != 0)
        {
            if(current.right.element.compareTo(e) == 0)
            {
                pre = current;
                break;
            }
            if(current.left.element.compareTo(e) == 0)
            {
                pre = current;
                break;
            }
            if(current.element.compareTo(e) < 0 )
                current = current.right;
            else
                current = current.left;
        }
        return pre.element;
        
    }
    
    public  boolean sameTree(BinarySearchTree<E> tree1, BinarySearchTree<E> tree2)
    {
        if(tree1.size != tree2.size)
            return false;
        else
        {
            ArrayList<E> list1 = leftSubTree(tree1.root.element);
            ArrayList<E> list2 = leftSubTree(tree2.root.element);
            for(int i = 0; i < list1.size(); i++)
            {
                if(list1.get(i).compareTo(list2.get(i)) != 0)
                    return false;
            }
            list1 = rightSubTree(tree1.root.element);
            list2 = rightSubTree(tree2.root.element);
            for(int i = 0; i < list1.size(); i++)
            {
                if(list1.get(i).compareTo(list2.get(i)) != 0)
                    return false;
            }
        }
        return true;
    }
    
    
  /** Delete an element from the binary tree.
   * Return true if the element is deleted successfully
   * Return false if the element is not in the tree */
  public boolean delete(E e) {
    // Locate the node to be deleted and also locate its parent node
    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      }
      else
        break; // Element is in the tree pointed by current
    }
    if (current == null)
      return false; // Element is not in the tree
    // Case 1: current has no left children
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
         root = current.right;
      }
      else {
        if (e.compareTo(parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
    }
    else {
      // Case 2 & 3: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;

      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right; // Keep going to the right
      }
      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;
      
      // Eliminate rightmost node
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else
        // Special case: parentOfRightMost == current
        parentOfRightMost.left = rightMost.left;
    }
    size--;
    return true; // Element inserted
  }
  
  /** Obtain an iterator. Use inorder. */
  public java.util.Iterator iterator() {
    return inorderIterator();
  }

  /** Obtain an inorder iterator */
  public java.util.Iterator inorderIterator() {
    return new InorderIterator();
  }

  // Inner class InorderIterator
  class InorderIterator implements java.util.Iterator {
    // Store the elements in a list
    private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
    private int current = 0; // Point to the current element in list

    public InorderIterator() {
      inorder(); // Traverse binary tree and store elements in list
    }

    /** Inorder traversal from the root*/
    private void inorder() {
      inorder(root);
    }

    /** Inorder traversal from a subtree */
    private void inorder(TreeNode<E> root) {
      if (root == null)return;
      inorder(root.left);
      list.add(root.element);
      inorder(root.right);
    }

    /** Next element for traversing? */
    public boolean hasNext() {
      if (current < list.size())
        return true;
      return false;
    }

    /** Get the current element and move cursor to the next */
    public Object next() {
      return list.get(current++);
    }

    /** Remove the current element and refresh the list */
    public void remove() {
      delete(list.get(current)); // Delete the current element
      list.clear(); // Clear the list
      inorder(); // Rebuild the list
    }
  }

  /** Remove all elements from the tree */
  public void clear() {
    root = null;
    size = 0;
  }
}
