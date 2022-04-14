import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
/**
 * Creates a game of Minesweeper with a start screen, game board, win screen, and game over screen.
 *
 * @author okew3
 * @version 11.0.9.1
 */
public class MinesweeperView extends Application {
    /**
     * Starts up the game at the start screen to put in your name and pick your difficulty.
     * @param primaryStage the primary window where the start screen appears on
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Minesweeper");
        Button start = new Button("Start");
        Label welcome = new Label("Welcome to Minesweeper!");
        Label selectDifficulty = new Label("Select Your Difficulty");
        Label empty = new Label("");
        Label empty2 = new Label("");
        welcome.setFont(new Font(30));
        ChoiceBox<Difficulty> difficulty = new ChoiceBox<>(FXCollections.observableArrayList(Difficulty.EASY,
                                                           Difficulty.MEDIUM, Difficulty.HARD));
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Your Name...");
        Alert warning = new Alert(AlertType.WARNING, "Enter Your Name or Pick a Difficulty!");
        VBox vbox = new VBox(10);
        vbox.getChildren().add(welcome);
        vbox.getChildren().add(empty);
        vbox.getChildren().add(selectDifficulty);
        vbox.getChildren().add(difficulty);
        vbox.getChildren().add(empty2);
        vbox.getChildren().add(nameField);
        vbox.getChildren().add(start);
        vbox.setAlignment(Pos.CENTER);
        start.setOnAction(e -> {
            if (!(nameField.getCharacters().toString().equals("")) && (difficulty.getValue() == Difficulty.EASY
                    || difficulty.getValue() == Difficulty.MEDIUM
                    || difficulty.getValue() == Difficulty.HARD)) {
                Difficulty diff = difficulty.getValue();
                playMinesweeper(primaryStage, diff, nameField.getCharacters().toString());
            } else {
                warning.showAndWait();
            }
        });
        Scene scene = new Scene(vbox, 500, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * Creates the game board and begins the game and implements the end screen and other game features.
     * @param primaryStage the primary window which the game appears on
     * @param difficulty the difficulty that the user selected on the start screen
     * @param name the name the user put in on the start screen
     */
    public void playMinesweeper(Stage primaryStage, Difficulty difficulty, String name) {
        MinesweeperGame game = new MinesweeperGame(difficulty);
        GridPane board = new GridPane();
        Button newGame = new Button("New Game");
        newGame.setOnAction(new Restart(primaryStage));
        int column = 0;
        while (column < 15) {
            int row = 0;
            while (row < 15) {
                Button tile = new Button("X");
                board.add(tile, row, column);
                row += 1;
                tile.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        int rowInd = GridPane.getRowIndex(tile);
                        int columnInd = GridPane.getColumnIndex(tile);
                        Tile[] tileList = game.check(rowInd, columnInd);
                        if (tileList.length == 1 && tileList[0].isMine()) {
                            Label youLost = new Label("You Lost, " + name);
                            VBox gameOverScreen = new VBox(10);
                            gameOverScreen.getChildren().add(youLost);
                            gameOverScreen.getChildren().add(newGame);
                            gameOverScreen.setAlignment(Pos.CENTER);
                            Scene gameOver = new Scene(gameOverScreen, 200, 200);
                            primaryStage.setScene(gameOver);
                            primaryStage.show();
                        } else if (game.isWon()) {
                            Label youWon = new Label("Congratulations, " + name);
                            VBox gameWonScreen = new VBox(10);
                            gameWonScreen.getChildren().add(youWon);
                            gameWonScreen.getChildren().add(newGame);
                            gameWonScreen.setAlignment(Pos.CENTER);
                            Scene gameWon = new Scene(gameWonScreen, 200, 200);
                            primaryStage.setScene(gameWon);
                            primaryStage.show();
                        } else {
                            for (Tile t : tileList) {
                                int tileX = t.getX();
                                int tileY = t.getY();
                                int bMines = t.getBorderingMines();
                                Button doneButton = new Button(String.valueOf(bMines));
                                if (doneButton.getText().equals("1")) {
                                    Paint oneColor = (Paint) Color.YELLOW;
                                    BackgroundFill oneFill = new BackgroundFill(oneColor,
                                                                                CornerRadii.EMPTY, Insets.EMPTY);
                                    Background one = new Background(oneFill);
                                    doneButton.setBackground(one);
                                } else if (doneButton.getText().equals("2")) {
                                    Paint twoColor = (Paint) Color.ORANGE;
                                    BackgroundFill twoFill = new BackgroundFill(twoColor,
                                                                                CornerRadii.EMPTY, Insets.EMPTY);
                                    Background two = new Background(twoFill);
                                    doneButton.setBackground(two);
                                } else if (doneButton.getText().equals("3")) {
                                    Paint threeColor = (Paint) Color.RED;
                                    BackgroundFill threeFill = new BackgroundFill(threeColor,
                                                                                  CornerRadii.EMPTY, Insets.EMPTY);
                                    Background three = new Background(threeFill);
                                    doneButton.setBackground(three);
                                } else if (doneButton.getText().equals("0")) {
                                    Paint zeroColor = (Paint) Color.GREEN;
                                    BackgroundFill zeroFill = new BackgroundFill(zeroColor,
                                                                                 CornerRadii.EMPTY, Insets.EMPTY);
                                    Background zero = new Background(zeroFill);
                                    doneButton.setBackground(zero);
                                } else {
                                    Paint highColor = (Paint) Color.DARKRED;
                                    BackgroundFill highFill = new BackgroundFill(highColor,
                                                                                 CornerRadii.EMPTY, Insets.EMPTY);
                                    Background high = new Background(highFill);
                                    doneButton.setBackground(high);
                                }
                                board.add(doneButton, tileX, tileY);
                            }
                        }
                    }
                });
            }
            column += 1;
        }
        BorderPane bp = new BorderPane();
        bp.setCenter(board);
        Pane flagPane = new Pane();
        Image flagImg = new Image("/flag.png");
        ImageView flag = new ImageView();
        ImageView stationaryFlag = new ImageView();
        stationaryFlag.setImage(flagImg);
        flag.setImage(flagImg);
        flag.setFitWidth(24);
        flag.setFitHeight(24);
        stationaryFlag.setFitWidth(24);
        stationaryFlag.setFitHeight(24);
        flag.setOnMouseDragged(new Flag(flag, flagPane, flagImg));
        bp.setTop(stationaryFlag);
        flagPane.getChildren().add(bp);
        flagPane.getChildren().add(flag);
        Scene gameBoard = new Scene(flagPane, 348, 407);
        primaryStage.setScene(gameBoard);
        primaryStage.show();
    }

    /**
     * Implements what happens when the user drags a flag from its starting position.
     */
    private class Flag implements EventHandler<MouseEvent> {
        private ImageView flag;
        private Pane flagPane;
        private Image flagImg;
        Flag(ImageView flag, Pane flagPane, Image flagImg) {
            this.flag = flag;
            this.flagPane = flagPane;
            this.flagImg = flagImg;
        }
        public void handle(MouseEvent e) {
            flag.setX(e.getX());
            flag.setY(e.getY());
            ImageView newFlag = new ImageView();
            newFlag.setImage(flagImg);
            newFlag.setFitWidth(24);
            newFlag.setFitHeight(24);
            newFlag.setOnMouseDragged(new Flag(newFlag, flagPane, flagImg));
            flagPane.getChildren().add(newFlag);
        }
    }
    /**
     * Implements the functionality of the New Game button on the game over and win screens.
     */
    private class Restart implements EventHandler<ActionEvent> {
        private Stage primaryStage;
        Restart(Stage primaryStage) {
            this.primaryStage = primaryStage;
        }
        public void handle(ActionEvent e) {
            start(primaryStage);
        }
    }
    /**
     * Used to start the JavaFx process for applications with limited JavaFx support.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}