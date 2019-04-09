package patientmanagementsystemgui;

/**
 * A helper class to check for similar PatientID
 */
public class SimilarityCheck {

    /**
     * @param patientID
     * @return returns true if there is a duplicate item
     * @throws Exception File not found or any other related
     */
    public static boolean patientID(String patientID) throws Exception {

        Patient[] PatientArray = PatientFileHandling.readFile();

        boolean dublicate = false;

        for (Patient PatientArray1 : PatientArray) {
            if (Integer.parseInt(patientID) == PatientArray1.getPatientID()) {
                dublicate = true;
            }
        }

        return dublicate;
    }
}
