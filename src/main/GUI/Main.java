package GUI;


import GaussianPrimeSpirals.GaussianPrimeSpiral;
import GaussianPrimeSpirals.GaussianPrimes;
import database.GaussianPrimeDatabase;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.Label;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        VBox VB = new VBox();
        VB.setAlignment(Pos.CENTER);
        HBox HB = new HBox();
        HB.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane();
        Pane pane = new Pane();

        HBox buttonBox = new HBox();
        //buttons
        Label label = new Label("plot range");
        Button Button10 = new Button("10");
        Button Button20 = new Button("20");
        Button Button30 = new Button("30");
        Button Button40 = new Button("40");
        Button Button50 = new Button("50");
        Button Button60 = new Button("60");
        Button Button70 = new Button("70");
        Button Button80 = new Button("80");
        Button Button90 = new Button("90");
        Button Button100 = new Button("100");
        Button Button110 = new Button("110");
        Button Button120 = new Button("120");
        Separator sep1 = new Separator();
        Button capturePathButton = new Button("capture path");
        Separator sep2 = new Separator();
        CheckBox checkBox = new CheckBox("Show gaussian primes");
        buttonBox.setAlignment((Pos.TOP_CENTER));
        buttonBox.getChildren().addAll(Button10,Button20,Button30,Button40,Button50,Button60,Button70,Button80,Button90,Button100,Button110,Button120,sep1,capturePathButton,sep2,checkBox);

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLowerBound(-1000);
        yAxis.setUpperBound(1000);
        xAxis.setLowerBound(-1000);
        xAxis.setUpperBound(1000);


        Scene scene = new Scene(VB, 625, 540);
        VB.getChildren().addAll(buttonBox, HB);
        HB.getChildren().addAll(scrollPane, pane);

        scrollPane.setContent(pane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);


        VBox.setVgrow(HB, Priority.ALWAYS);
        HBox.setHgrow(scrollPane, Priority.ALWAYS);



        final ScatterChart<Number,Number> scatterChart = new ScatterChart<>(xAxis,yAxis);
        scatterChart.setLegendVisible(false);

        GaussianPrimeDatabase gaussianPrimeDatabase = GaussianPrimeDatabase.getInstance();
        gaussianPrimeDatabase.startup();
        gaussianPrimeDatabase.dropTable();
        gaussianPrimeDatabase.createTable();

        GaussianPrimes gaussianPrimes = new GaussianPrimes();
        gaussianPrimes.generatePrimes(240);

        gaussianPrimeDatabase.init();
        ArrayList<String> gaussianPrimesString = gaussianPrimeDatabase.getGaussianPrimes();
        ArrayList<int[]> primes = new ArrayList<>();

        for (int i = 0; i<gaussianPrimesString.size(); i++){
            String a = gaussianPrimesString.get(i);
            String[] b = a.split(",");
            int[] element = new int[b.length];
            for (int x = 0; x<b.length;x++){
                element[x] = Integer.valueOf(b[x]);
            }
            primes.add(element);
        }

        GaussianPrimeSpiral gaussianPrimeSpiral = new GaussianPrimeSpiral();
        List<int[]> spirals = gaussianPrimeSpiral.primeSpirals();

        gaussianPrimeDatabase.shutdown();




        EventHandler eventHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() instanceof CheckBox) {
                    CheckBox chk = (CheckBox) event.getSource();
                    System.out.println("Action performed on checkbox " + chk.getText());

                    if (checkBox.isSelected()) {

                        XYChart.Series series1 = new XYChart.Series();
                        for (int[] p : primes) {
                            if (p[0] <= 120 && p[0] >= -120 && p[1] <= 120 && p[1] >= -120)
                                series1.getData().add(new XYChart.Data(p[0], p[1]));
                        }

                        scatterChart.getData().addAll(series1);

                    }
                    if(!checkBox.isSelected()){
                        scatterChart.getData().clear();
                    }
                }

                }

        };



        checkBox.setOnAction(eventHandler);

        pane.getChildren().addAll(scatterChart);


        scene.getStylesheets().add("GUI/Chart.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("GPS: Primzahlen, Fraktale und Käse");
        primaryStage.show();
    }

/*
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Gaussian Prime Spiral");
        primaryStage.setScene(new Scene(root, 600, 650));
        primaryStage.show();



    }*/


    public static void main(String[] args) {
        launch(args);
    }
}
