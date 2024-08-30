package entity;

import java.util.Objects;

/**
 *
 * @author yanqi
 */
public class Donation implements Comparable<Donation> {

    private String type;
    private String desc;
    private double amount;
    private String date;
    
    public Donation() {
    }

    public Donation(String type, String desc, double amount, String date) {
        this.type = type;
        this.desc = desc;
        this.amount = amount;
        this.date = date;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        final Donation other = (Donation) obj;
        if (!Objects.equals(this.desc, other.desc)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Donation donation) {
        return type.compareTo(donation.type);   //compare Type
    }
    
    @Override
    public String toString() {
        return String.format("| %-10s | %-10s | %-8s | %-10s |\n", type, desc, amount, date);
    }
}
