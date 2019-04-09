package patientmanagementsystemgui;

/**
 * A helper class to sort algorithms
 */
public class Sort {
    /**
     * @param OldPatientArray - Takes old array from file
     * @return New Sorted Array in Last Name Starting from Ascending order,
     * if there are two equal surnames first name is used to sort
     * @throws Exception Incase files fails to read or not found
     */
    public static Patient[] bubleSortFName(Patient[] OldPatientArray) throws Exception {

        PatientFileHandling PatientFile=new PatientFileHandling();
        Patient[] PatientArray = PatientFile.readFile();
        boolean needsort = true;// checks if array needs to be sorted
        Patient Temp = new Patient();
        for (int i = 1; i < PatientArray.length && needsort; i++) {
            needsort = false; // array maybe sorted next pass not needed
            for (int j = 0; j < PatientArray.length - i; j++) {
                if (PatientArray[j].getFirstName().compareToIgnoreCase(PatientArray[j + 1].getFirstName()) > 0) {
                    Temp = PatientArray[j + 1];
                    PatientArray[j + 1] = PatientArray[j];
                    PatientArray[j] = Temp;
                    needsort = true;
                    if (needsort == false) {
                        MessageBox.box("Already Sorted");
                    }
                }

            }
        }

        return PatientArray;

    }

    /**
     * @param OldPatientArray
     * @return
     * @throws Exception
     */
    public static Patient[] bubleSortLName(Patient[] OldPatientArray) throws Exception {

        PatientFileHandling PatientFile=new PatientFileHandling();
        Patient[] PatientArray = PatientFile.readFile();
        boolean needsort = true;// checks if array needs to be sorted
        Patient Temp = new Patient();
        for (int i = 1; i < PatientArray.length && needsort; i++) {
            needsort = false; // array maybe sorted next pass not needed
            for (int j = 0; j < PatientArray.length - i; j++) {
                if(PatientArray[j].getLastName().compareToIgnoreCase(PatientArray[j + 1].getLastName()) == 0){
                    if (PatientArray[j].getFirstName().compareToIgnoreCase(PatientArray[j + 1].getFirstName()) > 0) {
                        Temp = PatientArray[j + 1];
                        PatientArray[j + 1] = PatientArray[j];
                        PatientArray[j] = Temp;
                        needsort = true;
                    }
                }
                else if (PatientArray[j].getLastName().compareToIgnoreCase(PatientArray[j + 1].getLastName()) > 0) {
                    Temp = PatientArray[j + 1];
                    PatientArray[j + 1] = PatientArray[j];
                    PatientArray[j] = Temp;
                    needsort = true;
                    if (needsort == false) {
                        MessageBox.box("Already Sorted");
                    }
                }

            }
        }

        return PatientArray;

    }

    /**
     * @param PatientArray Recursive method
     *                     Merge sorting
     *                     breaks the array into 2 parts
     *                     then sorts the array buy comparing each elements
     * @return The sorted array
     */
    public static Patient[] PatientMergeSortID(Patient[] PatientArray) {

        if (PatientArray.length <= 1) { //if size of array is one no need to sort/ controls recursive
            return PatientArray;
        }
        int midpoint = PatientArray.length / 2;
        Patient[] leftside = new Patient[midpoint];
        Patient[] rightside;
        if (PatientArray.length % 2 == 0) { // checks if length is even or odd
            rightside = new Patient[midpoint];
        } else {
            rightside = new Patient[midpoint + 1]; //if it is an odd number
        }

        for (int i = 0; i < leftside.length; i++) {
            leftside[i] = PatientArray[i]; // populating left side
        }

        for (int j = 0; j < rightside.length; j++) {
            rightside[j] = PatientArray[midpoint + j]; //starting where variable i left
        }

        Patient[] Result = new Patient[PatientArray.length];
        leftside = PatientMergeSortID(leftside); // recursive recalling itself
        rightside = PatientMergeSortID(rightside);

        // Get the merged left and right arrays.
        Result = MergePatientID(leftside, rightside);

        // Return the sorted merged array.

        return Result;
    }

    /**
     * @param leftside
     * @param rightside Swapping is performed
     * @return
     */
    private static Patient[] MergePatientID(Patient[] leftside, Patient[] rightside) {
        Patient[] Result = new Patient[leftside.length + rightside.length];
        int rightpointer = 0, leftpointer = 0, resultpointer = 0;

        while (leftpointer < leftside.length || rightpointer < rightside.length) {
            // ensures that the array is not empty
            if (leftpointer < leftside.length && rightpointer < rightside.length) {
                //ensure both arrays are not empty
                if (leftside[leftpointer].getPatientID() < rightside[rightpointer].getPatientID()) { // swapping is done
                    Result[resultpointer++] = leftside[leftpointer++];// incrementing both pointers

                } else {
                    Result[resultpointer++] = rightside[rightpointer++];
                }
            } else if (leftpointer < leftside.length) {
                // if there are elements only in the leftarray
                Result[resultpointer++] = leftside[leftpointer++];// incrementing both pointers
            } else if (rightpointer < rightside.length) {
                // if there are elements only in the rightarray
                Result[resultpointer++] = rightside[rightpointer++];// incrementing both pointers
            }

        }
        return Result;
    }
}

