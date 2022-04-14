import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a Brochure for OOPTopia.
 * @author okew3
 * @version 11.0.9.1
 */
public class Brochure extends Application {
    /**
     * Creates a Brochure for OOPTopia with various JavaFx elements.
     * @param primaryStage the stage for the brochure to be put on
     */
    public void start(Stage primaryStage) {
        Rectangle banner = new Rectangle(175, 0, 150, 50);
        banner.setFill(Color.BLUE);
        Text bannerText = new Text(177, 30, "WELCOME TO OOP-TOPIA!");
        bannerText.setFill(Color.WHITE);
        Image image = new Image("/l.jpg");
        Text imageText = new Text(25, 350, "YOU CAN WATCH THE BEAUTIFUL SUNSET OVER "
                                            + "THE BREATHTAKING MOUNTAINS!");
        Circle circle1 = new Circle(400, 250, 50);
        Circle circle2 = new Circle(100, 250, 50);
        circle1.setFill(Color.CHARTREUSE);
        circle2.setFill(Color.GOLDENROD);

        ImageView iv = new ImageView();
        iv.setImage(image);
        iv.setPreserveRatio(true);
        iv.setFitWidth(150);
        iv.setX(175);
        iv.setY(200);

        Pane pane = new Pane();
        pane.getChildren().add(banner);
        pane.getChildren().add(bannerText);
        pane.getChildren().add(iv);
        pane.getChildren().add(imageText);
        pane.getChildren().add(circle1);
        pane.getChildren().add(circle2);

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("TravelBrochure");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Used to start the JavaFx process for applications with limited JavaFx support.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
