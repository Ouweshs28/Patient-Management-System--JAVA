package patinentmanagementsystem;

import java.util.Scanner;

public class PatientFeatures {
    public static void LinearSearch(Patient[] PatientArray, Scanner input) {

        System.out.print("Search by:"
                + "\n1. Patient ID"
                + "\n2. First Name"
                + "\n3. Last Name"
                + "\n4. Medical Condition"
                + "\n5. Appointment Date (DD/MM/YYYY):"
                + "\n6. BillingStatus (Paid/Pending)"
                + "\nEnter your choice:");

        int searchchoice = PatientTester.CheckInt(input);
        switch (searchchoice) {
            case 1:
                SearchOptions.byID(input, PatientArray);
                break;
            case 2:
                SearchOptions.byName(input, PatientArray);
                break;
            case 3:
                SearchOptions.byLastName(input, PatientArray);
                break;
            case 4:
                SearchOptions.byMedicalCond(input, PatientArray);
                break;
            case 5:
                SearchOptions.byAppoitmentDate(input, PatientArray);
                break;
            case 6:
                SearchOptions.byBill(input, PatientArray);
                break;
            default:
                System.out.println("Invalid selection,try again");

                break;


        }
    }

    public static Patient[] RemovePatient(Patient[] PatientArray, Scanner input) {
        PrintAllPatients(PatientArray);
        int i = PatientArray.length;
        int removeindex;
        while (true) {
            System.out.print("Select the patient position to remove: ");
            String str = input.next();

            try {
                removeindex = (Integer.parseInt(str));
                break;
            } catch (NumberFormatException e) {

                System.out.println("Not a number, Enter a valid PatientID");
            }
        }
        if (PatientArray == null
                || removeindex < 0
                || removeindex > i) { // if number entered by user is invalid or array is null
            System.out.print("Invalid selection\n");
            return PatientArray;
        }
        Patient[] CopyArray = new Patient[i-1];

        System.arraycopy(PatientArray, 0, CopyArray, 0, removeindex); // copies till the deleted value
        System.arraycopy(PatientArray, removeindex + 1, CopyArray, removeindex, i - removeindex - 1);
        // copies from deleted +1 till the end of the array

        return CopyArray;

    }

    public static void PrintAllPatients(Patient[] PatientArray) {
        System.out.println("_____________________________________________________"
                + "________________________________________________________"
                + "________________________________________________________");

        System.out.printf("  | %15s %15s %15s %20s %20s %15s %20s %20s %15s",
                "Patient ID |", "FirstName |", "LastName |", "Medical Condition |", "Next Appointment |",
                "PhoneNo |", "Billing Status |", "Comments |", "Bill |\n");
        System.out.println("-----------------------------------------------------"
                + "--------------------------------------------------------"
                + "--------------------------------------------------------");

        for (int j = 0; j < PatientArray.length; j++) {
            int postition = 1 + j;
            System.out.print(postition + ".");
            PatientArray[j].Display();
        }
        System.out.println("-----------------------------------------------------"
                + "--------------------------------------------------------"
                + "--------------------------------------------------------");

    }
    public static Patient [] getSortChoice(Scanner input, Patient[] PatientArray){
        System.out.println("Select how you want to sort Patient data" +
                "\n1.Patient ID" +
                "\n2.Patient First Name" +
                "\n3.Patient Last Name" +
                "\n.Make your choice");
        int choice=PatientTester.CheckInt(input);
        PatientArray = Sorting(choice, PatientArray);

        return PatientArray;
    }

    public static Patient[] Sorting(int choice,Patient[] PatientArray){
        switch (choice){
            case 1:
                PatientArray=PatientMergeSortID(PatientArray);
                break;

            case 2:
                PatientArray=bubleSortFName(PatientArray);
                break;
            case 3:
                PatientArray=bubleSortLName(PatientArray);
                break;


        }
        return PatientArray;
    }

    public  static  Patient[] bubleSortFName(Patient[] PatientArray){
        boolean needsort=true;// checks if array needs to be sorted
        Patient Temp=new Patient();
        for(int i=1;i<PatientArray.length && needsort;i++){
            needsort=false; // array maybe sorted next pass not needed
            for (int j=0;j<PatientArray.length-i;j++){
                if(PatientArray[j].getFirstName().compareToIgnoreCase(PatientArray[j+1].getFirstName())>0){
                    Temp=PatientArray[j+1];
                    PatientArray[j+1]=PatientArray[j];
                    PatientArray[j]=Temp;
                    needsort=true;
                    if (needsort==false){
                        System.out.println("Already Sorted");
                    }
                }

            }
        }
        return PatientArray;
    }

    public  static  Patient[] bubleSortLName(Patient[] PatientArray){
        boolean needsort=true;// checks if array needs to be sorted
        Patient Temp=new Patient();
        for(int i=1;i<PatientArray.length && needsort;i++){
            needsort=false;
            for (int j=0;j<PatientArray.length-i;j++){
                if(PatientArray[j].getLastName().compareToIgnoreCase(PatientArray[j+1].getLastName())>0){
                    Temp=PatientArray[j+1];
                    PatientArray[j+1]=PatientArray[j];
                    PatientArray[j]=Temp;
                    needsort=true;
                    if (!needsort){
                        System.out.println("Already Sorted");
                    }
                }

            }
        }
        return PatientArray;
    }

    public static Patient[] PatientMergeSortID(Patient[] PatientArray){

        if (PatientArray.length<=1){ //if size of array is one no need to sort/ controls recursive
            return PatientArray;
        }
        int midpoint=PatientArray.length/2;
        Patient[] leftside=new Patient[midpoint];
        Patient[] rightside;
        if (PatientArray.length%2==0){ // checks if length is even or odd
            rightside=new Patient[midpoint];
        }else{
            rightside=new Patient[midpoint+1]; //if it is an odd number
        }

        for(int i=0;i<leftside.length;i++){
            leftside[i]=PatientArray[i]; // populating left side
        }

        for (int j=0;j<rightside.length;j++){
            rightside[j]=PatientArray[midpoint+j]; //starting where variable i left
        }

        Patient[] Result=new Patient[PatientArray.length];
        leftside= PatientMergeSortID(leftside); // recursive recalling itself
        rightside=PatientMergeSortID(rightside);

        // Get the merged left and right arrays.
        Result = MergePatientID(leftside, rightside);

        // Return the sorted merged array.

        return Result;
    }

    private static Patient[] MergePatientID(Patient[] leftside, Patient[] rightside){
        Patient[] Result=new Patient[leftside.length+rightside.length];
        int rightpointer=0,leftpointer=0,resultpointer=0;

        while(leftpointer<leftside.length || rightpointer<rightside.length) {
            // ensures that the array is not empty
            if (leftpointer<leftside.length && rightpointer<rightside.length){
                //ensure both arrays are not empty
                if(leftside[leftpointer].getPatientID()<rightside[rightpointer].getPatientID()){ // swapping is done
                    Result[resultpointer++]=leftside[leftpointer++];// incrementing both pointers

                }else{
                    Result[resultpointer++]=rightside[rightpointer++];
                }
            }
            else if(leftpointer<leftside.length){
                // if there are elements only in the leftarray
                Result[resultpointer++]=leftside[leftpointer++];// incrementing both pointers
            }else if(rightpointer<rightside.length){
                // if there are elements only in the rightarray
                Result[resultpointer++]=rightside[rightpointer++];// incrementing both pointers
            }

        }
        return Result;
    }

}
    class SearchOptions{

        public static void byID(Scanner input,Patient[] PatientArray){
            int intsearch;
            while (true) {
                System.out.print("Enter a Patient ID search: ");
                String str = input.next();
                try {
                    intsearch = (Integer.parseInt(str));
                    break;
                } catch (NumberFormatException e) {

                    System.out.println("Not a number, Enter a valid PatientID");
                }

            }
            for (int j = 0; j <PatientArray.length; j++) {
                if (PatientArray[j].getPatientID() == intsearch) { // compares one by one until found
                    System.out.println("Patient found, details below");
                    PatientArray[j].Display();
                    break;
                } else if (j == PatientArray.length) { // if last element and not found not found prompted
                    System.out.println("\"" + intsearch + "\"" + "  Patient ID not found");
                }

            }

        }
        public static void byName(Scanner input,Patient[] PatientArray){
            System.out.print("Enter a First Name to search: ");
            String str = PatientTester.ValidateAlpha(input);
            for (int j = 0; j < PatientArray.length; j++) {
                if (PatientArray[j].getFirstName().equalsIgnoreCase(str)) {
                    System.out.println("Patient found, details below");
                    PatientArray[j].Display();
                } else if (j == PatientArray.length) {
                    System.out.println("\"" + str + "\"" + " first name not found!");
                }
            }
        }
        public static void byLastName(Scanner input,Patient[] PatientArray){
            System.out.print("Enter a Last Name to search: ");
            String str = PatientTester.ValidateAlpha(input);
            for (int j = 0; j < PatientArray.length; j++) {
                if (PatientArray[j].getLastName().equalsIgnoreCase(str)) {
                    System.out.println("Patient found, details below");
                    PatientArray[j].Display();
                    break;
                } else if (j == PatientArray.length) {
                    System.out.println("\"" + str + "\"" + " last name not found!");
                }
            }
        }

        public static void byMedicalCond(Scanner input, Patient[] PatientArray){
            System.out.print("Enter a Medical Condition to search: ");
            String str = input.next();
            for (int j = 0; j < PatientArray.length; j++) {
                if (PatientArray[j].getMedicalCondition().equalsIgnoreCase(str)) {
                    System.out.println("Patient found, details below");
                    PatientArray[j].Display();
                    break;
                } else if (j == PatientArray.length) {
                    System.out.println("\"" + str + "\"" + " medical condition not found!");
                }
            }
        }

        public static void byAppoitmentDate(Scanner input, Patient[] PatientArray) {
            System.out.print("Enter a Appointment date to search: ");
            String str = PatientTester.InputDate(input);
            for (int j = 0; j < PatientArray.length; j++) {
                if (PatientArray[j].getNextAppointmentDate().equalsIgnoreCase(str)) {
                    System.out.println("Patient found, details below");
                    PatientArray[j].Display();
                    break;
                } else if (j == PatientArray.length) {
                    System.out.println("\"" + str + "\"" + "  appointment date not found!");

                }
            }
        }

        public static void byBill(Scanner input, Patient[] PatientArray){
            System.out.print("Search paid or pending billing patients:\n"
                    + "Enter 1 to search paid bill patients\n"
                    + "Enter 2 to search pending bill patients\n"
                    + "Make your choice: ");
            int intsearch = PatientTester.InputBillChoice(input);

            switch (intsearch) {
                case 1:
                    for (int j = 0; j < PatientArray.length; j++) {
                        if (PatientArray[j].getBillingStatus().equalsIgnoreCase("paid")) {
                            System.out.println("Patient found, details below");
                            PatientArray[j].Display();
                        }
                    }
                    break;
                case 2:
                    for (int j = 0; j < PatientArray.length; j++) {
                        if (PatientArray[j].getBillingStatus().equalsIgnoreCase("pending")) {
                            System.out.println("Patient found, details below");
                            PatientArray[j].Display();
                        }
                    }
                    break;
            }
        }
    }



