package snakegame2;
import model.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The user interface, UI, for the snake game. Do not change this code.
 *
 * @author Anders Lindström <anderslm@kth.se>
 */
public class SnakeGame2 extends Application {

    private World world;

    private Canvas canvas; // the surface to draw the game upon
    private double widthPixels, heightPixels, tileXPixels, tileYPixels;
    private AnimationTimer timer;
    private final long FRAME_NS = 500_000_000; // 2 frames/second

    private final Color snakeFillColor = Color.YELLOW,
            snakeStrokeColor = Color.BLACK,
            appleFillColor = Color.LIGHTGREEN,
            powerAppleFillColor = Color.DARKGREEN,
            appleStrokeColor = Color.RED,
            obstacleStrokeColor = Color.BLACK,
            obstacleFillColor = Color.BROWN;

    private FlowPane textPane;
    private Text info;

    protected class SnakeTimer extends AnimationTimer {

        private long previousNs = 0;

        /**
         * This method deals with drawing the world. You do not have to change
         * this code, except for handling "game over" (see comment below).
         *
         * @param nowNs
         */
        @Override
        public void handle(long nowNs) {
            // initialization, previous is 0 at first call...
            if (previousNs == 0) {
                previousNs = nowNs;
            }

            if (nowNs - previousNs < FRAME_NS) {
                // do not move yet...
                return;
            } else {
                // save the timestamp for this new cycle
                previousNs = nowNs;
            }

            // move the objects in the world
            world.move();

            // draw the world...
            GraphicsContext gc = canvas.getGraphicsContext2D();

            // paint the background
            gc.setFill(Color.WHITESMOKE);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            // draw the objects
            drawApples(gc);
            drawObstacles(gc);
            drawSnake(gc);
            drawBorder(gc);
            updateInfo();

            // game over?
            if (world.getState() == State.GAME_OVER) {
                showAlert("Game over!");
                stop();
            }
        }
    }

    private void drawSnake(GraphicsContext gc) {
        double arcLengthX = tileXPixels / 4.0;
        double arcLengthY = tileYPixels / 4.0;
        gc.setStroke(snakeStrokeColor);
        for (int i = 0; i < world.getSnakeTrail().size(); i++) {
            Point p = world.getSnakeTrail().get(i);
            double xPos = p.getX() * tileXPixels;
            double yPos = p.getY() * tileYPixels;
            if (i == 0) {
                gc.setFill(Color.ORANGERED); // head
            } else {
                gc.setFill(snakeFillColor);
            }
            gc.fillRoundRect(
                    xPos, yPos,
                    tileXPixels, tileYPixels,
                    arcLengthX, arcLengthY);
            gc.strokeRoundRect(
                    xPos, yPos,
                    tileXPixels, tileYPixels,
                    arcLengthX, arcLengthY);
        }
    }

    private void drawApples(GraphicsContext gc) {
        gc.setFill(appleFillColor);
        gc.setStroke(appleStrokeColor);
        for (Apple a : world.getApples()) {
            double xPos = a.getPosition().getX() * tileXPixels;
            double yPos = a.getPosition().getY() * tileYPixels;
            if (a.isPowered()) {
                gc.setFill(powerAppleFillColor);
            } else {
                gc.setFill(appleFillColor);
            }
            gc.fillOval(xPos, yPos, tileXPixels, tileYPixels);
            gc.strokeOval(xPos, yPos, tileXPixels, tileYPixels);
        }
    }

    private void drawObstacles(GraphicsContext gc) {
    
        double arcLengthX = tileXPixels / 4.0;
        double arcLengthY = tileYPixels / 4.0;
        gc.setFill(obstacleFillColor);
        gc.setStroke(obstacleStrokeColor);
        for (Obstacle o : world.getObstacles()) {
            for (Point p : o.getPoints()) {
                double xPos = p.getX() * tileXPixels;
                double yPos = p.getY() * tileYPixels;
                gc.fillRoundRect(
                        xPos, yPos,
                        tileXPixels, tileYPixels,
                        arcLengthX, arcLengthY);
                gc.strokeRoundRect(
                        xPos, yPos,
                        tileXPixels, tileYPixels,
                        arcLengthX, arcLengthY);
            }
        }
        
    }

    private void drawBorder(GraphicsContext gc) {
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void updateInfo() {
        info.setText("Score : " + this.world.getScore()
                + ", status: " + world.getState());
    }

    // This code initializes the graphics. 
    // DO NOT CHANGE THIS CODE.
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, Color.WHITESMOKE);

        canvas = new Canvas(300, 300);
        root.setCenter(canvas);

        info = new Text("Initializing...");
        Font font = Font.font("Monospaced", FontWeight.NORMAL, 16);
        info.setFont(font);
        info.setFontSmoothingType(FontSmoothingType.LCD);
        textPane = new FlowPane();
        textPane.getChildren().add(info);
        root.setBottom(info);

        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();

        // init the model
        final int tilesX = 16;
        final int tilesY = (int) (canvas.getHeight()
                * tilesX / canvas.getWidth());
        world = new World(tilesX, tilesY);
        // calculate metrics
        widthPixels = (int) canvas.getWidth();
        heightPixels = (int) canvas.getHeight();
        tileXPixels = widthPixels / world.getBoardWidth();
        tileYPixels = heightPixels / world.getBoardHeight();

        //world.initNewGame();
        world.setState(State.RUNNING);

        timer = new SnakeTimer();
        timer.start();

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                // calculate mouse position relative to snakes head
                Point head = world.getSnakeTrail().get(0);
                double headX = (head.getX() + 0.5) * tileXPixels;
                double headY = (head.getY() + 0.5) * tileYPixels;
                double mouseX = me.getX();
                double mouseY = me.getY();
                // check quadrants
                if (world.getDirection() == Direction.SOUTH
                        || world.getDirection() == Direction.NORTH) {
                    if (mouseX < headX) {
                        world.setDirection(Direction.WEST);
                    } else {
                        world.setDirection(Direction.EAST);
                    }
                } else // i.e. WEST or EAST
                 if (mouseY < headY) {
                        world.setDirection(Direction.NORTH);
                    } else {
                        world.setDirection(Direction.SOUTH);
                    }
            }
        }
        );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void showAlert(String message) {
        if (!alert.isShowing()) {
            alert.setHeaderText("");
            alert.setTitle("Alert!");
            alert.setContentText(message);
            alert.show();
        }
    }

    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
}
