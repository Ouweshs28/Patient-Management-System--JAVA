package patientmanagementsystemgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * A helper class to search
 */
public class Search {

    static TableView tablePatient;
    static Stage searchWindow, resultWindow, table;
    static TextField inputCriteria, patientIDField, fNameField, lNameField, medCond, nextDate, phone, billStatus, comments, bill;

    /**
     * @throws Exception File exception handling
     *                   Provides different search options according to thee data available in the system
     */
    public static void choice() throws Exception {
        searchWindow = new Stage();
        searchWindow.getIcons().add(new Image("file:images/logo.png"));
        searchWindow.setTitle("Search for an item");
        searchWindow.setResizable(false);
        searchWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane searchItem = new GridPane();
        searchItem.setAlignment(Pos.CENTER);
        searchItem.setVgap(30);

        Label searchLabel = new Label();
        searchLabel.setText("Search for a patient");
        searchLabel.setAlignment(Pos.CENTER);
        searchLabel.setPrefWidth(Double.MAX_VALUE);

        Text searchText = new Text("Search by :");
        final ToggleGroup searchBy = new ToggleGroup();
        RadioButton choiceID = new RadioButton("Patient ID");
        choiceID.setToggleGroup(searchBy);
        choiceID.setSelected(true);
        RadioButton choiceFName = new RadioButton("First name");
        choiceFName.setToggleGroup(searchBy);
        RadioButton choiceLName = new RadioButton("last name");
        choiceLName.setToggleGroup(searchBy);
        RadioButton choiceMedCond = new RadioButton("Medical Condition");
        choiceMedCond.setToggleGroup(searchBy);
        RadioButton choiceDate = new RadioButton("Date");
        choiceDate.setToggleGroup(searchBy);

        Text inputCriteriaText = new Text("Input criteria here :");
        inputCriteria = new TextField();
        inputCriteria.setPromptText("input criteria here");
        inputCriteria.setFocusTraversable(false);

        Button btnSearch = new Button("Search");
        btnSearch.setMinWidth(250);
        btnSearch.setFocusTraversable(false);
        btnSearch.setOnAction(e -> {
            try {
                if (inputCriteria.getText().equalsIgnoreCase("")) {
                    MessageBox.box("Criteria field cannot be empty !");
                } else if (choiceID.isSelected()) {
                    patientID();
                } else if (choiceFName.isSelected()) {
                    table(1);
                } else if (choiceLName.isSelected()) {
                    table(2);
                } else if (choiceMedCond.isSelected()) {
                    table(3);
                } else if (choiceDate.isSelected()) {
                    table(4);
                }

            } catch (Exception ex) {
            }
        });

        Button btnCancel = new Button("Cancel");
        btnCancel.setMinWidth(250);
        btnCancel.setFocusTraversable(false);
        btnCancel.setOnAction(e -> searchWindow.close());

        HBox Hbtns = new HBox(40);
        Hbtns.getChildren().addAll(btnSearch, btnCancel);
        Hbtns.setAlignment(Pos.CENTER);

        searchItem.add(searchLabel, 0, 0, 5, 1);
        searchItem.add(searchText, 0, 2);
        searchItem.add(choiceID, 2, 2);
        searchItem.add(choiceFName, 3, 2);
        searchItem.add(choiceLName, 4, 2);
        searchItem.add(choiceMedCond, 2, 3);
        searchItem.add(choiceDate, 3, 3);
        searchItem.add(inputCriteriaText, 0, 6);
        searchItem.add(inputCriteria, 1, 6, 4, 1);
        searchItem.add(Hbtns, 0, 8, 5, 1);
        searchItem.setPadding(new Insets(10, 250, 10, 250));
        searchItem.setHgap(50);
        Scene scene = new Scene(searchItem);

        searchWindow.setScene(scene);
        searchWindow.setMaximized(true);
        searchWindow.showAndWait();

    }

    /**
     * @throws Exception Validates input of patientID
     *                   Does the searching accordingly
     *                   If found it returns -1 else it returns the index of the round patient
     */
    private static void patientID() throws Exception {

        if (!inputCriteria.getText().matches("^[0-9+]+$")) {
            MessageBox.box("Invalid input. Try again !");
            return;
        }

        Patient[] PatientArray = PatientFileHandling.readFile();

        int result = -1;

        for (int i = 0; i < PatientArray.length; i++) {
            if (PatientArray[i].getPatientID() == Integer.parseInt(inputCriteria.getText())) {
                result = i;
            }
        }

        if (result == -1) {
            MessageBox.box("No patient found !");
            return;
        }

        resultWindow = new Stage();
        resultWindow.getIcons().add(new Image("file:images/logo.png"));
        resultWindow.setTitle("Search result");
        resultWindow.setResizable(false);
        resultWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane confirmPane = new GridPane();
        confirmPane.setAlignment(Pos.CENTER);
        confirmPane.setVgap(30);

        Label searchResultLabel = new Label();
        searchResultLabel.setText("Search Result");
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

        Button btnMainMenu = new Button("Go to main menu");
        btnMainMenu.setMinWidth(250);
        btnMainMenu.setFocusTraversable(false);
        btnMainMenu.setOnAction(e -> {
            try {
                searchWindow.close();
                resultWindow.close();
            } catch (Exception ex) {
            }
        });

        Button btnAnotherSearch = new Button("Search again");
        btnAnotherSearch.setMinWidth(250);
        btnAnotherSearch.setFocusTraversable(false);
        btnAnotherSearch.setOnAction(e -> {
            try {
                resultWindow.close();
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

        resultWindow.setScene(scene);
        resultWindow.setMaximized(true);
        resultWindow.showAndWait();
    }

    /**
     * @param choice - the choice by the user
     * @throws Exception
     */

    private static void table(int choice) throws Exception {

        Patient[] PatientArray = PatientFileHandling.readFile();

        table = new Stage();
        table.getIcons().add(new Image("file:images/logo.png"));
        table.setResizable(true);
        table.setTitle("Table");

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double columnWidth = screenWidth / 9;

        try {
            TableColumn<Patient, Integer> IDColumns = new TableColumn<>("PatientID");
            IDColumns.setCellValueFactory(new PropertyValueFactory<>("patientID"));
            IDColumns.setMinWidth(columnWidth);
            IDColumns.setResizable(false);
            IDColumns.setSortable(false);

            TableColumn<Patient, String> FName = new TableColumn<>("First Name");
            FName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            FName.setMinWidth(153);
            FName.setResizable(false);
            FName.setSortable(false);

            TableColumn<Patient, String> LNameColumns = new TableColumn<>("Last Name");
            LNameColumns.setCellValueFactory(new PropertyValueFactory<>("LastName"));
            LNameColumns.setMinWidth(columnWidth);
            LNameColumns.setSortable(false);
            LNameColumns.setResizable(false);

            TableColumn<Patient, String> MedCondColumns = new TableColumn<>("Medical Condition");
            MedCondColumns.setCellValueFactory(new PropertyValueFactory<>("MedicalCondition"));
            MedCondColumns.setMinWidth(columnWidth);
            MedCondColumns.setSortable(false);
            MedCondColumns.setResizable(false);

            TableColumn<Patient, String> DateColumns = new TableColumn<>("Next Appointment Date");
            DateColumns.setCellValueFactory(new PropertyValueFactory<>("NextAppointmentDate"));
            DateColumns.setMinWidth(columnWidth);
            DateColumns.setSortable(false);
            DateColumns.setResizable(false);

            TableColumn<Patient, String> PhoneNo = new TableColumn<>("Phone Number");
            PhoneNo.setCellValueFactory(new PropertyValueFactory<>("PhoneNo"));
            PhoneNo.setMinWidth(columnWidth);
            PhoneNo.setSortable(false);
            PhoneNo.setResizable(false);

            TableColumn<Patient, String> BillStatus = new TableColumn<>("Billing Status");
            BillStatus.setCellValueFactory(new PropertyValueFactory<>("BillingStatus"));
            BillStatus.setMinWidth(columnWidth);
            BillStatus.setSortable(false);
            BillStatus.setResizable(false);

            TableColumn<Patient, String> Comments = new TableColumn<>("Comments");
            Comments.setCellValueFactory(new PropertyValueFactory<>("Comments"));
            Comments.setMinWidth(columnWidth);
            Comments.setSortable(false);
            Comments.setResizable(false);

            TableColumn<Patient, Double> Bill = new TableColumn<>("Bill (Rs)");
            Bill.setCellValueFactory(new PropertyValueFactory<>("Bill"));
            Bill.setMinWidth(columnWidth);
            Bill.setSortable(false);
            Bill.setResizable(false);

            tablePatient = new TableView<>();
            tablePatient.setFocusTraversable(false);
            tablePatient.setEditable(false);
            tablePatient.prefHeightProperty().bind(table.heightProperty());
            tablePatient.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            if (choice == 1) {
                for (int i = 0; i < PatientArray.length; i++) {
                    if (inputCriteria.getText().equalsIgnoreCase(PatientArray[i].getFirstName())) {
                        tablePatient.getItems().add(new Patient(PatientArray[i].getPatientID(),
                                PatientArray[i].getFirstName(),
                                PatientArray[i].getLastName(),
                                PatientArray[i].getMedicalCondition(),
                                PatientArray[i].getNextAppointmentDate(),
                                PatientArray[i].getPhoneNo(),
                                PatientArray[i].getBillingStatus(),
                                PatientArray[i].getComments(),
                                PatientArray[i].getBill()));
                    }
                }
            } else if (choice == 2) {
                for (Patient PatientArray1 : PatientArray) {
                    if (inputCriteria.getText().equalsIgnoreCase(PatientArray1.getLastName())) {
                        tablePatient.getItems().add(new Patient(PatientArray1.getPatientID(), PatientArray1.getFirstName(), PatientArray1.getLastName(), PatientArray1.getMedicalCondition(), PatientArray1.getNextAppointmentDate(), PatientArray1.getPhoneNo(), PatientArray1.getBillingStatus(), PatientArray1.getComments(), PatientArray1.getBill()));
                    }
                }

            } else if (choice == 3) {
                for (Patient PatientArray1 : PatientArray) {
                    if (inputCriteria.getText().equalsIgnoreCase(PatientArray1.getMedicalCondition())) {
                        tablePatient.getItems().add(new Patient(PatientArray1.getPatientID(), PatientArray1.getFirstName(), PatientArray1.getLastName(), PatientArray1.getMedicalCondition(), PatientArray1.getNextAppointmentDate(), PatientArray1.getPhoneNo(), PatientArray1.getBillingStatus(), PatientArray1.getComments(), PatientArray1.getBill()));
                    }
                }
            } else if (choice == 4) {
                for (Patient PatientArray1 : PatientArray) {
                    if (inputCriteria.getText().equalsIgnoreCase(PatientArray1.getNextAppointmentDate())) {
                        tablePatient.getItems().add(new Patient(PatientArray1.getPatientID(), PatientArray1.getFirstName(), PatientArray1.getLastName(), PatientArray1.getMedicalCondition(), PatientArray1.getNextAppointmentDate(), PatientArray1.getPhoneNo(), PatientArray1.getBillingStatus(), PatientArray1.getComments(), PatientArray1.getBill()));
                    }
                }

            }

            tablePatient.getColumns().addAll(IDColumns, FName, LNameColumns, MedCondColumns, DateColumns, PhoneNo, BillStatus, Comments, Bill);
        } catch (Exception e) {
        }

        Button btnBack = new Button("Back");
        btnBack.setPadding(new Insets(10, 10, 10, 10));
        btnBack.setMinWidth(200);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e -> table.close());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(tablePatient, btnBack);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane bp = new BorderPane();
        bp.setCenter(vbox);

        Scene scene = new Scene(bp);
        table.setScene(scene);
        table.setMaximized(true);
        table.show();
        searchWindow.close();

    }

}
