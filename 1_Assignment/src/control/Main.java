package control;

import java.util.InputMismatchException;
import java.util.Scanner;
import utility.MessageUI;

/**
 *
 * @author yanqi
 */

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DonorManagement donorManagement = new DonorManagement();
        
        int choice = 0;
        int validate;
        do {
            validate = 0;

            System.out.println("=========================================");
            System.out.println("\t\tMAIN MENU");
            System.out.println("=========================================");
            System.out.println("(1) Donor Management Subsystem\n");
            System.out.println("(0) Exit\n");

            try {
                System.out.print("\nEnter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException ex) {
                validate++;
                MessageUI.displayInvalidChoiceMessage();
                break;
            }
            switch(choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    donorManagement.DonorMain();
                    break;
                default:
                    validate++;
                    System.out.println("\nInvalid choice. Please try again. (Options: 1 - 4)\n");
            }
            
        } while (validate != 0);
    }
}
