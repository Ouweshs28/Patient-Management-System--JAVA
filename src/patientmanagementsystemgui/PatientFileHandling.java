package patientmanagementsystemgui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A helper class for File Handling operations
 */
public class PatientFileHandling {

    /**
     * @param PatientArray
     * A method used to write hard coded Patient in binary files to be used by the programmer
     */
    public static void writeFile(Patient[] PatientArray) {

        String filename = "patientdata.bin";
        try {
            ObjectOutputStream ObjOut = new ObjectOutputStream(new FileOutputStream(filename));
            ObjOut.writeObject(PatientArray);
            ObjOut.flush();
            ObjOut.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }
    /**
     * @param PatientArray - Existing Array
     * @param InputPatient - Updated UserInput Array
     *  Creates a new array that is one size larger of the original one
     *  Copies the old elements to the array
     *  Adds the new element to the last position.
     */

    public static void appendFile(Patient[] PatientArray, Patient InputPatient) {

        String filename = "patientdata.bin";
        Patient TempPatient = InputPatient;
        Patient[] UpdatedPatient = new Patient[PatientArray.length + 1]; //Creates an array 1 size better to store the new object
        System.arraycopy(PatientArray, 0, UpdatedPatient, 0, PatientArray.length); // copies the old ones
        UpdatedPatient[UpdatedPatient.length - 1] = TempPatient; // adds the new object at the end of the array
        try {
            ObjectOutputStream ObjOut = new ObjectOutputStream(new FileOutputStream(filename));
            ObjOut.writeObject(UpdatedPatient);
            ObjOut.flush();
            ObjOut.close();

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
        }
    }

    /**
     * @return Patient Array from File
     * Reads patient.bin file and returns an array of objects
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static Patient[] readFile() throws IOException, ClassNotFoundException {
        String filename = "patientdata.bin";
        ObjectInputStream ObjIn = new ObjectInputStream(new FileInputStream(filename));
        Patient[] PatientFileRead = (Patient[]) ObjIn.readObject();
        ObjIn.close();
        return PatientFileRead;

    }

}
