package GUI;

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

public class Main extends Application {

    final ScrollPane scrollPane = new ScrollPane();
    final Pane pane = new Pane();

    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    final Image[] images = new Image[5];
    final ImageView[] pics = new ImageView[5];

    final VBox vb = new VBox();
    final HBox hb = new HBox();


    final Label fileName = new Label();

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox VB = new VBox();
        HBox HB = new HBox();
        yAxis.setLowerBound(-1000);
        yAxis.setUpperBound(1000);
        xAxis.setLowerBound(-1000);
        xAxis.setUpperBound(1000);


        Scene scene = new Scene(VB, 500, 500);

        primaryStage.setScene(scene);
        primaryStage.setTitle("GPS: Primzahlen, Fraktale und Käse");
        VB.getChildren().addAll(HB);
        HB.getChildren().addAll(scrollPane, pane);

        VBox.setVgrow(HB, Priority.ALWAYS);
        HBox.setHgrow(scrollPane, Priority.ALWAYS);



        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);


        //fileName.setLayoutX(30);
        //fileName.setLayoutY(160);

        for (int i = 0; i < 5; i++) {
            //images[i] = new Image(getClass().getResourceAsStream(imageNames[i]));
            pics[i] = new ImageView(images[i]);
            pics[i].setFitWidth(100);
            pics[i].setPreserveRatio(true);
            vb.getChildren().add(pics[i]);
        }

        //scrollPane.setVmax(440);
        //scrollPane.setPrefSize(115, 150);
        //scrollPane.setContent(vb);

        pane .getChildren().addAll(lineChart);
        //pane .getChildren().addAll(new Axis<>() {
                           //        }

        scrollPane.setContent(pane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPannable(true);

        primaryStage.show();
    }

        /*
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Gaussian Prime Spiral");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }*/


    public static void main(String[] args) {
        launch(args);
    }
}
