package application;

public class IntLinkedList {
    private Node head;
    private int size;

    public IntLinkedList() {
        head = null;
        size = 0;
    }

    public void addToFront(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public int removeFromFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Linked list is empty");
        }

        int data = head.data;
        head = head.next;
        size--;
        return data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void displayList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
