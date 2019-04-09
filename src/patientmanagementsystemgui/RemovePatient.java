package patientmanagementsystemgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A helper class to remove patient
 */
public class RemovePatient {

    static Stage removeWindow, confirmWindow;
    static TextField inputPatientID, patientIDField, fNameField, lNameField, medCond, nextDate, phone, billStatus, comments, bill;

    /**
     * @throws Exception In case file is not found or could not be read
     *                   This method checks and validates the PatientID input
     */
    public static void removePatient() throws Exception {
        PatientFileHandling PatientFile = new PatientFileHandling();

        removeWindow = new Stage();
        removeWindow.getIcons().add(new Image("file:images/logo.png"));
        removeWindow.setTitle("Remove a patient");
        removeWindow.setResizable(false);
        removeWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane removeItem = new GridPane();
        removeItem.setAlignment(Pos.CENTER);
        removeItem.setVgap(30);

        Label removeItemTitle = new Label();
        removeItemTitle.setText("Removing a patient from record");
        removeItemTitle.setAlignment(Pos.CENTER);
        removeItemTitle.setPrefWidth(Double.MAX_VALUE);

        Label chooseItemCode = new Label();
        chooseItemCode.setText("Enter the patient id that you want to remove");
        chooseItemCode.setAlignment(Pos.CENTER);
        chooseItemCode.setPrefWidth(Double.MAX_VALUE);

        Text patientID = new Text("Enter patient id :");
        inputPatientID = new TextField();
        inputPatientID.setFocusTraversable(false);
        inputPatientID.setPromptText("patient id here");

        Button btnRemove = new Button("Remove");
        btnRemove.setMinWidth(250);
        btnRemove.setFocusTraversable(false);
        btnRemove.setOnAction(e -> {
            if (inputPatientID.getText().equalsIgnoreCase("")) {
                try {
                    MessageBox.box("Field cannot be empty. Try again !");
                } catch (Exception ex) {
                }
            } else if (!inputPatientID.getText().matches("^[0-9+]+$")) {
                try {
                    MessageBox.box("Error ! Check item code and try again !");
                } catch (Exception ex) {
                }
            } else if (Integer.parseInt(inputPatientID.getText()) < 0) {
                try {
                    MessageBox.box("Error ! Please enter a value greater than zero");
                } catch (Exception ex) {
                }
            } else {
                try {
                    confirmRemove();
                } catch (Exception ex) {
                }
            }
        });

        Button btnCancel = new Button("Cancel");
        btnCancel.setMinWidth(250);
        btnCancel.setFocusTraversable(false);
        btnCancel.setOnAction(e -> removeWindow.close());

        HBox hBtns = new HBox(40);
        hBtns.getChildren().addAll(btnRemove, btnCancel);
        hBtns.setAlignment(Pos.CENTER);

        removeItem.add(removeItemTitle, 0, 0, 2, 1);
        removeItem.add(chooseItemCode, 0, 2, 2, 1);
        removeItem.add(patientID, 0, 4);
        removeItem.add(inputPatientID, 1, 4);
        removeItem.add(hBtns, 0, 6, 2, 1);
        removeItem.setPadding(new Insets(10, 250, 10, 250));
        removeItem.setHgap(50);

        Scene scene = new Scene(removeItem);

        removeWindow.setScene(scene);
        removeWindow.setMaximized(true);
        removeWindow.showAndWait();
    }

    /**
     * @throws Exception In case file is not read or found
     *                   Searches for the PatientID
     *                   If found it returns the index
     *                   else it returns -1
     */
    private static void confirmRemove() throws Exception {

        Patient[] PatientArray = PatientFileHandling.readFile();

        int result = -1;

        for (int i = 0; i < PatientArray.length; i++) {
            if (PatientArray[i].getPatientID() == Integer.parseInt(inputPatientID.getText())) {
                result = i;
            }
        }

        if (result == -1) {
            MessageBox.box("No patient found !");
            return;
        }

        confirmWindow = new Stage();
        confirmWindow.getIcons().add(new Image("file:images/logo.png"));
        confirmWindow.setTitle("Confirm remove patient");
        confirmWindow.setResizable(false);
        confirmWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane confirmPane = new GridPane();
        confirmPane.setAlignment(Pos.CENTER);
        confirmPane.setVgap(30);

        Label searchResultLabel = new Label();
        searchResultLabel.setText("Confirm remove data of this patient :");
        searchResultLabel.setAlignment(Pos.CENTER);
        searchResultLabel.setPrefWidth(Double.MAX_VALUE);

        Text patientID = new Text("Patient ID :");
        patientIDField = new TextField();
        patientIDField.setText(String.valueOf(PatientArray[result].getPatientID()));
        patientIDField.setEditable(false);
        patientIDField.setFocusTraversable(false);

        Text firstName = new Text("First name :");
        fNameField = new TextField();
        fNameField.setText(PatientArray[result].getFirstName());
        fNameField.setEditable(false);
        fNameField.setFocusTraversable(false);

        Text lastName = new Text("Last name :");
        lNameField = new TextField();
        lNameField.setText(PatientArray[result].getLastName());
        lNameField.setEditable(false);
        lNameField.setFocusTraversable(false);

        Text medicalCond = new Text("Medical condition :");
        medCond = new TextField();
        medCond.setText(PatientArray[result].getMedicalCondition());
        medCond.setEditable(false);
        medCond.setFocusTraversable(false);

        Text nextAppDate = new Text("Next appointment date :");
        nextDate = new TextField();
        nextDate.setText(PatientArray[result].getNextAppointmentDate());
        nextDate.setEditable(false);
        nextDate.setFocusTraversable(false);

        Text phoneNo = new Text("Phone number :");
        phone = new TextField();
        phone.setText(PatientArray[result].getPhoneNo());
        phone.setEditable(false);
        phone.setFocusTraversable(false);

        Text billStatusText = new Text("Biling status :");
        billStatus = new TextField();
        billStatus.setText(PatientArray[result].getBillingStatus());
        billStatus.setEditable(false);
        billStatus.setFocusTraversable(false);

        Text commentsText = new Text("Comments :");
        comments = new TextField();
        comments.setText(PatientArray[result].getComments());
        comments.setEditable(false);
        comments.setFocusTraversable(false);

        Text billText = new Text("Bill :");
        bill = new TextField();
        bill.setText(String.valueOf(PatientArray[result].getBill()));
        bill.setEditable(false);
        bill.setFocusTraversable(false);

        final int indexResult = result;

        Button btnMainMenu = new Button("Confirm");
        btnMainMenu.setMinWidth(250);
        btnMainMenu.setFocusTraversable(false);
        btnMainMenu.setOnAction(e -> {
            try {
                remove(indexResult);
            } catch (Exception ex) {
            }
        });

        Button btnAnotherSearch = new Button("Cancel");
        btnAnotherSearch.setMinWidth(250);
        btnAnotherSearch.setFocusTraversable(false);
        btnAnotherSearch.setOnAction(e -> {
            try {
                removeWindow.close();
                confirmWindow.close();
            } catch (Exception ex) {
            }
        });

        HBox Hbtns = new HBox(40);
        Hbtns.getChildren().addAll(btnMainMenu, btnAnotherSearch);
        Hbtns.setAlignment(Pos.CENTER);

        confirmPane.add(searchResultLabel, 0, 0, 2, 1);
        confirmPane.add(patientID, 0, 2);
        confirmPane.add(patientIDField, 1, 2);
        confirmPane.add(firstName, 0, 3);
        confirmPane.add(fNameField, 1, 3);
        confirmPane.add(lastName, 0, 4);
        confirmPane.add(lNameField, 1, 4);
        confirmPane.add(medicalCond, 0, 5);
        confirmPane.add(medCond, 1, 5);
        confirmPane.add(nextAppDate, 0, 6);
        confirmPane.add(nextDate, 1, 6);
        confirmPane.add(phoneNo, 0, 7);
        confirmPane.add(phone, 1, 7);
        confirmPane.add(billStatusText, 0, 8);
        confirmPane.add(billStatus, 1, 8);
        confirmPane.add(commentsText, 0, 9);
        confirmPane.add(comments, 1, 9);
        confirmPane.add(billText, 0, 10);
        confirmPane.add(bill, 1, 10);
        confirmPane.add(Hbtns, 0, 12, 2, 1);
        confirmPane.setPadding(new Insets(10, 250, 10, 250));
        confirmPane.setHgap(50);

        Scene scene = new Scene(confirmPane);

        confirmWindow.setScene(scene);
        confirmWindow.setMaximized(true);
        confirmWindow.showAndWait();
    }

    /**
     * @param index selected index to remove
     *              Method to remove the Patient
     * @throws Exception - in case file not found
     */
    private static void remove(int index) throws Exception {

        Patient[] PatientArray = PatientFileHandling.readFile();

        PatientArray = RemovePatient(PatientArray, index);

        PatientFileHandling.writeFile(PatientArray);

        MessageBox.box("Patient data successfully removed !");
        removeWindow.close();
        confirmWindow.close();

    }

    /**
     * @param PatientArray -Takes the array from the file
     * @param removeindex  - The patient to be removed (index)
     * @return Returns the new array with the deleted object
     */
    private static Patient[] RemovePatient(Patient[] PatientArray, int removeindex) {

        Patient[] CopyArray = new Patient[PatientArray.length - 1];

        System.arraycopy(PatientArray, 0, CopyArray, 0, removeindex); // copies till the deleted value
        System.arraycopy(PatientArray, removeindex + 1, CopyArray, removeindex, PatientArray.length - removeindex - 1);
        // copies from deleted +1 till the end of the array

        return CopyArray;

    }
}
