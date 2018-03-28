package GUI;


import GaussianPrimeSpirals.GaussianPrimeSpiral;
import GaussianPrimeSpirals.GaussianPrimes;
import database.GaussianPrimeDatabase;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    public static int scale = 120;
    public static boolean path = false;

    @Override
    public void start(Stage primaryStage) throws Exception{


        VBox VB = new VBox();
        VB.setAlignment(Pos.CENTER);
        VBox VB2 = new VBox();
        VB2.setAlignment(Pos.CENTER);
        HBox HB = new HBox();
        HB.setAlignment(Pos.CENTER);

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
        Button capturePathButton = new Button("capture path");
        CheckBox checkBox = new CheckBox("Show gaussian primes");
        buttonBox.setAlignment((Pos.TOP_CENTER));
        buttonBox.getChildren().addAll(label,Button10,Button20,Button30,Button40,Button50,Button60,Button70,Button80,Button90,Button100,Button110,Button120,capturePathButton,checkBox);
        HBox.setMargin(label,new Insets(6,5,1,1));
        HBox.setMargin(Button10,new Insets(3,0,1,0));
        HBox.setMargin(Button20,new Insets(3,0,1,0));
        HBox.setMargin(Button30,new Insets(3,0,1,0));
        HBox.setMargin(Button40,new Insets(3,0,1,0));
        HBox.setMargin(Button50,new Insets(3,0,1,0));
        HBox.setMargin(Button60,new Insets(3,0,1,0));
        HBox.setMargin(Button70,new Insets(3,0,1,0));
        HBox.setMargin(Button80,new Insets(3,0,1,0));
        HBox.setMargin(Button90,new Insets(3,0,1,0));
        HBox.setMargin(Button100,new Insets(3,0,1,0));
        HBox.setMargin(Button110,new Insets(3,0,1,0));
        HBox.setMargin(Button120,new Insets(3,0,1,0));
        HBox.setMargin(capturePathButton, new Insets(3,10,1,10));
        HBox.setMargin(checkBox, new Insets(6,0,1,0));

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLowerBound(-100);
        yAxis.setUpperBound(100);
        xAxis.setLowerBound(-100);
        xAxis.setUpperBound(100);

        HBox cycle = new HBox();
        cycle.setAlignment(Pos.CENTER);


        Scene scene = new Scene(VB, 700, 650);
        VB.getChildren().addAll(buttonBox, cycle,HB);
        HB.getChildren().addAll(VB2);
        VB2.getChildren().addAll(pane);

        VBox.setVgrow(HB, Priority.ALWAYS);

        final LineChart<Number,Number> scatterChart = new LineChart<>(xAxis,yAxis);
        scatterChart.setLegendVisible(true);

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
        List<int[]> spiral = gaussianPrimeSpiral.getPrimes();

        gaussianPrimeDatabase.shutdown();

        Label textLabel = new Label();
        String start1;
        String start2;
        if (spiral.get(0)[0]<0) {
            start1 = String.valueOf(spiral.get(0)[0]);
        }else start1 = "+"+String.valueOf(spiral.get(0)[0]);
        if (spiral.get(0)[1]<0) {
            start2 = String.valueOf(spiral.get(0)[1]);
        }else start2 = "+"+String.valueOf(spiral.get(0)[1]);
        textLabel.setText("initial point is "+start1+" "+start2+"i; cycle length is "+spiral.size());
        cycle.getChildren().add(textLabel);
        HBox.setMargin(textLabel,new Insets(5,0,0,0));

            checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() instanceof CheckBox) {
                    CheckBox chk = (CheckBox) event.getSource();
                    System.out.println("Action performed on checkbox " + chk.getText());

                    showPrimesAndPath(spiral, checkBox, primes, scatterChart);

                    if(!checkBox.isSelected()){
                        scatterChart.getData().clear();
                    }
                }
                }
        });

        capturePathButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
                if(path){
                    path=true;
                }
                else if(!path){
                    path=false;
                }

            }
        });


        Button10.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 10;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });

        Button20.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 20;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });

        Button30.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 30;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });

        Button40.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 40;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });

        Button50.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 50;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });

        Button60.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 60;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });

        Button70.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 70;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });

        Button80.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 80;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });

        Button90.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 90;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });

        Button100.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 100;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });

        Button110.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 110;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });

        Button120.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                scale = 120;
                showPrimesAndPath(spiral, checkBox, primes, scatterChart);
            }
        });


        scatterChart.setPrefSize(675,675);
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


    private void showPrimesAndPath(List<int[]> spiral, CheckBox checkBox, ArrayList<int[]> primes, LineChart<Number, Number> scatterChart) {
        scatterChart.getData().clear();

        XYChart.Series series1 = new XYChart.Series();
        for (int[] p : primes) {
            if (p[0] <= scale && p[0] >= -scale && p[1] <= scale && p[1] >= -scale)
                series1.getData().add(new XYChart.Data(p[0], p[1]));
        }

        XYChart.Series series2 = new XYChart.Series();
        for (int[] s : spiral){
            if (s[0] <= scale && s[0] >= -scale && s[1] <= scale && s[1] >= -scale)
                series2.getData().add(new XYChart.Data(s[0], s[1]));
        }

        if(!checkBox.isSelected()){
            scatterChart.getData().removeAll(series1);
            capturePath(series2, scatterChart);
        }

        else if (checkBox.isSelected()) {
            scatterChart.getData().addAll(series1);
            capturePath(series2, scatterChart);
        }

    }

    private void capturePath(XYChart.Series series2, LineChart<Number, Number> scatterChart) {

        if(!path){
            scatterChart.getData().addAll(series2);
        }

        else  if(path){
            scatterChart.getData().removeAll(series2);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
