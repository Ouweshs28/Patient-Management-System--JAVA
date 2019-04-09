package patientmanagementsystemgui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This is the main class extending it to application for GUI
 */
public class PatientManagementSystemGUI extends Application {

    /**
     * @param args Launches the main method
     */
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * @param primaryStage The main GUI Application
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Quit End = new Quit();

        GridPane mainMenu = new GridPane();
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setVgap(30);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label programTitle = new Label();
        programTitle.setText("Patient Management System");
        vbox.getChildren().add(programTitle);

        Button btnAdd = new Button("Add Patient");
        btnAdd.setAlignment(Pos.BASELINE_LEFT);
        btnAdd.setMaxWidth(250);
        btnAdd.setFocusTraversable(false);
        btnAdd.setOnAction(e -> {
            try {
                AddPatient NewPatient = new AddPatient();
                NewPatient.InputPatient();
            } catch (Exception ex) {
            }
        });

        Button btnRemove = new Button("Remove a Patient");
        btnRemove.setAlignment(Pos.BASELINE_LEFT);
        btnRemove.setMaxWidth(250);
        btnRemove.setFocusTraversable(false);
        btnRemove.setOnAction(e -> {
            try {
                RemovePatient DelPatient = new RemovePatient();
                DelPatient.removePatient();
            } catch (Exception ex) {
            }
        });

        Button btnDisplayAll = new Button("Show all Patient Stored");
        btnDisplayAll.setAlignment(Pos.BASELINE_LEFT);
        btnDisplayAll.setMaxWidth(250);
        btnDisplayAll.setFocusTraversable(false);
        btnDisplayAll.setOnAction(e -> {
            try {
                ViewAllPatients Display = new ViewAllPatients();
                Display.choice();
            } catch (Exception ex) {
            }

        });

        Button btnSearch = new Button("Search for Patient details");
        btnSearch.setAlignment(Pos.BASELINE_LEFT);
        btnSearch.setMaxWidth(250);
        btnSearch.setFocusTraversable(false);
        btnSearch.setOnAction(e -> {
            try {
                Search SearchPatient = new Search();
                SearchPatient.choice();
            } catch (Exception ex) {
            }
        });

        Button btnExit = new Button("Exit");
        btnExit.setAlignment(Pos.BASELINE_LEFT);
        btnExit.setMaxWidth(250);
        btnExit.setFocusTraversable(false);
        btnExit.setOnAction(e -> {
            try {

                End.Exit();
            } catch (Exception ex) {
            }
        });

        Text about = new Text("Ouwesh Seeroo");

        mainMenu.getChildren().add(vbox);
        mainMenu.add(btnAdd, 0, 1);
        mainMenu.add(btnRemove, 0, 2);
        mainMenu.add(btnDisplayAll, 0, 3);
        mainMenu.add(btnSearch, 0, 4);
        mainMenu.add(btnExit, 0, 6);
        mainMenu.add(about, 0, 8);
        mainMenu.setHgap(50);

        Scene scene = new Scene(mainMenu);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Patient Management System");
        primaryStage.setMaximized(true);
        primaryStage.setOnCloseRequest(e -> {
            try {
                e.consume();
                End.Exit();

            } catch (Exception ex) {
            }
        });
        primaryStage.setResizable(true);
        primaryStage.getIcons().add(new Image("file:images/logo.png"));
        primaryStage.show();
    }
}
