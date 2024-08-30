package entity;

import java.util.Objects;
import java.util.Comparator;
import adt.DoublyLinkedList;
import adt.ListInterface;

/**
 *
 * @author yanqi
 */
public class Donor implements Comparable<Donor> {

    private String id;
    private String name;
    private char gender;
    private String number;
    private String category;
    
    private ListInterface<Donation> monetary = new DoublyLinkedList<>();
    private ListInterface<Donation> food = new DoublyLinkedList<>();
    private ListInterface<Donation> necessities = new DoublyLinkedList<>();
    
    public Donor() {
    }

    public Donor(String id, String name, char gender , String number, String category) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.number = number;
        this.category = category;
        this.monetary = new DoublyLinkedList<>();
        this.food = new DoublyLinkedList<>();
        this.necessities = new DoublyLinkedList<>();
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public char getGender() {
        return gender;
    }
    
    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }

    public ListInterface<Donation> getMonetary() {
        return monetary;
    }
    
    public void setMonetary(ListInterface<Donation> monetary) {
        this.monetary = monetary;
    }
    
    public ListInterface<Donation> getFood() {
        return food;
    }
    
    public void setFood(ListInterface<Donation> food) {
        this.food = food;
    }
    
    public ListInterface<Donation> getNecessities() {
        return necessities;
    }
    
    public void setNecessities(ListInterface<Donation> necessities) {
        this.necessities = necessities;
    }
    
    
    // Methods to add donations to respective categories
    public void addMonetary(Donation donation) {
        this.monetary.addBack(donation);
    }
    
    public void addFood(Donation donation) {
        this.food.addBack(donation);
    }
    
    public void addNecessities(Donation donation) {
        this.necessities.addBack(donation);
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Donor other = (Donor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Donor donor) {
        return id.compareTo(donor.id);  
    }
    
    // Comparator for certain criteria
    
    public static class IdComparator implements Comparator<Donor> {
        @Override
        public int compare (Donor donor1, Donor donor2) {
            return donor1.getId().compareTo(donor2.getId());
        }
    }
    
    public static class NameComparator implements Comparator<Donor> {
        @Override
        public int compare (Donor donor1, Donor donor2) {
            return donor1.getName().compareTo(donor2.getName());
        }
    }
    
    public static class GenderComparator implements Comparator<Donor> {
        @Override
        public int compare (Donor donor1, Donor donor2) {
            return Character.compare(donor1.getGender(),(donor2.getGender()));
        }
    }
    
    public static class CategoryComparator implements Comparator<Donor> {
        @Override
        public int compare (Donor donor1, Donor donor2) {
            return donor1.getCategory().compareTo(donor2.getCategory());
        }
    }

    @Override
    public String toString() {
        return String.format("| %-7s | %-22s |    %-5c | %-16s | %-16s |\n", id, name, gender, number, category);
    }
}
