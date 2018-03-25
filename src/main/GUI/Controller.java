package GUI;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class Controller {



    @FXML
    NumberAxis xAxis = new NumberAxis();
    @FXML
    CategoryAxis yAxis = new CategoryAxis();

    @FXML
    ScatterChart<Number,Number> sc;

    public void initialize(){
        xAxis.setLabel("xAxis");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("yAxis");

        //bc = new ScatterChart<Number,String>(xAxis,yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data(80, 50));


        sc.getData().add(series1);
    }
}


