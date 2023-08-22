package application;

public interface StackADT {
	void push(int x); //adds value top list appropriately for a stack
	int pop();//remove and return value from list
	boolean isEmpty(); //returns true if the stack is empty
	int size(); //returns number of items in stack
	boolean isFull();
	void display();
}
