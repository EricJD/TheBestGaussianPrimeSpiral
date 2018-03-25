package GUI;


import GaussianPrimeSpirals.GaussianPrimes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{


        VBox VB = new VBox();
        HBox HB = new HBox();

        ScrollPane scrollPane = new ScrollPane();
        Pane pane = new Pane();

        HBox buttonBox = new HBox();
        //buttons
        Button testButton1 = new Button("KÄSE");
        Button testButton2 = new Button("KÄSE");
        Button testButton3 = new Button("KÄSE");
        buttonBox.setAlignment((Pos.TOP_CENTER));
        buttonBox.getChildren().addAll(testButton1,testButton2,testButton3);

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLowerBound(-1000);
        yAxis.setUpperBound(1000);
        xAxis.setLowerBound(-1000);
        xAxis.setUpperBound(1000);


        Scene scene = new Scene(VB, 500, 500);
        VB.getChildren().addAll(buttonBox, HB);
        HB.getChildren().addAll(scrollPane, pane);

        scrollPane.setContent(pane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);


        VBox.setVgrow(HB, Priority.ALWAYS);
        HBox.setHgrow(scrollPane, Priority.ALWAYS);



        final ScatterChart<Number,Number> scatterChart =
                new ScatterChart<>(xAxis,yAxis);

        GaussianPrimes primeGen = new GaussianPrimes();
        List<int[]> primes = primeGen.generatePrimes();

        XYChart.Series series1 = new XYChart.Series();
        for (int[] p : primes){
            series1.getData().add(new XYChart.Data(p[0],p[1]));
        }

        scatterChart.getData().addAll(series1);


        pane.getChildren().addAll(scatterChart);



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
