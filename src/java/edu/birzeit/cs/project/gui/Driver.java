package edu.birzeit.cs.project.gui;

// READ ME HERE MOHANNAD
// If you think the GUI sucks, tell me. Me lazy guy.
// 53rd line has setToolTip, write in the string what order does.
// For each button there is setOnAction, between the braces
// you can write any method like as it normally works.
// To change messages, write mainMessage.setText(A String).
// Two text fields are used, to use one, write id1.getText(), or id2...
// The message indicates the returned object, as we write its
// full info. If you think of something else, tell me.
// Now study web service, I'll be depending on you tomorrow. XD
import edu.birzeit.cs.project.web.PharamacySolutionService;
import javax.xml.ws.WebServiceRef;
import javafx.application.Application;
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

public class Driver extends Application {

    private Label mainMessage = new Label("Welcome to our pharmacy!\n"
            + "We just became doctors yesterday.\n"
            + "Note: We are not responsible for your deaths\n"
            + "(which is gonna be a lot).\n"
            + "Happy curing!");

    public static void main(String[] args) {
        Application.launch(args);
    }
    @WebServiceRef(wsdlLocation="http://localhost:8080/Course-Project/PharamacySolutionService?wsdl")
    static PharamacySolutionService service;
    
     
    public void start(Stage primaryStage) throws Exception {

        ButtonMod //buttonDiagnose = new ButtonMod("Diagnose", 175),
                //				  buttonCure = new ButtonMod("Find Cure", 175),
                //				  buttonPharmacies = new ButtonMod("Find Pharmacies", 175),
                buttonDisease = new ButtonMod("Pharmacy List", 175),
                buttonMedicine = new ButtonMod("Disease List", 175),
                buttonPharmacy = new ButtonMod("Medicnes List", 175)
                //				  ,buttonOrder = new ButtonMod("Order", 175)
                ;

//		buttonDiagnose.setTooltip(new Tooltip("Get list of diseases with a symptom"));
//		buttonCure.setTooltip(new Tooltip("Get the medicine(s) for a disease"));
//		buttonPharmacies.setTooltip(new Tooltip("Find pharmacies selling a medicine"));
        buttonDisease.setTooltip(new Tooltip("Display a list of Diseases"));
        buttonMedicine.setTooltip(new Tooltip("Display a list of Medicnes"));
        buttonPharmacy.setTooltip(new Tooltip("Display a list of Diseases"));
//		buttonOrder.setTooltip(new Tooltip("MOHANNAD WRITE HERE WHATEVER ORDER SERVICE MEANS"));

        TextField id1 = new TextField(),
                id2 = new TextField();

        id1.setPromptText("First id");
        id2.setPromptText("Second id");

//        buttonDiagnose.setOnAction(e -> {
//            mainMessage.setText("Pharm");
//        });
//
//        buttonCure.setOnAction(e -> {
//
//        });
//
//        buttonPharmacies.setOnAction(e -> {
//
//        });

        buttonDisease.setOnAction(e -> {
            System.out.println(service.listtDisease());
            mainMessage.setText("");
        });

        buttonMedicine.setOnAction(e -> {

        });

        buttonPharmacy.setOnAction(e -> {

        });

//        buttonOrder.setOnAction(e -> {
//
//        });

        VBox paneButtonsCountries = new VBox(20, 
                //buttonDiagnose, buttonCure, buttonPharmacies,
                buttonDisease, buttonMedicine, buttonPharmacy,
                //buttonOrder,
                id1, id2);

        BorderPane paneLeft = new BorderPane(null, null, null, null, paneButtonsCountries);

        paneButtonsCountries.setAlignment(Pos.TOP_CENTER);

        paneLeft.setPadding(new Insets(20));
        paneLeft.setStyle(Styles.BACKGROUND_GREEN);

        mainMessage.setAlignment(Pos.CENTER);
        VBox paneMess = new VBox(mainMessage);

        mainMessage.setStyle(Styles.TITLE_WHITE);
        paneMess.setStyle("-fx-background-color: darkgray;");
        paneMess.setAlignment(Pos.CENTER);

        BorderPane paneMain = new BorderPane(paneMess, null, null, new VBox(20), paneLeft);

        Scene mainScene = new Scene(paneMain, 700, 520);

        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
        });
        primaryStage.setResizable(true);
        primaryStage.setTitle("Web Service Technologies Project");
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

}
