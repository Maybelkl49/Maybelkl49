package boundary;

import adt.DoublyLinkedList;
import adt.ListInterface;
import dao.Initializer;
import entity.Donor;
import entity.Donation;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

/**
 *
 * @author yanqi
 */

public class DonorManagementUI {
    private ListInterface<Donor> donorList = new DoublyLinkedList<>();
    private Initializer donorInit = new Initializer();
    
    Scanner scanner = new Scanner(System.in);
    
    public DonorManagementUI() {
        donorList = donorInit.initializer();
    }

    public int menuChoice() {
        int choice = 0;
        int validate;
        do
        {
            validate = 0;
            clearScr();
            System.out.println("""
                               ====================================
                               \tDonor Management Menu
                               ====================================
                               
                               1) List all donors
                               2) Add a new Donor
                               3) Search Donor Details
                               4) Update Donor Details
                               5) Remove a Donor
                               6) Filter donors
                               7) Categorized donors
                               8) Generate summary reports
                               0) Exit
                               """);
        
            try {
                System.out.print("\nEnter your choice: ");
                String choiceChar = scanner.nextLine();
                choice = Integer.parseInt(choiceChar);
            } catch (NumberFormatException ex) {
                validate++;
                System.out.println("\nInvalid input. Please enter option 1-8. Enter 0 to exit.");
                pause();
                continue;
            }
            if (choice < 0 || choice > 9) {
                validate++;
                System.out.println("\nInvalid input. Please enter option 1-8. Enter 0 to exit.");
                pause();
            } 
        } while (validate != 0);
        return choice;
    }
    
    public void clearScr() {
        System.out.println("\n\n\n\n\n\n\n\n\n");
    }
    
    public void listAllDonors (ListInterface<Donor> donorList) {  
        ListInterface<Donor> allDonor = new DoublyLinkedList<>();
        
        System.out.print("""
                        ============================================================================================================================================
                        |                                                    Donor Details                                                                         |
                        ============================================================================================================================================
                        |   ID    |       Donor Name       |  Gender  |  Contact Number  |     Category     |                   Donation(s) made                   |
                        --------------------------------------------------------------------------------------------------------------------------------------------                            
                        """);

        for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
            allDonor.addBack(donorList.get(i));
        }

        for (int i = 1; i <= allDonor.getNumberOfEntries(); i++) {
            Donor donor = allDonor.get(i);
        
            System.out.printf("| %-7s | %-22s |    %-5s | %-16s | %-16s |", donor.getId(), donor.getName(), donor.getGender(), donor.getNumber(), donor.getCategory());

            ListInterface<Donation> allDonation = new DoublyLinkedList<>(); 

            ListInterface<Donation> monetary = donor.getMonetary();
            for (int j = 1; j <= monetary.getNumberOfEntries(); j++) {
                allDonation.addBack(monetary.get(j));
            }

            ListInterface<Donation> food = donor.getFood();
            for (int j = 1; j <= food.getNumberOfEntries(); j++) {
                allDonation.addBack(food.get(j));
            }

            ListInterface<Donation> necessities = donor.getNecessities();
            for (int j = 1; j <= necessities.getNumberOfEntries(); j++) {
                allDonation.addBack(necessities.get(j));
            }

            // Print the donor details on the same line as the first donor
            if (allDonation.getNumberOfEntries() > 0) {
                Donation firstDonation = allDonation.get(1);
                System.out.printf(" %-22s | %-12s | %-12s |\n", firstDonation.getDesc(), getDonationUnit(firstDonation.getType(), firstDonation.getAmount()), firstDonation.getDate());
            } else {
                System.out.println(); // Move to the next line for donations of the second donor
            }

            // Print remaining donations onward
            for (int j = 2; j <= allDonation.getNumberOfEntries(); j++) {
                Donation donation = allDonation.get(j);
                System.out.printf("| %-7s | %-22s |    %-5s | %-16s | %-16s | %-22s | %-12s | %-12s |\n", "", "", "", "", "", donation.getDesc(), getDonationUnit(donation.getType(), donation.getAmount()), donation.getDate());
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
    
    public void printDonorDetails (Donor donor) {
        System.out.print("""
                            ============================================================================================================================================
                            |                                                          Donor Details                                                                   |
                            ============================================================================================================================================
                            |   ID    |       Donor Name       |  Gender  |  Contact Number  |     Category     |                   Donation(s) made                   |
                            --------------------------------------------------------------------------------------------------------------------------------------------                            
                            """);

            System.out.printf("| %-7s | %-22s |    %-5s | %-16s | %-16s |", donor.getId(), donor.getName(), donor.getGender(), donor.getNumber(), donor.getCategory());
            
            ListInterface<Donation> allDonation = new DoublyLinkedList<>(); 
            
            ListInterface<Donation> monetary = donor.getMonetary();
            for (int j = 1; j <= monetary.getNumberOfEntries(); j++) {
                allDonation.addBack(monetary.get(j));
            }
            
            ListInterface<Donation> food = donor.getFood();
            for (int j = 1; j <= food.getNumberOfEntries(); j++) {
                allDonation.addBack(food.get(j));
            }
            
            ListInterface<Donation> necessities = donor.getNecessities();
            for (int j = 1; j <= necessities.getNumberOfEntries(); j++) {
                allDonation.addBack(necessities.get(j));
            }

            // Print the donor details on the same line as the first donor
            if (allDonation.getNumberOfEntries() > 0) {
                Donation firstDonation = allDonation.get(1);
                System.out.printf(" %-22s | %-12s | %-12s |\n", firstDonation.getDesc(), getDonationUnit(firstDonation.getType(), firstDonation.getAmount()), firstDonation.getDate());
            } else {
                System.out.println(); // Move to the next line for donations of second donor
            }
            
            // Print remaining donations onward
            for (int j = 2; j <= allDonation.getNumberOfEntries(); j++) {
                Donation donation = allDonation.get(j);
                System.out.printf("| %-7s | %-22s |    %-5s | %-16s | %-16s | %-22s | %-12s | %-12s |\n", "", "", "", "", "", donation.getDesc(), getDonationUnit(donation.getType(), donation.getAmount()), donation.getDate());
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
           
    }
    
    public String operationId(String operation) {
        System.out.print("\nEnter the donor ID that you would like to " + operation + ": ");
        String id = scanner.nextLine();
        System.out.println();
        return id;
    }
    
    public String searchDonor() {
        System.out.print("Enter the donor id for search: ");
        String id = scanner.nextLine();
        return id;
    }

    public String inputId() {
        String id = "";
        int validate;
        char check;
        
        do {
            validate = 0;
            
            try {
                System.out.print("Enter donor ID                      : ");
                id = scanner.nextLine();
            } catch (StringIndexOutOfBoundsException ex) {
                validate++;
                System.out.println("\nID cannot be empty. Please enter again.\n");
                continue;
            }
            
            String s1 = id.substring(0, 1).toUpperCase();
            String s2 = id.substring(1);
            id = s1 + s2;
            
            if (id.length() != 5) {
                validate++;
                System.out.println("\nInvalid length. Please enter again. (E.g. D1001)\n");
            } else if (id.charAt(0) != 'D') {
                validate++;
                System.out.println("\nInvalid ID. Starting with 'D' for Donor ID. (E.g. D1001)\n");
            }
            
            for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
                Donor current = donorList.get(i);
                if (current.getId().equals(id)) {
                    validate++;
                    System.out.println("\nThis donor ID already exists. Please enter again.\n");
                }
            }
        } while (validate != 0);
        
        return id;
    }
    
    public String inputName() {
        String name = "";
        int validate;
        
        do {
            validate = 0;
            System.out.print("Enter donor name                    : ");
            name = scanner.nextLine();
            
            if (name.isEmpty()) {
                validate++;
                System.out.println("\nName cannot be empty. Please enter again.\n");
            }
            
            for (int i = 0; i < name.length(); i++) {
                if (!Character.isLetter(name.charAt(i))) {
                    validate++;
                    System.out.println("\nInvalid name. Please enter again.\n");
                } else if (Character.isDigit(name.charAt(i))) {
                    validate++;
                    System.out.println("\nInvalid name. Please enter again.\n");
                }
            }
        } while (validate != 0);
        
        return name;
    }
    
    public char inputGender() {
        String input = "";
        char gender = 0;
        int validate;
        
        do {
            validate = 0;
            
            System.out.print("Enter gender (M/F)                  : ");
            input = scanner.nextLine();
            if (input.length() != 1) {
                validate++;
                System.out.println("\nInvalid length. Please enter again. ('M' for Male, 'F' for Female)\n");
            } else if (!Character.isAlphabetic(input.charAt(0))) {
                validate++;
                System.out.println("\nInvalid format. Please enter again. ('M' for Male, 'F' for Female)\n");
            } else if (Character.toUpperCase(input.charAt(0)) != 'M' && Character.toUpperCase(input.charAt(0)) != 'F') {
                validate++;
                System.out.println("\nInvalid input. Please enter again. ('M' for Male, 'F' for Female)\n");
            } else {
                gender = input.charAt(0);
            }
        } while (validate != 0);
        
        return Character.toUpperCase(gender);
    }

    public String inputContactNumber() {
        String number = "";
        int validate;
        
        do {
            validate = 0;
            
            System.out.print("Enter contact number (without dash) : ");
            number = scanner.nextLine();
            if (!(number.length() >= 10 && number.length() <= 11)) {
                validate++;
                System.out.println("\nInvalid contact number length. Please enter again. (10-11 digits)\n");
                continue;
            } else if (number.charAt(0) != '0') {
                validate++;
                System.out.println("\nInvalid contact number format. Please enter again. (First digit must be '0')\n");
                continue;
            }
            for (int i = 0; i < number.length(); i++) {
                if (!Character.isDigit(number.charAt(i))) {
                    validate++;
                    System.out.println("\nInvalid contact number format. Please enter again. (10-11 digits)\n");
                    break;
                }
            }
        } while (validate != 0);
        
        return number;
    }
    
    public String inputCategory() {
        String category = "";
        int validate;
        
        do {
            validate = 0;
            
            try {
                System.out.print("Enter donor's category              : ");
                category = scanner.nextLine();
                String s1 = category.substring(0, 1).toUpperCase();
                String s2 = category.substring(1);
                category = s1 + s2;
            } catch (StringIndexOutOfBoundsException ex) {
                validate++;
                System.out.println("\nCategory cannot be empty. Please enter again.\n");
                continue;
            }
            
            if (!(category.toLowerCase()).equals("public") && 
                !(category.toLowerCase()).equals("private") && 
                !(category.toLowerCase()).equals("government")) {
                validate++;
                System.out.println("\nInvalid category. Please enter again. (Public, Private, Government)\n");
            }
        } while (validate != 0);
        
        return category;
    }
    
    public Donation inputDonation() {
        String type = "";
        String donation = "";
        double amount = 0.0;
        String date;
        int validate;
        
        // input donation
        do {
            validate = 0;

            try {
                System.out.print("Enter donation made : ");
                donation = scanner.nextLine();
                String s1 = donation.substring(0, 1).toUpperCase();
                String s2 = donation.substring(1);
                donation = s1 + s2;
            } catch (StringIndexOutOfBoundsException ex) {
                validate++;
                System.out.println("\nDonation cannot be empty. Please enter again.\n");
            }
        } while (validate != 0);

        // input donation amount
        do {
            validate = 0;

            System.out.print("Enter quantity      : ");
            try {
                amount = Double.parseDouble(scanner.nextLine().trim());
                System.out.println();
                if (amount <=0 ) {
                    validate++;
                    System.out.println("\nInvalid amount. Please enter a value more than 0.\n");
                }
            } catch (NumberFormatException ex) {
                validate++;
                System.out.println("\nInvalid input. Please enter a numeric value.\n");
            } catch (StringIndexOutOfBoundsException ex) {
                validate++;
                System.out.println("\nDonation Amount cannot be empty. Please enter again.\n");
            }
        } while (validate != 0);

        // current date
        date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));     

        return new Donation(type, donation, amount, date);
    }
    
    public Donor inputDonorDetails() {
        String donorId = inputId();
        String donorName = inputName();
        char donorGender = inputGender();
        String contactNumber = inputContactNumber();
        String category = inputCategory();
        
        Donor donor = new Donor(donorId, donorName, donorGender, contactNumber, category);
        char yon = 0;
        
         do {
            String type;
            
            do {
                System.out.print("\nEnter donation type : ");
                type = scanner.nextLine().toLowerCase();

                if (!type.equals("monetary") && !type.equals("food") && !type.equals("necessities")) {
                    System.out.println("\nInvalid donation type. Please enter a valid type: (Monetary, Food, Necessities)");
                    type = "";
                }
            } while (type.isEmpty());
            
            Donation donation = inputDonation();
            donation.setType(type);
            
            switch (type) {
                case "monetary":
                    donor.addMonetary(donation);
                    break;
                case "food":
                    donor.addFood(donation);
                    break;
                case "necessities":
                    donor.addNecessities(donation);
                    break;
            }
            int validate;
            do {
                validate = 0;
                System.out.print("Continue to add another donation? (Y/N): ");
                try {
                    yon = scanner.nextLine().toUpperCase().charAt(0);
                } catch (StringIndexOutOfBoundsException ex) {
                    validate++;
                    System.out.println("\nInvalid input. Please enter: (Y/N)\n");
                }
            } while (validate != 0);
        } while (yon == 'Y');

        ListInterface<Donor> donorList = new DoublyLinkedList<>();
        donorList.addBack(donor);
        return donor;
    }
    
    public int filterDonors() {
        System.out.print("""
                           ============================
                           |       Filtering by       |
                           ============================
                           |                          |
                           | 1) Filtering by name     |
                           |                          |
                           | 2) Filtering by gender   |
                           |                          |
                           | 3) Filtering by category |
                           |                          |
                           ============================
                         
                        """);
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        return choice;
    }
    
    public void summaryReport(ListInterface<Donor> donorList) {
        double totalMonetary = 0.0;
        double totalFood = 0.0;
        double totalNecessities = 0.0;
        int totalDonor = donorList.getNumberOfEntries();
        int totalDonation = 0;
        int choice;
        
        do {
            System.out.print("""
                            ==================================================================================================================================================
                            |                                                            Summary Report                                                                      |
                            ==================================================================================================================================================
                            |     |    ID    |       Donor Name       |  Gender  |  Contact Number  |     Category     |  Total Monetary  |  Total Food  |   Total Necessities |
                            --------------------------------------------------------------------------------------------------------------------------------------------------                            
                            """);
            
            ListInterface<Donor> sortedIdList = new DoublyLinkedList<>();
            Comparator<Donor> comparator = new Donor.IdComparator();
            
            for (int i = 1; i <= donorList.getNumberOfEntries(); i++) {
                //Donor donor = donorList.get(i);
                sortedIdList.addBack(donorList.get(i));
            }
            
            for (int i = 1; i <= sortedIdList.getNumberOfEntries(); i++) {

                double donorMonetary = 0.0;
                double donorFood = 0.0;
                double donorNecessities = 0.0;
                int donorDonation = 0;

                donorMonetary = calculateTotal(sortedIdList.get(i).getMonetary());
                donorFood = calculateTotal(sortedIdList.get(i).getFood());
                donorNecessities = calculateTotal(sortedIdList.get(i).getNecessities());

                totalMonetary += donorMonetary;
                totalFood += donorFood;
                totalNecessities += donorNecessities;

                donorDonation = sortedIdList.get(i).getMonetary().getNumberOfEntries() +
                                sortedIdList.get(i).getFood().getNumberOfEntries() +
                                sortedIdList.get(i).getNecessities().getNumberOfEntries();

                System.out.printf("| %-3d | %-8s | %-22s |    %-5s | %-16s | %-16s | RM %-13.2f | %9.1f kg | %11.0f item(s) |\n",
                                i, sortedIdList.get(i).getId(), sortedIdList.get(i).getName(), sortedIdList.get(i).getGender(), 
                                sortedIdList.get(i).getNumber(), sortedIdList.get(i).getCategory(),
                                donorMonetary, donorFood, donorNecessities);

                totalDonation += donorDonation;
            }

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| Total Donors                : %-105d        |\n", totalDonor);
            System.out.printf("| Total Donations             : %-105d        |\n", totalDonation);
            System.out.printf("| Total Monetary Donations    : RM %-107.2f   |\n", totalMonetary);
            System.out.printf("| Total Food Donations        : %.1f kg                                                                                                          |\n", totalFood);
            System.out.printf("| Total Necessities Donations : %.0f item(s)                                                                                                       |\n", totalNecessities);
            System.out.println("===================================================================================================================================================");
            System.out.print("\nEnter no. of donor to view details (or '0' to exit): ");
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            
            if (choice < 0 && choice > donorList.getNumberOfEntries()) {
                System.out.println("\nInvalid input. Please try again.\n");
            } else if (choice == 0) {
                break;
            } else {
                Donor donor = donorList.get(choice);
                printDonorDetails(donor);
                pause();
            }
        } while (choice != 0);
    }
    
    public double calculateTotal(ListInterface<Donation> donation) {
    double total = 0.0;
    for (int i = 1; i <= donation.getNumberOfEntries(); i++) {
        total += donation.get(i).getAmount();
    }
    return total;
}
    
    public String getDonationUnit(String type, double amount) {
        switch (type.toLowerCase()) {
            case "monetary":
                return "RM " + String.format("%.2f", amount);
            case "food":
                return String.format("%.0f", amount)+ " kg";
            case "necessities":
                return String.format("%.0f", amount) + " item(s)";
            default:
                return "";
        }
    }
    
    public void pause() {
        System.out.print("\nPress any key to continue...");
        scanner.nextLine();
    }
    
    public void successMsg(String operation) {
        System.out.println("\nRecord has been " + operation + " successfully!");
    }
    
    public boolean confirmMsg(String operation) {
        System.out.print("\nAre you sure want to " + operation + " this donor? (Y/N): ");
        char yon;
        
        do {
            yon = scanner.next().charAt(0); 
            scanner.nextLine();
            
            if (Character.toString(yon).equalsIgnoreCase("Y")) {
                return true;
            } else if (Character.toString(yon).equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.print("Invalid input. Please re-enter your choice (Y or N): ");
            }
        } while (!(Character.toString(yon).equalsIgnoreCase("Y") || Character.toString(yon).equalsIgnoreCase("N")));
    
        return false;
    } 
}
