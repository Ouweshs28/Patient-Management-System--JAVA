package patientmanagementsystemgui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * A helper class that displays the data on table
 */
public class ViewAllPatients {

    static Stage tableWindow, choiceWindow;
    static TableView tablePatient;

    /**
     * @throws Exception in case file not found
     *                   Allows user input for sorting
     */
    public static void choice() throws Exception {

        choiceWindow = new Stage();
        choiceWindow.setTitle("Viewing all items - sort by");
        choiceWindow.setResizable(false);
        choiceWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane viewAll = new GridPane();
        viewAll.setAlignment(Pos.CENTER);
        viewAll.setVgap(30);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label programTitle = new Label();
        programTitle.setText("Viewing all patients ...");
        vbox.getChildren().add(programTitle);

        Label sortBy = new Label();
        sortBy.setText("\n\nChoose sorting method :");
        vbox.getChildren().add(sortBy);

        Button btnPatientFName = new Button("First Name");
        btnPatientFName.setAlignment(Pos.BASELINE_LEFT);
        btnPatientFName.setMaxWidth(250);
        btnPatientFName.setFocusTraversable(false);
        btnPatientFName.setOnAction(e -> {
            try {
                Patient[] PatientArray = PatientFileHandling.readFile();
                PatientArray = Sort.bubleSortFName(PatientArray);
                ViewAllPatients.Table(PatientArray);
            } catch (Exception ex) {
            }
        });

        Button btnPatientLName = new Button("Last Name");
        btnPatientLName.setAlignment(Pos.BASELINE_LEFT);
        btnPatientLName.setMaxWidth(250);
        btnPatientLName.setFocusTraversable(false);
        btnPatientLName.setOnAction(e -> {
            try {
                Patient[] PatientArray = PatientFileHandling.readFile();
                PatientArray = Sort.bubleSortLName(PatientArray);
                ViewAllPatients.Table(PatientArray);
            } catch (Exception ex) {
            }
        });

        Button btnPatientID = new Button("Patient ID");
        btnPatientID.setAlignment(Pos.BASELINE_LEFT);
        btnPatientID.setMaxWidth(250);
        btnPatientID.setFocusTraversable(false);
        btnPatientID.setOnAction(e -> {
            try {
                Patient[] PatientArray = PatientFileHandling.readFile();
                PatientArray = Sort.PatientMergeSortID(PatientArray);
                ViewAllPatients.Table(PatientArray);
            } catch (Exception ex) {
            }
        });

        Button btnBack = new Button("Back");
        btnBack.setText("Back");
        btnBack.setAlignment(Pos.BASELINE_LEFT);
        btnBack.setMaxWidth(250);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e -> choiceWindow.close());

        viewAll.getChildren().add(vbox);

        viewAll.add(btnPatientID, 0, 1);
        viewAll.add(btnPatientFName, 0, 2);
        viewAll.add(btnPatientLName, 0, 3);
        viewAll.add(btnBack, 0, 5);

        Scene scene = new Scene(viewAll);

        choiceWindow.setScene(scene);
        choiceWindow.setMaximized(true);
        choiceWindow.showAndWait();
    }

    /**
     *
     * @param PatientArray
     * @throws Exception
     */
    public static void Table(Patient[] PatientArray) throws Exception {
        tableWindow = new Stage();
        tableWindow.getIcons().add(new Image("file:images/logo.png"));
        tableWindow.setResizable(false);
        tableWindow.setTitle("Table");

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
            tablePatient.prefHeightProperty().bind(tableWindow.heightProperty());
            tablePatient.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            for (Patient PatientArray1 : PatientArray) {
                tablePatient.getItems().add(new Patient(PatientArray1.getPatientID(), PatientArray1.getFirstName(), PatientArray1.getLastName(), PatientArray1.getMedicalCondition(), PatientArray1.getNextAppointmentDate(), PatientArray1.getPhoneNo(), PatientArray1.getBillingStatus(), PatientArray1.getComments(), PatientArray1.getBill()));
            }

            tablePatient.getColumns().addAll(IDColumns, FName, LNameColumns, MedCondColumns, DateColumns, PhoneNo, BillStatus, Comments, Bill);
        } catch (Exception e) {
        }

        Button btnBack = new Button("Back");
        btnBack.setPadding(new Insets(10, 10, 10, 10));
        btnBack.setMinWidth(200);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e -> tableWindow.close());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(tablePatient, btnBack);
        vbox.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane bp = new BorderPane();
        bp.setCenter(vbox);

        Scene scene = new Scene(bp);
        tableWindow.setScene(scene);
        tableWindow.setMaximized(true);
        tableWindow.show();
    }
}
