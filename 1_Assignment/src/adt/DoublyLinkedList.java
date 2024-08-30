package adt;

import entity.Donor;
import java.util.Comparator;
import java.util.NoSuchElementException;
/**
 *
 * @author yanqi
 */

public class DoublyLinkedList<T extends Comparable<T>> implements ListInterface<T> {
    
    private Node head;
    private Node tail;
    private int numberOfEntries;
    
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        private Node(T data) {
            this.data = data;
        }
        
        private Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
    
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.numberOfEntries = 0;
    }
    
    @Override
    public boolean addFront(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        newNode.next = head;
        head = newNode;
        numberOfEntries++;
        
        return true;
    }
    
    @Override
    public boolean addBack(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        numberOfEntries++;
        
        return true;
    }
      
    @Override
    public T get(int position) {
        T result = null;
        Node current = head;
        
        if (isEmpty()) {
            return null;
        } else {
            if (position >= 1 && position <= numberOfEntries) {
                position--;
                
                for (int i = 0; i < position; i++) {
                    current = current.next;
                } 
                result = current.data;
            }
        }
        return result;
    }
    
    @Override
    public T sortData(int position, Comparator<T> comparator) {
        T result = null;
        boolean isSwapped;
        
        if (isEmpty()) {
            return null;
        } else {
            do {
                isSwapped = false;
                Node current = head;
                if (position >= 1 && position <= numberOfEntries) {
                    position--;
                    for (int i = 0; i < position; i++) {
                        if (comparator.compare(current.data, current.next.data) > 0) {
                            T temp = current.data;
                            current.data = current.next.data;
                            current.next.data = temp;

                            isSwapped = true;
                        }
                        current = current.next;
                    }
                }
                result = current.data;
            } while (isSwapped);
        }

        return result;
    }
    
    @Override
    public boolean modify(int position, T newEntry) {
        if (isEmpty()) {
            return false;
        } else {
            Node current = head;
            position--;
            for (int i = 0; i < position; i++) {
                current = current.next;
            }
            if (current != null) {
                current.data = newEntry;
            }
        }
        return true;
    }
   
    @Override
    public boolean removeFront(){
        Node current = head;
        
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (head == tail) { // last node
            tail = null;
        } else {
            head.next.prev = null; 
        }
        head = head.next;
        current.next = null;
        numberOfEntries--;
        
        return true;
    }
    
    @Override
    public boolean removeBack(){
        Node current = tail;
        
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (head == tail) { // last node
            head = null;
        } else {
            tail.prev.next = null; 
        }
        tail = tail.prev;
        current.prev = null;
        numberOfEntries--;
        
        return true;
    }
    
    @Override
    public T remove(int position) {
        T result = null;
        if (isEmpty()) {
            return result;
        }
        if (position >= 1 && position <= numberOfEntries) {
            position--;
            
            Node current = head;
            
            for (int i = 0; i < position; i++) {
                current = current.next;
            }
            result = current.data;
            
            if (current == head) {
                removeFront();
            } else if (current == tail) {
                removeBack();
            } else {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                numberOfEntries--;
            }
        }
        return result;
    }
    
    @Override
    public T remove(T anEntry) {
        Node current = head;
        T result = null;
        
        if (isEmpty()) {
            return result;
        } else {
            if (current != null) {
                if (current.data.equals(anEntry)) {
                    result = current.data;
                }
                current = current.next;
            } else {
                return null;
            }
            if (current == head) {
                removeFront();
            } else if (current == tail) {
                removeBack();
            } else {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                numberOfEntries--;
            }
        }
        return result;
    }

    @Override
    public final void clear() {
        head = null;
        tail = null;
        numberOfEntries = 0;
  }
    
    @Override
    public int getNumberOfEntries(){
        return numberOfEntries;
    }
    
    @Override
    public boolean isEmpty() {
        return (numberOfEntries == 0);
    }
    
    @Override
    public boolean isFull() {
        return false;
    }
}

