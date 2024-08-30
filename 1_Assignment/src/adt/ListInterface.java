package adt;

import java.util.Comparator;

/**
 *
 * @author yanqi
 */

public interface ListInterface<T extends Comparable<T>> {
    public boolean addFront(T newEntry);
    public boolean addBack(T newEntry);
    public T get(int position);
    public T sortData(int position, Comparator<T> comparator);
    public boolean modify(int position, T newEntry);
    public boolean removeFront();
    public boolean removeBack();
    public T remove(int position);
    public T remove(T anEntry);
    public void clear();
    public int getNumberOfEntries();
    public boolean isEmpty();
    public boolean isFull();
}
