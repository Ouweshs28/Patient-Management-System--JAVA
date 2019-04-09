package patinentmanagementsystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class PatientFileHandling {
    
     public static void writeFile (Patient[] PatientArray) {

        String filename = "patientdata.bin";
        try {
            ObjectOutputStream ObjOut = new ObjectOutputStream(new FileOutputStream(filename));
            ObjOut.writeObject(PatientArray);
            ObjOut.flush();
            ObjOut.close();
            System.out.println("File Written Successfully");

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
     }
        

    public static void appendFile(Patient[] PatientArray, Patient InputPatient) {

        String filename = "patientdata.bin";
        Patient TempPatient = InputPatient;
        Patient[] UpdatedPatient = new Patient[PatientArray.length + 1]; //Creates an array 1 size better to store the new object
        for (int i = 0; i < PatientArray.length; i++) { // copies the old ones
            UpdatedPatient[i] = PatientArray[i];
        }
        UpdatedPatient[UpdatedPatient.length - 1] = TempPatient; // adds the new object at the end of the array
        try {
            ObjectOutputStream ObjOut = new ObjectOutputStream(new FileOutputStream(filename));
            ObjOut.writeObject(UpdatedPatient);
            ObjOut.flush();
            ObjOut.close();
            System.out.println("File Updated Successfully");

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    public static Patient[] readFile() throws IOException, ClassNotFoundException {
        String filename = "patientdata.bin";
        ObjectInputStream ObjIn = new ObjectInputStream(new FileInputStream(filename));
        Patient[] PatientFileRead = (Patient[]) ObjIn.readObject();
        ObjIn.close();
        System.out.println("Data File Loaded with success");
        return PatientFileRead;

    }

}



