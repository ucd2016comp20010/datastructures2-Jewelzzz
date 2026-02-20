package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;
//import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() // constructs an empty binary tree
    {

    }

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n)
    {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last)
    {
        if (first > last)
            return null;

        else
        {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args)
    {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());
        System.out.println("\n");

        Integer[] array = new Integer[]{1,
                2,3,
                4,5,6,7,
                8,9,10,11,12,13,14,15,
                16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
                null,null,null,35};

        LinkedBinaryTree<Integer> bTree = new LinkedBinaryTree<>();
        bTree.createLevelOrder(array);
        System.out.println(bTree.toBinaryTreeString());
        System.out.println("Height: " + bTree.height());
        System.out.println("Diameter: " + bTree.diameter());
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right)
    {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException
    {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Not valid position type");

        Node<E> node = (Node<E>) p; // safe cast

        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");

        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size()
    {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root()
    {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException
    {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException
    {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException
    {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException
    {
        //TODO
        if (!isEmpty())
        {
          throw new IllegalStateException();
        }

        else
        {
            root = createNode(e, null, null, null);
            size++;
            return root;
        }
    }

    /* in discord, says these can be ignored
    public void insert(E e)
    {
        // TODO

    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e)
    {
        // TODO
        return null;
    }
    */

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException
    {
        // TODO
        Node<E> parent = validate(p);

        if (parent.left != null)
        {
            throw new IllegalArgumentException("position already has a left child");
        }

        else
        {
            parent.left = createNode(e, parent, null, null);
            size++;
            return parent.left;
        }
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException
    {
        // TODO
        Node<E> parent = validate(p);

        if (parent.right != null)
        {
            throw new IllegalArgumentException("position already has a right child");
        }

        else
        {
            parent.right = createNode(e, parent, null, null);
            size++;
            return parent.right;
        }
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException
    {
        // TODO
        Node<E> node = validate(p);

        E replaced = node.element;
        node.element = e;

        return replaced;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException
    {
        // TODO
        Node<E> node = validate(p);

        if (node.left != null || node.right != null)
        {
            throw new IllegalArgumentException("node is not a leaf, cannot attach trees 1 and 2");
        }

        size += t1.size() + t2.size();

        if (t1.root != null)
        {
            node.left = t1.root;
            t1.root.parent = node;
            t1.root = null;
            t1.size = 0;
        }

        if (t2.root != null)
        {
            node.right = t2.root;
            t2.root.parent = node;
            t2.root = null;
            t2.size = 0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException
    {
        // TODO
        Node<E> trashNode = validate(p);

        if (trashNode.left != null && trashNode.right != null)
        {
            throw new IllegalArgumentException("node has two children, cannot remove");
        }

        //get surviving child (could be null if trashNode is a leaf)
        Node<E> child = (trashNode.left != null) ? trashNode.left : trashNode.right;

        //relink child to grandparent
        if (child != null)
        {
            child.parent = trashNode.parent;
        }

        if (trashNode.parent == null) //trashNode was root
        {
            root = child;
            size--;
            return trashNode.element;
        }

        else
        {
            Node<E> parent = trashNode.parent;

            if (parent.left == trashNode)
            {
                parent.left = child;
            }

            else
            {
                parent.right = child;
            }

            size--;
            return trashNode.element;
        }
    }

    public String toString()
    {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l)
    {
        // TODO
        root = createLevelOrderHelper(l, root, 0);
    }

    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i)
    {
        // TODO
        if (i < l.size() && l.get(i) != null)
        {
            p = createNode(l.get(i), null, null, null);
            size++;

            p.left = createLevelOrderHelper(l, p.left, 2 * i + 1);
            if (p.left != null)
            {
                p.left.parent = p;
            }

            p.right = createLevelOrderHelper(l, p.right, 2 * i + 2);
            if (p.right != null)
            {
                p.right.parent = p;
            }
        }

        return p;
    }

    public void createLevelOrder(E[] arr)
    {
        root = createLevelOrderHelper(arr, root, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i)
    {
        // TODO
        if (i < arr.length && arr[i] != null)
        {
            p = createNode(arr[i], null, null, null);
            size++;

            p.left = createLevelOrderHelper(arr, p.left, 2 * i + 1);
            if (p.left != null)
            {
                p.left.parent = p;
            }

            p.right = createLevelOrderHelper(arr, p.right, 2 * i + 2);
            if (p.right != null)
            {
                p.right.parent = p;
            }
        }

        return p;
    }

    private int[] diameterHelper(Position<E> p)
    {
        if (p == null)
        {
            return new int[]{-1, 0}; // {height, diameter}
        }

        int[] left = diameterHelper(left(p));
        int[] right = diameterHelper(right(p));

        int myHeight = 1 + Math.max(left[0], right[0]);
        int pathThroughRoot = left[0] + right[0] + 2; //path through this node
        int myDiameter = Math.max(pathThroughRoot, Math.max(left[1], right[1]));

        return new int[]{myHeight, myDiameter};
    }

    public int diameter()
    {
        if (isEmpty())
        {
            return 0;
        }
        return diameterHelper(root())[1];
    }

    public String toBinaryTreeString()
    {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    /**
     * Nested static class for a binary tree node.
     */
    //NOTE: Nodes are also Positions because of polymorphism, that's why the return type for a lot of these is
    //Position<E>, bc you can return a Node<E> too
    public static class Node<E> implements Position<E>
    {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r)
        {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement()
        {
            return element;
        }

        // modifiers
        public void setElement(E e)
        {
            element = e;
        }

        public Node<E> getLeft()
        {
            return left;
        }

        public void setLeft(Node<E> n)
        {
            left = n;
        }

        public Node<E> getRight()
        {
            return right;
        }

        public void setRight(Node<E> n)
        {
            right = n;
        }

        public Node<E> getParent()
        {
            return parent;
        }

        public void setParent(Node<E> n)
        {
            parent = n;
        }

        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            if (element == null)
            {
                sb.append("\u29B0");
            }

            else
            {
                sb.append(element);
            }
            return sb.toString();
        }
    }

}
