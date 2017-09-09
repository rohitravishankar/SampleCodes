package SampleCodes;

public class BinarySearchTree {
		 
	    /* Class containing left and right child of current node and key value*/
	    class Node {
	        int key;
	        Node left, right;
	 
	        public Node(int data) {
	            key = data;
	            left = right = null;
	        }
	    }
	 
	    static // Root of BST
	    Node root;
	 
	    // Constructor
	    BinarySearchTree() { 
	        root = null; 
	    }
	 
     
	    /* A recursive function to insert a new key in BST */
	    Node insert(Node root, int key) {
	 
	        /* If the tree is empty, return a new node */
	        if (root == null) {
	            root = new Node(key);
	            return root;
	        }
	 
	        /* Otherwise, recur down the tree */
	        if (key < root.key)
	            root.left = insert(root.left, key);
	        else if (key > root.key)
	            root.right = insert(root.right, key);
	 
	        /* return the (unchanged) node pointer */
	        return root;
	    }
	 
	 
	    // A utility function to do inorder traversal of BST
	    void inorder(Node root) {
	        if (root != null) {
	            inorder(root.left);
	            System.out.println(root.key);
	            inorder(root.right);
	        }
	    }
	    
	    // A utility function to do preorder traversal of BST
	    void preOrder(Node root) {
	        if (root != null) {
	            preOrder(root.left);
	            preOrder(root.right);
	            System.out.println(root.key);
	            
	        }
	    }
	    // A utility function to do postorder traversal of BST
	    void postOrder(Node root) {
	        if (root != null) {
	        	System.out.println(root.key);
	        	postOrder(root.left);
	            postOrder(root.right);
	                       
	        }
	    }
	    
	 // A utility function to search a given key in BST
	    public Node search(Node root, int key)
	    {
	        // Base Cases: root is null or key is present at root
	        if (root==null || root.key==key)
	            return root;
	     
	        // val is greater than root's key
	        if (root.key > key)
	            return search(root.left, key);
	     
	        // val is less than root's key
	        return search(root.right, key);
	    }
	 
	    /* A recursive function to insert a new key in BST */
	    Node delete(Node root, int key)
	    {
	        /* Base Case: If the t
	         * ree is empty */
	        if (root == null)  return root;
	 
	        /* Otherwise, recur down the tree */
	        if (key < root.key)
	            root.left = delete(root.left, key);
	        else if (key > root.key)
	            root.right = delete(root.right, key);
	 
	        // if key is same as root's key, then This is the node
	        // to be deleted
	        else
	        {
	            // node with only one child or no child
	            if (root.left == null)
	                return root.right;
	            else if (root.right == null)
	                return root.left;
	 
	            // node with two children: Get the inorder successor (smallest
	            // in the right subtree)
	            root.key = minValue(root.right);
	 
	            // Delete the inorder successor
	            root.right = delete(root.right, root.key);
	        }
	 
	        return root;
	    }
	    int minValue(Node root)
	    {
	        int minv = root.key;
	        while (root.left != null)
	        {
	            minv = root.left.key;
	            root = root.left;
	        }
	        return minv;
	    }
	    
	    int getLeafCount(Node node) 
	    {
	        if (node == null)
	            return 0;
	        if (node.left == null && node.right == null)
	            return 1;
	        else
	            return getLeafCount(node.left) + getLeafCount(node.right);
	    }
	    
	    	    Node lca(Node node, int n1, int n2) 
	    {
	        if (node == null)
	            return null;
	  
	        // If both n1 and n2 are smaller than root, then LCA lies in left
	        if (node.key > n1 && node.key > n2)
	            return lca(node.left, n1, n2);
	  
	        // If both n1 and n2 are greater than root, then LCA lies in right
	        if (node.key < n1 && node.key < n2) 
	            return lca(node.right, n1, n2);
	  
	        return node;
	    }
	    int maxDepth(Node node) 
	    {
	        if (node == null)
	            return 0;
	        else
	        {
	            /* compute the depth of each subtree */
	            int lDepth = maxDepth(node.left);
	            int rDepth = maxDepth(node.right);
	  
	            /* use the larger one */
	            if (lDepth > rDepth)
	                return (lDepth + 1);
	             else
	                return (rDepth + 1);
	        }
	    }
	int Ceil(Node node, int input) {
         
        // Base case
        if (node == null) {
            return -1;
        }
 
        // We found equal key
        if (node.key == input) {
            return node.key;
        }
 
        // If root's key is smaller, ceil must be in right subtree
        if (node.key < input) {
            return Ceil(node.right, input);
        }
 
        // Else, either left subtree or root has the ceil value
        int ceil = Ceil(node.left, input);
        return (ceil >= input) ? ceil : node.key;
    }
	 
	    // Driver Program to test above functions
	    public static void main(String[] args) {
	        BinarySearchTree tree = new BinarySearchTree();

	        tree.insert(root,50);
	        tree.insert(root,30);
	        tree.insert(root,20);
	        tree.insert(root,40);
	        tree.insert(root,70);
	        tree.insert(root,60);
	        tree.insert(root,80);
	        
	        System.out.print("\nLeaf Node count of a binary tree :");tree.getLeafCount(root);
	        System.out.println("\nThe lowest common ancestor to 60 & 80 is: ");tree.lca(root, 80, 60);
	        System.out.print("\nDepth of a binary tree :");tree.maxDepth(root);
	        
	        System.out.println("\nThe key 80 can be reached in the following manner: ");tree.search(root,80);
	        // print inorder, preorder, postorder traversal of the BS
	      
		for (int i = 0; i < 16; i++) {
       			     System.out.println(i + " " + tree.Ceil(root, i));
        	}
	       System.out.print("\nThe tree in inorder traversal : ");tree.inorder(root);
	       System.out.print("\nThe tree in preOrder traversal : ");tree.preOrder(root);
	       System.out.print("\nThe tree in postOrder traversal : ");tree.postOrder(root);
	       System.out.print("\nLowest common ancestors of 30 and 20 :");tree.lca(root, 30, 20);
	       System.out.print("\nThe binary tree after deletion of node with key 80 is :");tree.delete(root,80);tree.inorder(root);
	    }
	}
	
