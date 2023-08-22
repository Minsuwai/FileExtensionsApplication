package application;

public class MyLinkedStack implements StackADT {
    private IntLinkedList list;

    public MyLinkedStack() {
        list = new IntLinkedList();
    }

    @Override
    public void push(int x) {
        list.addToFront(x);
    }

    @Override
    public int pop() {
        if (!isEmpty()) {
            return list.removeFromFront();
        } else {
            System.out.println("Stack is empty. Cannot pop.");
            return -1; // You can choose a specific value to indicate an error
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isFull() {
        return false; // Linked list implementation doesn't have a fixed size
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void display() {
        list.displayList();
    }

    public static void main(String[] args) {
        linkedstackdriver();
    }

    static void linkedstackdriver() {
        MyLinkedStack astack = new MyLinkedStack();
        System.out.println("testing Stack");
        System.out.println("testing is empty " + astack.isEmpty());
        for (int i = 1; i < 6; i++) {
            astack.push(i);
        }
        System.out.println("num values in stack: " + astack.size());
        astack.display();
        System.out.println("popping value " + astack.pop());
        System.out.println("value 5 should have been removed");
        astack.display();
    }
}
