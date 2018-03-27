package GUI;


import GaussianPrimeSpirals.GaussianPrimeSpiral;
import GaussianPrimeSpirals.GaussianPrimes;
import database.GaussianPrimeDatabase;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;

import java.awt.Label;
import java.util.ArrayList;


public class Main extends Application {

    public static int scale = 120;

    @Override
    public void start(Stage primaryStage) throws Exception{


        VBox VB = new VBox();
        VB.setAlignment(Pos.CENTER);
        VBox VB2 = new VBox();
        VB2.setAlignment(Pos.CENTER);
        HBox HB = new HBox();
        HB.setAlignment(Pos.CENTER);

        //ScrollPane scrollPane = new ScrollPane();
        StackPane pane = new StackPane();

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
        HB.getChildren().addAll(VB2);
        VB2.getChildren().addAll(pane);


        //scrollPane.setContent(pane);
        //scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        //scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        //scrollPane.setPannable(true);


        VBox.setVgrow(HB, Priority.ALWAYS);
        //HBox.setHgrow(scrollPane, Priority.ALWAYS);



        final ScatterChart<Number,Number> scatterChart = new ScatterChart<>(xAxis,yAxis);
        scatterChart.setLegendVisible(false);

        GaussianPrimeDatabase gaussianPrimeDatabase = GaussianPrimeDatabase.getInstance();
        gaussianPrimeDatabase.startup();
        gaussianPrimeDatabase.dropTable();
        gaussianPrimeDatabase.createTable();

        GaussianPrimes gaussianPrimes = new GaussianPrimes();
        gaussianPrimes.generatePrimes();

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
        //List<int[]> spirals = gaussianPrimeSpiral.primeSpirals();

        gaussianPrimeDatabase.shutdown();




            checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() instanceof CheckBox) {
                    CheckBox chk = (CheckBox) event.getSource();
                    System.out.println("Action performed on checkbox " + chk.getText());

                    showPrimes(checkBox, primes, scatterChart);
                }
                }
        });

        Button10.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 10;
                showPrimes(checkBox, primes, scatterChart);
            }
        });

        Button20.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 20;
                showPrimes(checkBox, primes, scatterChart);
            }
        });

        Button30.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 30;
                showPrimes(checkBox, primes, scatterChart);
            }
        });

        Button40.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 40;
                showPrimes(checkBox, primes, scatterChart);
            }
        });

        Button50.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 50;
                showPrimes(checkBox, primes, scatterChart);
            }
        });

        Button60.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 60;
                showPrimes(checkBox, primes, scatterChart);
            }
        });

        Button70.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 70;
                showPrimes(checkBox, primes, scatterChart);
            }
        });

        Button80.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 80;
                showPrimes(checkBox, primes, scatterChart);
            }
        });

        Button90.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 90;
                showPrimes(checkBox, primes, scatterChart);
            }
        });

        Button100.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 100;
                showPrimes(checkBox, primes, scatterChart);
            }
        });

        Button110.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 110;
                showPrimes(checkBox, primes, scatterChart);
            }
        });

        Button120.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 120;
                showPrimes(checkBox, primes, scatterChart);
            }
        });


//scatterChart.getData().

        scatterChart.setPrefSize(700,700);
        pane.getChildren().addAll(scatterChart);
        pane.setAlignment(scatterChart, Pos.CENTER);

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


    private void showPrimes(CheckBox checkBox, ArrayList<int[]> primes, ScatterChart<Number, Number> scatterChart) {
        if(!checkBox.isSelected()){
            scatterChart.getData().clear();
        }

        else if (checkBox.isSelected()) {
            scatterChart.getData().clear();

            XYChart.Series series1 = new XYChart.Series();
            for (int[] p : primes) {
                if (p[0] <= scale && p[0] >= -scale && p[1] <= scale && p[1] >= -scale)
                    series1.getData().add(new XYChart.Data(p[0], p[1]));
            }
            scatterChart.getData().addAll(series1);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
