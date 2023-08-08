//
//
// CONSTRUCTION: with no initializer
//
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * BinarySearchTree class                                     <br>
 * Implements an unbalanced binary search tree.               <br>
 * Note that all "matching" is based on the compareTo method. <br>
 * <br>
 * ******************PUBLIC OPERATIONS*********************   <br>
 *  void insert( x )       --> Insert x                       <br>
 *  void remove( x )       --> Remove x                       <br>
 *  boolean contains( x )  --> Return true if x is present    <br>
 *  Comparable findMin( )  --> Return smallest item           <br>
 *  Comparable findMax( )  --> Return largest item            <br>
 *  boolean isEmpty( )     --> Return true if empty; else false <br>
 *  void makeEmpty( )      --> Remove all items               <br>
 *  void printTree( )      --> Print tree in sorted order     <br>
 * <br>
 * @author Mark Allen Weiss;   Modified for MCON264 Spring2022 <br>
 *  *                          remove main test driver <br>
 *  *                          remove generation of UnderflowExceptions <br>
 *  *                          rearrange order of methods and fields <br>
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{

    /**
     * The tree root.
     */
    private BinaryNode<AnyType> root;
    public int totalWordCount = 0;
    public int diffWords = 0;


    //<editor-fold defaultstate="collapsed" desc="BinaryNode">
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
        AnyType element;            // The data in the node
        int count;                  // N3umber of times the element appears
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child

        // Constructors
        BinaryNode(AnyType theElement)
        {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt)
        {
            element = theElement;
            left = lt;
            right = rt;
            count++;
        }

        public void incrementCount()
        {
            count++;
        }
    }
    //</editor-fold>


    //////////////////////////////////
    //                              //
    //      public methods:         //
    //                              //
    //////////////////////////////////
    /**
     * Construct the tree.
     */
    public BinarySearchTree()
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void insert(AnyType x)
    {
        root = insert(x, root);
        totalWordCount++;
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x)
    {
        root = remove(x, root);
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public AnyType findMin()
    {
        AnyType retVal = null;
        if (!isEmpty())
            retVal = findMin(root).element;
        return retVal;
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public AnyType findMax()
    {
        AnyType retVal = null;
        if (!isEmpty())
            retVal = findMax(root).element;
        return retVal;
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains(AnyType x)
    {
        return contains(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty()
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     *
     * @return
     */
    public void printTree()
    {
        System.out.println("Word count:" + totalWordCount);

        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);

        int ratio = totalWordCount/diffWords;
        System.out.println("TTR ratio: " + ratio);
    }

    /**
     * a string presentation of this tree
     *
     * @return String representing this tree
     */
    public String toString()
    {
        String strTree = "empty";
        if (root != null)
        {
            StringBuilder buffer = new StringBuilder();
            toStr(root, buffer, "", "");
            strTree = buffer.toString();
        }
        return strTree;
    }


    ///////////////////////////////////////////////////
    //////                                       //////
    //////      private methods                  //////
    //////                                       //////
    ///////////////////////////////////////////////////

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t)
    {
        if (t == null)
            return new BinaryNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            t.incrementCount();  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */

    //bug in my remove method
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t)
    {
        if (t == null)
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) // Two children
        {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        }
        else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t)
    {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t)
    {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t)
    {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t)
    {
        if (t != null)
        {
            diffWords++;
            printTree(t.left);
            System.out.println(t.element + " (" + t.count + ")");
            printTree(t.right);
        }
    }

    /**
     * Intenal method to convert a subtree to a tree-like string
     *
     * @param node           that roots the subtree
     * @param buffer         StringBuilder collecting the evergrowing string
     * @param prefix         prefix of node display - so far
     * @param childrenPrefix prefix for subsequent children
     */
    private void toStr(BinaryNode<AnyType> node,
                       StringBuilder buffer,
                       String prefix,
                       String childrenPrefix)
    {
        buffer.append(prefix);
        buffer.append(node.element.toString() + " " + node.count);
        buffer.append('\n');
        if (node.right != null)
        {
            toStr(node.right, buffer, childrenPrefix + ">── ",
                    node.left != null
                            ? childrenPrefix + "│   "
                            : childrenPrefix + "    ");
        }
        if (node.left != null)
        {
            toStr(node.left, buffer, childrenPrefix + "<── ", childrenPrefix + "    ");
        }
    }
}

