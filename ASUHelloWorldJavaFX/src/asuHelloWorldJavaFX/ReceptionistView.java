package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ReceptionistView extends Application {

    private Label lastPatientIdLabel; // Label to display the last generated patient ID

    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(20, 50, 20, 50));

        Label titleLabel = new Label("Patient Intake Form");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Create and configure the grid pane for form inputs
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(20);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Form fields
        TextField firstNameTextField = new TextField();
        TextField lastNameTextField = new TextField();
        TextField emailTextField = new TextField();
        TextField phoneTextField = new TextField();
        TextField healthHistoryTextField = new TextField();
        TextField insuranceIDTextField = new TextField();

        // Populate the grid with labels and corresponding text fields
        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstNameTextField, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastNameTextField, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailTextField, 1, 2);
        grid.add(new Label("Phone Number:"), 0, 3);
        grid.add(phoneTextField, 1, 3);
        grid.add(new Label("Health History:"), 0, 4);
        grid.add(healthHistoryTextField, 1, 4);
        grid.add(new Label("Insurance ID:"), 0, 5);
        grid.add(insuranceIDTextField, 1, 5);

        // Create the save button with an event handler to save patient data and update the patient ID label
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white;");
        saveButton.setMinWidth(100);
        saveButton.setOnAction(e -> {
            String patientID = generatePatientID();
            savePatientData(patientID,
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    emailTextField.getText(),
                    phoneTextField.getText(),
                    healthHistoryTextField.getText(),
                    insuranceIDTextField.getText());

            lastPatientIdLabel.setText("Last Generated Patient ID: " + patientID);
        });

        // Initialize the label for displaying the last generated patient ID
        lastPatientIdLabel = new Label("Last Generated Patient ID: None");
        lastPatientIdLabel.setStyle("-fx-font-size: 14px;");

        vbox.getChildren().addAll(titleLabel, grid, saveButton, lastPatientIdLabel);
        Scene scene = new Scene(vbox, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Patient Intake Form");
        primaryStage.show();
    }

    private String generatePatientID() {
        Random random = new Random();
        return String.format("%05d", random.nextInt(100000));
    }

    private void savePatientData(String patientID, String firstName, String lastName, String email, String phone, String healthHistory, String insuranceID) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(patientID + "_PatientInfo.txt"))) {
            writer.write("Patient ID: " + patientID);
            writer.newLine();
            writer.write("First Name: " + firstName);
            writer.newLine();
            writer.write("Last Name: " + lastName);
            writer.newLine();
            writer.write("Email: " + email);
            writer.newLine();
            writer.write("Phone: " + phone);
            writer.newLine();
            writer.write("Health History: " + healthHistory);
            writer.newLine();
            writer.write("Insurance ID: " + insuranceID);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
