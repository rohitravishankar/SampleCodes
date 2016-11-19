/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rohit Ravishankar
 */
// Linked List Class
class LinkedList {

    Node head;  // head of list 
    /* Node Class */

    class Node {

        int data;
        Node next;
        // Constructor to create a new node

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public void insertBeginning(int newData) {
        Node newNode = new Node(newData);
        newNode.next = head;
        head = newNode;
    }

    public void insertAfter(Node previousNode, int newData) {
        if (previousNode == null) {
            System.out.println("The given previous node cannot be null");
            return;
        }
        Node newNode = new Node(newData);
        newNode.next = previousNode.next;
        previousNode.next = newNode;
    }

    public void insertEnd(int newData) {
        Node newNode = new Node(newData);

        if (head == null) {
            head = new Node(newData);
            return;
        }
        newNode.next = null;

        Node last = head;
        while (last.next != null) {
            last = last.next;
        }

        last.next = newNode;
    }

    public void deleteNode(int key) {
        
        Node temp = head;
        Node prev = head;
        while(temp.data!=key){
            prev = temp;
            temp = temp.next;
        }
        prev.next = temp.next;
    }

    public void printList() {
        Node temporaryNode = head;
        while (temporaryNode != null) {
            System.out.println(temporaryNode.data);
            temporaryNode = temporaryNode.next;
        }
    }

    public int LengthLinkedList() {
        int countNumberOfNodes = 0;
        if (head == null) {
            return countNumberOfNodes;
        }
        Node temporaryNode = head;
        while (temporaryNode != null) {
            countNumberOfNodes++;
            temporaryNode = temporaryNode.next;
        }
        return countNumberOfNodes;
    }

    public void swapPositions(int x, int y) {
        Node temporaryNode1 = head, temporaryNode2 = head, previousNode1 = null, previousNode2 = null;
        if (head == null) {
            return;
        }
        if (x == y) {
            return;
        }
        while (temporaryNode1 != null && temporaryNode1.data != x) {
            previousNode1 = temporaryNode1;
            temporaryNode1 = temporaryNode1.next;
        }
        while (temporaryNode2 != null && temporaryNode2.data != y) {
            previousNode2 = temporaryNode2;
            temporaryNode2 = temporaryNode2.next;
        }
        if (temporaryNode1 == null || temporaryNode2 == null) {
            return;
        }

        // If x is not head of linked list
        if (previousNode1 != null) {
            previousNode1.next = temporaryNode2;
        } else //make y the new head
        {
            head = temporaryNode2;
        }

        // If y is not head of linked list
        if (previousNode2 != null) {
            previousNode2.next = temporaryNode1;
        } else // make x the new head
        {
            head = temporaryNode1;
        }

        // Swap next pointers
        Node temp = temporaryNode1.next;
        temporaryNode1.next = temporaryNode2.next;
        temporaryNode2.next = temp;


    }

    public void reverseList() {
        Node previousNode = null;
        Node currentNode = head;
        Node nextNode = null;
        while (currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        head = previousNode;
    }

    Node MergeLists(Node list1, Node list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.data < list2.data) {
            list1.next = MergeLists(list1.next, list2);
            return list1;
        } else {
            list2.next = MergeLists(list2.next, list1);
            return list2;
        }
    }

    public static void main(String[] args) {
        LinkedList llist = new LinkedList();

        llist.insertBeginning(7);
        llist.insertBeginning(1);
        llist.insertBeginning(3);
        llist.insertBeginning(2);
        llist.insertBeginning(5);

        System.out.println("\nCreated Linked list is:");
        llist.printList();

        // Delete node at position 4

        System.out.println("\nLinked List after Deletion at position 4:");
        llist.deleteNode(1);
        llist.printList();

        llist.swapPositions(2, 1);

        System.out.println("\nLinked List after swap:");
        llist.printList();

        System.out.println("\nNumber of nodes in the Linked List:");
        System.out.println(llist.LengthLinkedList());
    }
}
