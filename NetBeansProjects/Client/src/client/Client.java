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
        
        buttonDiagnose.setVisible(false);
        buttonCure.setVisible(false);
        buttonOrder.setVisible(false);
        
        TextField id1 = new TextField(),
                id2 = new TextField();

        id1.setPromptText("First id");
        id2.setPromptText("Second id");
        id1.setVisible(false);
        id2.setVisible(false);
        
        buttonDiagnose.setOnAction(e -> {
        });

        buttonCure.setOnAction(e -> {

        });

        buttonPharmacies.setOnAction(e -> {

        });

        buttonDisease.setOnAction(e -> {
            mainMessage.setText(listDisease());

        });

        buttonMedicine.setOnAction(e -> {
            mainMessage.setText(listMedicine());

        });

        buttonPharmacy.setOnAction(e -> {
            mainMessage.setText(listPharmacy());
        });

        buttonOrder.setOnAction(e -> {

        });

        VBox paneButtonsCountries = new VBox(20, buttonDisease, buttonMedicine, buttonPharmacy);

        BorderPane paneLeft = new BorderPane(null, null, null, null, paneButtonsCountries);

        paneButtonsCountries.setAlignment(Pos.TOP_CENTER);

        paneLeft.setPadding(new Insets(20));
        paneLeft.setStyle(Styles.BACKGROUND_GREEN);

        mainMessage.setAlignment(Pos.CENTER);
        buttonDiagnose.setAlignment(Pos.BOTTOM_LEFT);
        buttonOrder.setAlignment(Pos.BOTTOM_RIGHT);
        id1.setAlignment(Pos.BOTTOM_RIGHT);
        id2.setAlignment(Pos.BOTTOM_RIGHT);
        VBox paneMess = new VBox(mainMessage);

        mainMessage.setStyle(Styles.TITLE_WHITE);
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

}
