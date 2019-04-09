/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patinentmanagementsystem;

/**
 *
 * @author Ouwesh
 */
import java.io.IOException;
import java.util.Scanner;


public class PatientTester {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Patient PatientArray[]=PatientFileHandling.readFile();
        StartProgram();
        Scanner input = new Scanner(System.in);


        // Menu of program
        /* 1. Add item
           2. Remove patient
           3. Search patient details
           4. Sort Patient by Patient ID
           5. Print all patient details
           6. Exit the program

         */
        int choice;
        do {
            PatientFileHandling.writeFile(PatientArray);
            choice = getChoiceInput(input);
            switch (choice) {
                case 1:
                    Patient InputPatient = getPatientInput(input, PatientArray);
                    PatientFileHandling.appendFile(PatientArray,InputPatient);

                    break;
                case 2:
                    PatientArray = PatientFeatures.RemovePatient(PatientArray, input);
                    break;
                case 3:
                    PatientFeatures.LinearSearch(PatientArray, input);
                    break;
                case 4:
                    PatientArray = PatientFeatures.getSortChoice(input, PatientArray);
                    break;
                case 5:
                    PatientFeatures.PrintAllPatients(PatientArray);
                    break;
                case 6:
                    EndProgram();
                    break;
                default:
                    System.out.println("Invalid selection,try again");
                    break;
            }

        } while (choice != 6);

    }

    public static Patient getPatientInput(Scanner input, Patient[] PatientArray) {
        Patient create = new Patient();
        boolean dublicate = false;
        int tempid; // variable to store the new entered patient id and check with older ones
        String str;
        do {
            while (true) {
                System.out.print("Enter PatientID : ");
                str = input.next();
                try {
                    create.setPatientID(Integer.parseInt(str));
                    break;
                } catch (NumberFormatException e) {

                    System.out.println("Not a number, Enter a valid PatientID");
                }
            }
            for (int j = 0; j < PatientArray.length;j++) {
                tempid = (Integer.parseInt(str));
                if (tempid == PatientArray[j].getPatientID()) {
                    System.out.println("Please enter another Patient ID, the current entered is already stored");
                    dublicate = true;
                    break;
                } else {
                    dublicate = false;
                }

            }
        } while (dublicate);
        System.out.print("Enter First Name : ");
        create.setFirstName(ValidateAlpha(input));
        System.out.print("Enter Last Name : ");
        create.setLastName(ValidateAlpha(input));
        System.out.print("Enter Medical Condition : ");
        create.setMedicalCondition(input.next());
        System.out.print("Enter Next Appointment Date (DD/MM/YYYY):");
        create.setNextAppointmentDate(InputDate(input));
        System.out.print("Enter Phone Number : ");
        create.setPhoneNo(ValidatePhoneNum(input));
        System.out.print("Enter BillingStatus:" // choice to chose between 1 and 2
                + "\n 1. Pending"
                + "\n 2. Paid"
                + "\n Choice:");
        int billchoice=InputBillChoice(input);

        if (billchoice == 1) {
            create.setBillingStatus("Pending");
        }
        if (billchoice == 2) {
            create.setBillingStatus("Paid");
        }
        System.out.print("Enter Comments :");
        create.setComments(input.next());
        System.out.print("Enter Bill Amount (Rs) : ");
        double bill;
        do {
            while (!input.hasNextDouble()) {
                System.out.print(" Please input a number. Try again !:");
                input.next(); // to avoid infinite looping 
            }
            bill = input.nextDouble();
            create.setBill(bill);
            if (bill < 0) {
                System.out.print("Invalid input. Please enter a postive number or zero:");
            }
        } while (bill < 0);

        return create; // returns created array
    }

    public static int getChoiceInput(Scanner input) {
            System.out.print("1. Add a patient"
                    + "\n2. Remove a pateint"
                    + "\n3. Search patient details"
                    + "\n4. Sort Patient"
                    + "\n5. Show all patients stored"
                    + "\n6. Exit the program"
                    + "\nEnter your choice: ");
        int choice=CheckInt(input);

        return choice;

    }


    public static void StartProgram() {
        System.out.println("+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+\n"
                + "|P|a|t|i|e|n|t| |M|a|n|a|g|e|m|e|n|t| |S|y|s|t|e|m| |b|y| |O|u|w|e|s|h|s|2|8|\n"
                + "+-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+");
    }

    public static void EndProgram() {
        System.out.println("____                          __     \n"
                + " /  / _   / (__/             / _)  _ \n"
                + "(  /)(//)/(  / ()(/.  .  .  /(_)(/(- \n"
                + "                                /    ");
    }

    public static String ValidateAlpha(Scanner input) {
        String str;
        String alphacheck = "^[a-zA-Z]+$"; // range that accepts only aphabets
        do {
            str = input.next();
            if (!str.matches(alphacheck)) {
                System.out.print("Invalid please Enter Alphabets!:");
            } else {
                break;
            }

        } while (!str.matches(alphacheck));
        return str;
    }

    public static String ValidatePhoneNum(Scanner input) {
        String str;
        String numcheck = "^[0-9+]+$"; // accepts only numbers and +
        do {
            str = input.next();
            if (!str.matches(numcheck)) {
                System.out.print("Invalid please Enter Numbers!:");
            } else {
                break;
            }
        } while (!str.matches(numcheck));
        return str;
    }

    public static String ValidateNum(Scanner input) {
        String str;
        String numcheck = "^[0-9]+$"; //accepts only numbers
        do {
            str = input.next();
            if (!str.matches(numcheck)) {
                System.out.print("Invalid please Enter Numbers!:");
            } else {
                break;
            }
        } while (!str.matches(numcheck));
        return str;
    }

    public static String InputDate(Scanner input) {
        String day, month, year, date;
        System.out.print("Enter Day Date (1-31): ");
        day = ValidateNum(input);
        do {
            if ((Integer.parseInt(day) < 1) || (Integer.parseInt(day) > 31)) {
                System.out.print("Invalid please a correct date 1-31!:");
                day = ValidateNum(input);
            }

        } while ((Integer.parseInt(day) < 1) || (Integer.parseInt(day) > 31));

        System.out.print("Enter Month Date (For example 02 for Febuary):  ");
        month = ValidateNum(input);
        do {
            if ((Integer.parseInt(month) < 1) || (Integer.parseInt(month) > 12)) {
                System.out.print("Invalid please a correct month! 1-12):");
                month = ValidateNum(input);
            }

        } while ((Integer.parseInt(month) < 1) || (Integer.parseInt(month) > 12));

        System.out.print("Enter the Year (2019 or afterwards):  ");
        year = ValidateNum(input);
        do {

            if ((Integer.parseInt(year) < 2019) && (Integer.parseInt(year) > 2100)) {
                System.out.print("Invalid please a correct year 2019 or after!:");
                year = ValidateNum(input);
            }

        } while ((Integer.parseInt(year) < 2019) && (Integer.parseInt(year) > 2100));
        date = day + "/" + month + "/" + year;
        return date;
    }
    public static int InputBillChoice(Scanner input){
        int billchoice;
        do {
            while (!input.hasNextInt()) {
                System.out.print("Invalid input. Try again !:");
                input.next();
            }
            billchoice = input.nextInt();
            if ((billchoice != 1 && billchoice != 2)) {
                System.out.print("Invalid input. Try again !:");
            }
        } while (billchoice != 1 && billchoice != 2);
        return billchoice;
    }

    public static int CheckInt(Scanner input){
        int choice;
        while (true) {
            String str = input.next();
            try {
                choice = Integer.parseInt(str);
                break;

            } catch (NumberFormatException e) {

                System.out.println("Not valid, Enter a number");
            }
        }
        return choice;
    }
    }

