package control;

import adt.DoublyLinkedList;
import adt.ListInterface;
import boundary.DonorManagementUI;
import dao.Initializer;
import entity.Donor;
import entity.Donation;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import utility.MessageUI;
import java.util.Scanner;

/**
 *
 * @author yanqi
 */

public class DonorManagement {
    private ListInterface<Donor> donorList = new DoublyLinkedList<>();
    private ListInterface<Donation> donationList = new DoublyLinkedList<>();
    private Initializer init = new Initializer();
    private DonorManagementUI donorUI = new DonorManagementUI();
    
    Scanner scanner = new Scanner(System.in);
    
    public DonorManagement() {
        donorList = init.initializer();
    }
    
    public void runDonorManagement() {
        int choice = 0;
        
        do {
            choice = donorUI.menuChoice();
            switch(choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                    
                case 1:
                    donorUI.clearScr();
                    displayDonors();
                    
                    char yon;
                    do {
                        System.out.print("\n\nView sorted donor list? : ");
                        yon = scanner.nextLine().toUpperCase().charAt(0);
                        if (yon != 'Y' && yon != 'N') {
                            System.out.println("\nInvalid input. Please try again.\n");
                        } else if (yon == 'Y') {
                            int validate;
                            do {
                                validate = 0;
                                System.out.print("""
                                                   ======================
                                                   |     Sorted by:     | 
                                                   ======================
                                                   | 1) Donor ID        |
                                                   | 2) Donor Name      |
                                                   | 3) Gender          |
                                                   | 4) Donor Category  |
                                                   =======================

                                                   Please enter your choice: """);
                                int sortChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch(sortChoice) {
                                    case 1:
                                        displaySortId();
                                        break;
                                    case 2:
                                        displaySortName();
                                        break;
                                    case 3:
                                        displaySortGender();
                                        break;
                                    case 4:
                                        displaySortCategory();
                                        break;
                                    default:
                                        validate++;
                                        System.out.println("\nInvalid choice. Please try again.\n");
                                }
                            } while (validate != 0);
                        }
                    } while (yon != 'N');
                    break;
                    
                case 2:
                    donorUI.clearScr();
                    displayDonors();
                    System.out.println("\n");
                    addNewDonor();
                    donorUI.pause();
                    break;
                    
                case 3:
                    donorUI.clearScr();
                    searchDonorDetails();
                    donorUI.pause();
                    break;
                    
                case 4:
                    donorUI.clearScr();
                    displayDonors();
                    updateDonor();
                    donorUI.pause();
                    break;
                    
                case 5:
                    donorUI.clearScr();
                    displayDonors();
                    removeDonor();
                    donorUI.pause();
                    break;
                    
                case 6:
                    donorUI.clearScr();
                    filterDonors();
                    donorUI.pause();
                    break;
                    
                case 7:
                    donorUI.clearScr();
                    categorizedPublic();
                    categorizedPrivate();
                    categorizedGovernment();
                    donorUI.pause();
                    break;
                    
                case 8:
                    donorUI.clearScr();
                    report();
                    donorUI.pause();
                    break;
                    
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }
    
    public void displaySortId() {
        donorUI.listAllDonors(sortedById());
    }
    
    public void displaySortName() {
        donorUI.listAllDonors(sortedByName());
    }
    
    public void displaySortGender() {
        donorUI.listAllDonors(sortedByGender());
    }
    
    public void displaySortCategory() {
        donorUI.listAllDonors(sortedByCategory());
    }

    public ListInterface<Donor> getAllDonors() {
        ListInterface<Donor> donor = new DoublyLinkedList<>();
        for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
            donor.addBack(donorList.get(i));
        }
        return donor;
    }
    
    public ListInterface<Donor> sortedById() {
        ListInterface<Donor> sortedList = new DoublyLinkedList<>();
        Comparator<Donor> comparator = new Donor.IdComparator();
        
        for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
            sortedList.addBack(donorList.get(i));
        }
        
        for (int i = 1; i <= sortedList.getNumberOfEntries(); i++) {
            sortedList.sortData(i, comparator);
        }
       
        return sortedList;
    }
    
    public ListInterface<Donor> sortedByName() {
        ListInterface<Donor> sortedList = new DoublyLinkedList<>();
        Comparator<Donor> comparator = new Donor.NameComparator();
        
        for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
            sortedList.addBack(donorList.get(i));
        }
        
        for (int i = 1; i <= sortedList.getNumberOfEntries(); i++) {
            sortedList.sortData(i, comparator);
        }
       
        return sortedList;
    }
    
    public ListInterface<Donor> sortedByGender() {
        ListInterface<Donor> sortedList = new DoublyLinkedList<>();
        Comparator<Donor> comparator = new Donor.GenderComparator();
        
        for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
            sortedList.addBack(donorList.get(i));
        }
        
        for (int i = 1; i <= sortedList.getNumberOfEntries(); i++) {
            sortedList.sortData(i, comparator);
        }
       
        return sortedList;
    }
    
    public ListInterface<Donor> sortedByCategory() {
        ListInterface<Donor> sortedList = new DoublyLinkedList<>();
        Comparator<Donor> comparator = new Donor.CategoryComparator();
        
        for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
            sortedList.addBack(donorList.get(i));
        }
        
        for (int i = 1; i <= sortedList.getNumberOfEntries(); i++) {
            sortedList.sortData(i, comparator);
        }
       
        return sortedList;
    }
    
    public void displayDonors() {
        donorUI.listAllDonors(getAllDonors());
    }
    
    public void addNewDonor() {
        System.out.print("""
                         Adding new donor
                         ----------------
                         """);
        
        Donor newDonor =  donorUI.inputDonorDetails();
        
        if (donorUI.confirmMsg("add")) {
            donorList.addBack(newDonor);
            donorUI.successMsg("added");
        }
    }

    public void searchDonorDetails() {
        String id = donorUI.searchDonor();
        boolean donorFound = false;

        if (id != null) {
            for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
                Donor donor = donorList.get(i);
                if (donor.getId().toLowerCase().equals(id.toLowerCase())) {
                    donorFound = true;
                    donorUI.printDonorDetails(donor);
                    break;
                }
            } 
            if (donorFound == false) {
                System.out.println("\nNo record found.\n");
            }
        } else {
            System.out.println("\nInvalid ID.\n");
        }
    }
    
    public void updateDonation(Donor donor) {
        char yon = 0;
        int validate;
        
        do {
            validate = 0;
            
            System.out.println("\nChoose the donation type to update:");
            System.out.println("-----------------------------------");
            System.out.println(" 1) Monetary");
            System.out.println(" 2) Food");
            System.out.println(" 3) Necessities");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            ListInterface<Donation> donationType = null;

            switch (choice) {
                case 1:
                    donationType = donor.getMonetary();
                    break;
                case 2:
                    donationType = donor.getFood();
                    break;
                case 3:
                    donationType = donor.getNecessities();
                    break;
                default:
                    validate++;
                    System.out.println("\nInvalid choice. Please try again. (Options: 1 - 3)\n");
                    continue;
            }

            if (donationType.getNumberOfEntries() == 0) {
                System.out.println("\nNo donations of this type to update.\n");
                System.out.print("Do you want to add new donation of this type? (Y/N): ");
                char addEmpty = scanner.next().toUpperCase().charAt(0);
                System.out.println();
                
                char addMore;
                if (addEmpty == 'Y') {
                    do{
                        Donation newDonation = donorUI.inputDonation();
                        donationType.addBack(newDonation);
                        System.out.println("\nDonation added successfullly.\n");
                        System.out.print("\nContinue to add more donation? (Y/N) :\n");
                        addMore = scanner.next().toUpperCase().charAt(0);
                    } while (addMore == 'Y');
                    break;
                }else {
                    System.out.println("\nNo donation added.");
                }

            } else {
                for (int i = 1; i <= donationType.getNumberOfEntries(); i++) {
                    Donation donation = donationType.get(i);
                    System.out.println("\n");
                    System.out.println("======================================================");
                    System.out.println("|         Donation         |    Amount    |    Date   ");
                    System.out.println("======================================================");
                    System.out.printf ("|%d) %-22s | RM%-10.2f | %-12s |\n", i, donation.getDesc(), donation.getAmount(), donation.getDate());
                    System.out.println("=======================================================");
                }
                System.out.print("\nEnter the number of the donation to update or '0' to add a new donation: ");
                int donationChoice = scanner.nextInt();
                scanner.nextLine();

                if (donationChoice > 0 && donationChoice <= donationType.getNumberOfEntries()) {
                    Donation selectedDonation = donationType.get(donationChoice);
                    System.out.println("\nUpdating donation: " + selectedDonation.getDesc());
                    System.out.println("---------------------");
                    
                    Donation updatedDonation = donorUI.inputDonation();
                    selectedDonation.setDesc(updatedDonation.getDesc());
                    selectedDonation.setAmount(updatedDonation.getAmount());
                    selectedDonation.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))); 

                } else if (donationChoice == 0) {
                    char addMore;
                    do {
                        Donation newDonation = donorUI.inputDonation();
                        donationType.addBack(newDonation);
                        System.out.print("Continue to add more donation? (Y/N) :");
                        addMore = scanner.nextLine().toUpperCase().charAt(0);
                        if(addMore != 'Y' && addMore != 'N') {
                            System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                        }
                    } while (addMore == 'Y');
                } else {
                    System.out.println("Invalid choice.");
                }
            }
            System.out.print("\nDo you want to update another donation? (Y/N): ");
            yon = scanner.nextLine().toUpperCase().charAt(0);
        } while (yon == 'Y' || validate != 0);
    }
    
    public void updateDonor() {
        String id = donorUI.operationId("update");
        Donor result = null;
        int position = 0;
        
        for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
            Donor current = donorList.get(i);
            if (current.getId().equals(id)) {
                result = current;
                position = i;
                break;
            }
        }
        
        if (result != null) {
            donorUI.clearScr();
            donorUI.printDonorDetails(result);
            System.out.println("\n\nUpdate for ID " + result.getId());
            System.out.println("-----------------------");
            
            result.setName(donorUI.inputName());
            result.setGender(donorUI.inputGender());
            result.setNumber(donorUI.inputContactNumber());
            result.setCategory(donorUI.inputCategory());
            
            char yon = 0;
            do {
                System.out.print("\nDo you want to update donations? (Y/N): ");
                try{
                    yon = scanner.nextLine().toUpperCase().charAt(0);
                } catch (StringIndexOutOfBoundsException ex) {
                    yon = 'C';
                    System.out.println("\nInvalid input. Please enter 'Y' or 'N'.");
                }
                if (yon == 'Y') {
                    updateDonation(result);
                } else if (yon != 'N') {
                    System.out.println("\nInvalid input. Please enter 'Y' or 'N'.");
                }
            } while (yon != 'N' && yon != 'Y');

            if (donorUI.confirmMsg("update")) {
                donorList.modify(position, result);
                donorUI.successMsg("updated");
                System.out.println();
                donorUI.printDonorDetails(donorList.get(position));
                System.out.println();
            }
            
        } else {
            System.out.println("Record not found.");
        }
    }
   
    public void removeDonor() {
        String id = donorUI.operationId("remove");
        Donor result = null;
        int position = 0;
        
        for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
            Donor current = donorList.get(i);
            if (current.getId().equals(id)) {
                result = current;
                position = i;
                break;
            }
        }
        if (result != null) {
            donorUI.printDonorDetails(result);
            if (donorUI.confirmMsg("remove")) {
                donorList.remove(position);
                donorUI.successMsg("removed");
            }
        } else {
            System.out.println("Donor not found.");
        }
    }
    
    public void filterDonors(){
        int validate;
        
        do {
            validate = 0;
            int choice = donorUI.filterDonors();
            Donor result = new Donor();
            result = null;
            int count = 0;
            
            switch(choice) {
                case 1:
                    System.out.print("\nEnter donor name: ");
                    String name = scanner.nextLine();
                    for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
                        Donor current = donorList.get(i);
                        if ((current.getName().toLowerCase()).contains(name.toLowerCase())) {
                            result = current;
                            donorUI.printDonorDetails(result);
                            count++;
                        } 
                    }    
                    System.out.println(count + " record(s) found.");
                    break;

                case 2:
                    do {
                        validate = 0;
                        System.out.print("""
                                           Filter Gender By:
                                           =================
                                           1) M - Male
                                           2) F - Female

                                           """);
                        System.out.print("Enter your choice: ");
                        int choice2 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("\n");

                        switch(choice2) {
                            case 1:
                                for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
                                    Donor current = donorList.get(i);
                                    if (Character.toUpperCase(current.getGender()) == Character.toUpperCase('M')) {
                                        result = current;
                                        donorUI.printDonorDetails(result);
                                        count++;
                                    } 
                                }
                                System.out.println(count + " record(s) found.");
                                break;

                            case 2:
                                for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
                                    Donor current = donorList.get(i);
                                    if (Character.toUpperCase(current.getGender()) == Character.toUpperCase('F')) {
                                        result = current;
                                        donorUI.printDonorDetails(result);
                                        count++;
                                    }
                                }
                                System.out.println(count + " record(s) found.");
                                break;

                            default:
                                MessageUI.displayInvalidChoiceMessage();
                                validate++;
                        }
                    } while (validate != 0);
                    break;

                case 3:
                    do {
                        validate = 0;
                        System.out.print("""
                                           Filter Category By:
                                           ===================
                                           1) Public
                                           2) Private
                                           3) Government

                                           """);
                        System.out.print("Enter your choice: ");
                        int choice3 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("\n");

                        switch(choice3) {
                            case 1:
                                for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
                                    Donor current = donorList.get(i);
                                    if ((current.getCategory().toLowerCase()).equals(String.valueOf("Public").toLowerCase())) {
                                        result = current;
                                        donorUI.printDonorDetails(result);
                                        count++;
                                    } 
                                }
                                System.out.println(count + " record(s) found.");
                                break;

                            case 2:
                                for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
                                    Donor current = donorList.get(i);
                                    if ((current.getCategory().toLowerCase()).equals(String.valueOf("Private").toLowerCase())) {
                                        result = current;
                                        donorUI.printDonorDetails(result);
                                        count++;
                                    } 
                                }
                                System.out.println(count + " record(s) found.");
                                break;

                            case 3:
                                for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
                                    Donor current = donorList.get(i);
                                    if ((current.getCategory().toLowerCase()).equals(String.valueOf("Government").toLowerCase())) {
                                        result = current;
                                        donorUI.printDonorDetails(result);
                                        count++;
                                    } 
                                }
                                System.out.println(count + " record(s) found.");
                                break;

                            default:
                                MessageUI.displayInvalidChoiceMessage();
                                validate++;
                        }
                    } while (validate != 0);
                    break;

                default:
                    MessageUI.displayInvalidChoiceMessage();
                    validate++;
            }
        } while (validate != 0);
    }
    
    public void categorizedPublic() {
        Donor result = new Donor();
        result = null;
        int count = 0;

        System.out.println("");
        System.out.println("================================================================================================================");
        System.out.println("|                                              Public Donors                                                   |");
        System.out.println("================================================================================================================");
        System.out.println("|   ID    |       Donor Name       |  Gender  |  Contact Number  |     Category     |      Donations made      |");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        
        for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
            Donor current = donorList.get(i);
            if ((current.getCategory().toLowerCase()).equalsIgnoreCase("public")) {
                result = current;
                System.out.print(result); 
                count++;
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println(count + " of record(s) found.");
        System.out.println("\n\n");
    }
    
    public void categorizedPrivate() {
        Donor result = new Donor();
        result = null;
        int count = 0;

        System.out.println("");
        System.out.println("================================================================================================================");
        System.out.println("|                                              Private Donors                                                  |");
        System.out.println("================================================================================================================");
        System.out.println("|   ID    |       Donor Name       |  Gender  |  Contact Number  |     Category     |      Donations made      |");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        
        for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
            Donor current = donorList.get(i);
            if ((current.getCategory().toLowerCase()).equalsIgnoreCase("private")) {
                result = current;
                System.out.print(result); 
                count++;
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println(count + " of record(s) found.");
        System.out.println("\n\n");
    }
    
    public void categorizedGovernment() {
        Donor result = new Donor();
        result = null;
        int count = 0;

        System.out.println("");
        System.out.println("================================================================================================================");
        System.out.println("|                                            Government Donors                                                 |");
        System.out.println("================================================================================================================");
        System.out.println("|   ID    |       Donor Name       |  Gender  |  Contact Number  |     Category     |      Donations made      |");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        
        for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
            Donor current = donorList.get(i);
            if ((current.getCategory().toLowerCase()).equalsIgnoreCase("government")) {
                result = current;
                System.out.print(result); 
                count++;
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.println(count + " of record(s) found.");
        System.out.println("\n\n");
    }
 
    public void report() {
        donorUI.summaryReport(sortedById());
    }

    public static void DonorMain() {
        DonorManagement donorManagement = new DonorManagement();
        donorManagement.runDonorManagement();
    }
}
