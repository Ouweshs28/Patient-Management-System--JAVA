package patientmanagementsystemgui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Helper Class to input patient
 */
public class AddPatient {

    static DatePicker nextAppDatePick;
    static Stage addWindow;
    static TextField inputPatientID, inputPatientFName, inputPatientLName, inputMedicalCond, inputPhoneNo, inputComments, inputBill;
    static ComboBox inputBillStatus;

    /**
     * @throws Exception Input Patient GUI Method
     */

    public static void InputPatient() throws Exception {

        addWindow = new Stage();
        addWindow.getIcons().add(new Image("file:images/logo.png"));
        addWindow.setTitle("Add new patient");
        addWindow.setResizable(true);
        addWindow.initModality(Modality.APPLICATION_MODAL);

        Locale.setDefault(Locale.getDefault());


        GridPane addPatient = new GridPane();
        addPatient.setAlignment(Pos.CENTER);
        addPatient.setVgap(30);

        Label addPatientTitle = new Label();
        addPatientTitle.setText("Add a new patient to the system");
        addPatientTitle.setAlignment(Pos.CENTER);
        addPatientTitle.setPrefWidth(Double.MAX_VALUE);

        Text patientID = new Text("Enter PatientID :");
        inputPatientID = new TextField();
        inputPatientID.setPromptText("enter patient id (integers)");
        inputPatientID.setFocusTraversable(false);

        Text patientFName = new Text("Enter Patient First Name :");
        inputPatientFName = new TextField();
        inputPatientFName.setPromptText("enter patient first name");
        inputPatientFName.setFocusTraversable(false);

        Text patientLName = new Text("Enter Patient Last Name:");
        inputPatientLName = new TextField();
        inputPatientLName.setPromptText("enter patient last name");
        inputPatientLName.setFocusTraversable(false);

        Text medicalCond = new Text("Enter Patient Medical Condition :");
        inputMedicalCond = new TextField();
        inputMedicalCond.setPromptText("enter patient condition");
        inputMedicalCond.setFocusTraversable(false);

        Text nextAppdate = new Text("Enter Patient Next Appointment Date :");
        nextAppDatePick = new DatePicker();
        nextAppDatePick.setMinWidth(400);
        nextAppDatePick.setFocusTraversable(false);

        Text phoneNo = new Text("Enter Patient Phone Number :");
        inputPhoneNo = new TextField();
        inputPhoneNo.setPromptText("enter patient phone number");
        inputPhoneNo.setFocusTraversable(false);

        Text billStatus = new Text("Enter Patient Bill Status :");
        inputBillStatus = new ComboBox();
        inputBillStatus.setMinWidth(400);
        inputBillStatus.setFocusTraversable(false);
        inputBillStatus.getItems().addAll("Paid", "Pending");
        inputBillStatus.setValue("Choose");

        Text comments = new Text("Enter Patient Comments :");
        inputComments = new TextField();
        inputComments.setPromptText("enter patient comments");
        inputComments.setFocusTraversable(false);

        Text bill = new Text("Enter Patient Bill :");
        inputBill = new TextField();
        inputBill.setPromptText("enter patient bill (0 and above)");
        inputBill.setFocusTraversable(false);

        Button btnAdd = new Button("Add");
        btnAdd.setMinWidth(185);
        btnAdd.setFocusTraversable(false);
        btnAdd.setOnAction(e -> {
            try {

                AddPatient();

            } catch (Exception ex) {
            }
        });

        Button btnCancel = new Button("Cancel");
        btnCancel.setMinWidth(185);
        btnCancel.setFocusTraversable(false);
        btnCancel.setOnAction(e -> addWindow.close());

        HBox Hbtns = new HBox(40);
        Hbtns.getChildren().addAll(btnAdd, btnCancel);
        Hbtns.setAlignment(Pos.CENTER);

        addPatient.add(addPatientTitle, 0, 0, 2, 1);
        addPatient.add(patientID, 0, 2);
        addPatient.add(inputPatientID, 1, 2);
        addPatient.add(patientFName, 0, 3);
        addPatient.add(inputPatientFName, 1, 3);
        addPatient.add(patientLName, 0, 4);
        addPatient.add(inputPatientLName, 1, 4);
        addPatient.add(medicalCond, 0, 5);
        addPatient.add(inputMedicalCond, 1, 5);
        addPatient.add(nextAppdate, 0, 6);
        addPatient.add(nextAppDatePick, 1, 6);
        addPatient.add(phoneNo, 0, 7);
        addPatient.add(inputPhoneNo, 1, 7);
        addPatient.add(billStatus, 0, 8);
        addPatient.add(inputBillStatus, 1, 8);
        addPatient.add(comments, 0, 9);
        addPatient.add(inputComments, 1, 9);
        addPatient.add(bill, 0, 10);
        addPatient.add(inputBill, 1, 10);
        addPatient.add(Hbtns, 0, 12, 2, 1);
        addPatient.setPadding(new Insets(10, 60, 10, 60));
        addPatient.setHgap(50);

        Scene scene = new Scene(addPatient);

        addWindow.setScene(scene);
        addWindow.setMaximized(true);
        addWindow.showAndWait();

    }

    /**
     * @throws Exception in case file not found or there is an error
     *                   Validations of different data text fields and duplication
     *                   checks if filed is empty as well
     */
    private static void AddPatient() throws Exception {
        /*
         *
         * Alphacheck is a regex that verifies alphabets within a string
         * Numcheck is a regex that verifies numbers with a string
         * Pricecheck is a regex that verifies that a string has number and decimal
         * */

        String dateToday = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String alphacheck = "^[a-zA-Z]+$"; // range that accepts only alphabets
        String numcheck = "^[0-9]+$"; //accepts only numbers
        String pricecheck = "-?\\d+(\\.\\d+)?";
        String empty = "";

        String patientID = inputPatientID.getText();
        String patientFName = inputPatientFName.getText();
        String patientLName = inputPatientLName.getText();
        String medicalCond = inputMedicalCond.getText();
        String phoneNo = inputPhoneNo.getText();
        String billStatus = String.valueOf(inputBillStatus.getValue());
        String comments = inputComments.getText();
        String bill = inputBill.getText();


        if (patientID.equalsIgnoreCase(empty)) {
            String messageIDEmpty = "Patient ID field cannot be empty !";
            MessageBox.box(messageIDEmpty);
            return;
        } else if (!patientID.matches(numcheck)) {
            String messageErrorName = "Error ! Please enter a number and try again !";
            MessageBox.box(messageErrorName);
            return;
        } else if (SimilarityCheck.patientID(patientID) == true) {
            String messageIDExists = "Error ! Patient ID already exists !\nCheck and try again !";
            MessageBox.box(messageIDExists);
            return;
        } else if (patientFName.equalsIgnoreCase(empty)) {
            String messageFNameEmpty = "First Patient Name field cannot be empty !";
            MessageBox.box(messageFNameEmpty);
            return;
        } else if (!patientFName.matches(alphacheck)) {
            String messageErrorFNameCode = "Error ! please enter alphabets and try again !";
            MessageBox.box(messageErrorFNameCode);
            return;
        } else if (patientLName.equalsIgnoreCase(empty)) {
            String messageCodeLNameEmpty = "Last Patient Name field cannot be empty !";
            MessageBox.box(messageCodeLNameEmpty);
            return;
        } else if (!patientLName.matches(alphacheck)) {
            String messageErrorLNameCode = "Error ! please enter alphabets and try again !";
            MessageBox.box(messageErrorLNameCode);
            return;
        } else if (medicalCond.equalsIgnoreCase(empty)) {
            String messageEmptyMedicalCond = "Medical Condition field cannot be empty !";
            MessageBox.box(messageEmptyMedicalCond);
            return;
        } else if (nextAppDatePick.getValue() == null) {
            MessageBox.box("Please select a date");
            return;
        } else if ((nextAppDatePick.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).compareTo(dateToday) < 0) || (nextAppDatePick.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).compareTo(dateToday) == 0)) {
            MessageBox.box("Choose a future date !");
            return;
        } else if (phoneNo.equalsIgnoreCase(empty)) {
            String messageEmptyPhone = "Phone Number field cannot be empty !";
            MessageBox.box(messageEmptyPhone);
            return;
        } else if (!phoneNo.matches(numcheck)) {
            String messageErrorPhone = "Error ! Please enter numbers in phone field and try again !";
            MessageBox.box(messageErrorPhone);
            return;
        } else if (billStatus.equalsIgnoreCase("Choose")) {
            String messageEmptyBill = "Please choose a bill status !";
            MessageBox.box(messageEmptyBill);
            return;
        } else if (comments.equalsIgnoreCase(empty)) {
            String messageEmptyComments = "Comments field cannot be empty !";
            MessageBox.box(messageEmptyComments);
            return;
        } else if (!bill.matches(pricecheck) || bill.equalsIgnoreCase("0")) {
            String messageErrorBill = "Error ! Price should be numbers. Try again !";
            MessageBox.box(messageErrorBill);
            return;
        } else if (bill.equalsIgnoreCase(empty)) {
            String messageEmptyBill = "Bill field cannot be empty !";
            MessageBox.box(messageEmptyBill);
            return;
        }

        double b = Double.parseDouble(bill);
        int id = Integer.parseInt(patientID);

        String date = nextAppDatePick.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Patient Input = new Patient(id, patientFName, patientLName, medicalCond, date, phoneNo, billStatus, comments, b);
        Patient[] PatientArray = PatientFileHandling.readFile(); /*Reads Stored PatientObject Arrays from the file*/

        PatientFileHandling.appendFile(PatientArray, Input);

        MessageBox.box("Successfully added !");

        addWindow.close();
    }

}
