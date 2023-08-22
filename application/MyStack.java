package application;

public class MyStack implements StackADT {
    static int[] arr = new int[5];
    static int topIndex = 0;

    @Override
    public void push(int x) {
        if (!isFull()) {
            arr[topIndex] = x;
            topIndex++;
        } else {
            System.out.println("Stack is full. Cannot push " + x);
        }
    }

    @Override
    public int pop() {
        if (!isEmpty()) {
            int poppedValue = arr[--topIndex];
            arr[topIndex] = 0;
            return poppedValue;
        } else {
            System.out.println("Stack is empty. Cannot pop.");
            return -1; // You can choose a specific value to indicate an error
        }
    }

    @Override
    public int size() {
        return topIndex;
    }

    @Override
    public boolean isFull() {
        return topIndex >= arr.length;
    }

    @Override
    public boolean isEmpty() {
        return topIndex == 0;
    }

    @Override
    public void display() {
        System.out.println("Stack elements:");
        for (int i = topIndex - 1; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) {
        stackdriver();
    }

    static void stackdriver() {
        MyStack astack = new MyStack();
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
