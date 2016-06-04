/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author root
 */
import client.Styles;
import client.ButtonMod;
import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application {

    /**
     * @param args the command line arguments
     */
    /**
     * @param args the command line arguments
     */
    private Label mainMessage = new Label("Welcome to our pharmacy!\n"
            + "We just became doctors yesterday.\n"
            + "Note: We are not responsible for your deaths\n"
            + "(which is gonna be a lot).\n"
            + "Happy curing!");
    private Label secondaryMessage = new Label("");

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ButtonMod buttonDiagnose = new ButtonMod("Diagnose", 175),
                buttonCure = new ButtonMod("Find Cure", 175),
                buttonPharmacies = new ButtonMod("Find Pharmacies", 175),
                buttonDisease = new ButtonMod("Disease List", 175),
                buttonMedicine = new ButtonMod("Medicine List", 175),
                buttonPharmacy = new ButtonMod("Pharmacy List", 175),
                buttonOrder = new ButtonMod("Order", 175);

        buttonDiagnose.setTooltip(new Tooltip("Get list of diseases with a symptom"));
        buttonCure.setTooltip(new Tooltip("Get the medicine(s) for a disease"));
        buttonPharmacies.setTooltip(new Tooltip("Find pharmacies selling a medicine"));
        buttonDisease.setTooltip(new Tooltip("Display disease list"));
        buttonMedicine.setTooltip(new Tooltip("Display medicnes list"));
        buttonPharmacy.setTooltip(new Tooltip("Display pharmacies list"));
        buttonOrder.setTooltip(new Tooltip("Order a medicne from a Pharmacy"));

        buttonPharmacies.setVisible(false);
        buttonCure.setVisible(false);
        buttonOrder.setVisible(false);

        TextField id1 = new TextField(),
                id2 = new TextField();

        id1.setPromptText("First id");
        id2.setPromptText("Second id");
        id1.setVisible(false);
        id2.setVisible(false);

        buttonDiagnose.setOnAction(e -> {
            secondaryMessage.setText("");
        });

        buttonCure.setOnAction(e -> {
            secondaryMessage.setText("");
        });

        buttonPharmacies.setOnAction(e -> {
            secondaryMessage.setText("");
        });

        buttonDisease.setOnAction(e -> {
            secondaryMessage.setText("");
            mainMessage.setText(listDisease());
            buttonCure.setVisible(true);
            buttonPharmacies.setVisible(false);
            buttonOrder.setVisible(false);
            id1.setVisible(true);
            id2.setVisible(false);

        });

        buttonMedicine.setOnAction(e -> {
            secondaryMessage.setText("");
            mainMessage.setText(listMedicine());
            buttonCure.setVisible(false);
            buttonPharmacies.setVisible(true);
            buttonOrder.setVisible(false);
            id1.setVisible(true);
            id2.setVisible(false);

        });

        buttonPharmacy.setOnAction(e -> {
            secondaryMessage.setText("");
            mainMessage.setText(listPharmacy());
            buttonCure.setVisible(false);
            buttonPharmacies.setVisible(false);
            buttonOrder.setVisible(true);
            id1.setVisible(true);
            id2.setVisible(true);

            id1.setText("");
            id2.setText("");
            id1.setPromptText("Pharmacy you want to buy it from");
            id2.setPromptText("Medicine you want to buy.");
        });

        buttonOrder.setOnAction(e -> {
            secondaryMessage.setText("");
            if (id1.getText().equals("") && id2.getText().equals("") || (!id2.isVisible() && !id1.isVisible())) {
                secondaryMessage.setText("ERROR: Check Inputs ");
            } else {
                int firstid = Integer.parseInt(id1.getText());
                int secondid = Integer.parseInt(id1.getText());
                if (order(firstid, secondid)) {
                    secondaryMessage.setText("Order complete, transaction reported");
                }else{
                    secondaryMessage.setText("ERROR: Check inputs");

                }

            }
        });

        VBox paneButtonsCountries = new VBox(20, buttonDisease, buttonMedicine, buttonPharmacy, buttonDiagnose);

        BorderPane paneLeft = new BorderPane(null, null, null, null, paneButtonsCountries);

        paneButtonsCountries.setAlignment(Pos.TOP_CENTER);

        paneLeft.setPadding(new Insets(20));
        paneLeft.setStyle(Styles.BACKGROUND_GREEN);

        VBox inputs = new VBox(id1, id2);
        inputs.setAlignment(Pos.BOTTOM_RIGHT);
        buttonCure.setAlignment(Pos.BOTTOM_LEFT);
        buttonPharmacies.setAlignment(Pos.BOTTOM_LEFT);
        buttonOrder.setAlignment(Pos.BASELINE_LEFT);

        mainMessage.setAlignment(Pos.CENTER);
        secondaryMessage.setAlignment(Pos.TOP_CENTER);

        VBox secondaryButtons = new VBox(buttonCure, buttonPharmacies, buttonOrder, inputs);
        secondaryButtons.setAlignment(Pos.BASELINE_LEFT);
        secondaryButtons.setSpacing(5);

        VBox paneMess = new VBox(secondaryMessage, mainMessage, secondaryButtons);

        mainMessage.setStyle(Styles.TITLE_WHITE);
        secondaryMessage.setStyle(Styles.TITLE_BLACK);
        paneMess.setStyle("-fx-background-color: darkgray;");
        paneMess.setAlignment(Pos.CENTER);

        BorderPane paneMain = new BorderPane(paneMess, null, null, new VBox(20), paneLeft);

        Scene mainScene = new Scene(paneMain, 1200, 600);

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
        });
        primaryStage.setResizable(true);
        primaryStage.setTitle("Web Service Technologies Project");
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    private static String listMedicine() {
        edu.birzeit.cs.project.web.PharamacySolutionService_Service service = new edu.birzeit.cs.project.web.PharamacySolutionService_Service();
        edu.birzeit.cs.project.web.PharamacySolutionService port = service.getPharamacySolutionServicePort();
        return port.listMedicine();
    }

    private static String listDisease() {
        edu.birzeit.cs.project.web.PharamacySolutionService_Service service = new edu.birzeit.cs.project.web.PharamacySolutionService_Service();
        edu.birzeit.cs.project.web.PharamacySolutionService port = service.getPharamacySolutionServicePort();
        return port.listDisease();
    }

    private static String listPharmacy() {
        edu.birzeit.cs.project.web.PharamacySolutionService_Service service = new edu.birzeit.cs.project.web.PharamacySolutionService_Service();
        edu.birzeit.cs.project.web.PharamacySolutionService port = service.getPharamacySolutionServicePort();
        return port.listPharmacy();
    }

    private static java.util.List<edu.birzeit.cs.project.web.Medicine> getCure(int diseaseId) {
        edu.birzeit.cs.project.web.PharamacySolutionService_Service service = new edu.birzeit.cs.project.web.PharamacySolutionService_Service();
        edu.birzeit.cs.project.web.PharamacySolutionService port = service.getPharamacySolutionServicePort();
        return port.getCure(diseaseId);
    }

    private static boolean order(int pharmId, int medId) {
        edu.birzeit.cs.project.web.PharamacySolutionService_Service service = new edu.birzeit.cs.project.web.PharamacySolutionService_Service();
        edu.birzeit.cs.project.web.PharamacySolutionService port = service.getPharamacySolutionServicePort();
        return port.order(pharmId, medId);
    }

}
