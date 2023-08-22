package application;

public interface SetADT {
	void add(Object o); //Add an object to the set.
	void remove(Object o); //Removes object o from the set.
	void intersection(SetADT s); //sets this set to the intersection of itself and s.
	void difference(SetADT s); //sets this set to the difference between itself and s.
	int size(); //returns the number of objects in the set.
	boolean isEmpty(); //returns true if size =0, else false.
}
