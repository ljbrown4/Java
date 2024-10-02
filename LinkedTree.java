/*
 * LinkedTree.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: 
 *     username:
 */

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }
    
    /*
     * Prints the keys of the tree in the order given by a preorder traversal.
     * Invokes the recursive preorderPrintTree method to do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a postorder traversal.
     * Invokes the recursive postorderPrintTree method to do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }
    
    /*
     * Prints the keys of the tree in the order given by an inorder traversal.
     * Invokes the recursive inorderPrintTree method to do the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }
    
    /* 
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;
        
        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();
        
        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }
        
        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();
            
            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");
            
            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }
    
    /*
     * Searches for the specified key in the tree.
     * If it finds it, it returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }
    
    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }
    
    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }
    
    /*
     * FOR TESTING: Processes the integer keys in the specified array from 
     * left to right, adding a node for each of them to the tree. 
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }
    
    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key    
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }
    
    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted. 
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as 
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            
            // Replace toDelete's key and data with those of the 
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;
            
            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if it has no children
            }
            
            if (toDelete == root) {
                root = toDeleteChild;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            } else {
                parent.right = toDeleteChild;
            }
        }
    }
    public int depth(int key) {
        int depth = 0;
        Node iter = root;
        while (iter != null) {
            if (key == iter.key) {
                return depth;
            } else if (key > iter.key) {
                iter = iter.right;
            } else {
                iter = iter.left;
            }
            depth ++;
        }
        return -1;
    }

    private static int countEvensInTree(Node root) {
        if (root == null) {
            return 0;
        }
        int left = countEvensInTree(root.left);
        int right = countEvensInTree(root.right);
        if (root.key % 2 == 0) {
            return 1 + left + right;
        } else {
            return left + right;
        }    
    }
    public int countEvens() {
        return countEvensInTree(root);
    }
    public int deleteMax() {
        if (root == null) {
            return -1;
        }
        Node parent = null;
        Node current = root;
        while (current.right != null) {
            parent = current;
            current = current.right;
        }
        int delete = current.key;

        // case 1: the root node is the largest element in the tree - make the new root the left child of the root
        if (current == root) {
            root = current.left;
        }

        // case 2: a given node in the tree is the largest that is not the root, just make that node = node.left 
        if (parent != null) {
            parent.right = current.left;
        }
        return delete;
    }
    public static void main(String[] args) {
        /*
         * Add at least two unit tests for each method that you write.
         * Test a variety of different cases. 
         * Follow the same format that we used in the previous problem.
         * We have given you some preliminary code for the first test below.
         */

         System.out.println("--- Testing depth ---");
         System.out.println();
         System.out.println("(0) Testing tree.depth(13) on tree from Problem 6, ...");
         try {
             LinkedTree tree = new LinkedTree();
             int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
             tree.insertKeys(keys);
 
             // add the rest of the test here
             int results = tree.depth(13); 
             int expected = 2;
             System.out.println("actual results:");
             System.out.println(results);
             System.out.println("expected results:");
             System.out.println(expected);
             System.out.print("MATCHES EXPECTED RESULTS?: ");
             System.out.println(results == expected);
         
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
                            
         System.out.println();    // include a blank line between tests
 
         // Put your other tests below.   
         System.out.println("(1) Testing tree.depth(47) on tree from Problem 6, ...");
         try {
             LinkedTree tree = new LinkedTree();
             int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
             tree.insertKeys(keys);
 
             // add the rest of the test here
             int results = tree.depth(47); 
             int expected = 3;
             System.out.println("actual results:");
             System.out.println(results);
             System.out.println("expected results:");
             System.out.println(expected);
             System.out.print("MATCHES EXPECTED RESULTS?: ");
             System.out.println(results == expected);
         
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
                            
         System.out.println();   
         
         System.out.println("(2) Testing tree.depth(56) on tree from Problem 6, ...");
         try {
             LinkedTree tree = new LinkedTree();
             int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
             tree.insertKeys(keys);
 
             // add the rest of the test here
             int results = tree.depth(50); 
             int expected = -1;
             System.out.println("actual results:");
             System.out.println(results);
             System.out.println("expected results:");
             System.out.println(expected);
             System.out.print("MATCHES EXPECTED RESULTS?: ");
             System.out.println(results == expected);
         
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
                            
         System.out.println();

         System.out.println("(3) Testing tree.countEvens");
         try {
             LinkedTree tree = new LinkedTree();
             int[] keys = {1,3,5,6,7};
             tree.insertKeys(keys);
 
             // add the rest of the test here
             int results = tree.countEvens(); 
             int expected = 1;
             System.out.println("actual results:");
             System.out.println(results);
             System.out.println("expected results:");
             System.out.println(expected);
             System.out.print("MATCHES EXPECTED RESULTS?: ");
             System.out.println(results == expected);
         
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
                            
         System.out.println();

         System.out.println("(4) Testing tree.countEvens");
         try {
             LinkedTree tree = new LinkedTree();
             int[] keys = {2,4,6,8,10};
             tree.insertKeys(keys);
 
             // add the rest of the test here
             int results = tree.countEvens(); 
             int expected = 5;
             System.out.println("actual results:");
             System.out.println(results);
             System.out.println("expected results:");
             System.out.println(expected);
             System.out.print("MATCHES EXPECTED RESULTS?: ");
             System.out.println(results == expected);
         
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
                            
         System.out.println();

         System.out.println("(5) Testing tree.countEvens");
         try {
             LinkedTree tree = new LinkedTree();
             int[] keys = {37, 27, 41, 13, 35, 53, 39, 47, 77};
             tree.insertKeys(keys);
 
             // add the rest of the test here
             int results = tree.countEvens(); 
             int expected = 0;
             System.out.println("actual results:");
             System.out.println(results);
             System.out.println("expected results:");
             System.out.println(expected);
             System.out.print("MATCHES EXPECTED RESULTS?: ");
             System.out.println(results == expected);
         
         } catch (Exception e) {
             System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
         }
                            
         System.out.println();
    }
}

