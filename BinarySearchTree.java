package demo1;

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
	 // This method mainly calls deleteRec()
	    /* A recursive function to insert a new key in BST */
	    Node delete(Node root, int key)
	    {
	        /* Base Case: If the tree is empty */
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
 /*Count the number of nodes in Linked List */
        int n = countNodes(head);
 
        /* Construct BST */
        return sortedListToBSTRecur(n);
    }
 
    /* The main function that constructs balanced BST and
       returns root of it.
       n  --> No. of nodes in the Doubly Linked List */
    Node sortedListToBSTRecur(int n)
    {
        /* Base Case */
        if (n <= 0)
            return null;
 
        /* Recursively construct the left subtree */
        Node left = sortedListToBSTRecur(n / 2);
 
        /* head_ref now refers to middle node,
           make middle node as root of BST*/
        Node root = head;
 
        // Set pointer to left subtree
        root.prev = left;
 
        /* Change head pointer of Linked List for parent
           recursive calls */
        head = head.next;
 
        /* Recursively construct the right subtree and link it
           with root. The number of nodes in right subtree  is
           total nodes - nodes in left subtree - 1 (for root) */
        root.next = sortedListToBSTRecur(n - n / 2 - 1);
 
        return root;
    }
 
    /* UTILITY FUNCTIONS */
    /* A utility function that returns count of nodes in a
       given Linked List */
    int countNodes(Node head)
    {
        int count = 0;
        Node temp = head;
        while (temp != null)
        {
            temp = temp.next;
            count++;
        }
        return count;
    }
 
    /* Function to insert a node at the beginging of
       the Doubly Linked List */
    void push(int new_data)
    {
        /* allocate node */
        Node new_node = new Node(new_data);
 
        /* since we are adding at the begining,
           prev is always NULL */
        new_node.prev = null;
 
        /* link the old list off the new node */
        new_node.next = head;
 
        /* change prev of head node to new node */
        if (head != null)
            head.prev = new_node;
 
        /* move the head to point to the new node */
        head = new_node;
    }
 
    /* Function to print nodes in a given linked list */
    void printList()
    {
        Node node = head;
        while (node != null)
        {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
 
    /* A utility function to print preorder traversal of BST */
    void preOrder(Node node)
    {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrder(node.prev);
        preOrder(node.next);
    }
 
    /* Drier program to test above functions */
    public static void main(String[] args)
    {
        LinkedList llist = new LinkedList();
 
        /* Let us create a sorted linked list to test the functions
           Created linked list will be 7->6->5->4->3->2->1 */
        llist.push(7);
        llist.push(6);
        llist.push(5);
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);
 
        System.out.println("Given Linked List ");
        llist.printList();
 
        /* Convert List to BST */
        Node root = llist.sortedListToBST();
        System.out.println("");
        System.out.println("Pre-Order Traversal of constructed BST ");
        llist.preOrder(root);
    }
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
	        
	        System.out.println("\nThe key 80 can be reached in the following manner: ");tree.search(root,80);
	        // print inorder, preorder, postorder traversal of the BS
	      
	      
	       System.out.print("\nThe tree in inorder traversal : ");tree.inorder(root);
	       System.out.print("\nThe tree in preOrder traversal : ");tree.preOrder(root);
	       System.out.print("\nThe tree in postOrder traversal : ");tree.postOrder(root);
	       System.out.print("\nLowest common ancestors of 30 and 20 :");tree.lca(root, 30, 20);
	       System.out.print("\nThe binary tree after deletion of node with key 80 is :");tree.delete(root,80);tree.inorder(root);
	    }
	}
	
