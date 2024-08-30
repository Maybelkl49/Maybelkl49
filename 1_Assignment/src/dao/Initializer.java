package dao;

import adt.DoublyLinkedList;
import adt.ListInterface;
import entity.Donor;
import entity.Donation;

/**
 *
 * @author yanqi
 */

public class Initializer {
    
    //  Method to return a collection of with hard-coded entity values
    public ListInterface<Donor> initializer() {
        Donor donor1 = new Donor("D1001", "Ali", 'M', "0115800985", "Private");
        Donor donor2 = new Donor("D1002", "Pen", 'M', "01385499401", "Government");
        Donor donor3 = new Donor("D1003", "Pencil", 'F', "0134584065", "Private");
        Donor donor4 = new Donor("D1004", "Notepad", 'M', "0122315704", "Public");
        Donor donor5 = new Donor("D1005", "Ruler", 'F', "0199448523", "Government");
        Donor donor6 = new Donor("D1006", "Rectangle", 'F', "0112313123", "Public");
        Donor donor7 = new Donor("D1007", "Guohui", 'M', "01545747423", "Private");
        Donor donor8 = new Donor("D1008", "Nigga", 'F', "0177944325", "Private");
        Donor donor9 = new Donor("D1009", "Madara", 'F', "0156236852", "Government");
        Donor donor10 = new Donor("D1010", "Onichann", 'M', "0122111567", "Private");
        
        Donation monetary1 = new Donation("Monetary", "Cash", 50.00, "01-08-2024");
        Donation monetary2 = new Donation("Monetary", "Cash", 40.00, "16-08-2024");
        Donation monetary3 = new Donation("Monetary", "Bankin", 50.00, "19-08-2024");
        Donation monetary4 = new Donation("Monetary", "Bankin", 100.00, "21-08-2024");
        Donation monetary5 = new Donation("Monetary", "Cash", 50.00, "03-08-2024");
        
        Donation food1 = new Donation("Food", "Mineral water", 12, "02-08-2024");
        Donation food2 = new Donation("Food", "Bread", 10, "13-08-2024");
        Donation food3 = new Donation("Food", "Canned food", 4, "24-08-2024");
        Donation food4 = new Donation("Food", "Fruit", 5, "18-03-2024");
        Donation food5 = new Donation("Food", "Meat", 8, "21-02-2024");
        
        Donation necessities1 = new Donation("Necessities", "Clothes", 16, "11-08-2024");
        Donation necessities2 = new Donation("Necessities", "Towel", 2, "03-06-2024");
        Donation necessities3 = new Donation("Necessities", "Shirt", 2, "10-08-2024");
        Donation necessities4 = new Donation("Necessities", "Toothbrush", 7, "24-08-2024");
        Donation necessities5 = new Donation("Necessities", "Pants", 3, "06-08-2024");
        
        donor1.addMonetary(monetary1);
        donor1.addMonetary(monetary2);
        donor1.addNecessities(necessities4);
        donor2.addFood(food1);
        donor2.addNecessities(necessities5);
        donor3.addFood(food4);
        donor3.addNecessities(necessities1);
        donor4.addMonetary(monetary2);
        donor4.addFood(food5);
        donor5.addFood(food2);
        donor5.addNecessities(necessities1);
        donor6.addMonetary(monetary5);
        donor6.addNecessities(necessities2);
        donor7.addMonetary(monetary3);
        donor8.addMonetary(monetary4);
        donor8.addFood(food3);
        donor9.addNecessities(necessities3);
        donor10.addFood(food2);
        
        ListInterface<Donor> donorList = new DoublyLinkedList<>();
        donorList.addBack(donor1);
        donorList.addBack(donor2);
        donorList.addBack(donor3);
        donorList.addBack(donor4);
        donorList.addBack(donor5);
        donorList.addBack(donor6);
        donorList.addBack(donor7);
        donorList.addBack(donor8);
        donorList.addBack(donor9);
        donorList.addBack(donor10);
        
        return donorList;
    }

    public static void main(String[] args) {
        Initializer donorInit = new Initializer();
        ListInterface<Donor> donorList = donorInit.initializer();
    }
}
