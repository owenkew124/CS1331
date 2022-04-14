import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Spinner;
import javafx.geometry.Pos;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Toggle;
import javafx.beans.value.ObservableValue;

public class KewOwenExam5 extends Application {
    public void start(Stage primaryStage) {
        Text shortPhrase = new Text();
        Font georgia = new Font("Georgia", 12);
        shortPhrase.setFont(georgia);
        shortPhrase.setText("the quick brown fox jumps over the lazy dog.");
        Rectangle rec = new Rectangle(100, 50, Color.RED);
        Button changeColor = new Button("Color Me");
        changeColor.setOnAction(e -> {
           if (rec.getFill() == Color.RED) {
               rec.setFill(Color.BLACK);
           } else {
               rec.setFill(Color.RED);
           }

        });
        Spinner spin = new Spinner(0.0, 1.0, 1.0, 0.05);
        ToggleGroup group = new ToggleGroup();
        RadioButton b1 = new RadioButton("Top");
        RadioButton b2 = new RadioButton("Middle");
        RadioButton b3 = new RadioButton("Bottom");
        b1.setToggleGroup(group);
        b2.setToggleGroup(group);
        b3.setToggleGroup(group);
        BorderPane root = new BorderPane();
        StackPane st = new StackPane();
        spin.setOnMouseClicked(e -> {
            double opa = (double) spin.getValue();
            rec.setOpacity(opa);
        });
        VBox top = new VBox();
        VBox bot = new VBox();
        top.getChildren().add(shortPhrase);
        top.getChildren().add(changeColor);
        st.getChildren().add(rec);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue ob, Toggle o, Toggle n) {

                    RadioButton rb = (RadioButton)group.getSelectedToggle();
                    if (rb != null) {
                        if (rb.getText() == "Middle") {
                            StackPane.setAlignment(rec, Pos.CENTER);
                        } else if (rb.getText() == "Bottom") {
                            StackPane.setAlignment(rec, Pos.BOTTOM_CENTER);
                        } else if (rb.getText() == "Top") {
                            StackPane.setAlignment(rec, Pos.TOP_CENTER);
                        }
                }
            }
        });
        root.setCenter(st);
        bot.getChildren().add(b1);
        bot.getChildren().add(b2);
        bot.getChildren().add(b3);
        bot.getChildren().add(spin);
        root.setTop(top);
        root.setBottom(bot);
        Scene scene = new Scene(root, 500, 500);


        primaryStage.setScene(scene);
        primaryStage.setTitle("1331 Exam 5 - Owen Kew");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
